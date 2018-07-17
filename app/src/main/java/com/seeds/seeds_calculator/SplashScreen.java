package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent openMainActivity = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(openMainActivity);
                finish();

            }
        }, 3000);
    }
}