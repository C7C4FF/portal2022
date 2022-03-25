package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 커넥션
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jeju",
                "root", "1234"
        );
    }
}
