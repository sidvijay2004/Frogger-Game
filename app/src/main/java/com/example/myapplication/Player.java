package com.example.myapplication;
import android.graphics.Rect;

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
    private int score = 0;
    private int minYPos = 20000;


    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private boolean inCollision;
    private boolean isDead = false;

    public Rect getPlayerRect() {
        return playerRect;
    }

    public void setPlayerRect(Rect playerRect) {
        this.playerRect = new Rect(playerRect);
    }

    private Rect playerRect;

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

    public void decrementLives() {
        this.numLives--;
        if(numLives == 0) {
            setDead();
        }
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
        if(isDead) {
            return;
        }
        posX -= 10;
        if (posX < boundsLeft) {
            posX = boundsLeft;
        }
    }

    public void moveRight() {
        if(isDead) {
            return;
        }
        posX += 10;
        if (posX > boundsRight) {
            posX = boundsRight;
        }
    }

    public void setDead() {
        isDead = true;
    }
    public boolean isDead() {
        return isDead;
    }
    public void moveUp(Vehicle v1, Vehicle v2, Vehicle v3) {
        if(isDead) {
            return;
        }

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
        if(isDead) {
            return;
        }
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

    public boolean isGoal(int riverAndGoalTileBorderPos) {
        if (this.getPosY() < riverAndGoalTileBorderPos) {
            return true;
        }
        return false;
    }

    public boolean isCollidingWithPlayer(Rect collisionRect) {
        playerRect.left = posX;
        playerRect.right = posX + playerRect.width();
        playerRect.top = posY;
        playerRect.bottom = posY + playerRect.height();
        if(playerRect.intersect(collisionRect)) {
            return true;
        }
        return false;
    }
    public void riverCollisionPenalty() {
        score /= 3;
    }

    public void addVehicleCollisionPenalty() {
        score /= 2;
    }
}
