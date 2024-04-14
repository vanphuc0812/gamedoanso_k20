package repository;

import model.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RankingRepository extends AbtractRepository<Game> {
    public List<Game> getTop(int top) {
        return executeQueryList((connection -> {
            List<Game> listRank = new ArrayList<>();
            String sql = "SELECT " +
                    "TIMESTAMPDIFF(MICROSECOND,startTime, endTime) as playtime, " +
                    "g.* " +
                    "FROM game g " +
                    "WHERE startTime is not null and endTime is not null " +
                    "ORDER BY playtime " +
                    "LIMIT ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gameID = resultSet.getString("gameID");
                int targetNumber = resultSet.getInt("targetNumber");
                String username = resultSet.getString("username");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                int playTime = resultSet.getInt("playtime");
                listRank.add(new Game(gameID, targetNumber, username, startTime, endTime, playTime));
            }
            return listRank;
        }));
    }
}
