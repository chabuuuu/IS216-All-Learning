import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ThemNhaCungCap extends JFrame {

    //Khởi tạo các text field [CẦN CHÉP]
    JTextField TenNhaCungCap = new JTextField(15);;
    JTextField DiaChi = new JTextField(15);
    JTextField DienThoai = new JTextField(15);
    JTextField Email = new JTextField(15);

    //Khởi tạo các nút thêm và trở về [CẦN CHÉP]
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");



    ThemNhaCungCap(){

        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm nhà cung cấp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel tenNhaCungCapLabel = new JLabel("Tên nhà cung cấp");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(tenNhaCungCapLabel, constraints);

        JLabel diaChiLabel = new JLabel("Địa chỉ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(diaChiLabel, constraints);

        JLabel dienThoaiLabel = new JLabel("Điện thoại");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(dienThoaiLabel, constraints);

        JLabel emailLabel = new JLabel("Email");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(emailLabel, constraints);



        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(TenNhaCungCap, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(DiaChi, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(DienThoai, constraints);


        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(Email, constraints);


        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------


        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //-----PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----

    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();   //Ra thi không cần dòng này cũng đuược, vì không cần viết class QuanLy
        this.dispose();
    }


    //Hàm thêm bệnh nhân
    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String tenNhaCungCap = TenNhaCungCap.getText();
        String diaChi = DiaChi.getText();
        String dienThoai = DienThoai.getText();
        String email = Email.getText();

        String sql = "INSERT INTO NHACC (TENNCC, DIACHI, DTHOAI, EMAIL) VALUES ('" + tenNhaCungCap + "'" + ", '" + diaChi + "', '" + dienThoai + "', '" + email + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại!");
            e.printStackTrace();
        }
    }
}
