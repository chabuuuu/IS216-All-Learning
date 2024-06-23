import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuanLi extends JFrame {
    // Declare buttons
    JButton ThemBenhNhan = new JButton("Thêm bệnh nhân");
    JButton DatLichKham = new JButton("Đặt lịch khám");
    JButton ChiTietBenhNhan = new JButton("Chi tiết bệnh nhân");
    JButton ThanhToan = new JButton("Thanh toán");


    QuanLi(){
        setTitle("Quản lí");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        add(DatLichKham);
        add(ThemBenhNhan);
        add(ChiTietBenhNhan);
        add(ThanhToan);

        // Add action listener for buttons
        ThemBenhNhan.addActionListener(this::ThemBenhNhanActionListener);
        ChiTietBenhNhan.addActionListener(this::ChiTietKhamBenhActionListener);
        DatLichKham.addActionListener(this::DatLichKhamActionListener);
        ThanhToan.addActionListener(this::ThanhToanActionListener);

        //add buttons to frame
        DatLichKham.setBounds(100, 50, 200, 50);
        ThemBenhNhan.setBounds(100, 150, 200, 50);
        ChiTietBenhNhan.setBounds(100, 250, 200, 50);
        ThanhToan.setBounds(100, 350, 200, 50);
        setVisible(true);
    }
    private void ThemBenhNhanActionListener (ActionEvent event){
        ThemBenhNhan themBenhNhan = new ThemBenhNhan();
        this.dispose();
    }
    private void ChiTietKhamBenhActionListener (ActionEvent event){
        SwingUtilities.invokeLater(() -> new ChiTietKhamBenh().setVisible(true));
        this.dispose();
    }
    private void DatLichKhamActionListener (ActionEvent event){
        DatLichKham datLichKham = new DatLichKham();
        this.dispose();
    }
    public void ThanhToanActionListener(ActionEvent event){
        SwingUtilities.invokeLater(() -> new ThanhToan().setVisible(true));
        this.dispose();
    }


}
