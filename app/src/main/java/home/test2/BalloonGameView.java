package home.test2;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by christal on 3/22/2016.
 */

public class BalloonGameView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;
    private BalloonGameThread thread;
    private BalloonBackground background;
    private ArrayList<Balloon> balloons;
    private long balloonStartTime;
    private Random rand = new Random();
    private int currentQuestion = 0;


    List<Question> data = Data.getInstance().getData();
    int[] lessonQuestions = new int[10];
    String section = LessonMenu.currentLesson;
    private boolean dontdrawplz;

    public BalloonGameView(Context context){
        super(context);
        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        thread = new BalloonGameThread(getHolder(), this);
        //make view focusable to handle events
        setFocusable(true);
        setQuestions();
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
        background = new BalloonBackground(BitmapFactory.decodeResource(getResources(), R.drawable.eightbitsky));
        background.setVector(-5);
        balloons = new ArrayList<Balloon>();
        balloonStartTime = System.nanoTime();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.dontdrawplz = true;
        currentQuestion++;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                currentQuestion++;
        }
        for(Balloon b: balloons){
           if(event.getX() > b.getX() && event.getX() < b.getX() + 560 && event.getY() > b.getY() && event.getY() < b.getY() + 880) {
               Log.i("tag", "Clicked on balloon " + b.getX() + ", " + b.getY());
           }
        }
        return super.onTouchEvent(event);
    }

    public void update(){
        background.update();
        /*add balloons over time interval*/
        long balloonElapsed = (System.nanoTime()-balloonStartTime)/1000000;
        if(balloonElapsed > 2500) {

                balloons.add(new Balloon(BitmapFactory.decodeResource(getResources(), R.drawable.balloon), "4",
                        (int) (rand.nextDouble() * (WIDTH)), HEIGHT, 1));

            /*reset timer*/
            balloonStartTime = System.nanoTime();
        }

        /*loop through balloons*/
        for(int i = 0; i < balloons.size(); i++){
            balloons.get(i).update();
        }
    }


    @Override
    public void draw(Canvas canvas){

        Paint rectPaint = new Paint();
        final float scaleX = getWidth() / WIDTH;
        final float scaleY = getHeight() / HEIGHT;
        if (canvas != null) {
            final int original = canvas.save();
            canvas.scale(scaleX, scaleY);
            super.draw(canvas);
            background.draw(canvas);
            canvas.restoreToCount(original);
        }
        for(Balloon b: balloons){
            b.draw(canvas);
        }
        rectPaint.setARGB(128, 255, 0, 0);
        canvas.drawRect(getLeft(), getTop()/4, getRight(), getBottom()/4, rectPaint);
        drawText(canvas);
    }
    public void drawText(Canvas canvas)
    {
        Paint textPaint = new Paint();
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / (8)) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(100);
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
        canvas.drawText(showQuestion(), xPos, yPos, textPaint);

    }

    public String showQuestion(){
        return data.get(lessonQuestions[currentQuestion]).getProblem().toString();
    }

    /*add questions similarly to QuizActivity*/
    public void setQuestions(){
        Random r = new Random();
        int[] alreadyChosen = new int[10];
        int hold;
        int i = 0;
        boolean safe = true;

        while(i<=9) {
            hold = r.nextInt(64);
            safe = true;

            for (int j = 0; j <= 9; j++) {
                if (hold == alreadyChosen[j]) {
                    safe = false;
                }
            }
            if ((section == "Linear Equations") && hold >= 0 && hold <= 14){
                lessonQuestions[i] = hold; alreadyChosen[i] = hold;
                i++;
            }
            else if ((section == "Classification of Angles") && hold >= 35 && hold <= 44){
                lessonQuestions[i] = hold; alreadyChosen[i] = hold;
                i++;
            }
        }
    }
}
