package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GoalTile extends Tile {
    public GoalTile(Context context,int positionX, int positionY, @Nullable AttributeSet attrs) {
        super(context, Color.YELLOW, positionX, positionY, 500, attrs);
    }

    public GoalTile(Context context,int positionX, int positionY, int laneWidth, @Nullable AttributeSet attrs) {
        super(context, Color.YELLOW, positionX, positionY, laneWidth, attrs);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

