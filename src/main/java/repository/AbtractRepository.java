package repository;

import jdbc.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AbtractRepository<T> {
    public T executeQuery(JDBCExecute<T> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            return processor.process(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
    public List<T> executeQueryList(JDBCExecute<List<T>> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            return processor.process(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void executeUpdate(JDBCExecute<T> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            processor.process(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
