//package kr.ac.jejunu;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class JejuUserDao extends UserDao {
//    @Override
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        // 커넥션
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/jeju",
//                "root", "1234"
//        );
//    }
//}

// UserDao 에서 Connection을 Method 리팩토링 했을 때 사용했었음
// 그러면서 UserDao가 abstract class 가 되었기 때문에 UserDao 를 따로따로 만들었음.