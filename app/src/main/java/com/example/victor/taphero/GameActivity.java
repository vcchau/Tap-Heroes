package com.example.victor.taphero;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Button button;
    private TextView time_left;
    private TextView score;
    private int currentScore = 0;
    private int ROUND_IN_MILLIS = 10000;
    private int ROUND_COUNTDOWN = 5000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

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
