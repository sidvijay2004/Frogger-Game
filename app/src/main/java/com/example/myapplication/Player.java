package com.example.myapplication;
import java.io.Serializable;

public class Player implements Serializable {
    private int posX;
    private int posY;

    private int boundsLeft;
    private int boundsRight;
    private int boundsUp;
    private int boundsDown;

    private int imageResource;

    private String difficulty;
    private int numLives;


    public void setDifficulty(String difficulty, int numLives) {
        this.difficulty = difficulty;
        this.numLives = numLives;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public int getNumLives() {
        return numLives;
    }

    public void setImageResourceId(int id) {
        imageResource = id;
    }
    public int getImageResourceId() {
        return imageResource;
    }

    public void setBoundsLeft(int minSeperation) {
        this.boundsLeft = boundsLeft;
    }

    public void setBoundsRight(int screenWidth, int characterWidth) {
        this.boundsRight = screenWidth - characterWidth;
    }

    public void setBoundsDown(int startTileYPos, int startTileHeight, int characterWidth) {
        this.boundsDown = (startTileYPos + startTileHeight / 2 - characterWidth / 2);
    }
    public void setBoundsTop(int goalTilePosition) {
        this.boundsUp = goalTilePosition;
    }

    public void moveLeft() {
        posX -= 10;
        if (posX < boundsLeft) {
            posX = boundsLeft;
        }
    }

    public void moveRight() {
        posX += 10;
        if (posX > boundsRight) {
            posX = boundsRight;
        }
    }

    public void moveUp() {
        posY -= 10;
        if (posY < boundsUp) {
            posY = boundsUp;
        }
    }

    public void moveDown() {
        posY += 10;
        if (posY > boundsDown) {
            posY = boundsDown;
        }
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void setPosX(int newPosX) {
        posX = newPosX;
    }
    public void setPosY(int newPosY) {
        posY = newPosY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getBoundsUp() {
        return boundsUp;
    }

}
