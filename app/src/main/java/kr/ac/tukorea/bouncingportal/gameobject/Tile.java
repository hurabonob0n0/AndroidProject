package kr.ac.tukorea.bouncingportal.gameobject;

import android.graphics.RectF;

import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class Tile extends Sprite implements IBoxCollidable {
    public Tile(float x, float y) {
        super(R.mipmap.basic_tile, x, y, 1.0f, 1.0f);
    }

    @Override
    public RectF getCollisionRect() {
        return new RectF(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }
}