package com.engcria.ruifma_mobile.rotinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.engcria.ruifma_mobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        navBarColor(R.color.layout);
        nextWind();
    }


    public void nextWind(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginScreen = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginScreen);
                finish();
            }
        }, 2000);
    }
    public void navBarColor(int color){
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, color));
    }
}