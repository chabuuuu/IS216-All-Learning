import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuanLi extends JFrame {
    JButton ThemNhaCungCap = new JButton("Thêm nhà cung cấp");
    JButton ThemSanPham = new JButton("Thêm sản phẩm");
    JButton ThemPhieuNhapHang = new JButton("Thêm phiếu nhập hàng");

    QuanLi(){
        setTitle("Quản lí");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        add(ThemNhaCungCap);
        add(ThemSanPham);
        add(ThemPhieuNhapHang);

        // Add action listener for buttons
        ThemNhaCungCap.addActionListener(this::ThemNhaCungCapActionListener);
        ThemSanPham.addActionListener(this::ThemSanPhamActionListener);
        ThemPhieuNhapHang.addActionListener(this::ThemPhieuNhapHangActionListener);

        //add buttons to frame
        ThemNhaCungCap.setBounds(100, 50, 200, 50);
        ThemSanPham.setBounds(100, 150, 200, 50);
        ThemPhieuNhapHang.setBounds(100, 250, 200, 50);
        setVisible(true);
    }

    private void ThemPhieuNhapHangActionListener(ActionEvent actionEvent) {
        ThemPhieuNhapHang themPhieuNhapHang = new ThemPhieuNhapHang();
        this.dispose();
    }

    private void ThemSanPhamActionListener(ActionEvent actionEvent) {
        ThemSanPham themSanPham = new ThemSanPham();
        this.dispose();
    }

    private void ThemNhaCungCapActionListener(ActionEvent actionEvent) {
        ThemNhaCungCap themNhaCungCap = new ThemNhaCungCap();
        this.dispose();
    }
}
