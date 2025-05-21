package kr.ac.tukorea.bouncingportal.app;

import android.os.Bundle;
import kr.ac.tukorea.bouncingportal.scene.MapEditorScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapEditorActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Metrics.setGameSize(29f, 15f); // 29x15 좌표계
        super.onCreate(savedInstanceState);
        new MapEditorScene().push();   // 씬 진입
    }
}