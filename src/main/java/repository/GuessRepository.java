package repository;

import model.Guess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GuessRepository extends AbtractRepository<Guess> {
    public void save(Guess guess) {
        executeUpdate((connection -> {
            String sql = "INSERT INTO guess (gameID,guessNumber,guessResult,guessTime)" +
                    " values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setString(i++, guess.getGameID());
            statement.setInt(i++, guess.getGuessNumber());
            statement.setInt(i++, guess.getGuessResult());
            statement.setTimestamp(i++, Timestamp.valueOf(guess.getGuessTime()));
            statement.executeUpdate();
            return null;
        }));
    }

    public List<Guess> getGuessListByGameID(String gameID) {
        return executeQueryList((connection -> {
            List<Guess> guessList = new ArrayList<>();
            String sql = "SELECT * FROM guess where gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int guessNumber = resultSet.getInt("guessNumber");
                int guessResult = resultSet.getInt("guessResult");
                Timestamp guessTimeInTimestamp = resultSet.getTimestamp("guessTime");
                LocalDateTime guessTime = guessTimeInTimestamp != null ? guessTimeInTimestamp.toLocalDateTime() : null;
                Guess guess = new Guess(guessNumber, gameID, guessResult, guessTime);
                guessList.add(guess);
            }
            return guessList;
        }));
    }
}
