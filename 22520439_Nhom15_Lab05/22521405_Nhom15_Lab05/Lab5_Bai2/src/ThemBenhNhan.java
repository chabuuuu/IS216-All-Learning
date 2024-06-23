import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ThemBenhNhan extends JFrame {

    JTextField userIdField;
    JTextField usernameField;
    JDateChooser dobField;
    JComboBox sexField;
    JTextField addressField;
    JTextField phonenumberField;

    ThemBenhNhan() {
        setTitle("Thêm bệnh nhân");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel dateofbirthLabel = new JLabel("Ngày sinh");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(dateofbirthLabel, constraints);

        JLabel addressLabel = new JLabel("Địa chỉ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(addressLabel, constraints);

        JLabel phonenumberLabel = new JLabel("Điện thoại");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(phonenumberLabel, constraints);

        JLabel sexLabel = new JLabel("Giới tính");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(sexLabel, constraints);

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

        dobField = new JDateChooser();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(dobField, constraints);

        addressField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(addressField, constraints);

        phonenumberField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(phonenumberField, constraints);

        sexField = new JComboBox(new String[]{"Nam", "Nữ"});
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(sexField, constraints);

        JButton addButton = new JButton("Thêm");
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        JButton cancelButton = new JButton("Trở về");
        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        userIdField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent event) {
                userIdFieldFocusGained(event);
            }

            @Override
            public void focusLost(FocusEvent e) {
                userIdFieldFocusLost(e);
            }
        });

        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent event) {
                usernameFieldFocusGained(event);
            }

            @Override
            public void focusLost(FocusEvent e) {
                usernameFieldFocusLost(e);
            }
        });

        addressField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent event) {
                addressFieldFocusGained(event);
            }

            @Override
            public void focusLost(FocusEvent e) {
                addressFieldFocusLost(e);
            }
        });

        phonenumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent event) {
                phoneFieldFocusGained(event);
            }

            @Override
            public void focusLost(FocusEvent e) {
                phoneFieldFocusLost(e);
            }
        });

        addButton.addActionListener(this::themButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void userIdFieldFocusLost(java.awt.event.FocusEvent event) {
        if (userIdField.getText().equals("")) {
            userIdField.setText("Nhập mã bệnh nhân");
            userIdField.setForeground(new Color(153, 153, 153));
        }

    }

    private void userIdFieldFocusGained(java.awt.event.FocusEvent event) {
        if (userIdField.getText().equals("Nhập mã bệnh nhân")) {
            userIdField.setText("");
            userIdField.setForeground(new Color(0, 0, 0));
        }

    }

    private void usernameFieldFocusLost(java.awt.event.FocusEvent event) {
        if (usernameField.getText().equals("")) {
            usernameField.setText("Tên bệnh nhân");
            usernameField.setForeground(new Color(153, 153, 153));
        }

    }

    private void usernameFieldFocusGained(java.awt.event.FocusEvent event) {
        if (usernameField.getText().equals("Tên bệnh nhân")) {
            usernameField.setText("");
            usernameField.setForeground(new Color(0, 0, 0));
        }

    }

    private void addressFieldFocusLost(java.awt.event.FocusEvent event) {
        if (addressField.getText().equals("")) {
            addressField.setText("Địa chỉ");
            addressField.setForeground(new Color(153, 153, 153));
        }

    }

    private void addressFieldFocusGained(java.awt.event.FocusEvent event) {
        if (addressField.getText().equals("Địa chỉ")) {
            addressField.setText("");
            addressField.setForeground(new Color(0, 0, 0));
        }

    }

    private void phoneFieldFocusLost(java.awt.event.FocusEvent event) {
        if (phonenumberField.getText().equals("")) {
            phonenumberField.setText("Điện thoại");
            phonenumberField.setForeground(new Color(153, 153, 153));
        }

    }

    private void phoneFieldFocusGained(java.awt.event.FocusEvent event) {
        if (phonenumberField.getText().equals("Điện thoại")) {
            phonenumberField.setText("");
            phonenumberField.setForeground(new Color(0, 0, 0));
        }

    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent event) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void themButtonActionPerformed(java.awt.event.ActionEvent event) {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        String dob = dobField.getDate().toString();
        String address = addressField.getText();
        String phonenumber = phonenumberField.getText();
        String sex = sexField.getSelectedItem().toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dob1 = outputFormat.format(dobField.getDate());
        String sex1 ="";
        if (Objects.equals(sex, "Nam")) {
            sex1 = "TRUE";
        }else {
            sex1 = "FALSE";
        }

        String sql = "INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES (" + userId + ", '" + username + "', '" + dob1 + "', '" + address + "', '" + phonenumber + "', "+ sex1 + ");";

        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thất bại!");
            e.printStackTrace();
        }

    }
}
