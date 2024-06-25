import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class LietKeGiangVien extends JFrame {
    JComboBox cbMaKhoa = new JComboBox();
    //Tên các cột của bảng danh sách đội
    String[] columnNamesDanhSachGV = {"STT", "Mã giảng viên", "Tên giảng viên"};

    //Tạo bảng
    DefaultTableModel modelDSGV = new DefaultTableModel(new Object[][]{}, columnNamesDanhSachGV);
    JTable tableDanhSachGV = new JTable(modelDSGV);
    JScrollPane scrollPaneDanhSachGV = new JScrollPane(tableDanhSachGV);

    //Khởi tạo nút trở về
    JButton backButton = new JButton("Trở về");
    JButton deleteButton = new JButton("Xóa");

    LietKeGiangVien(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Liệt kê giảng viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maKhoaLabel = new JLabel("Mã khoa");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maKhoaLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(cbMaKhoa, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(scrollPaneDanhSachGV, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(backButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(deleteButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadMaKhoa();
        loadTableGV(cbMaKhoa.getSelectedItem().toString());

        //Khởi tạo các listener [CẦN CHÉP]
        backButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        
        //Lắng nghe khi chọn combo box
        cbMaKhoa.addActionListener(this::maKhoaSelectActionPerformed);

        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadTableGV(String maKhoa){
        //Xóa dữ liệu cũ
        modelDSGV.setRowCount(0);


        String sql = "SELECT * FROM GIANGVIEN WHERE MAKHOA = '" + maKhoa + "';";

        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            int stt = 1;
            while (resultSet.next()) {
                String maGV = resultSet.getString("MAGIANGVIEN");
                String tenGV = resultSet.getString("TENGIANGVIEN");
                modelDSGV.addRow(new Object[]{stt, maGV, tenGV});
                stt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void maKhoaSelectActionPerformed(ActionEvent actionEvent) {
        String maKhoa = cbMaKhoa.getSelectedItem().toString();
        loadTableGV(maKhoa);
    }

    private void deleteButtonActionPerformed(ActionEvent actionEvent) {
        String maGVDelete = tableDanhSachGV.getValueAt(tableDanhSachGV.getSelectedRow(), 1).toString();
        String sql = "DELETE FROM GIANGVIEN WHERE MAGIANGVIEN = '" + maGVDelete + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        jdbcConnect.executeCommand(sql);
        loadTableGV(cbMaKhoa.getSelectedItem().toString());
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void loadMaKhoa() {
        String sql = "SELECT MAKHOA FROM KHOA;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                cbMaKhoa.addItem(resultSet.getString("MAKHOA"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
