import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class ThemDoiVaoGiaiDau extends JFrame {
    JComboBox TenGiaiDauField = new JComboBox();

    //Tên các cột của bảng danh sách đội
    String[] columnNamesDanhSachDoi = {"Mã đội", "Tên đội"};

    //Tạo bảng 1
    DefaultTableModel modelTableDanhSachDoi = new DefaultTableModel(new Object[][]{}, columnNamesDanhSachDoi);
    JTable tableDanhSachDoi = new JTable(modelTableDanhSachDoi);
    JScrollPane scrollPaneDanhSachDoi = new JScrollPane(tableDanhSachDoi);

    //Tạo bảng 2
    DefaultTableModel modelTableDachSachDoiThamGia = new DefaultTableModel(new Object[][]{}, columnNamesDanhSachDoi);
    JTable tableDanhSachDoiThamGia = new JTable(modelTableDachSachDoiThamGia);
    JScrollPane scrollPaneDanhSachDoiThamGia = new JScrollPane(tableDanhSachDoiThamGia);

    //Khởi tạo các nút thêm và trở về
    JButton addButton = new JButton("Thêm");
    JButton backButton = new JButton("Trở về");
    JButton cancelButton = new JButton("Hủy");


    ThemDoiVaoGiaiDau(){
        // -------PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------
        setTitle("Thêm đội vào giải đấu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel tenGiaiLabel = new JLabel("Tên giải đấu");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(tenGiaiLabel, constraints);

        JLabel dsDoiLabel = new JLabel("Danh sách đội");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(dsDoiLabel, constraints);

        JLabel dsDoiTGLabel = new JLabel("Danh sách đội tham gia giải");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(dsDoiTGLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(TenGiaiDauField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(scrollPaneDanhSachDoi, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(scrollPaneDanhSachDoiThamGia, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(backButton, constraints);

        // -------PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-------


        //LOAD DATA
        loadGiaiDau();
        loadDSDoi();

        //Khởi tạo các listener [CẦN CHÉP]
        addButton.addActionListener(this::themButtonActionPerformed);
        backButton.addActionListener(e -> {
            new QuanLi();
            dispose();
        });
        cancelButton.addActionListener(this::cancelButtonActionPerformed);  //Nếu đề có vẽ nút cancel thì làm, không thì thôi

        //Thêm sự kiện click vào chọn sản phẩm bảng danh sách sản phẩm thì sẽ thêm vào bảng danh sách sản phẩm nhập
        tableDanhSachDoi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableDanhSachDoi.getSelectedRow();
                    if (selectedRow != -1) {
                        Object[] rowData = new Object[tableDanhSachDoi.getColumnCount()];
                        for (int i = 0; i < tableDanhSachDoi.getColumnCount(); i++) {
                            rowData[i] = tableDanhSachDoi.getValueAt(selectedRow, i);
                        }
                        modelTableDachSachDoiThamGia.addRow(rowData);
                    }
                }
            }
        });


        //-----PHÍA DƯỚI NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //-----PHÍA TRÊN NÀY LÀ GIAO DIỆN, KHÔNG CẦN CHÉP LẠI-----
    }

    private void loadDSDoi() {
        String sql = "SELECT MAD, TENDOI FROM DOIBONG;";
        JDBCConnect jdbcConnect = new JDBCConnect();
        ResultSet resultSet = jdbcConnect.executeCommandGet(sql);
        try {
            while (resultSet.next()) {
                modelTableDanhSachDoi.addRow(new Object[]{resultSet.getString("MAD"), resultSet.getString("TENDOI")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelButtonActionPerformed(ActionEvent actionEvent) {
        modelTableDachSachDoiThamGia.setRowCount(0);
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

    private void themButtonActionPerformed(ActionEvent actionEvent) {

        String tenGiai = TenGiaiDauField.getSelectedItem().toString();
        String maGiai = getMaGiaiDau(tenGiai);


        //Thêm chi tiết giải đấu

        int rowCount = modelTableDachSachDoiThamGia.getRowCount();
        int colCount = modelTableDachSachDoiThamGia.getColumnCount();
        for (int i = 0; i< rowCount; i++){
            String maDoi = modelTableDachSachDoiThamGia.getValueAt(i, 0).toString();
            themCT_GD(maGiai, maDoi);
        }
        JOptionPane.showMessageDialog(null, "Thêm các đội vào giải đấu thành công!");
    }

    private void themCT_GD(String maGiai, String maDoi) {

        String sql = "INSERT INTO CT_GD (MAG, MAD) VALUES ('" + maGiai + "'" + ", '" + maDoi + "');";
        System.out.println(sql);
        try {
            JDBCConnect jdbcConnect = new JDBCConnect();
            jdbcConnect.executeCommandInsert(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
