import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class ThemTour extends JFrame {
    JTextField tenTourField = new JTextField(15);
    JDateChooser ngayKhoiHanhField = new JDateChooser();
    JTextField soNgayField = new JTextField(15);
    JTextField soDemField = new JTextField(15);
    JTextField giaField = new JTextField(15);

    //Thêm nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemTour(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm tour");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel tenTourLabel = new JLabel("Tên tour");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(tenTourLabel, constraints);

        JLabel ngayKHLabel = new JLabel("Ngày khởi hành");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ngayKHLabel, constraints);

        JLabel soNgayLabel = new JLabel("Số ngày");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(soNgayLabel, constraints);

        JLabel soDemLabel = new JLabel("Số đêm");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(soDemLabel, constraints);

        JLabel giaTourLabel = new JLabel("Giá tour");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(giaTourLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(tenTourField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(ngayKhoiHanhField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(soNgayField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(soDemField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(giaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi



        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //-----PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String tenTour = tenTourField.getText();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); //Cái này là bắt buộc cho mấy cái date chooser
        String ngayKH = outputFormat.format(ngayKhoiHanhField.getDate());
        String soNgay = soNgayField.getText();
        String soDem = soDemField.getText();
        String gia = giaField.getText();

        String sql = "INSERT INTO TOUR (TENTOUR, NGAYKHOIHANH, SONGAY, SODEM, GIA) VALUES ('" + tenTour + "'" + ", '" + ngayKH + "', '" + soNgay + "', '" + soDem + "' , '" + gia + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm tour thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm tour thất bại!");
            e.printStackTrace();
        }
    }
}
