package com.example.segundoparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class SplashMCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(validate()) {
                    startActivity(new Intent(SplashMCActivity.this, MainMCActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashMCActivity.this, WelcomeMCActivity.class));
                    finish();
                }
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 1000);
    }

    private boolean validate(){
        boolean  b= false;
        File f = new File("/data/data/com.example.segundoparcial/shared_prefs/proprietary.xml");
        if(f.exists()){
            b = true;
        }
        return b;
    }
}