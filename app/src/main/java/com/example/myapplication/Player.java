package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;

public class Player {
    private static final double SPEED = 100.0;
    private ImageView charImageDisplay;

    private int xPos;
    private int yPos;

    public Player() {

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
