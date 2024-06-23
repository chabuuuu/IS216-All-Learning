import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

class LoginForm extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmField;

    public void TaoForm() {

        //Set title, size, location, close operation for JFrame
        setTitle("Sign in & Sign up Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a JPanel with GridBagLayout manager
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Set border for the panel
        constraints.insets = new Insets(5, 5, 5, 5);

        //Add labels, fields, buttons to the panel using GridBagLayout
        JLabel usernameLabel = new JLabel("Username");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        //Create a new label with the text "Password"
        JLabel passwordLabel = new JLabel("Password");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        //Create a new label with the text "Confirm"
        JLabel confirmLabel = new JLabel("Confirm");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(confirmLabel, constraints);

        //Create a new text field with 15 columns
        usernameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(usernameField, constraints);

        //Create a new password field with 15 columns
        passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(passwordField, constraints);

        //Create a new password field with 15 columns
        confirmField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;

        panel.add(confirmField, constraints);

        JButton signInButton = new JButton("Sign in");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;

        panel.add(signInButton, constraints);

        JButton signUpButton = new JButton("Sign up");
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(signUpButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(cancelButton, constraints);

        //Add button sign in listener
        signInButton.addActionListener(this::SignInButtonActionPerformed);

        signUpButton.addActionListener(this::SignUpButtonActionPerformed);

        //Add button cancel listener
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });


        //Add the panel to the frame
        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void SignInButtonActionPerformed(ActionEvent event) {
        JDBCConnect jdbcConnect = new JDBCConnect();
        jdbcConnect.connect();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
            return;
        }
        String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println(sql);
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        System.out.println(resultSet);
        try{
            if(resultSet.next()){
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
            }else{
                JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SevenButtonActionPerformed
        JDBCConnect jdbcConnect = new JDBCConnect();
        jdbcConnect.connect();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());
        if (password.equals(confirm)) {
            jdbcConnect.executeCommandInsert("INSERT INTO user(username, password) VALUES('" + username + "', '" + password + "')");
            JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.TaoForm();


    }
}