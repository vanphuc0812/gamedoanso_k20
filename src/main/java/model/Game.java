package model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Data
public class Game {
    private Random random;
    private String id;
    private int targetNumber;
    private String username;
    private List<Guess> guessList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int isComplete;
    private int isActive;
    private int playTime;

    public Game(String username) {
        this.id = "GAME" + String.format("%04d", generateRamdomNumber(9999));
        this.username = username;
        this.targetNumber = generateRamdomNumber(1000);
        guessList = new ArrayList<>();
        this.startTime = LocalDateTime.now();
        this.isComplete = 0;
    }

    public Game(String id, int targetNumber, String username, LocalDateTime startTime, LocalDateTime endTime, int isComplete, int isActive) {
        this.id = id;
        this.targetNumber = targetNumber;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isComplete = isComplete;
        this.isActive = isActive;
        this.guessList = new ArrayList<>();
    }

    public Game(String id, int targetNumber, String username, LocalDateTime startTime, LocalDateTime endTime, int playtime) {
        this.id = id;
        this.targetNumber = targetNumber;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playTime = playtime;
        this.guessList = new ArrayList<>();
    }

    private int generateRamdomNumber(int maxValue) {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(maxValue);
    }

    public List<Guess> getReverseGuessList() {
        List<Guess> reverseList = new ArrayList<>();
        reverseList.addAll(guessList);
        Collections.reverse(reverseList);

        return reverseList;

    }
}
