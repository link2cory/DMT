package com.coryperkins.dmt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CharacterGenerator extends AppCompatActivity implements AbilityFragment.CharacterCommunicator {
    private Character character = new Character();
    AbilityFragment abilityFragment;
    private static final String TAG = "CharacterGenerator: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_generator);

        if (findViewById(R.id.abilities_fragment_container) != null && savedInstanceState == null) {
            abilityFragment = new AbilityFragment();

            getFragmentManager().beginTransaction().add(R.id.abilities_fragment_container, abilityFragment).commit();
        }
    }

    public void rerollButtonListener(View view) {
        rollCharacterAbilities();
        abilityFragment.updateAbilities();
    }

    public ArrayList<Ability> getCharacterAbilities() {
        return character.abilities;
    }

    public void rollCharacterAbilities() {
        character.rollAbilities();
    }

    public void incrementCharacterAbilityScore(int index) {
        character.incrementAbilityScore(index);
    }

    public void decrementCharacterAbilityScore(int index) {
        character.decrementAbilityScore(index);
    }
}
