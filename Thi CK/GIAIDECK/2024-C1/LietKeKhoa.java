import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class LietKeKhoa extends JFrame {
    //Tên các cột của bảng danh sách đội
    String[] columnNamesDanhSachKhoa = {"STT", "Tên khoa", "Số giảng viên trong khoa"};

    //Tạo bảng
    DefaultTableModel modelDSKhoa = new DefaultTableModel(new Object[][]{}, columnNamesDanhSachKhoa);
    JTable tableDanhSachKhoa = new JTable(modelDSKhoa);
    JScrollPane scrollPaneDanhSachKhoa = new JScrollPane(tableDanhSachKhoa);

    //Khởi tạo nút trở về
    JButton backButton = new JButton("Trở về");

    LietKeKhoa(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Liệt kê khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(scrollPaneDanhSachKhoa, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(backButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadData();

        //Khởi tạo các listener [CẦN CHÉP]
        backButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi



        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private int countGVcuaKhoa(String maKhoa){
        String sql = "SELECT MAKHOA, COUNT(MAGIANGVIEN) AS SOGV FROM GIANGVIEN WHERE MAKHOA = '" + maKhoa + "' GROUP BY MAKHOA;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                return resultSet.getInt("SOGV");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void loadData (){
        String sql = "SELECT * FROM KHOA;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            Integer stt = 1;
            while (resultSet.next()) {
                String MaKhoa = resultSet.getString("MAKHOA");
                String TenKhoa = resultSet.getString("TENKHOA");
                Integer soGV = countGVcuaKhoa(MaKhoa);
                modelDSKhoa.addRow(new Object[]{stt, TenKhoa, soGV});
                stt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
