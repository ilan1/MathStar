package home.test2;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by christal on 3/29/2016.
 */
public class BalloonBackground {
    private Bitmap image;
    private int x, y, dy;

    public BalloonBackground(Bitmap res){
        image = res;
    }
    public void update(Canvas canvas){
        y+=dy;
        if(y < -canvas.getHeight()){
            y = 0;
        }
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        if(y < 0){
            canvas.drawBitmap(image, x, y+canvas.getHeight(), null);
        }
    }
    public void setVector(int dy){
        this.dy = dy;
    }
}
