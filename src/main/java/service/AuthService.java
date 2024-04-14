package service;

import model.Player;
import repository.AuthRepository;

public class AuthService {
    private AuthRepository repository = new AuthRepository();

    public Player login(String username, String password) {
        Player player = repository.getPlayerByUsername(username);
        if (player != null && password.equals(player.getPassword())) return player;
        else return null;
    }

    public String register(String username, String password) {
        Player player = repository.getPlayerByUsername(username);
        if (player == null) {
            Player newPlayer = new Player(username, password);
            repository.save(newPlayer);
        } else {
            return "username is exist";
        }
        return null;
    }
}
