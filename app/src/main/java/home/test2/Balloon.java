package home.test2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Random;

/**
 * Created by christal on 4/9/2016.
 */
public class Balloon extends GameObject {
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap image;
    private String answer;

    public Balloon(Bitmap res, String answer, int x, int y, int numFrames)
    {
        this.answer = answer;
        super.x = x;
        super.y = y;
        speed = 10;
        /*sprite array*/
        Bitmap[] balloon = new Bitmap[numFrames];
        image = res;

        balloon[0] = res;//Bitmap.createBitmap(image, 0, 0, width, height);

Log.i("tag", res.getHeight() + " " + res.getWidth());
        /*array to animation class*/
        animation.setFrames(balloon);
//        animation.setFrames
        animation.setDelay(1000);
    }
    public void update(){
        y-=speed;
        animation.update();
    }
    public void draw(Canvas canvas){
        try{
            Paint answerPaint = new Paint();
            answerPaint.setTextAlign(Paint.Align.CENTER);
            answerPaint.setColor(Color.BLACK);
            answerPaint.setTextSize(100);
            answerPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
            canvas.drawBitmap(animation.getImage(),x,y,null);
            canvas.drawText("4", x + 280, y + 440, answerPaint);
        }catch(Exception e){}
    }

    public String getAnswer(){
        return this.answer;
    }
}
