package com.coryperkins.dmt;


import java.util.ArrayList;

/**
 * Created by cory on 5/9/2017.
 */

public class Character {
    static final String STRENGTH_NAME = "strength";
    static final String STRENGTH_SHORT_NAME = "str";
    static final String DEXTERITY_NAME = "dexterity";
    static final String DEXTERITY_SHORT_NAME = "dex";
    static final String CONSTITUTION_NAME = "constitution";
    static final String CONSTITUTION_SHORT_NAME = "con";
    static final String INTELLIGENCE_NAME = "intelligence";
    static final String INTELLIGENCE_SHORT_NAME = "int";
    static final String WISDOM_NAME = "wisdom";
    static final String WISDOM_SHORT_NAME = "wis";
    static final String CHARISMA_NAME = "charisma";
    static final String CHARISMA_SHORT_NAME = "cha";

    ArrayList<Ability> abilities = new ArrayList<>();

    public Character() {
            abilities.add(new Ability(STRENGTH_NAME, STRENGTH_SHORT_NAME));
            abilities.add(new Ability(DEXTERITY_NAME, DEXTERITY_SHORT_NAME));
            abilities.add(new Ability(CONSTITUTION_NAME, CONSTITUTION_SHORT_NAME));
            abilities.add(new Ability(INTELLIGENCE_NAME, INTELLIGENCE_SHORT_NAME));
            abilities.add(new Ability(WISDOM_NAME, WISDOM_SHORT_NAME));
            abilities.add(new Ability(CHARISMA_NAME, CHARISMA_SHORT_NAME));
    }
}

