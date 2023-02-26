package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class NewSafeTile extends View {
    private Paint roadPaint;

    public NewSafeTile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        roadPaint = new Paint();
        roadPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 1000000, 400, roadPaint);
    }
}
