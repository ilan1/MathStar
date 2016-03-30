package home.test2;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;
/**
 * Created by christal on 3/22/2016.
 */

public class BalloonGameView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 1131;
    public static final int HEIGHT = 707;
    private BalloonGameThread thread;
    private BalloonBackground background;

    public BalloonGameView(Context context){
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        thread = new BalloonGameThread(getHolder(), this);

        //make view focusable to handle events
        setFocusable(true);

    }

    @Override
    /*balloons*/
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
        background = new BalloonBackground(BitmapFactory.decodeResource(getResources(), R.drawable.mm2));
        background.setVector(-5);
        thread.setRunning(true);
        thread.start();
    }

    public boolean onTouchEven(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void update(){
        background.update();
    }

    @Override
    public void draw(Canvas canvas){

        final float scaleX = getWidth()/WIDTH;
        final float scaleY = getHeight()/HEIGHT;
        if(canvas!=null){
            final int original = canvas.save();
            canvas.scale(scaleX, scaleY);
            super.draw(canvas);
            background.draw(canvas);
            canvas.restoreToCount(original);
        }
    }
}
