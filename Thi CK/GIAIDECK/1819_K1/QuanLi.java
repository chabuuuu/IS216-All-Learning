import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuanLi extends JFrame {
    JButton ThemDoiBong = new JButton("Thêm đội bóng");
    JButton ThemGiaiDau = new JButton("Thêm giải đấu");
    JButton ThemDoiBongVaoGiaiDau = new JButton("Thêm đội bóng vào giải đấu");
    JButton ThemThongTinTranDau = new JButton("Thêm thông tin trận đấu");

    QuanLi(){
        setTitle("Quản lí");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        add(ThemDoiBong);
        add(ThemGiaiDau);
        add(ThemDoiBongVaoGiaiDau);
        add(ThemThongTinTranDau);

        // Add action listener for buttons
        ThemDoiBong.addActionListener(this::ThemDoiBongActionListener);
        ThemGiaiDau.addActionListener(this::ThemGiaiDauActionListener);
        ThemDoiBongVaoGiaiDau.addActionListener(this::ThemDoiBongVaoGiaiDauActionListener);
        ThemThongTinTranDau.addActionListener(this::ThemThongTinTranDauActionListener);
        
        //add buttons to frame
        ThemDoiBong.setBounds(100, 50, 200, 50);
        ThemGiaiDau.setBounds(100, 150, 200, 50);
        ThemDoiBongVaoGiaiDau.setBounds(100, 250, 200, 50);
        ThemThongTinTranDau.setBounds(100, 350, 200, 50);
        setVisible(true);
    }

    private void ThemThongTinTranDauActionListener(ActionEvent actionEvent) {
        ThemThongTinTranDau themThongTinTranDau = new ThemThongTinTranDau();
        this.dispose();
    }

    private void ThemDoiBongVaoGiaiDauActionListener(ActionEvent actionEvent) {
        ThemDoiVaoGiaiDau themDoiBongVaoGiaiDau = new ThemDoiVaoGiaiDau();
        this.dispose();
    }

    private void ThemGiaiDauActionListener(ActionEvent actionEvent) {
        ThemGiaiDau themGiaiDau = new ThemGiaiDau();
        this.dispose();
    }

    private void ThemDoiBongActionListener(ActionEvent actionEvent) {
        ThemDoiBong themDoiBong = new ThemDoiBong();
        this.dispose();
    }
}
