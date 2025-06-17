package kr.ac.tukorea.bouncingportal.gameobject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class TextButton extends Button {
    private final String text;
    private final Paint bgPaint, textPaint;
    private final float textSize;

    public TextButton(String text, float cx, float cy, float width, float height, float textSize, OnTouchListener listener) {
        // 부모 Button에는 이미지가 필요하므로 0 (혹은 dummy) 전달
        super(0, cx, cy, width, height, listener);

        this.text = text;
        //this.textSize = Metrics.size(textSize); // 실제 픽셀 단위로 변환
        this.textSize = 1.f; // 실제 픽셀 단위로 변환

        // 배경색: 어두운 회색
        bgPaint = new Paint();
        bgPaint.setColor(Color.DKGRAY);

        // 텍스트색: 흰색
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(this.textSize);
    }

    @Override
    public void draw(Canvas canvas) {
        // 배경 그리기
        RectF rect = this.dstRect;
        canvas.drawRect(rect, bgPaint);

        // 텍스트 그리기 (수직 중심 조정)
        float x = rect.centerX();
        float y = rect.centerY() + textSize * 0.35f;
        canvas.drawText(text, x, y, textPaint);
    }
}
