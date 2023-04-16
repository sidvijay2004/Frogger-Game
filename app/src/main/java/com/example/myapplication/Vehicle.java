package com.example.myapplication;


public class Vehicle {

    private int posY;
    private float posX;
    private int id;
    private String size;

    public Vehicle(int id, int posY, String size) {
        this.id = id;
        this.posY = posY;
        this.size = size;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }

}
