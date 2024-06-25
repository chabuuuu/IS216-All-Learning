import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuanLi extends JFrame {
    JButton ThemTour = new JButton("Thêm tour");
    JButton ThemDDL = new JButton("Thêm điểm du lịch");
    JButton ThemDiemDLVaoTour = new JButton("Thêm điểm du lịch vào tour");

    QuanLi(){
        setTitle("Quản lí");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        add(ThemDDL);
        add(ThemTour);
        add(ThemDiemDLVaoTour);

        // Add action listener for buttons
        ThemTour.addActionListener(this::ThemTourActionListener);
        ThemDDL.addActionListener(this::ThemDDLActionListener);
        ThemDiemDLVaoTour.addActionListener(this::ThemDiemDLVaoTourActionListener);

        //add buttons to frame
        ThemTour.setBounds(100, 50, 200, 50);
        ThemDDL.setBounds(100, 150, 200, 50);
        ThemDiemDLVaoTour.setBounds(100, 250, 200, 50);
        setVisible(true);
    }

    private void ThemDiemDLVaoTourActionListener(ActionEvent actionEvent) {
        ThemDDLVaoTour themDiemDLVaoTour = new ThemDDLVaoTour();
        this.dispose();
    }

    private void ThemDDLActionListener(ActionEvent actionEvent) {
        ThemDLL themDLL = new ThemDLL();
        this.dispose();
    }

    private void ThemTourActionListener(ActionEvent actionEvent) {
        ThemTour themTour = new ThemTour();
        this.dispose();
    }
}
