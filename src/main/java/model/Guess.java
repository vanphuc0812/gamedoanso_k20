package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Guess {
    private int guessNumber;
    private String gameID;

    /*  1 -> higher than target
        -1 -> lower than target
        0 -> correct
    */
    private int guessResult;
    private LocalDateTime guessTime;

    public Guess(int guessNumber, String gameID, int guessResult) {
        this.guessNumber = guessNumber;
        this.gameID = gameID;
        this.guessResult = guessResult;
        this.guessTime = LocalDateTime.now();
    }

    public Guess(int guessNumber, String gameID, int guessResult, LocalDateTime guessTime) {
        this.guessNumber = guessNumber;
        this.gameID = gameID;
        this.guessResult = guessResult;
        this.guessTime = guessTime;
    }
}

