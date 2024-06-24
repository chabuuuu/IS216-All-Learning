import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ThemSanPham extends JFrame {
    JTextField MaNhaCCField = new JTextField(15);
    JTextField TenNCCField = new JTextField(15);
    JTextField TenSanPhamField = new JTextField(15);
    JTextField DonGiaField = new JTextField(15);

    //Khởi tạo các nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemSanPham(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thông tin sản phẩm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maNhaCCLabel = new JLabel("Mã nhà cung cấp");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maNhaCCLabel, constraints);

        JLabel tenNhaCCLabel = new JLabel("Tên nhà cung cấp");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(tenNhaCCLabel, constraints);

        JLabel tenSanPhamLabel = new JLabel("Tên sản phẩm");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(tenSanPhamLabel, constraints);

        JLabel donGiaLabel = new JLabel("Đơn giá");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(donGiaLabel, constraints);



        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(MaNhaCCField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(TenNCCField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(TenSanPhamField, constraints);


        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(DonGiaField, constraints);


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
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi

        // Thêm Lắng nghe sự kiện khi enter được nhấn
        MaNhaCCField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy văn bản từ JTextField khi Enter được nhấn
                String text = MaNhaCCField.getText();
                MaNhaCCFieldEnterActionPerformed(text);
            }
        });


        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //-----PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
    }

    private void MaNhaCCFieldEnterActionPerformed(String text) {
        String nhaccId = MaNhaCCField.getText();
        String sql = "SELECT * FROM NHACC WHERE MANCC = '" + nhaccId + "'";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                TenNCCField.setText(resultSet.getString("TENNCC"));

                //Dùng cái này để không cho phép sưửa nữa!!!
                TenNCCField.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp!");
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
        String maNCC = MaNhaCCField.getText();
        String tenSP = TenSanPhamField.getText();
        String donGia = DonGiaField.getText();

        String sql = "INSERT INTO SANPHAM (TENSP, SOLUONG, DONGIA, MANCC) VALUES ('" + tenSP + "'" + ", '" + "0" + "', '" + donGia + "', '" + maNCC + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
            e.printStackTrace();
        }
    }
}
