package com.example.lab6_51900491_caonguyenkyduyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.icu.text.Bidi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String APP_STARTUP_TIME_KEY = "APP_STARTUP_TIME";
    private TextView tvOpenTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOpenTime = findViewById(R.id.tvOpenTimes);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences myPref = getSharedPreferences("Lab6 bai 1", Activity.MODE_PRIVATE);
        int appStartupTimes = myPref.getInt(APP_STARTUP_TIME_KEY, 0);
        int increasedAppStartupTime = appStartupTimes + 1;
        SharedPreferences.Editor editor = myPref.edit();
        editor.putInt(APP_STARTUP_TIME_KEY, increasedAppStartupTime);
        editor.apply();

        tvOpenTime.setText(String.valueOf(increasedAppStartupTime));

    }

    @Override
    protected void onPause() {
        Log.d(this.getClass().getSimpleName(), "onPause");
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.d(this.getClass().getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }
}