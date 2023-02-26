package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoadTile extends View {
    private Paint roadPaint;

    public RoadTile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        roadPaint = new Paint();
        roadPaint.setColor(Color.DKGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 10000, 1000, roadPaint);
    }
}
