package com.cryocrystal.exampleproject.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.models.ListMots;
import com.cryocrystal.exampleproject.*;
import com.cryocrystal.exampleproject.models.Player;
import com.cryocrystal.exampleproject.models.Team;
import com.cryocrystal.exampleproject.models.Word;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Riper on 20/05/2016.
 */
public class GameActivity extends AppCompatActivity {

    public int pos;
    public int indexPlayer;
    public ArrayList<Word> mots;
    public ArrayList<Player> teamA;
    public ArrayList<Player> teamB;
    public TextView currentMot;
    public TextView timertext;
    public TextView nbMots;

    public int cpt = 30;
    public boolean timerStart = false;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        pos = 0;
        indexPlayer = 0;
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();

        timertext = (TextView) findViewById(R.id.timer);
        currentMot = (TextView) findViewById(R.id.currentMot);
        nbMots = (TextView) findViewById(R.id.nbmots);

        ListMots listMots = new ListMots();
        mots = listMots.getMots();
        currentMot.setText(mots.get(0));

        if (ListPlayersActivity.players != null) {
            for (int i = 0; i < ListPlayersActivity.players.size(); i++) {
                if (ListPlayersActivity.players.get(i).getTeam() == Team.BLUE) {
                    teamA.add(ListPlayersActivity.players.get(i));
                } else if (ListPlayersActivity.players.get(i).getTeam() == Team.YELLOW) {
                    teamB.add(ListPlayersActivity.players.get(i));
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView playerText = (TextView) findViewById(R.id.currentPlayer);
        playerText.setText(teamA.get(indexPlayer).getName());
        nbMots.setText(String.valueOf(mots.size()));
        currentMot.setText(mots.get(pos));

        ImageView img = (ImageView) findViewById(R.id.img_game);
        img.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeLeft() {
                if(timerStart) {
                    if (pos + 1 <= mots.size()) {
                        pos++;
                        currentMot.setText(mots.get(pos));
                    }
                    Toast.makeText(GameActivity.this, "Mot suivant", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(GameActivity.this, "Il faut démarrer le timer", Toast.LENGTH_LONG).show();
                }
            }

            public void onSwipeRight() {
                if(timerStart) {
                    mots.remove(pos);
                    nbMots.setText(String.valueOf(mots.size()));
                    if (pos + 1 <= mots.size()) {
                        pos++;
                        currentMot.setText(mots.get(pos));
                    }
                    Toast.makeText(GameActivity.this, "Passez le tour", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(GameActivity.this, "Il faut démarrer le timer", Toast.LENGTH_LONG).show();
                }
            }

            public void onSwipeBottom() {
            }

        });
    }

    public void timerStart(View v){
        startTimer();
        currentMot.setVisibility(View.VISIBLE);
    }


    public void startTimer() {
        timerStart = true;
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 1000); //
    }

    public void stopTimer() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timerStart = false;
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        final String strDate = simpleDateFormat.format(calendar.getTime());

                        //show the toast
                        cpt-=1;
                        timertext.setText(String.valueOf(cpt));
                        if(cpt <= 0){
                            cpt=30;
                            timertext.setText(String.valueOf(cpt));
                            currentMot.setVisibility(View.GONE);
                            stopTimer();
                        }
                    }
                });
            }

        };
    }
}