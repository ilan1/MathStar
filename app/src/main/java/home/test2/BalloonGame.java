package home.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaPlayer;

public class BalloonGame extends AppCompatActivity {

    MediaPlayer backgroundtheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_balloon_game);
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
