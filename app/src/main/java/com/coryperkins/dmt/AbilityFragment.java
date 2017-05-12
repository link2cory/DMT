package com.coryperkins.dmt;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cory on 5/9/2017.
 */

public class AbilityFragment extends Fragment {
    boolean tweakable_scores = true;
    private static final String TAG = "AbilityFragment: ";

    AbilityArrayListAdapter abilityArrayListAdapter;
    CharacterCommunicator characterCommunicator;

    public interface CharacterCommunicator {

        ArrayList<Ability> getCharacterAbilities();
        void rollCharacterAbilities();
        void incrementCharacterAbilityScore(int index);
        void decrementCharacterAbilityScore(int index);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            characterCommunicator = (CharacterCommunicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement characterCommunicator");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ability_fragment, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        characterCommunicator.rollCharacterAbilities();

        abilityArrayListAdapter = new AbilityArrayListAdapter(
                getActivity(),
                characterCommunicator.getCharacterAbilities()
        );

        Log.v(TAG, "Done rolling abilities");

        ListView abilityListView = (ListView) getView().findViewById(R.id.ability_list);
        abilityListView.setAdapter(abilityArrayListAdapter);
    }

    public void updateAbilities() {
        // abilityTableDataAdapter.notifyDataSetChanged();
        abilityArrayListAdapter.notifyDataSetChanged();
    }

    class AbilityArrayListAdapter extends ArrayAdapter<Ability> {

        public AbilityArrayListAdapter(Context context, ArrayList<Ability> abilityList) {
            super(context, 0, abilityList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Ability ability = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ability_row, parent, false);
            }

            TextView abilityName = (TextView) convertView.findViewById(R.id.ability_name);
            TextView abilityScore = (TextView) convertView.findViewById(R.id.ability_score);
            TextView abilityBonus = (TextView) convertView.findViewById(R.id.ability_bonus);

            abilityName.setText(ability.getName());
            abilityScore.setText(Integer.toString(ability.getScore()));

            String bonus = Integer.toString(ability.getBonus());
            if (ability.getBonus() > 0) {
                bonus = '+' + bonus;
            }

            abilityBonus.setText(bonus);

            if (tweakable_scores) {
                View tweaker = convertView.findViewById(R.id.ability_tweaker_container);
                tweaker.setVisibility(View.VISIBLE);

                tweaker.findViewById(R.id.increment_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        characterCommunicator.incrementCharacterAbilityScore((int)v.getTag());
                        abilityArrayListAdapter.notifyDataSetChanged();
                    }
                });

                tweaker.findViewById(R.id.decrement_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        characterCommunicator.decrementCharacterAbilityScore((int)v.getTag());
                        abilityArrayListAdapter.notifyDataSetChanged();
                    }
                });

                tweaker.findViewById(R.id.increment_button).setTag(position);
                tweaker.findViewById(R.id.decrement_button).setTag(position);
            }

            return convertView;
        }
    }
}
