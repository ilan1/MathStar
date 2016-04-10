package home.test2;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by christal on 3/22/2016.
 */

public class BalloonGameView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 672; //672
    public static final int HEIGHT = 1600; //1600
    private BalloonGameThread thread;
    private BalloonBackground background;
    private ArrayList<Balloon> balloons;
    private long balloonStartTime;
    private Balloon balloon; //test
    private Random rand = new Random();


    List<Question> data = Data.getInstance().getData();
    int[] lessonQuestions = new int[10];
    String section = LessonMenu.currentLesson;

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
        background = new BalloonBackground(BitmapFactory.decodeResource(getResources(), R.drawable.sky));
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
        drawText(canvas);
    }
    public void drawText(Canvas canvas)
    {

        Paint textPaint = new Paint();
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(100);
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
        canvas.drawText(showQuestion(), xPos, yPos , textPaint);
    }

    public String showQuestion(){
        return data.get(lessonQuestions[0]).getProblem().toString();
    }
    public void nextQuestion(){
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
