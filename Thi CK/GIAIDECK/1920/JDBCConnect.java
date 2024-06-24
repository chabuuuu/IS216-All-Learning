import java.sql.*;

class JDBCConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/QUANLYNHAPHANG";
    private static final String USER = "root";
    private static final String PASSWORD = "haphuthinh";
    Connection connection = null;

    // Phương thức để kết nối với cơ sở dữ liệu
    public Connection connect() {
        try {
            // Đăng ký driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return connection;
    }


    // Phương thức để thực thi các câu lệnh SQL
    // Phương thức để thực thi các câu lệnh SQL và trả về ResultSet
    public ResultSet executeCommandGet(String sql) {
        ResultSet resultSet = null;
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể thực thi lệnh SQL!");
            e.printStackTrace();
        }
        return resultSet;
    }
    public Statement executeCommandInsert(String sql) {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            return statement;
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể thực thi lệnh SQL!");
            e.printStackTrace();
        }
        return null;
    }
}
