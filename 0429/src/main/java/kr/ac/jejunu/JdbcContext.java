package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User jdbcContextForFind(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            // sql 작성

            preparedStatement = statementStrategy.makeStatement(connection);
            //sql 실행
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { // null 값이 아니라면
                // User 에 데이터 매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            // 자원 해지
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // User 리턴
        return user;
    }

    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            // 드라이버 로딩
            connection = dataSource.getConnection();
            // sql 작성
            preparedStatement = statementStrategy.makeStatement(connection);
            // sql 실행
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            // User 에 데이터 매핑
            user.setId(resultSet.getInt(1));
        } finally {
            // 자원 해지
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 드라이버 로딩
            connection = dataSource.getConnection();
            // sql 작성
            preparedStatement = statementStrategy.makeStatement(connection);
            // sql 실행
            preparedStatement.executeUpdate();
        } finally {
            // 자원 해지
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(User user, String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement= connection.prepareStatement(
                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );

            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        };
        jdbcContextForInsert(user, statementStrategy);
    }

    public void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
//            preparedStatement.setObject(1, user.getName());
//            preparedStatement.setObject(2, user.getPassword());
//            preparedStatement.setObject(3, user.getId());
            return preparedStatement;
        };
        jdbcContextForUpdate(statementStrategy);
    }

    public User find(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );

            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        };
        return jdbcContextForFind(statementStrategy);
    }
}