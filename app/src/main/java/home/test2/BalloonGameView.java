package home.test2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Vibrator;
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
    private Activity activity;
    private BalloonGameThread thread;
    private BalloonBackground background;
    private ArrayList<Balloon> balloons;
    private long balloonStartTime;
    private Random rand = new Random();
    private int currentQuestion = 0;
    private String[] possible = new String[20];
    private String[] anglesPossible = {"Acute", "Straight", "Obtuse", "Right"};
    private Context context;
    private int strikes = 0;
    private boolean incorrect = false;
    private int numCorrect = 0;
    List<Question> data = Data.getInstance().getData();
    int[] lessonQuestions = new int[10];
    String section = LessonMenu.currentLesson;
    private boolean readyToClose;

    public BalloonGameView(Context context){
        super(context);
        this.context = context;
        this.activity = ((BalloonGame)getContext());

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
        if(readyToClose) {
            this.close();
            Log.i("tag", "Close balloon game");
            return super.onTouchEvent(event);
        }
        //click close button at the bottom
        if(event.getY() > this.getHeight() - 140 && event.getX() > this.getWidth() / 2 - 210 && event.getX() < this.getWidth() / 2 + 100) {

            this.close();
            return super.onTouchEvent(event);

        }
            for(Balloon b: balloons) {
                if (event.getX() > b.getX() && event.getX() < b.getX() + 560 && event.getY() > b.getY() && event.getY() < b.getY() + 880) {

                    if (currentQuestion <= 10 && b.getAnswer().equals(data.get(lessonQuestions[currentQuestion]).getAnswer())) {
                        Log.i("tag", "CORRECT");
                        numCorrect++;
                        balloons.clear();
                        strikes = 0;
                        if(currentQuestion < 9) {
                            currentQuestion++;
                        }
                        else {
                            readyToClose = true;
                        }
                    }
                    else {
                        if(strikes < 2){
                            strikes ++;
                        }
                        else if(currentQuestion < 9){
                            balloons.clear();
                            currentQuestion++;
                            strikes = 0;
                        }
                        else {
                            readyToClose = true;
                        }
                        incorrect = true;
                        try{
                        Vibrator youralose = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        youralose.vibrate(300);}
                        catch(Exception e){

                        }
                    }
                    Log.i("tag", "Clicked on balloon " + b.getX() + ", " + b.getY());
                    return super.onTouchEvent(event);
                }


        }
        return super.onTouchEvent(event);
    }

    public void update(Canvas canvas){
        background.update(canvas);
        /*add balloons over time interval*/
        long balloonElapsed = (System.nanoTime()-balloonStartTime)/1000000;
        if(balloonElapsed > 2500) {
            Bitmap balloon = BitmapFactory.decodeResource(getResources(), R.drawable.balloon);
                balloons.add(new Balloon(balloon, generateRandomBalloon(),
                        (int) (rand.nextDouble() * (canvas.getWidth()-balloon.getWidth())), canvas.getHeight(), 1));

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
            final float scaleX = getWidth() / canvas.getWidth();
            final float scaleY = getHeight() /canvas.getHeight();
            final int original = canvas.save();
            canvas.scale(scaleX, scaleY);
            super.draw(canvas);
            canvas.restoreToCount(original);
            background.draw(canvas);
            for(Balloon b: balloons){
                b.draw(canvas);
            }
            if(incorrect){
                incorrect = false;
            }
            drawText(canvas);
            drawStrikes(canvas);
    }
    public void drawStrikes(Canvas canvas){
        Paint strikePaint = new Paint();
        strikePaint.setColor(Color.RED);
        strikePaint.setTextSize(150);
        int xPos = 0;
        int yPos = canvas.getHeight();
        if(strikes == 1){
            canvas.drawText("X", xPos, yPos, strikePaint);
        }
        else if(strikes == 2){
            canvas.drawText("XX", xPos, yPos, strikePaint);
        }
    }
    public void drawText(Canvas canvas)
    {
        Paint textPaint = new Paint();
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / (8)) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        if(section.equals("Linear Equations")){
            textPaint.setTextSize(100);
        }
        else {
            textPaint.setTextSize(65);
        }
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));

       if(readyToClose){
            canvas.drawText("You got " + numCorrect + " out of 10 correct!", xPos, yPos, textPaint);
           if(section.equals("Linear Equations")){
               canvas.drawText("Click anywhere to quit.", xPos, yPos + 100, textPaint);
           }
           else {
               canvas.drawText("Click anywhere to quit.", xPos, yPos + 65, textPaint);
           }

        }
        else {
           String[] lines = showQuestion().split("/n");
           canvas.drawText(lines[0], xPos, yPos, textPaint);
           if(lines.length > 1) {
               canvas.drawText(lines[1], xPos, yPos + 100, textPaint);
           }
       }
        textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(100);
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
        canvas.drawText("Close", canvas.getWidth() / 2, canvas.getHeight() - 10, textPaint);

    }

    public void close()
    {
        activity.finish();
    }

    public String showQuestion(){
        return data.get(lessonQuestions[currentQuestion]).getProblem().toString();
    }

    public String generateRandomBalloon(){
        String answer = data.get(lessonQuestions[currentQuestion]).getAnswer();
        int random = 0;
        if(section.equals("Linear Equations")) {
            int thisAnswer = Integer.parseInt(answer);
            int lower = thisAnswer - 5;
            int upper = thisAnswer + 5;
            random = rand.nextInt((upper-lower) + 1) + lower;
            return Integer.toString(random);
        }
        else{
            int lower = 0;
            int upper = anglesPossible.length;
            return anglesPossible[rand.nextInt(upper)];
        }
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
            if ((section.equals("Linear Equations")) && hold >= 0 && hold <= 14){
                lessonQuestions[i] = hold; alreadyChosen[i] = hold;
                i++;
            }
            else if ((section.equals("Classification of Angles")) && hold >= 35 && hold <= 44){
                lessonQuestions[i] = hold; alreadyChosen[i] = hold;
                i++;
            }
        }
    }
}
