package com.coryperkins.dmt;

import android.util.Log;

import java.util.Random;

/**
 * Created by cory on 5/9/2017.
 */

public class Ability {
    private static final String TAG = "Ability: ";

    private static final Integer AVERAGE_ABILITY_SCORE = 10;
    private static final Integer ABILITY_SCORE_DIVISOR = 2;
    private static final Integer MAX_D6 = 6;
    private static final Integer MIN_D6 = 1;

    private String name;
    private String shortName;
    private Integer score;
    private Integer bonus;

    public Ability(String abilityName, String abilityShortName) {
        name = abilityName;
        shortName = abilityShortName;
    }

    public void putScore(Integer value) {
        score = value;
        calculateBonus();
    }

    private void calculateBonus() {
        bonus = (int) Math.floor((double)(score - AVERAGE_ABILITY_SCORE) / ABILITY_SCORE_DIVISOR);
    }

    public String getName() {
        return name;
    }

    public String getShortName() { return shortName; }

    public Integer getScore() {
        return score;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void increment() {
        putScore(getScore()+1);
    }

    public void decrement() {
        putScore(getScore()-1);
    }

    public void roll() {
        Random rand = new Random();
        int[] rolls = new int[4];
        int score = 0;

        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rand.nextInt(MAX_D6) + MIN_D6;
            score += rolls[i];
        }

        score -= min(rolls);

        putScore(score);
    }

    private int min(int[] rolls) {
        int min = rolls[0];
        for(int check : rolls) {
            if (check < min) {
                min = check;
            }
        }
        return min;
    }
}
