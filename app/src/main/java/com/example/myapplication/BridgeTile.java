package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

public class BridgeTile extends Tile {

    private int bridgeWidth;


    public BridgeTile(Context context, int positionX,
                     int positionY, int laneWidth, int bridgeWidth, @Nullable AttributeSet attrs) {
        super(context, Color.GREEN, positionX, positionY, laneWidth, attrs);
        this.bridgeWidth = bridgeWidth;

    }
    @Override
    protected void onDraw(Canvas canvas) {

        this.tileRect = new Rect(positionX, positionY, positionX + bridgeWidth, positionY + laneWidth);
        canvas.drawRect(positionX, positionY, positionX + bridgeWidth, positionY + laneWidth, tilePaint);
        this.bringToFront();
    }
}
