import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ThemDLL extends JFrame {
    JComboBox TTPField = new JComboBox();
    JTextField tenDDLField = new JTextField(15);
    JTextField dacTrungField = new JTextField(15);

    //Thêm nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemDLL(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm điểm du lịch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel tenTourLabel = new JLabel("Tên Tỉnh/Thành phố");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(tenTourLabel, constraints);

        JLabel ngayKHLabel = new JLabel("Tên điểm du lịch");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ngayKHLabel, constraints);

        JLabel soNgayLabel = new JLabel("Đặc trưng");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(soNgayLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(TTPField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(tenDDLField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(dacTrungField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadTTP();

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

    private void loadTTP(){
            String sql = "SELECT TENTTP FROM TINHTP;";
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
            while (resultSet.next()){
                TTPField.addItem(resultSet.getString("TENTTP"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private String getMaTTP(String tenTTP) {
        String sql = "SELECT MATTP FROM TINHTP WHERE TENTTP = '" + tenTTP + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getString("MATTP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String tenTTP = TTPField.getSelectedItem().toString();
        String maTTP = getMaTTP(tenTTP);
        String tenDDL = tenDDLField.getText();
        String dacTrung = dacTrungField.getText();
        String sql = "INSERT INTO DIEMDL (TENDDL, MATTP, DACTRUNG) VALUES ('" + tenDDL + "'" + ", '" + maTTP + "', '" + dacTrung + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm điểm du lịch thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm điểm du lịch thất bại!");
            e.printStackTrace();
        }
    }
}
