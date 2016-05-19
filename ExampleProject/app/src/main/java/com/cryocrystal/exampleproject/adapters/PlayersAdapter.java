package com.cryocrystal.exampleproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.activities.ListPlayersActivity;
import com.cryocrystal.exampleproject.models.Player;
import com.cryocrystal.exampleproject.models.Team;

public class PlayersAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.item_player;
    private boolean inEditMode = false;

    public PlayersAdapter(Context context) {
        super(context, layoutResource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        final Player player = getItem(position);
        TextView playerName = (TextView) convertView.findViewById(R.id.player_name_text_view);
        playerName.setText(player.getName());

        final ImageView teamImage = (ImageView) convertView.findViewById(R.id.team_image_view);
        teamImage.setImageResource(player.getTeam().getDrawableRes());

        final Spinner team = (Spinner) convertView.findViewById(R.id.player_team_spinner);
        String teams[] = new String[] {"Bleu","Jaune","Neutre"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, teams); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        team.setAdapter(spinnerArrayAdapter);
        team.setSelection(2);

        team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (team.getSelectedItem().toString()){
                    case "Bleu" :
                        player.setTeam(Team.BLUE);
                        teamImage.setImageResource(R.drawable.blue);
                        break;
                    case "Jaune" :
                        player.setTeam(Team.YELLOW);
                        teamImage.setImageResource(R.drawable.yellow);
                        break;
                    case "Neutre" :
                        player.setTeam(Team.NEUTRE);
                        teamImage.setImageResource(R.drawable.neutral);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Button buttonDelete = (Button) convertView.findViewById(R.id.delete_player_button);
        buttonDelete.setVisibility(inEditMode ? View.VISIBLE : View.GONE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(player);
            }
        });

        return convertView;
    }

    public void setEditMode(boolean inEditMode) {
        this.inEditMode = inEditMode;
        notifyDataSetChanged();
    }
}
