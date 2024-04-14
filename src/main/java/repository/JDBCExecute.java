package repository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface JDBCExecute<T> {
    public T process(Connection connection) throws SQLException;
}
