import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ThemDoiBong extends JFrame {
    JTextField MaDoiField = new JTextField(15);
    JTextField TenDoiField = new JTextField(15);
    JTextField QuocGiaField = new JTextField(15);

    //Thêm nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton cancelButton = new JButton("Trở về");

    ThemDoiBong(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm đội bóng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel maDoiLabel = new JLabel("Mã đội");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(maDoiLabel, constraints);

        JLabel tenDoiLabel = new JLabel("Tên đội");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(tenDoiLabel, constraints);

        JLabel quocGiaLabel = new JLabel("Quốc gia");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(quocGiaLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(MaDoiField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(TenDoiField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(QuocGiaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
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
        QuanLi quanLi = new QuanLi();   //Ra thi không cần dòng này cũng đuược, vì không cần viết class QuanLy
        this.dispose();
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {
        String maDoi = MaDoiField.getText();
        String tenDoi = TenDoiField.getText();
        String quocGia = QuocGiaField.getText();

        String sql = "INSERT INTO DOIBONG (MAD, TENDOI, QUOCGIA) VALUES ('" + maDoi + "'" + ", '" + tenDoi + "', '" + quocGia + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm đội bóng thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm đội bóng thất bại!");
            e.printStackTrace();
        }
    }
}
