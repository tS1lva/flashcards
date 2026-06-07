package com.tiago.flashcards.service;

import lombok.Getter;
import lombok.Setter;

public class FlashCard {
    @Getter
    @Setter
    private int score;

    @Getter
    @Setter
    private int interval;

    @Getter
    @Setter
    private int repetition;

    @Getter
    @Setter
    private double difficult;

    public FlashCard(int score, int interval, int repetition, double difficult) {
        this.score = score;
        this.interval = interval;
        this.repetition = repetition;
        this.difficult = difficult;
    }

    public void calculateInterval (int score, int repetition) {
        if (score >= 3) {

            if (repetition ==0) {
                this.interval = 1;
            }
            else if (repetition == 1) {
                this.interval = 6;
            }
            else if (repetition >= 3) {
                this.calculateDifficult(score);
                this.interval = (int) (this.interval * this.difficult);
            }
            this.repetition++;

        } else {
            this.repetition = 0;
            this.interval = 1;
        }
    }

    public void calculateDifficult (int score) {
        double newDificult;

        newDificult = this.difficult + (0.1 - (5 - score) * (0.08 + (5 - score) * 0.02));

        if (newDificult < 1.3) {
            this.difficult = 1.3;
        } else {
            this.difficult = newDificult;
        }
    }


}
