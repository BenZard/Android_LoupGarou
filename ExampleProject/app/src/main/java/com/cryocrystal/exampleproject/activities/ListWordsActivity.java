package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
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
import com.cryocrystal.exampleproject.adapters.WordsAdapter;
import com.cryocrystal.exampleproject.models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoit on 20/05/2016.
 */
public class ListWordsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ListViewCompat wordListView;
    private EditText wordEditText;
    private Button wordAddButton;
    private WordsAdapter wordsAdapter;
    private CheckBox editModeCheckbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_words);

        wordListView = (ListViewCompat) findViewById(R.id.word_list_view);
        wordEditText = (EditText) findViewById(R.id.word_edit_text);
        wordAddButton = (Button) findViewById(R.id.button_add_word);
        editModeCheckbox = (CheckBox) findViewById(R.id.edition_checkbox);

        if (wordAddButton != null) {
            wordAddButton.setOnClickListener(this);
        }

        if (editModeCheckbox != null) {
            editModeCheckbox.setOnCheckedChangeListener(this);
        }

        wordsAdapter = new WordsAdapter(this);
        wordListView.setAdapter(wordsAdapter);
        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = wordsAdapter.getItem(position);
                Toast.makeText(ListWordsActivity.this, "Player clicked : " + word.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String playerName = wordEditText.getText().toString();
        if (!playerName.isEmpty()){
            wordsAdapter.add(new Word(playerName));
            wordEditText.getText().clear();
            if (wordsAdapter.getCount() == 4){
                Button play = (Button) findViewById(R.id.btnLaunchGame);
                play.setVisibility(View.VISIBLE);
            }
        }
        List<Word> players = new ArrayList<>();
        for (int i = 0; i < wordsAdapter.getCount(); i++){
            players.add(wordsAdapter.getItem(i));
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Button play = (Button) findViewById(R.id.btnLaunchGame);
        play.setVisibility(View.INVISIBLE);
        wordsAdapter.setEditMode(isChecked);
        if ((wordsAdapter.getCount() >= 4)&&(isChecked == false)){

            play.setVisibility(View.VISIBLE);
        }
    }

    public void launchGame(View v){
        Intent exampleActivityIntent = new Intent(this, GameActivity.class);
        startActivity(exampleActivityIntent);
    }

}
