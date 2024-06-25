import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ThemDDLVaoTour extends JFrame {
    JTextField MaTourField = new JTextField(15),
            tenTourField = new JTextField(15),
            ngayKhoiHanhField = new JTextField(15),
            soNgayField = new JTextField(15),
             soDemField = new JTextField(15),
             giaField = new JTextField(15);

    JComboBox TenTTPField = new JComboBox();
    //Tên các cột của bảng danh sách đội
    String[] columnNamesDSDDL = {"Tên điểm du lịch", "Dặc trưng"};
    String[] columnNamesDSDDLDuocChon = {"Tên điểm du lịch"};

    //Tạo bảng 1
    DefaultTableModel modelTableDSDDL = new DefaultTableModel(new Object[][]{}, columnNamesDSDDL);
    JTable tableDSDLL = new JTable(modelTableDSDDL);
    JScrollPane scrollDSDDL = new JScrollPane(tableDSDLL);

    //Tạo bảng 2
    DefaultTableModel modelTableDSDDLDuocChon = new DefaultTableModel(new Object[][]{}, columnNamesDSDDLDuocChon);
    JTable tableDSDDLDuocChon = new JTable(modelTableDSDDLDuocChon);
    JScrollPane scrollDSDDLDuocChon = new JScrollPane(tableDSDDLDuocChon);

    //Khởi tạo các nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemDDLVaoTour(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Chi tiết tour");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel matourLB = new JLabel("Mã tour");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(matourLB, constraints);

        JLabel ngayKHLabel = new JLabel("Ngày khởi hành");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ngayKHLabel, constraints);

        JLabel soNgayLabel = new JLabel("Số ngày");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(soNgayLabel, constraints);

        JLabel soDemLabel = new JLabel("Số đêm");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(soDemLabel, constraints);

        JLabel giaTourLabel = new JLabel("Giá tour");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(giaTourLabel, constraints);

        JLabel tenTourLB = new JLabel("Tên tour");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(tenTourLB, constraints);

        JLabel ttpLB = new JLabel("Tên tỉnh/thành phố");
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(ttpLB, constraints);

        JLabel ddlLB = new JLabel("Điểm du lịch");
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(ddlLB, constraints);

        JLabel ddlChonLB = new JLabel("Điểm du lịch được chọn");
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(ddlChonLB, constraints);



        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(MaTourField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(ngayKhoiHanhField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(soNgayField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(soDemField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(giaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(tenTourField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(TenTTPField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(scrollDSDDL, constraints);


        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(scrollDSDDLDuocChon, constraints);



        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadTTP();


        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi

        // Thêm Lắng nghe sự kiện khi enter được nhấn
        MaTourField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy văn bản từ JTextField khi Enter được nhấn
                String text = MaTourField.getText();
                MaTourFieldEnterActionPerformed(text);
            }
        });

        TenTTPField.addActionListener((this::tenTTPFieldActionPerformed));

        //Thêm sự kiện click vào chọn sản phẩm bảng danh sách sản phẩm thì sẽ thêm vào bảng danh sách sản phẩm nhập
        tableDSDLL.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableDSDLL.getSelectedRow();
                    if (selectedRow != -1) {
                        Object[] rowData = new Object[tableDSDLL.getColumnCount()];
                        rowData[0] = tableDSDLL.getValueAt(selectedRow, 0);
                        modelTableDSDDLDuocChon.addRow(rowData);
                    }
                }
            }
        });


        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //-----PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
    }

    private void tenTTPFieldActionPerformed(ActionEvent actionEvent) {
        String tenTPP = TenTTPField.getSelectedItem().toString();
        String maTTP = getMaTTP(tenTPP);
        loadTableDSDDL(maTTP);
    }

    private void loadTTP(){
        String sql = "SELECT TENTTP FROM TINHTP;";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            while (resultSet.next()){
                TenTTPField.addItem(resultSet.getString("TENTTP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void MaTourFieldEnterActionPerformed(String maTour) {
        String sql = "SELECT * FROM TOUR WHERE MATOUR = '" + maTour + "'";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            while (resultSet.next()){
                ngayKhoiHanhField.setText(resultSet.getString("NGAYKHOIHANH"));
                ngayKhoiHanhField.setEditable(false);

                soNgayField.setText(resultSet.getString("SONGAY"));
                soNgayField.setEditable(false);

                soDemField.setText(resultSet.getString("SODEM"));
                soDemField.setEditable(false);

                giaField.setText(resultSet.getString("GIA"));
                giaField.setEditable(false);

                tenTourField.setText(resultSet.getString("TENTOUR"));
                tenTourField.setEditable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private String getMaDDL(String tenDDL) {
        String sql = "SELECT MADDL FROM DIEMDL WHERE TENDDL = '" + tenDDL + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getString("MADDL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getMaTTP(String tenTTP) {
        String sql = "SELECT MATTP FROM TINHTP WHERE TENTTP = '" + tenTTP + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getString("MATTP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void loadTableDSDDL(String maTTP) {
        modelTableDSDDL.setRowCount(0);
        String sql = "SELECT * FROM DIEMDL WHERE MATTP = ' " + maTTP + "';";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            while (resultSet.next()) {
                modelTableDSDDL.addRow(new Object[]{resultSet.getString("TENDDL"), resultSet.getString("DACTRUNG")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String maTour = MaTourField.getText();

        //Thêm chi tiết
        //Lấy ra các giá trị trong bảng
        int rowCount = modelTableDSDDLDuocChon.getRowCount();
        int colCount = modelTableDSDDLDuocChon.getColumnCount();
        for (int i = 0; i< rowCount; i++){
            String tenDDL = modelTableDSDDLDuocChon.getValueAt(i, 0).toString();
            String maDDL = getMaDDL(tenDDL);
            themCHITIET(maDDL, maTour);
        }
        JOptionPane.showMessageDialog(null, "Thêm chi tiết du lịch thành công!");

    }

    private void themCHITIET(String maDDL, String maTour) {
        String sql = "INSERT INTO CHITIET (MATOUR, MADDL) VALUES ('" + maTour + "'" + ", '" + maDDL + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết du lịch thất bại!");
            e.printStackTrace();
        }
    }

}
