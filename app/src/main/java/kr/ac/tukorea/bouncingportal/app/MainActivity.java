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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startActivity(new Intent(this, BouncingPortalActivity.class));
        }
        return false;
    }

    public void onMapEditorClicked(View view) {
        Intent intent = new Intent(this, MapEditorActivity.class);
        startActivity(intent);
    }
}