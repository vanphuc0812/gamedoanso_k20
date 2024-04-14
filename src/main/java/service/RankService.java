package service;

import model.Game;
import repository.RankingRepository;

import java.util.List;

public class RankService {
    RankingRepository repository = new RankingRepository();

    public List<Game> getTop(int top) {
        return repository.getTop(top);
    }
}
