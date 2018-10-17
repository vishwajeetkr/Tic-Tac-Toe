package com.gamesbyvj.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void twoPlayerGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("numberOfPlayers", "two");
        startActivity(intent);
    }

    public void onePlayerGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("numberOfPlayers", "one");
        startActivity(intent);
    }
}
