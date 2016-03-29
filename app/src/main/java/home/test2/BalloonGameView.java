package home.test2;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;
/**
 * Created by christal on 3/22/2016.
 */

public class BalloonGameView extends SurfaceView implements SurfaceHolder.Callback {
    private BalloonGameThread thread;
    public BalloonGameView(Context context){

        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        thread = new BalloonGameThread(getHolder(), this);

        //make view focusable to handle events
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        //start game loop
        thread.setRunning(true);
        thread.start();
    }

    public boolean onTouchEven(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void update(){

    }
}
