package com.cryocrystal.exampleproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cryocrystal.exampleproject.R;

/**
 * Created by Riper on 20/05/2016.
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        TextView currentMot = (TextView) findViewById(R.id.currentMot);

        currentMot.setText("Bite à ton pére");
    }
}
