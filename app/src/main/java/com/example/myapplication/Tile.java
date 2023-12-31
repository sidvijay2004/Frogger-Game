package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Tile extends View {
    protected int laneWidth;
    protected int positionX;
    protected int positionY;
    protected Paint tilePaint;
    protected Rect tileRect;

    public Tile(Context context, int color, int positionX,
                int positionY, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tilePaint = new Paint();
        tilePaint.setColor(color);
        this.positionX = positionX;
        this.positionY = positionY;
        this.laneWidth = 500;
    }

    public Tile(Context context, int color, int positionX,
                int positionY, int laneWidth, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tilePaint = new Paint();
        tilePaint.setColor(color);
        this.positionX = positionX;
        this.positionY = positionY;
        this.laneWidth = laneWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.tileRect = new Rect(positionX, positionY, getWidth(), positionY + laneWidth);
        canvas.drawRect(positionX, positionY, getWidth(), positionY + laneWidth, tilePaint);
    }


    public Rect getRect() {
        return tileRect;
    }
}
