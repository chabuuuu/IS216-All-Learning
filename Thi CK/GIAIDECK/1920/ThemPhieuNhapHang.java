import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;


public class ThemPhieuNhapHang extends JFrame {

    //Tạo 2 bảng danh sách sản phẩm và danh sách sản phẩm nhập
    String[] columnNamesDanhSachSP = {"Sản phẩm"};
    String[] columnNamesDanhSachSPNhap = {"Sản phẩm", "Số lượng"};

    Object[][] dataA = {};
    DefaultTableModel modelTableDanhSachSP = new DefaultTableModel(dataA, columnNamesDanhSachSP);
    JTable tableDanhSachSP = new JTable(modelTableDanhSachSP);
    JScrollPane scrollPaneDanhSachSP = new JScrollPane(tableDanhSachSP);

    DefaultTableModel modelTableDanhSachSPNhap = new DefaultTableModel(dataA, columnNamesDanhSachSPNhap);
    JTable tableDanhSachSPNhap = new JTable(modelTableDanhSachSPNhap);
    JScrollPane scrollPaneDanhSachSPNhap = new JScrollPane(tableDanhSachSPNhap);

    //Ngày khám
    JDateChooser ngayLapField = new JDateChooser();

    JTextField ghiChuField = new JTextField(15);

    JComboBox nhaCCField = new JComboBox<>();

    //Khởi tạo các nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemPhieuNhapHang(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Phiếu nhập hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel ngayLapLabel = new JLabel("Ngày lập");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(ngayLapLabel, constraints);

        JLabel ghiChuLabel = new JLabel("Ghi chú");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ghiChuLabel, constraints);

        JLabel nhaCCLabel = new JLabel("Nhà cung cấp");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(nhaCCLabel, constraints);

        JLabel dsSPLabel = new JLabel("Danh sách sản phẩm");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(dsSPLabel, constraints);

        JLabel dsSPNhapLabel = new JLabel("Danh sách sản phẩm nhập");
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(dsSPNhapLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(ngayLapField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(ghiChuField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(nhaCCField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(scrollPaneDanhSachSP, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(scrollPaneDanhSachSPNhap, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------


        //Khơởi taạo các load
        loadNhaCC();
        loadTableSP();


        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi
        nhaCCField.addActionListener(this::nhaCCFieldActionPerformed);

        //Thêm sự kiện click vào chọn sản phẩm bảng danh sách sản phẩm thì sẽ thêm vào bảng danh sách sản phẩm nhập
        tableDanhSachSP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableDanhSachSP.getSelectedRow();
                    if (selectedRow != -1) {
                        Object[] rowData = new Object[tableDanhSachSP.getColumnCount()];
                        for (int i = 0; i < tableDanhSachSP.getColumnCount(); i++) {
                            rowData[i] = tableDanhSachSP.getValueAt(selectedRow, i);
                        }
                        modelTableDanhSachSPNhap.addRow(rowData);
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

    private void loadTableSP() {
        //Load danh sách sản phẩm vào bảng danh sách sản phẩm
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT TENSP FROM SANPHAM WHERE MANCC = (SELECT MANCC FROM NHACC WHERE TENNCC = '" + nhaCCField.getSelectedItem() + "')";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TENSP"));
                //Thêm vào bảng
                Object[] row = {resultSet.getString("TENSP")};
                modelTableDanhSachSP.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nhaCCFieldActionPerformed(ActionEvent actionEvent) {
        //Load danh sách sản phẩm của nhà cung cấp vào bảng danh sách sản phẩm
        modelTableDanhSachSP.setRowCount(0);
        loadTableSP();
    }

    private void loadNhaCC(){
        //Load danh sách nhà cung cấp vào combobox
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT TENNCC FROM NHACC";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TENNCC"));
                nhaCCField.addItem(resultSet.getString("TENNCC"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();   //Ra thi không cần dòng này cũng đuược, vì không cần viết class QuanLy
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); //Cái này là bắt buộc cho mấy cái date chooser
        String ngayLap = outputFormat.format(ngayLapField.getDate());

        String ghiChu = ghiChuField.getText();
        String tenNCC = nhaCCField.getSelectedItem().toString();

        //Dưới đây để lấy data trong bảng ra
        int rowCount = modelTableDanhSachSPNhap.getRowCount();
        int colCount = modelTableDanhSachSPNhap.getColumnCount();
        Object[][] dsSPNhapData = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(modelTableDanhSachSPNhap.getValueAt(i, j) + "hehe \n");
                dsSPNhapData[i][j] = modelTableDanhSachSPNhap.getValueAt(i, j);
            }
            System.out.println();
        }

        float tongTien = 0;
        for (int i = 0; i< rowCount; i++){
            String tenSP = dsSPNhapData[i][0].toString();
            String soLuong = dsSPNhapData[i][1].toString();
            tongTien += tinhTongTien(tenSP, soLuong);
        }

        String sql = "INSERT INTO PHIEUNHAP (NGAYLAP, GHICHU, TONGTIEN) VALUES ('" + ngayLap + "'" + ", '" + ghiChu + "', '" + Float.toString(tongTien) + "');";
        System.out.println(sql);
        String maPN = "";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            Statement statement = jdbcConnect.executeCommandInsert(sql);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                maPN = generatedKeys.getString(1);
            }
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
            e.printStackTrace();
        }


        //Thêm chi tiết phiếu nhập
        for (int i = 0; i< rowCount; i++){
            String tenSP = dsSPNhapData[i][0].toString();
            String soLuong = dsSPNhapData[i][1].toString();
            themCTPhieuNhap(maPN, tenSP, soLuong);
        }
    }

    private float tinhTongTien(String tenSP, String soLuong) {
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT DONGIA FROM SANPHAM WHERE TENSP = '" + tenSP + "'";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getFloat("DONGIA") * Integer.parseInt(soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void themCTPhieuNhap(String maPN, String tenSP, String soLuong) {

        try {
            JDBCConnect jdbcConnect = new JDBCConnect();

            String sqlGetMaSP = "SELECT MASP FROM SANPHAM WHERE TENSP = '" + tenSP + "'";
            String maSP = "";
            ResultSet resultSet = jdbcConnect.executeCommandGet(sqlGetMaSP);
            if (resultSet.next()) {
                maSP = resultSet.getString("MASP");
            }
            if (maSP.equals("")) {
                return;
            }

            String sqlInsertPN = "INSERT INTO CT_NHAP (MAPN, MASP, SL) VALUES ('" + maPN + "'" + ", '" + maSP + "', '" + soLuong + "');";
            System.out.println(sqlInsertPN);
            jdbcConnect.executeCommandInsert(sqlInsertPN);
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
            e.printStackTrace();
        }
    }

}
