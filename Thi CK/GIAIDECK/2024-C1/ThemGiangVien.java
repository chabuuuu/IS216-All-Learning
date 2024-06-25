import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class ThemGiangVien extends JFrame {
    JComboBox cbMaKhoa = new JComboBox();
    JTextField txtMaGV = new JTextField(15);
    JTextField txtTenGV = new JTextField(15);
    JRadioButton rbGioiTinh = new JRadioButton("Nam");

    //Thêm nút thêm và trở về
    JButton btnThem = new JButton("Thêm");
    JButton btnHuy = new JButton("Hủy");

    ThemGiangVien(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm giảng viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maKhoaLabel = new JLabel("Mã khoa");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maKhoaLabel, constraints);

        JLabel maGVLabel = new JLabel("Mã giảng viên");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(maGVLabel, constraints);

        JLabel tenGVLabel = new JLabel("Tên giảng viên");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(tenGVLabel, constraints);


        JLabel gioiTinhLabel = new JLabel("Giới tính");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(gioiTinhLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(cbMaKhoa, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(txtMaGV, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(txtTenGV, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(rbGioiTinh, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(btnThem, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(btnHuy, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadMaKhoa();

        //Khởi tạo các listener [CẦN CHÉP]
        btnThem.addActionListener(this::themButtonActionPerformed);
        btnHuy.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi



        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadMaKhoa(){
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

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String maGV = txtMaGV.getText();
        String tenGV = txtTenGV.getText();
        String gioiTinh = rbGioiTinh.isSelected() ? "1" : "0";
        String maKhoa = cbMaKhoa.getSelectedItem().toString();

        String sql = "INSERT INTO GIANGVIEN (MAGIANGVIEN, TENGIANGVIEN, GIOITINH, MAKHOA) VALUES ('" + maGV + "'" + ", '" + tenGV + "', '" + gioiTinh + "', '" + maKhoa + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm giảng viên thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm giảng viên thất bại!");
            e.printStackTrace();
        }
    }
}
