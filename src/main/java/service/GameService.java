package service;

import model.Game;
import model.Guess;
import repository.GameRepository;
import repository.GuessRepository;

import java.time.LocalDateTime;
import java.util.List;

public class GameService {
    GameRepository gameRepository = new GameRepository();
    GuessRepository guessRepository = new GuessRepository();

    public Game createGame(String username) {
        gameRepository.deactiveAllGame(username);
        Game newGame = new Game(username);
        gameRepository.save(newGame);
        return newGame;
    }

    public Game loadGame(String username, String gameID) {
        Game game;
        if (gameID != null && !gameID.isEmpty()) {
            game = gameRepository.loadGameByGameID(gameID);
            if (game != null) {
                gameRepository.deactiveAllGame(username);
                gameRepository.activeGameByGameID(game.getId());
            }
        } else {
            game = gameRepository.loadActiveGameByUsername(username);
        }
        if (game == null) return createGame(username);
        else {
            List<Guess> oldGuessList = guessRepository.getGuessListByGameID(game.getId());
            game.getGuessList().addAll(oldGuessList);
        }
        return game;
    }

    public Game processGame(String gameID, int guessNumber) {
        int guessResult;
        Game game = gameRepository.loadGameByGameID(gameID);

        List<Guess> oldGuessList = guessRepository.getGuessListByGameID(gameID);
        game.getGuessList().addAll(oldGuessList);
        if (game.getIsComplete() == 1) return game;

        if (guessNumber > game.getTargetNumber()) {
            guessResult = 1;
        } else if (guessNumber < game.getTargetNumber()) {
            guessResult = -1;
        } else {
            guessResult = 0;
            game.setEndTime(LocalDateTime.now());
            game.setIsComplete(1);
            game.setIsActive(0);
            gameRepository.finishGame(game);
        }

        Guess guess = new Guess(guessNumber, gameID, guessResult);
        guessRepository.save(guess);
        game.getGuessList().add(guess);
        return game;
    }

    public List<Game> loadAllGameByUsername(String username) {
        return gameRepository.loadAllGameByUsername(username);
    }
}
