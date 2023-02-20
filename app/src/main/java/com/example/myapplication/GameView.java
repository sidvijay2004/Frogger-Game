package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Paint blockPaint;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        blockPaint = new Paint();
        blockPaint.setColor(Color.parseColor("blue"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(20, 50, 100, 200, blockPaint);
    }

}
