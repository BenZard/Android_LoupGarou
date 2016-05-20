package com.cryocrystal.exampleproject.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.models.ListMots;
import com.cryocrystal.exampleproject.*;
import com.cryocrystal.exampleproject.models.Player;
import com.cryocrystal.exampleproject.models.Team;
import com.cryocrystal.exampleproject.models.Word;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    public ArrayList<Word> swipedWords;
    public ArrayList<Player> tableplayers;
    public TextView currentMot;
    public TextView timertext;
    public TextView score;
    public int scoreA = 0;
    public int scoreB = 0;

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
        tableplayers = new ArrayList<>();
        swipedWords = new ArrayList<>();

        timertext = (TextView) findViewById(R.id.timer);
        currentMot = (TextView) findViewById(R.id.currentMot);
        score = (TextView) findViewById(R.id.score);

        ListMots listMots = ListWordsActivity.wordsList;
        mots = listMots.getMots();
        currentMot.setText(mots.get(0).getName());

        if (ListPlayersActivity.players != null) {
            for (int i = 0; i < ListPlayersActivity.players.size(); i++) {
                if (ListPlayersActivity.players.get(i).getTeam() == Team.BLUE) {
                    teamA.add(ListPlayersActivity.players.get(i));
                } else if (ListPlayersActivity.players.get(i).getTeam() == Team.YELLOW) {
                    teamB.add(ListPlayersActivity.players.get(i));
                }
            }
            for (int i = 0; i < teamA.size(); i++) {
                tableplayers.add(teamA.get(i));
                tableplayers.add(teamB.get(i));
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView playerText = (TextView) findViewById(R.id.currentPlayer);
        playerText.setText(tableplayers.get(indexPlayer).getName());

        if(tableplayers.get(indexPlayer).getTeam() == Team.BLUE){
            score.setText(String.valueOf(scoreA));
        }else {
            score.setText(String.valueOf(scoreB));
        }

        currentMot.setText(mots.get(pos).getName());
        currentMot.setVisibility(View.GONE);

        ImageView img = (ImageView) findViewById(R.id.img_game);
        img.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeLeft() {
                if (timerStart) {
                    if (pos + 1 <= mots.size()) {
                        pos++;
                        currentMot.setText(mots.get(pos).getName());
                    }else {
                        pos = 0;
                        mots = swipedWords;
                    }
                    Toast.makeText(GameActivity.this, "Mot suivant", Toast.LENGTH_LONG).show();
                    if(tableplayers.get(indexPlayer).getTeam() == Team.BLUE){
                        scoreA += 1;
                        score.setText(String.valueOf(scoreA));
                    }else {
                        scoreB += 1;
                        score.setText(String.valueOf(scoreB));
                    }
                } else {
                    Toast.makeText(GameActivity.this, "Il faut démarrer le timer", Toast.LENGTH_LONG).show();
                }
            }

            public void onSwipeRight() {
                if (timerStart) {

                    swipedWords.add(mots.get(pos));
                    //mots.remove(pos);
                    if (pos + 1 <= mots.size()) {
                        pos++;
                        currentMot.setText(mots.get(pos).getName());
                    }else {
                        pos = 0;
                        mots = swipedWords;
                    }
                    Toast.makeText(GameActivity.this, "Passez le tour", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GameActivity.this, "Il faut démarrer le timer", Toast.LENGTH_LONG).show();
                }
            }

            public void onSwipeBottom() {
            }

        });
    }

    public void timerStart(View v) {
        Button timer = (Button) findViewById(R.id.starttimerbtn);
        timer.setVisibility(View.INVISIBLE);
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
        indexPlayer += 1;
        if (indexPlayer <= tableplayers.size()) {
            TextView playerText = (TextView) findViewById(R.id.currentPlayer);
            playerText.setText(tableplayers.get(indexPlayer).getName());
        }
        Button timer = (Button) findViewById(R.id.starttimerbtn);
        timer.setVisibility(View.VISIBLE);

        if(tableplayers.get(indexPlayer).getTeam() == Team.BLUE){
            score.setText(String.valueOf(scoreA));
        }else {
            score.setText(String.valueOf(scoreB));
        }
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
                        cpt -= 1;
                        timertext.setText(String.valueOf(cpt));
                        if (cpt <= 0) {
                            cpt = 30;
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