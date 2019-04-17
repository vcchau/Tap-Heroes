package com.example.victor.taphero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button new_game;
    private TextView previous_score;
    private Button highscores;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private ArrayList<Integer> SCORES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previous_score = findViewById(R.id.previous_score);

        sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE);

        new_game = findViewById(R.id.new_game);
        highscores = findViewById(R.id.highscores);

//        recyclerView = findViewById(R.id.recyclerview);

        // Starts a new game
        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        highscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int prev = sharedPreferences.getInt("previous_score", -1);

//        SCORES.add(prev);

//        previous_score.setText("Previous score: " + prev);

    }
}
