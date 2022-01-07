package it.emanuelemelini.copypasta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import it.emanuelemelini.copypasta.R;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Integer post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        post = 1000;

        handler.postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }, post);

    }
}