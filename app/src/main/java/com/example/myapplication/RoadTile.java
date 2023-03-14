package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class RoadTile extends Tile {
    public RoadTile(Context context, int positionX, int positionY, @Nullable AttributeSet attrs) {
        super(context, Color.DKGRAY, positionX, positionY, 500, attrs);
    }

    public RoadTile(Context context,int positionX, int positionY, int laneWidth, @Nullable AttributeSet attrs) {
        super(context, Color.DKGRAY, positionX, positionY, laneWidth, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // TODO - Add logic for cars.
    }
}
