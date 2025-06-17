package kr.ac.tukorea.bouncingportal.scene;

import android.view.MotionEvent;

import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

import kr.ac.tukorea.bouncingportal.gameobject.TextButton;

public class TitleScene extends Scene {
    public enum Layer {
        bg, button;
        public static final int COUNT = values().length;
    }

    public TitleScene() {
        initLayers(Layer.COUNT);

        // 배경 추가: 화면 전체 덮는 Sprite
        add(Layer.bg, new Sprite(R.mipmap.start_scene, Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height));

        // MAP EDITOR 버튼
        add(Layer.button, new TextButton("MAP EDITOR", 14.5f, 11.0f, 10.0f, 2.0f, 1.0f, pressed -> {
            if (pressed) new MapEditorScene().push();
            return true;
        }));

        // START 버튼
        add(Layer.button, new TextButton("START", 14.5f, 14.0f, 10.0f, 2.0f, 1.0f, pressed -> {
            if (pressed) new SelectMapScene().push();
            return true;
        }));
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.button.ordinal();
    }
}
