package home.test2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerGame extends AppCompatActivity {

    TextView timerview;
     Timer timer;
    int interval;
    int secs = 1000;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        timer = new Timer();
        interval = secs;
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = interval;
            public void run() {
                System.out.println(i--);
                if (i< 0)
                {
                    timerview.setText("Sorry your time is up and your score is: " +  score);
                    timer.cancel();


                }
            }
        }, 0, 1000);
    }

}
