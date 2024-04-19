package repository;

import model.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameRepository extends AbtractRepository<Game> {
    public void save(Game game) {
        executeUpdate((connection -> {
            String sql = "INSERT INTO game (gameID, username, targetNumber, startTime, isActive)" +
                    " values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            preparedStatement.setString(i++, game.getId());
            preparedStatement.setString(i++, game.getUsername());
            preparedStatement.setInt(i++, game.getTargetNumber());
            preparedStatement.setTimestamp(i++, Timestamp.valueOf(game.getStartTime()));
            preparedStatement.setInt(i++, 1);
            preparedStatement.executeUpdate();
            return null;
        }));
    }

    public Game loadActiveGameByUsername(String username) {
        return executeQuery((connection -> {
            String sql = "SELECT * FROM game where username = ? and isActive = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gameID = resultSet.getString("gameID");
                int targetNumber = resultSet.getInt("targetNumber");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                Timestamp endTimeInTimestamp = resultSet.getTimestamp("endTime");
                /*
                    if(endTimeInTimestamp == null) {
                        endTime = null;
                    } else {
                        endTime = endTimeInTimestamp.toLocalDateTime();
                    }
                */
                LocalDateTime endTime = endTimeInTimestamp == null ? null : endTimeInTimestamp.toLocalDateTime();
                int isComplete = resultSet.getInt("isComplete");
                int isActive = resultSet.getInt("isActive");
                return new Game(gameID, targetNumber, username, startTime, endTime, isComplete, isActive);
            }
            return null;
        }));
    }

    public List<Game> loadAllGameByUsername(String username) {
        return executeQueryList((connection -> {
            List<Game> gameList = new ArrayList<>();
            String sql = "SELECT * FROM game where username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gameID = resultSet.getString("gameID");
                int targetNumber = resultSet.getInt("targetNumber");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                Timestamp endTimeInTimestamp = resultSet.getTimestamp("endTime");
                LocalDateTime endTime = endTimeInTimestamp == null ? null : endTimeInTimestamp.toLocalDateTime();
                int isComplete = resultSet.getInt("isComplete");
                int isActive = resultSet.getInt("isActive");
                gameList.add(new Game(gameID, targetNumber, username, startTime, endTime, isComplete, isActive));
            }
            return gameList;
        }));
    }

    //set other game to isActive = 0
    public void deactiveAllGame(String username) {
        executeUpdate((connection -> {
            String sql = "UPDATE game SET isActive = 0 WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeUpdate();
            return null;
        }));
    }

    public void activeGameByGameID(String gameID) {
        executeUpdate((connection -> {
            String sql = "UPDATE game SET isActive = 1 WHERE gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameID);
            statement.executeUpdate();
            return null;
        }));
    }

    public Game loadGameByGameID(String gameID) {
        return executeQuery((connection -> {
            String sql = "SELECT * from game where gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int targetNumber = resultSet.getInt("targetNumber");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                Timestamp endTimeInTimestamp = resultSet.getTimestamp("endTime");
                /*
                    if(endTimeInTimestamp == null) {
                        endTime = null;
                    } else {
                        endTime = endTimeInTimestamp.toLocalDateTime();
                    }
                */
                LocalDateTime endTime = endTimeInTimestamp == null ? null : endTimeInTimestamp.toLocalDateTime();
                int isComplete = resultSet.getInt("isComplete");
                int isActive = resultSet.getInt("isActive");
                return new Game(gameID, targetNumber, username, startTime, endTime, isComplete, isActive);
            }
            return null;
        }));
    }

    public void finishGame(Game game) {
        executeUpdate((connection -> {
            String sql = "UPDATE game SET isComplete = ?, isActive=?, endTime=?" +
                    " where gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setInt(i++, game.getIsComplete());
            statement.setInt(i++, game.getIsActive());
            statement.setTimestamp(i++, Timestamp.valueOf(game.getEndTime()));
            statement.setString(i++, game.getId());
            statement.executeUpdate();
            return null;
        }));
    }
}
