package kr.ac.tukorea.bouncingportal.scene;

import android.graphics.Point;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.bouncingportal.gameobject.MapData;
import kr.ac.tukorea.bouncingportal.gameobject.Tile;
import kr.ac.tukorea.bouncingportal.gameobject.Ball;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class GamePlayScene extends Scene {

    public enum Layer {
        bg, tile, ball, ui, controller;
        public static final int COUNT = values().length;
    }

    private final MapData mapData;

    public GamePlayScene(MapData mapData) {
        this.mapData = mapData;
        initLayers(GamePlayScene.Layer.COUNT);
        initObjects();
    }

    private void initObjects() {
        // 타일 오브젝트 생성
        for (Point p : mapData.tileList) {
            add(Layer.tile, new Tile(p.x, p.y));
        }

        // 공 오브젝트 생성
        if (mapData.ballPosition != null) {
            add(Layer.ball, new Ball(mapData.ballPosition.x, mapData.ballPosition.y));
        }
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.controller.ordinal(); // 필요 시 조정
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // 필요 시 디버그 정보 등 추가 가능
    }

    public boolean onTouchEvent(MotionEvent event) {
        ArrayList<IGameObject> objs = objectsAt(Layer.ball);
        for (IGameObject obj : objs) {
            if (obj instanceof ITouchable) {
                ((ITouchable) obj).onTouchEvent(event);
            }
        }
        return true;
    }
}
