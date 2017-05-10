package com.coryperkins.dmt;

import android.util.Log;

import java.util.Random;

/**
 * Created by cory on 5/9/2017.
 */

public class Ability {
    private static final String TAG = "Ability";

    static final Integer AVERAGE_ABILITY_SCORE = 10;
    static final Integer ABILITY_SCORE_DIVISOR = 2;
    static final Integer MAX_D6 = 6;
    static final Integer MIN_D6 = 1;

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

    public void roll() {
        Random rand = new Random();
        int[] rolls = new int[4];
        int score = 0;

        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rand.nextInt(MAX_D6) + MIN_D6;
            score += rolls[i];
            Log.v(TAG, name+": roll of: "+rolls[i]);
        }

        score -= min(rolls);

        putScore(score);
    }

    private int min(int[] rolls) {
        int min = rolls[0];
        Log.v(TAG, name+": original min val: "+min);
        for(int check : rolls) {
            if (check < min) {
                min = check;
            }
        }
        Log.v(TAG, name+": min val: "+min);
        return min;
    }
}
