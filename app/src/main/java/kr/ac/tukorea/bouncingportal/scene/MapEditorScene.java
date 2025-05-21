package kr.ac.tukorea.bouncingportal.scene;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.bouncingportal.gameobject.MapData;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapEditorScene extends Scene {
    public enum Layer {
        bg, obj, ui, touch;
        public static final int COUNT = values().length;
    }

    private final Paint gridPaint = new Paint();

    private Bitmap tileBitmap;
    private Bitmap ballBitmap;

    public enum ObjectType { Tile, Ball }
    private ObjectType selectedType = null;

    private final ArrayList<Point> tileList = new ArrayList<>();
    private Point ballPosition = null;

    public MapEditorScene() {
        initLayers(Layer.COUNT);
        initResources();
        initUI();
    }

    private void initResources() {
        gridPaint.setColor(Color.LTGRAY);
        tileBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.basic_tile);
        ballBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.ball);
    }

    private void initUI() {
        addTileButton();
        addBallButton();
        addSaveButton();
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    private void addTileButton() {
        add(Layer.touch, new Button(R.mipmap.basic_tile,
                25f, 1f, 1.5f, 1.5f,
                pressed -> {
                    if (pressed) selectedType = ObjectType.Tile;
                    return true; // 이벤트를 소비했다는 의미
                }));
    }

    private void addBallButton() {
        add(Layer.touch, new Button(R.mipmap.ball,
                27f, 1f, 1.5f, 1.5f,
                pressed -> {
                    if (pressed) selectedType = ObjectType.Ball;
                    return true;
                }));
    }

    private void addSaveButton() {
        add(Layer.touch, new Button(R.mipmap.save_button, 26.5f, 13.5f, 2f, 1.5f, pressed -> {
            if (!pressed) return true; // UP일 때만 처리 (DOWN 무시)
            try {
                saveMapToFile("map3.json");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }));
    }


    @Override
    public void draw(Canvas canvas) {
        drawGrid(canvas);
        drawTiles(canvas);
        drawBall(canvas);
        super.draw(canvas);
    }

    private void drawGrid(Canvas canvas) {
        for (int x = 0; x <= 29; x++) {
            gridPaint.setStrokeWidth((x % 5 == 0) ? 0.08f : 0.04f);
            canvas.drawLine(x, 0, x, 15, gridPaint);
        }
        for (int y = 0; y <= 15; y++) {
            gridPaint.setStrokeWidth((y % 5 == 0) ? 0.08f : 0.04f);
            canvas.drawLine(0, y, 29, y, gridPaint);
        }
    }

    private void drawTiles(Canvas canvas) {
        for (Point p : tileList) {
            canvas.drawBitmap(tileBitmap, null,
                    new RectF(p.x, p.y, p.x + 1, p.y + 1), null);
        }
    }

    private void drawBall(Canvas canvas) {
        if (ballPosition != null) {
            canvas.drawBitmap(ballBitmap, null,
                    new RectF(ballPosition.x, ballPosition.y, ballPosition.x + 1, ballPosition.y + 1), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 먼저 버튼이 터치 이벤트를 처리했는지 확인
        if (event.getAction() == MotionEvent.ACTION_DOWN && super.onTouchEvent(event)) {
            return true; // 버튼이 처리했으면 더 이상 처리 안 함
        }

        if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
        if (selectedType == null) return false;

        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        int gx = (int) pts[0];
        int gy = (int) pts[1];

        if (gx < 0 || gx >= 29 || gy < 0 || gy >= 15) return false;

        switch (selectedType) {
            case Tile:
                tileList.add(new Point(gx, gy));
                break;
            case Ball:
                ballPosition = new Point(gx, gy);
                break;
        }
        return true;
    }

    private void saveMapToFile(String fileName) throws IOException, JSONException {
        MapData data = new MapData();
        data.tileList.addAll(tileList);
        data.ballPosition = ballPosition;

        JSONObject json = data.toJson();

        File file = new File(GameView.view.getContext().getExternalFilesDir("maps"), fileName);
        Log.d("MapEditorScene", "Saving to: " + file.getAbsolutePath());
        FileWriter writer = new FileWriter(file);
        writer.write(json.toString());
        writer.close();
    }
}