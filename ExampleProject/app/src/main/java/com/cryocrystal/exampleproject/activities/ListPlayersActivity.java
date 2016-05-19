package com.cryocrystal.exampleproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.adapters.PlayersAdapter;
import com.cryocrystal.exampleproject.models.Player;

public class ListPlayersActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ListViewCompat playerListView;
    private EditText playerEditText;
    private Button playerAddButton;
    private PlayersAdapter playersAdapter;
    private CheckBox editModeCheckbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_players);

        playerListView = (ListViewCompat) findViewById(R.id.player_list_view);
        playerEditText = (EditText) findViewById(R.id.player_edit_text);
        playerAddButton = (Button) findViewById(R.id.button_add_player);
        editModeCheckbox = (CheckBox) findViewById(R.id.edition_checkbox);

        if (playerAddButton != null){
            playerAddButton.setOnClickListener(this);
        }

        if (editModeCheckbox != null){
            editModeCheckbox.setOnCheckedChangeListener(this);
        }

        playersAdapter = new PlayersAdapter(this);
        playerListView.setAdapter(playersAdapter);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = playersAdapter.getItem(position);
                Toast.makeText(ListPlayersActivity.this, "Player clicked : " + player.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String playerName = playerEditText.getText().toString();
        if (!playerName.isEmpty()){
            playersAdapter.add(new Player(playerName));
            playerEditText.getText().clear();
            if (playersAdapter.getCount() == 4){
                Button play = (Button) findViewById(R.id.btnPlay);
                play.setVisibility(View.VISIBLE);
            }
        }

        for (int i = 0; i < playersAdapter.getCount(); i++){
            playersAdapter.getItem(i);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        playersAdapter.setEditMode(isChecked);
    }


}
