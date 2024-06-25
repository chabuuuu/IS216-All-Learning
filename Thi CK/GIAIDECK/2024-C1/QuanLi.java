import javax.swing.*;
import java.awt.event.ActionEvent;

    public class QuanLi extends JFrame {
        JButton ThemTruong = new JButton("Thêm trường");
        JButton ThemGiangVien = new JButton("Thêm giảng viên");
        JButton LietKeKhoa = new JButton("Liệt kê khoa");
        JButton LietKeGiangVien = new JButton("Liệt kê giảng viên");

        QuanLi(){
            setTitle("Quản lí");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 500);
            setLocationRelativeTo(null);
            setLayout(null);
            add(LietKeKhoa);
            add(LietKeGiangVien);
            add(ThemTruong);
            add(ThemGiangVien);

            // Add action listener for buttons
            ThemTruong.addActionListener(this::ThemTruongActionListener);
            ThemGiangVien.addActionListener(this::ThemGiangVienActionListener);
            LietKeKhoa.addActionListener(this::LietKeKhoaActionListener);
            LietKeGiangVien.addActionListener(this::LietKeGiangVienActionListener);

            //add buttons to frame
            ThemTruong.setBounds(100, 50, 200, 50);
            ThemGiangVien.setBounds(100, 150, 200, 50);
            LietKeKhoa.setBounds(100, 250, 200, 50);
            LietKeGiangVien.setBounds(100, 350, 200, 50);
            setVisible(true);
        }

        private void LietKeGiangVienActionListener(ActionEvent actionEvent) {
            LietKeGiangVien lietKeGiangVien = new LietKeGiangVien();
            this.dispose();
        }

        private void LietKeKhoaActionListener(ActionEvent actionEvent) {
            LietKeKhoa lietKeKhoa = new LietKeKhoa();
            this.dispose();
        }

        private void ThemGiangVienActionListener(ActionEvent actionEvent) {
            ThemGiangVien themGiangVien = new ThemGiangVien();
            this.dispose();
        }

        private void ThemTruongActionListener(ActionEvent actionEvent) {
            ThemTruong themTruong = new ThemTruong();
            this.dispose();
        }

    }
