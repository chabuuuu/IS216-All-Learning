import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class ThemTruong extends JFrame {
    JTextField txtMaTruong = new JTextField(15);
    JTextField txtTenTruong = new JTextField(15);
    JTextField txtDiaChi = new JTextField(15);

    JButton btnThem = new JButton("Thêm");
    JButton btnHuy = new JButton("Hủy");

    ThemTruong(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm trường");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maTruongLabel = new JLabel("Mã trường");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maTruongLabel, constraints);

        JLabel tenTruongLabel = new JLabel("Tên trường");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(tenTruongLabel, constraints);

        JLabel diaChiLabel = new JLabel("Địa chỉ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(diaChiLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(txtMaTruong, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(txtTenTruong, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(txtDiaChi, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(btnThem, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(btnHuy, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //Khởi tạo các listener [CẦN CHÉP]
        btnThem.addActionListener(this::themButtonActionPerformed);
        btnHuy.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi



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

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String maTruong = txtMaTruong.getText();
        String tenTruong = txtTenTruong.getText();
        String diaChi = txtDiaChi.getText();

        String sql = "INSERT INTO TRUONG (MATRUONG, TENTRUONG, DIACHI) VALUES ('" + maTruong + "'" + ", '" + tenTruong + "', '" + diaChi + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm trường thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm trường thất bại!");
            e.printStackTrace();
        }
    }
}
