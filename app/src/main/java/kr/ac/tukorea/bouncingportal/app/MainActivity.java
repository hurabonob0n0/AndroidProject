package kr.ac.tukorea.bouncingportal.app;

import android.os.Bundle;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.bouncingportal.scene.MapEditorScene;
import kr.ac.tukorea.bouncingportal.scene.TitleScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Metrics.setGameSize(29, 15); // 게임 좌표계 설정
        super.onCreate(savedInstanceState);

        setContentView(GameView.view); // GameView를 화면에 붙이기

        new TitleScene().push();
    }
}