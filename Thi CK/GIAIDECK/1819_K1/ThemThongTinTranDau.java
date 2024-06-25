import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ThemThongTinTranDau extends JFrame {
    JComboBox TenGiaiDauField = new JComboBox();
    JDateChooser NgayDaField = new JDateChooser();
    JTextField DiaDiemField = new JTextField(15);
    JComboBox ChuNhaField = new JComboBox();
    JComboBox DoiKhachField = new JComboBox();
    JTextField TySoField = new JTextField(15);

    //Thêm nút thêm và trở về và hủy
    JButton addButton = new JButton("Thêm");
    JButton backButton = new JButton("Trở về");
    JButton cancelButton = new JButton("Hủy");

    ThemThongTinTranDau(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm thông tin trận đấu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel tenGiaiLabel = new JLabel("Tên giải đấu");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(tenGiaiLabel, constraints);

        JLabel ngayDaLabel = new JLabel("Ngày đá");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ngayDaLabel, constraints);

        JLabel diaDiemLabel = new JLabel("Địa điểm");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(diaDiemLabel, constraints);

        JLabel chuNhaLabel = new JLabel("Chủ nhà");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(chuNhaLabel, constraints);

        JLabel doiKhachLabel = new JLabel("Đội khách");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(doiKhachLabel, constraints);

        JLabel tisoLabel = new JLabel("Tỉ số");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(tisoLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(TenGiaiDauField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(NgayDaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(DiaDiemField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(ChuNhaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(DoiKhachField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(TySoField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------

        //LOAD DATA
        loadChuNha();
        loadDoiKhach();
        loadGiaiDau();

        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        backButton.addActionListener(this::backButtonActionPerformed);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi



        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void backButtonActionPerformed(ActionEvent actionEvent) {
        QuanLi quanLi = new QuanLi();
        this.dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        //Xóa tất cả
        TenGiaiDauField.setSelectedIndex(0);
        NgayDaField.setDate(null);
        DiaDiemField.setText("");
        ChuNhaField.setSelectedIndex(0);
        DoiKhachField.setSelectedIndex(0);
        TySoField.setText("");
    }

    private void loadChuNha() {
        String sql = "SELECT TENDOI FROM DOIBONG;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                ChuNhaField.addItem(resultSet.getString("TENDOI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDoiKhach() {
        String sql = "SELECT TENDOI FROM DOIBONG;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                DoiKhachField.addItem(resultSet.getString("TENDOI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGiaiDau() {
        String sql = "SELECT TENGIAI FROM GIAIDAU;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                TenGiaiDauField.addItem(resultSet.getString("TENGIAI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMaGiaiDau(String tenGiaiDau) {
        String sql = "SELECT MAG FROM GIAIDAU WHERE TENGIAI = '" + tenGiaiDau + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getString("MAG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getMaDoi(String tenDoi) {
        String sql = "SELECT MAD FROM DOIBONG WHERE TENDOI = '" + tenDoi + "';";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            if (resultSet.next()) {
                return resultSet.getString("MAD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void themButtonActionPerformed(ActionEvent actionEvent) {

        String tenGD = TenGiaiDauField.getSelectedItem().toString();
        String maGD = getMaGiaiDau(tenGD);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); //Cái này là bắt buộc cho mấy cái date chooser
        String ngayDa = outputFormat.format(NgayDaField.getDate());
        String diaDiem = DiaDiemField.getText();
        String chuNha = ChuNhaField.getSelectedItem().toString();
        String maChuNha = getMaDoi(chuNha);
        String doiKhach = DoiKhachField.getSelectedItem().toString();
        String maDoiKhach = getMaDoi(doiKhach);
        String tySo = TySoField.getText();

        String sql = "INSERT INTO TRANDAU (NGAYDA, DIADIEM, MAG, MADN, MADK, TISO) VALUES ('" + ngayDa + "'" + ", '" + diaDiem + "', '" + maGD + "', '" + maChuNha + "', '" + maDoiKhach + "', '" + tySo + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
            JOptionPane.showMessageDialog(null, "Thêm thông tin trận đấu thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm thông tin trận đấu thất bại!");
            e.printStackTrace();
        }
    }
}
