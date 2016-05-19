package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cryocrystal.exampleproject.R;

/**
 * Created by Riper on 19/05/2016.
 */
public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_layout);
    }

    public void GotoListPlayers(View v){
        Intent exampleActivityIntent = new Intent(this, DisplayImageActivity.class);
        startActivity(exampleActivityIntent);
    }

}
