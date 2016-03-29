package home.test2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by christal on 3/29/2016.
 */
public class BalloonGameThread extends Thread {
    private int fps = 30;
    private double avgfps;
    private SurfaceHolder surfaceHolder;
    private BalloonGameView balloonGameView;
    private boolean running;
    public static Canvas canvas;

    public BalloonGameThread(SurfaceHolder surfaceHolder, BalloonGameView balloonGameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.balloonGameView = balloonGameView;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / fps;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.balloonGameView.update();
                    this.balloonGameView.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){ e.printStackTrace(); }
                }

            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;

            if(frameCount == fps){
                avgfps = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgfps);
            }
        }
    }
    public void setRunning(boolean b){
        running = b;
    }
}
