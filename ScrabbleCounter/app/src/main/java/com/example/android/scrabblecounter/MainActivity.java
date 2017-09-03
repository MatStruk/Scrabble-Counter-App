package com.example.android.scrabblecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.android.scrabblecounter.R.string.too_low_value;

public class MainActivity extends AppCompatActivity {
    int user1score = 0;
    int user2score = 0;
    int valueBeforeAdd = 0;
    public int seconds = 0;
    public int minutes = 0;
    TextView timer;
    Timer countDownTimer;
    int invisibleTimer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // display timer when this Activity is opened
        Timer();
        timer = (TextView) findViewById(R.id.timer);

        // don't show keyboard when activity opens
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    // add +1 to value
    public void addPlusOne(View v) {
        valueBeforeAdd = valueBeforeAdd + 1;
        updateResults();
    }

    // subtract 1 to value
    public void addMinusOne(View v) {
        valueBeforeAdd = valueBeforeAdd - 1;
        updateResults();
    }

    // add +5 to value
    public void addPlusFive(View v) {
        valueBeforeAdd = valueBeforeAdd + 5;
        updateResults();
    }

    // subtract 5 to value
    public void addMinusFive(View v) {
        valueBeforeAdd = valueBeforeAdd - 5;
        updateResults();
    }

    // reset score
    public void resetValueBeforeAdd(View v) {
        valueBeforeAdd = 0;
        updateResults();
    }

    // add +10 to value
    public void addPlusTen(View v) {
        valueBeforeAdd = valueBeforeAdd + 10;
        updateResults();
    }

    // subtract 10 to value
    public void addMinusTen(View v) {
        valueBeforeAdd = valueBeforeAdd - 10;
        updateResults();
    }

    // reset both players score
    public void resetPlayersScore(View v) {
        user1score = 0;
        user2score = 0;
        updateResults();
    }

    // updates values visibly
    private void updateResults() {
        TextView text = (TextView) findViewById(R.id.texted);
        text.setText("" + valueBeforeAdd);
        TextView userOnescore = (TextView) findViewById(R.id.user1score);
        userOnescore.setText("" + user1score);
        TextView userTwoscore = (TextView) findViewById(R.id.user2score);
        userTwoscore.setText("" + user2score);
    }

    // add value to user one
    public void addValueToUserOne(View v) {
        user1score = user1score + valueBeforeAdd;
        if (user1score < 0) {
            user1score = user1score - valueBeforeAdd;
            Toast.makeText(this, too_low_value, Toast.LENGTH_LONG).show();
        }
        valueBeforeAdd = 0;
        updateResults();
    }

    public void addValueToUserTwo(View v) {
        user2score = user2score + valueBeforeAdd;
        if (user2score < 0) {
            user2score = user2score - valueBeforeAdd;
            Toast.makeText(this, too_low_value, Toast.LENGTH_LONG).show();
        }
        valueBeforeAdd = 0;
        updateResults();
    }

    public void setTimerOneMinute(View v) {
        seconds = 1;
        minutes = 1;
    }

    public void setTimerThreeMinutes(View v) {
        seconds = 1;
        minutes = 3;
    }

    public void setTimerFiveMinutes(View v) {
        seconds = 1;
        minutes = 5;
    }

    public void pauseTimer(View v) {
        invisibleTimer = 0;
    }

    public void resumeTimer(View v) {
        invisibleTimer = 1;
    }

    //timer
    public void Timer() {
        countDownTimer = new Timer();
        countDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView timer = (TextView) findViewById(R.id.timer);
                        timer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                        seconds -= 1;
                        // if 00 seconds, subtract minutes and add 59 seconds
                        if (seconds == -1) {
                            timer.setText(String.valueOf(minutes) + ":" + String.valueOf("00"));
                            seconds = 59;
                            minutes = minutes - 1;
                        }
                        // adding leading zero for seconds
                        if (seconds < 10) {
                            timer.setText(String.valueOf(minutes) + ":" + String.valueOf("0" + seconds));
                        }
                        // adding leading zero for minutes
                        if (minutes < 10) {
                            timer.setText(String.valueOf("0" + minutes) + ":" + String.valueOf(seconds));
                        }
                        // adding leading zero for minutes and seconds
                        if (minutes < 10 && seconds < 10) {
                            timer.setText(String.valueOf("0" + minutes) + ":" + String.valueOf("0" + seconds));
                        }
                        // at 00:00 it stops
                        if (minutes == -1 && seconds == 59) {
                            timer.setText(String.valueOf("00") + ":" + String.valueOf("00"));
                            seconds += 1;
                        }
                        // if stop button pressed, it stops timer
                        if (invisibleTimer == 0) {
                            seconds += 1;
                        }
                    }
                });
            }
        }, 0, 1000);
    }
}


