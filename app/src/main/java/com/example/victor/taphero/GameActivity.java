package com.example.victor.taphero;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private Button button;
    private Button finish;
    private TextView time_left;
    private TextView score;
    private int currentScore = 0;
    private int ROUND_IN_MILLIS = 10000;
    private int ROUND_COUNTDOWN = 5000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        finish = findViewById(R.id.finish);
        editor = getSharedPreferences("scores", MODE_PRIVATE).edit();

        // Finish the game and crew new Round object
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add score to the scoreboard
                editor.putInt("previous_score", currentScore);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        score = findViewById(R.id.score);
        time_left = findViewById(R.id.time_left);
        time_left.setText("Time left: " + ROUND_IN_MILLIS / 1000);
        score.setText("");

        button = findViewById(R.id.imageButton);
        button.setEnabled(false);
        button.setClickable(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++currentScore;
                score.setText("Score: " + currentScore);
            }
        });

        // Countdown to round start
        new CountDownTimer(ROUND_COUNTDOWN, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left.setText("Round starts in: " + (millisUntilFinished - 1000) / 1000);
            }

            @Override
            public void onFinish() {
                button.setClickable(true);
                button.setEnabled(true);

                // A new round of x seconds
                new CountDownTimer(ROUND_IN_MILLIS, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        time_left.setText("Time left: " + (millisUntilFinished) / 1000 + "." + (millisUntilFinished % 1000) / 100);
                    }

                    @Override
                    public void onFinish() {
                        button.setClickable(false);
                        button.setEnabled(false);
                        time_left.setText("Time's up!");
                        score.setText("Final score: " + currentScore);
                        finish.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        }.start();

    }




    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
