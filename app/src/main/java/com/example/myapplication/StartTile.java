package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StartTile extends Tile {
    private Paint roadPaint;

    public StartTile(Context context,int positionX, int positionY, @Nullable AttributeSet attrs) {
        super(context, Color.RED, positionX, positionY, 500, attrs);
    }

    public StartTile(Context context,int positionX, int positionY, int laneWidth, @Nullable AttributeSet attrs) {
        super(context, Color.RED, positionX, positionY, laneWidth, attrs);

    }
}

