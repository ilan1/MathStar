package home.test2;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by christal on 4/9/2016.
 */
public class Balloon extends GameObject {
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap image;

    public Balloon(Bitmap res, int x, int y, int w, int h, int numFrames)
    {
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        speed = 10;
        /*sprite array*/
        Bitmap[] balloon = new Bitmap[numFrames];
        image = res;

        /*make bitmap image array(if using spritesheet) */
        for(int i = 0; i < balloon.length;i++)
        {
            balloon[i] = Bitmap.createBitmap(image, 0, i*height, width, height);
        }

        /*array to animation class*/
        animation.setFrames(balloon);
        animation.setDelay(1000);
    }
    public void update(){
        y-=speed;
        animation.update();
    }
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }
   /* @Override
    public int getWidth()
    {
        return width-10;
    }*/
}
