package repository;

import model.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthRepository extends AbtractRepository<Player> {

    public Player getPlayerByUsername(String pUsername) {
        return executeQuery((connection -> {
            String sql = "SELECT * FROM player WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Player player = new Player();
                player.setUsername(resultSet.getString("username"));
                player.setPassword(resultSet.getString("password"));
                return player;
            }
            return null;
        }));
    }

    public void save(Player newPlayer) {
        executeUpdate((connection -> {
            String sql = "INSERT INTO player (username, password) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPlayer.getUsername());
            preparedStatement.setString(2, newPlayer.getPassword());
            preparedStatement.executeUpdate();
            return null;
        }));

    }
}
