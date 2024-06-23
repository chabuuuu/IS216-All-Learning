import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class DatLichKham extends JFrame {
    JTextField userIdField;
    JTextField usernameField;
    JTextField dateField;
    JComboBox bacsiField = new JComboBox();
    JButton kiemTraButton;
    JTextField yeucauField;
    JDateChooser dateChooser;

    private void loadDoctors() {
        JDBCConnect jdbcConnect = new JDBCConnect();
        String sql = "SELECT TenBS FROM BACSI";
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TenBS"));
                bacsiField.addItem(resultSet.getString("TenBS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DatLichKham() {
        setTitle("Đặt lịch khám");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadDoctors();
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);


        JLabel userIdLabel = new JLabel("Mã bệnh nhân");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(userIdLabel, constraints);

        JLabel usernameLabel = new JLabel("Tên bệnh nhân");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(usernameLabel, constraints);

        JLabel dateofbirthLabel = new JLabel("Ngày khám");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(dateofbirthLabel, constraints);

        JLabel yeucauLabel = new JLabel("Yêu cầu khám");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(yeucauLabel, constraints);

        JLabel phonenumberLabel = new JLabel("Bác sĩ khám");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(phonenumberLabel, constraints);

        userIdField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(userIdField, constraints);

        usernameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(usernameField, constraints);

        dateChooser = new JDateChooser();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(dateChooser, constraints);

        yeucauField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(yeucauField, constraints);


        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(bacsiField, constraints);

        JButton addButton = new JButton("Đặt lịch khám");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        kiemTraButton = new JButton("Kiểm tra");
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(kiemTraButton, constraints);

        JButton cancelButton = new JButton("Trở về");
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        kiemTraButton.addActionListener(this::kiemTraButtonActionPerformed);
        addButton.addActionListener(this::addButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);


        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void kiemTraButtonActionPerformed(java.awt.event.ActionEvent event) {
        String userId = userIdField.getText();
        String sql = "SELECT * FROM BENHNHAN WHERE MABN = " + userId;
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                usernameField.setText(resultSet.getString("TenBN"));
                usernameField.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addButtonActionPerformed(java.awt.event.ActionEvent event) {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        String yeucau = yeucauField.getText();
        String bacsi = bacsiField.getSelectedItem().toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = outputFormat.format(dateChooser.getDate());


        String sql = "INSERT INTO KHAMBENH (MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) " +
                "VALUES ('" + userId + "', (SELECT MABS FROM BACSI WHERE TENBS = '" + bacsi + "'), '" + date + "', '" + yeucau + "', NULL, FALSE);";

        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(this, "Đặt lịch khám thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đặt lịch khám thất bại!");
            e.printStackTrace();
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent event) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }
}
