package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Player extends View {
    private static final double SPEED = 100.0;
    private ImageView charImageDisplay;

    private int xPos;
    private int yPos;

    public Player(Context context) {
        super(context);
    }

    public Player(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the player at the current position
//        canvas.drawCircle(xPosition, yPosition, 50, new Paint());
    }

    public ImageView getCharImageDisplay() {
        return charImageDisplay;
    }

    public void setCharImageDisplay(ImageView charImageDisplay) {
        this.charImageDisplay = charImageDisplay;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
