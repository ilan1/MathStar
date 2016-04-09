package home.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaPlayer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BalloonGame extends AppCompatActivity {

    int[] quiznums = new int[10];
    MediaPlayer backgroundtheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new BalloonGameView(this));
        backgroundtheme = MediaPlayer.create(BalloonGame.this, R.raw.balloon_blast);
        backgroundtheme.setLooping(true);
        backgroundtheme.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        backgroundtheme.release();
        finish();
    }
}
