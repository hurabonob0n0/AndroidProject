package kr.ac.tukorea.bouncingportal.gameobject;

import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.bouncingportal.scene.GamePlayScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Ball extends Sprite implements IBoxCollidable , ITouchable{
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private final float speed = 5.0f; // 속도 조절 가능
    private final float moveSpeed = 5.0f;
    private float vx = 0.f;
    private float vy = 7.0f;
    private final float gravity = 20.0f;    // 중력 가속도
    private final float bounce = 0.9f;      // 튕기는 비율

    private float posX = 0.f;
    private float posY = 0.f;
    public Ball(float x, float y) {
        super(R.mipmap.ball, x, y, 0.5f, 0.5f);
        posX = x;
        posY = y;
    }

    @Override
    public void update() {

        if (movingLeft) {
            vx = -moveSpeed;
        } else if (movingRight) {
            vx = moveSpeed;
        } else {
            vx = 0;
        }

        boolean leftcollision = false;
        boolean rightcollision = false;
        boolean upcollision = false;
        boolean downcollision = false;

        int collcnt = 0;

        // 충돌 체크
        ArrayList<IGameObject> tiles = Scene.top().objectsAt(GamePlayScene.Layer.tile);
        for (IGameObject obj : tiles) {
            if (!(obj instanceof IBoxCollidable)) continue;

            RectF tileRect = ((IBoxCollidable) obj).getCollisionRect();
            float tileLeft = tileRect.left;
            float tileRight = tileRect.right;
            float tileTop = tileRect.top;
            float tileBottom = tileRect.bottom;

            if (RectF.intersects(this.getCollisionRect(), tileRect)) {

                if(posY - 0.25f <= tileTop && !upcollision){
                    //posY = tileTop - height / 2 - 0.01f;
                    posY = tileTop - height / 2;
                    vy *= -bounce;
                    upcollision = true;
                    continue;
                }
                else if(posY + 0.25f >= tileBottom && !downcollision){
                    //posY = tileBottom + height / 2+ 0.01f;
                    posY = tileBottom + height / 2;
                    vy *= bounce;
                    downcollision = true;
                    continue;
                }
                else if(posX - 0.25f <= tileLeft && !leftcollision){
                    //posX = tileLeft - height / 2- 0.01f;
                    //posX = tileLeft - height / 2;
                    vx *= -bounce;
                    leftcollision = true;
                    continue;
                }
                else if(posX + 0.25f <= tileRight && !rightcollision){
                    //posX = tileRight - height / 2+ 0.01f;
                    //posX = tileRight - height / 2;
                    vx *= -bounce;
                    rightcollision = true;
                    continue;
                }

                if (upcollision)
                    ++collcnt;
                if (downcollision)
                    ++collcnt;
                if (leftcollision)
                    ++collcnt;
                if (rightcollision)
                    ++collcnt;

                if (collcnt >= 2)
                    break;

                // 아주 약해졌으면 0으로 처리
                if (Math.abs(vy) < 1.0f) {
                    vy = 0.0f;
                }

            }
        }

        vy += gravity * GameView.frameTime;
        posX += vx * GameView.frameTime;
        posY += vy * GameView.frameTime;

        setPosition(posX, posY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX() * Metrics.width / GameView.view.getWidth(); // 터치 좌표를 게임 좌표로 변환

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (touchX < Metrics.width / 2) {
                    movingLeft = true;
                    movingRight = false;
                } else {
                    movingLeft = false;
                    movingRight = true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                movingLeft = movingRight = false;
                break;
        }
        return true;
    }

    @Override
    public RectF getCollisionRect() {
        return new RectF(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }
}