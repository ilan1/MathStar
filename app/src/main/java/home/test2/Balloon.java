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
        speed = 50;
        Bitmap[] balloon = new Bitmap[numFrames];
        image = res;

        for(int i = 0; i<balloon.length;i++)
        {
            balloon[i] = Bitmap.createBitmap(image, 0, i*height, width, height);
        }

        animation.setFrames(balloon);
        animation.setDelay(100-speed);
    }
    public void update(){
        x-=speed;
        animation.update();
    }
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }
    public int getWidth()
    {
        return width-10;
    }
}
