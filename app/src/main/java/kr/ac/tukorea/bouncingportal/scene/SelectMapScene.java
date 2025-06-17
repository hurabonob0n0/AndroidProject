package kr.ac.tukorea.bouncingportal.scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import kr.ac.tukorea.bouncingportal.gameobject.MapData;
import kr.ac.tukorea.bouncingportal.gameobject.TextButton;
import kr.ac.tukorea.bouncingportal.scene.GamePlayScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class SelectMapScene extends Scene {
    public enum Layer {
        bg, button;
        public static final int COUNT = values().length;
    }
    public ArrayList<MapData> mapList = new ArrayList<>();

    public SelectMapScene() {
        initLayers(SelectMapScene.Layer.COUNT);

        // maps 폴더 접근
        File mapDir = GameView.view.getContext().getExternalFilesDir("maps");
        if (mapDir == null || !mapDir.exists()) return;

        File[] mapFiles = mapDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (mapFiles == null || mapFiles.length == 0) return;

        Arrays.sort(mapFiles);

        float startY = 4.0f;
        float buttonHeight = 2.0f;
        float gap = 0.5f;

        for (int i = 0; i < mapFiles.length; i++) {
            File file = mapFiles[i];
            MapData mapData = MapData.readFile(file);
            if (mapData == null) continue;

            mapList.add(mapData); // 혹시 나중에 다시 쓰일 수 있으니 저장

            float y = startY + i * (buttonHeight + gap);

            add(Layer.button, new TextButton(
                    mapData.name,
                    Metrics.width / 2, y,
                    20.0f, buttonHeight,
                    1.f,
                    pressed -> {
                        if (pressed) new GamePlayScene(mapData).push();
                        return true;
                    }
            ));
        }
    }

    protected int getTouchLayerIndex() {
        return TitleScene.Layer.button.ordinal();
    }
}