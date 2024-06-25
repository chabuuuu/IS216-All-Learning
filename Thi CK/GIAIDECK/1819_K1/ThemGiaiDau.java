import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class ThemGiaiDau extends JFrame {
    JTextField MaGiaiDauField = new JTextField(15);
    JTextField TenGiaiDauField = new JTextField(15);
    JDateChooser NgayBatDauField = new JDateChooser();
    JDateChooser NgayKetThucField = new JDateChooser();

    //Thêm nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemGiaiDau(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm giải đấu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maGiaiLabel = new JLabel("Mã giải");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maGiaiLabel, constraints);

        JLabel tenGiaiLabel = new JLabel("Tên giải");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(tenGiaiLabel, constraints);

        JLabel ngayBatDauLabel = new JLabel("Ngày bắt đầu");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(ngayBatDauLabel, constraints);

        JLabel ngayKetThucLabel = new JLabel("Ngày kết thúc");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(ngayKetThucLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(MaGiaiDauField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(TenGiaiDauField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(NgayBatDauField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(NgayKetThucField, constraints);

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



        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();   //Ra thi không cần dòng này cũng đuược, vì không cần viết class QuanLy
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String maGiai = MaGiaiDauField.getText();
        String tenGiai = TenGiaiDauField.getText();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); //Cái này là bắt buộc cho mấy cái date chooser
        String ngayBD = outputFormat.format(NgayBatDauField.getDate());
        String ngayKT = outputFormat.format(NgayKetThucField.getDate());


        String sql = "INSERT INTO GIAIDAU (MAG, TENGIAI, NGAYBATDAU, NGAYKETTHUC) VALUES ('" + maGiai + "'" + ", '" + tenGiai + "', '" + ngayBD + "', '" + ngayKT + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm giải đấu thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm giải đấu thất bại!");
            e.printStackTrace();
        }
    }
    }

