package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

public class NewSafeTile extends Tile {
    public NewSafeTile(Context context, int positionX,
                       int positionY, @Nullable AttributeSet attrs) {
        super(context, Color.GRAY, positionX, positionY, 500, attrs);
    }

    public NewSafeTile(Context context, int positionX,
                       int positionY, int laneWidth, @Nullable AttributeSet attrs) {
        super(context, Color.GRAY, positionX, positionY, laneWidth, attrs);
    }
}
