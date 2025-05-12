package kr.ac.tukorea.bounceball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float x, y;
    private float radius = 50;
    private float dy = 0;
    private final float gravity = 2000f;
    private final float bounce = -0.7f;
    private final float floorY = 1600f;

    private final Paint paint = new Paint();

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
        paint.setColor(Color.BLUE);
    }

    public void update(float dt) {
        dy += gravity * dt;
        y += dy * dt;

        if (y + radius > floorY) {
            y = floorY - radius;
            dy *= bounce;

            if (Math.abs(dy) < 50f) {
                dy = 0;
            }
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
