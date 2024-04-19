package service;

import model.Game;
import repository.RankingRepository;

import java.util.List;

public class RankService {
    RankingRepository repository = new RankingRepository();

    public List<Game> getTopByPlayTime(int top) {
        return repository.getTopByPlayTime(top);
    }

    public List<Game> getTopByGuessTimes(int top) {
        return repository.getTopByGuessTimes(top);
    }
}
