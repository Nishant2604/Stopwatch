package com.example.nishant.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import java.util.Timer;


public class StopwatchActivity extends AppCompatActivity {

    int seconds = 0;
    boolean isRunning = false;
    boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }

        runTimer();
    }

    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onStop(){
        super.onStop();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public  void onStart(){
        super.onStart();
        if(wasRunning){
            isRunning = true;
        }
    }

    public void onClickStart(View view){
        isRunning = true;
    }

    public void onClickStop(View view){
        isRunning = false;
    }

    public void onClickReset(View view){
        isRunning = false;
        seconds = 0;
    }

    public void runTimer(){

        final TextView textView = (TextView) findViewById(R.id.timer);
        final Handler handler = new Handler(getApplicationContext().getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds % 3600)/60;
                int sec = seconds % 60;

                String time = String.format("%d:%02d:%02d",hours,minutes,sec);
                textView.setText(time);

                if(isRunning){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}
