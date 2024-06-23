import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ThanhToan extends JFrame {
    private JTextField maBenhNhanIdField;
    private JTextField tenBenhNhanField;
    private JTextField yeuCauKhamField;
    private JTextArea ketLuanArea;
    private JList<String> dichVuList;
    private JList<String> dichVuBacSiList;
    private DefaultListModel<String> dichVuModel;
    private DefaultListModel<String> dichVuBacSiModel;
    private JTextField tongtienTextField;
    private JCheckBox dathanhtoan;
    private JDateChooser dateChooser;
    private Boolean isPaid = false;

    public ThanhToan() {
        super("Thanh Toán");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 0, 0));

        // Tạo các thành phần trên giao diện
        maBenhNhanIdField = new JTextField();
        tenBenhNhanField = new JTextField();
        JComboBox<String> ngayKhamComboBox = new JComboBox<>(new String[]{"Ngày khám"});
        dateChooser = new JDateChooser();

        yeuCauKhamField = new JTextField();

        ketLuanArea = new JTextArea();
        ketLuanArea.setLineWrap(true);
        tongtienTextField = new JTextField();

        dathanhtoan = new JCheckBox("Đã thanh toán");

        dichVuModel = new DefaultListModel<>();
        dichVuModel.addElement("Dịch vụ a");
        dichVuModel.addElement("Dịch vụ b");
        dichVuModel.addElement("Dịch vụ c");
        dichVuModel.addElement("Dịch vụ d");
        dichVuList = new JList<>(dichVuModel);

        dichVuBacSiModel = new DefaultListModel<>();
        dichVuBacSiModel.addElement("Dịch vụ a: 2");
        dichVuBacSiModel.addElement("Dịch vụ b: 1");
        dichVuBacSiList = new JList<>(dichVuBacSiModel);
        JLabel mabn = new JLabel("Mã bệnh nhân");
        JLabel ngaykham = new JLabel("Ngày khám");
        JLabel tenbenhnhan = new JLabel("Tên bệnh nhân");
        JLabel yeucaukham = new JLabel("Yêu cầu khám");
        JLabel dsDichVu = new JLabel("Danh sách dịch vụ");
        JLabel dsDichVuBacSi = new JLabel("Danh sách dịch vụ của bác sĩ");
        JLabel tongtien = new JLabel("Tổng tiền");
        JButton themButton = new JButton("Thanh Toán");
        JButton checkButton = new JButton("Kiểm tra");
        JButton troveButton = new JButton("Trở về");

        // Thêm các thành phần vào panel
        topPanel.add(mabn);
        topPanel.add(maBenhNhanIdField);
        topPanel.add(ngaykham);
        topPanel.add(dateChooser);
        topPanel.add(tenbenhnhan);
        topPanel.add(tenBenhNhanField);
        topPanel.add(yeucaukham);
        topPanel.add(yeuCauKhamField);
        topPanel.add(new JLabel("Kết luận"));
        topPanel.add(ketLuanArea);
        topPanel.add(tongtien);
        topPanel.add(tongtienTextField);
        topPanel.add(dathanhtoan);
//        topPanel.add(ketLuanArea);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Tạo một JPanel mới để chứa nút
        buttonPanel.add(themButton); // Thêm nút vào buttonPanel
        buttonPanel.add(checkButton);
        buttonPanel.add(troveButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        themButton.addActionListener(this::themButtonActionPerformed);
        checkButton.addActionListener(this::checkButtonActionPerformed);
        troveButton.addActionListener(this::troveButtonActionPerformed);
        dathanhtoan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Enable/disable the "Thanh Toán" button based on checkbox selection
                themButton.setEnabled(!dathanhtoan.isSelected());
            }
        });
        add(mainPanel);
    }

    public void themButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String mabn = maBenhNhanIdField.getText();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaykham = outputFormat.format(dateChooser.getDate());
        String sql = "UPDATE KHAMBENH k\n" +
                " JOIN (\n" +
                "    SELECT makb \n" +
                "    FROM KHAMBENH \n" +
                "    WHERE mabn = '" + mabn + "' AND ngaykham = '" + ngaykham + "'\n" +
                " ) tmp ON k.makb = tmp.makb\n" +
                " SET thanhtoan = 1;";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            isPaid = true;
            dathanhtoan.setSelected(true);
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String mabn = maBenhNhanIdField.getText();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaykham = outputFormat.format(dateChooser.getDate());
        System.out.println(dathanhtoan.isSelected());
        String sql = "select * from KHAMBENH \n" +
                " inner join THUPHI\n" +
                " on THUPHI.makb = KHAMBENH.makb\n" +
                " where mabn = '" + mabn + "'\n" +
                " and ngaykham ='" + ngaykham + "';";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            if (resultSet.next()) {
                yeuCauKhamField.setText(resultSet.getString("yeucaukham"));
                ketLuanArea.setText(resultSet.getString("ketluan"));
                tongtienTextField.setText(resultSet.getString("thanhtien"));
                dathanhtoan.setSelected(resultSet.getBoolean("thanhtoan"));
                tenBenhNhanField.setEnabled(false);
                yeuCauKhamField.setEnabled(false);
                ketLuanArea.setEnabled(false);
                tongtienTextField.setEnabled(false);
                sql = "select tenbn from BENHNHAN where mabn = '" + mabn + "';";
                resultSet = jdbcConnect.executeCommandGet(sql);
                if (resultSet.next()) {
                    tenBenhNhanField.setText(resultSet.getString("tenbn"));
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void troveButtonActionPerformed(java.awt.event.ActionEvent event) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }


}
