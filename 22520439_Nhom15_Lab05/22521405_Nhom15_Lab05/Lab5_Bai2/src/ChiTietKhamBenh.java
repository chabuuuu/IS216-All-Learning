import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ChiTietKhamBenh extends JFrame {
    private JComboBox bacSiComboBox = new JComboBox();
    private JComboBox<String> tenBenhNhanField;
    private JTextField yeuCauKhamField;
    private JTextArea ketLuanArea;
    private JList<String> dichVuList;
    private JList<String> dichVuBacSiList;
    private DefaultListModel<String> dichVuModel = new DefaultListModel<>();
    private DefaultListModel<String> dichVuBacSiModel;
    private String madv;
    private String thanhtien;
    JDateChooser dateChooser;
    private String mabn;
    private void loadDoctors() {
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT TenBS FROM BACSI";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TenBS"));
                bacSiComboBox.addItem(resultSet.getString("TenBS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDichVu() {
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT TenDV FROM DICHVU";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TenDV"));
                dichVuModel.addElement(resultSet.getString("TenDV"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChiTietKhamBenh() {
        super("Chi tiết khám bệnh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        loadDoctors();
        loadDichVu();
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 0, 0));

        // Tạo các thành phần trên giao diện
        tenBenhNhanField = new JComboBox<>(new String[]{});
        JComboBox<String> ngayKhamComboBox = new JComboBox<>(new String[]{"Ngày khám"});
        dateChooser = new JDateChooser();

        yeuCauKhamField = new JTextField();

        ketLuanArea = new JTextArea();
        ketLuanArea.setLineWrap(true);

        /*dichVuModel = new DefaultListModel<>();
        dichVuModel.addElement("Dịch vụ a");
        dichVuModel.addElement("Dịch vụ b");
        dichVuModel.addElement("Dịch vụ c");
        dichVuModel.addElement("Dịch vụ d");*/
        dichVuList = new JList<>(dichVuModel);

        dichVuBacSiModel = new DefaultListModel<>();
        dichVuBacSiModel.addElement("Dịch vụ a: 2");
        dichVuBacSiModel.addElement("Dịch vụ b: 1");
        dichVuBacSiList = new JList<>(dichVuBacSiModel);
        JLabel bskham = new JLabel("Bác sĩ khám");
        JLabel ngaykham = new JLabel("Ngày khám");
        JLabel tenbenhnhan = new JLabel("Tên bệnh nhân");
        JLabel yeucaukham = new JLabel("Yêu cầu khám");
        JLabel dsDichVu = new JLabel("Danh sách dịch vụ");
        JLabel dsDichVuBacSi = new JLabel("Danh sách dịch vụ của bác sĩ");

        JButton themButton = new JButton("Thêm");
        JButton checkButton = new JButton("Kiểm tra");
        JButton troveButton = new JButton("Trở về");
        // Thêm các thành phần vào panel
        topPanel.add(bskham);
        topPanel.add(bacSiComboBox);
        topPanel.add(ngaykham);
//        topPanel.add(ngayKhamComboBox);
        topPanel.add(dateChooser);
        topPanel.add(tenbenhnhan);
        topPanel.add(tenBenhNhanField);
        topPanel.add(yeucaukham);
        topPanel.add(yeuCauKhamField);
        topPanel.add(new JLabel("Kết luận"));
        topPanel.add(ketLuanArea);
//        topPanel.add(ketLuanArea);

        bottomPanel.add(dsDichVu);
        bottomPanel.add(new JScrollPane(dichVuList));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Tạo một JPanel mới để chứa nút
        buttonPanel.add(themButton); // Thêm nút vào buttonPanel
        buttonPanel.add(checkButton);
        buttonPanel.add(troveButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        checkButton.addActionListener(this::checkButtonActionPerformed);
        tenBenhNhanField.addActionListener(this::tenBenhNhanFieldActionPerformed);
        themButton.addActionListener(this::themButtonActionPerformed);
        dichVuList.addListSelectionListener(this::listDichvulistener);
        troveButton.addActionListener(this::troVeButtonActionPerformed);


        add(mainPanel);
    }

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String bacsi = bacSiComboBox.getSelectedItem().toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = outputFormat.format(dateChooser.getDate());

        /*String sql = "select * from khambenh\n" +
                " where mabs = (select mabs from bacsi where tenbs = '" + bacsi + "')\n" +
                " and ngaykham = '" + date + "';";*/
        String sql = "select tenbn from BENHNHAN\n" +
                " where mabn = (select mabn from KHAMBENH\n" +
                " where mabs = (select mabs from BACSI where tenbs = '" + bacsi + "')\n" +
                " and ngaykham = '" + date + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            if (resultSet.next()) {
                tenBenhNhanField.addItem(resultSet.getString("tenbn"));

            } else {
                JOptionPane.showMessageDialog(this, "Bác sĩ chưa có lịch khám vào ngày này!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listDichvulistener(ListSelectionEvent evt) {
        String selected = dichVuList.getSelectedValue();
        if (selected != null) {
            String sql = "select * from DICHVU where tendv = '" + selected + "';";
            try {
                JDBCConnect jdbcConnect = new JDBCConnect();
                ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
                if (resultSet.next()) {
                    madv = resultSet.getString("madv");
                    thanhtien = resultSet.getString("dongia");

                } else {
                    JOptionPane.showMessageDialog(this, "Dịch vụ không tồn tại!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void tenBenhNhanFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String bacsi = bacSiComboBox.getSelectedItem().toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = outputFormat.format(dateChooser.getDate());
        String sql = "select yeucaukham,mabn from KHAMBENH\n" +
                " where mabs = (select mabs from BACSI where tenbs = '" + bacsi + "')\n" +
                " and ngaykham = '" + date + "';";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            if (resultSet.next()) {
                yeuCauKhamField.setText(resultSet.getString("yeucaukham"));
                mabn = resultSet.getString("mabn");
            } else {
                JOptionPane.showMessageDialog(this, "Bệnh nhân chưa có lịch khám vào ngày này!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String bacsi = bacSiComboBox.getSelectedItem().toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = outputFormat.format(dateChooser.getDate());
        String tenbn = tenBenhNhanField.getSelectedItem().toString();
        String yeucau = yeuCauKhamField.getText();
        String ketluan = ketLuanArea.getText();

        String sql = "SELECT makb FROM KHAMBENH WHERE mabs = (SELECT mabs FROM BACSI WHERE tenbs = '" + bacsi + "') AND ngaykham = '" + date + "';";

        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            if (resultSet.next()) {
                String makb = resultSet.getString("makb");
                sql = "INSERT INTO THUPHI  VALUES ('" + makb + "', '" + madv + "', '1', '" + thanhtien + "');";
                System.out.println(sql);
                jdbcConnect.executeCommandInsert(sql);

                sql = "UPDATE KHAMBENH k\n" +
                        "JOIN (\n" +
                        "    SELECT makb \n" +
                        "    FROM KHAMBENH \n" +
                        "    WHERE mabn = "+mabn+" AND ngaykham = '"+date+"'\n" +
                        ") tmp ON k.makb = tmp.makb\n" +
                        "SET ketluan = '"+ketluan+"';\n";
                jdbcConnect.executeCommandInsert(sql);

                JOptionPane.showMessageDialog(this, "Thêm chi tiết khám bệnh thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã khám bệnh.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm chi tiết khám bệnh thất bại!");
            e.printStackTrace();
        }
    }
    public void troVeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }
}
