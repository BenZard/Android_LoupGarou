package com.cryocrystal.exampleproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.models.ListMots;
import com.cryocrystal.exampleproject.*;
import com.cryocrystal.exampleproject.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Riper on 20/05/2016.
 */
public class GameActivity extends AppCompatActivity {

    public int pos;
    public ArrayList<String> mots;
    public TextView currentMot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        currentMot = (TextView) findViewById(R.id.currentMot);
        ListMots listMots = new ListMots();
        mots = listMots.getMots();
        currentMot.setText(mots.get(0));

    }

    @Override
    protected void onResume() {
        super.onResume();

        currentMot.setText(mots.get(pos));

        ImageView img = (ImageView) findViewById(R.id.img_game);
        img.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeLeft() {
                if (pos+1 <= mots.size() ){
                    pos++;
                    currentMot.setText(mots.get(pos));
                }
                Toast.makeText(GameActivity.this, "Mot suivant", Toast.LENGTH_LONG).show();
            }

            public void onSwipeRight() {
                mots.remove(pos);
                if (pos+1 <= mots.size() ){
                    pos++;
                    currentMot.setText(mots.get(pos));
                }
                Toast.makeText(GameActivity.this, "Passez le tour", Toast.LENGTH_LONG).show();
            }
            public void onSwipeBottom() {
            }

        });
    }
}
