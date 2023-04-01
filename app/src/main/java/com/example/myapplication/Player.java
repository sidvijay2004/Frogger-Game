package com.example.myapplication;
import java.io.Serializable;

public class Player implements Serializable {

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }

    private int numLives;

    private int posX;
    private int posY;

    private int boundsLeft;
    private int boundsRight;
    private int boundsUp;
    private int boundsDown;

    private int imageResource;


    private String difficulty;
    private int numLives;
    private int score = 0;
    private int minYPos = 20000;


    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private boolean inCollision;

    public int getStartPosX() {
        return startPosX;
    }

    public void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }

    public void setStartPosY(int startPosY) {
        this.startPosY = startPosY;
    }

    private int startPosX;
    private int startPosY;

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
        //account for soft bar height
        this.boundsDown = (startTileYPos + startTileHeight - characterWidth - 200);
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

    public void moveUp(Vehicle v1, Vehicle v2, Vehicle v3) {
        posY -= 10;
        if (posY < boundsUp) {
            posY = boundsUp;
        }

        double height1 = v1.getPosY();
        double height2 = v2.getPosY();
        double height3 = v3.getPosY();

        if (assertEqualsDouble(posY, height1) && posY < minYPos) {
            minYPos = posY;
            score += 20;
        } else if (assertEqualsDouble(posY, height2) && posY < minYPos) {
            minYPos = posY;
            score += 40;
        } else if (assertEqualsDouble(posY, height3) && posY < minYPos) {
            minYPos = posY;
            score += 100;
        } else {
            if (posY < minYPos) {
                minYPos = posY;
                score += 5;
            }
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

    public int getScore() {
        return score;
    }

    public void setVehicle1(Vehicle vehicle1) {
        this.vehicle1 = vehicle1;
    }

    public void setVehicle2(Vehicle vehicle2) {
        this.vehicle2 = vehicle2;
    }

    public void setVehicle3(Vehicle vehicle3) {
        this.vehicle3 = vehicle3;
    }
    public Vehicle getVehicle1() {
        return vehicle1;
    }

    public Vehicle getVehicle2() {
        return vehicle2;
    }

    public Vehicle getVehicle3() {
        return vehicle3;
    }

    public boolean assertEqualsDouble(double one, double two) {
        if (one <= two + 5 && one >= two - 5) {
            return true;
        }
        return false;

    }
    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

}
