package com.coryperkins.dmt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class CharacterGenerator extends AppCompatActivity {
    Character character = new Character();
    AbilityTableDataAdapter abilityTableDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_generator);

        rollAbilityScores();
        abilityTableDataAdapter = new AbilityTableDataAdapter(this, character.abilities);

        TableView tableView = (TableView) findViewById(R.id.ability_table);
        tableView.setDataAdapter(abilityTableDataAdapter);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, R.string.ability_name, R.string.ability_score, R.string.ability_bonus));
    }

    private void rollAbilityScores() {
        for (Ability ability : character.abilities) {
            ability.roll();
        }
    }

    public void rerollButtonListener(View view) {
        rollAbilityScores();
        abilityTableDataAdapter.notifyDataSetChanged();
    }

    class AbilityTableDataAdapter extends TableDataAdapter<Ability> {
        private static final int TEXT_SIZE = 14;


        public AbilityTableDataAdapter(Context context, List<Ability> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            Ability ability = getRowData(rowIndex);
            View renderedView = null;

            switch (columnIndex) {
                case 0:
                    renderedView = renderAbilityName(ability);
                    break;
                case 1:
                    renderedView = renderAbilityScore(ability);
                    break;
                case 2:
                    renderedView = renderAbilityBonus(ability);
                    break;
                    
            }
            return renderedView;
        }

        private View renderAbilityName(Ability ability) {
            return renderString(ability.getName());
        }

        private View renderAbilityScore(Ability ability) {
            return renderString(Integer.toString(ability.getScore()));
        }

        private View renderAbilityBonus(Ability ability) {
            String bonus = Integer.toString(ability.getBonus());
            if (ability.getBonus() > 0) {
                bonus = '+'+bonus;
            }
            return renderString(bonus);
        }

        private View renderString(final String value) {
            final TextView textView = new TextView(getContext());
            textView.setText(value);
            textView.setPadding(20, 10, 20, 10);
            textView.setTextSize(TEXT_SIZE);
            return textView;
        }
    }
}
