package com.example.aladin_bureau.kademiatn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class ScreenSplash extends AppCompatActivity {
ProgressBar progressBar;
    public int splash_dialog_length=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_screen_splash);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(),MenyNavigation.class);
                startActivity(intent);
            }
        },splash_dialog_length);
    }
}
