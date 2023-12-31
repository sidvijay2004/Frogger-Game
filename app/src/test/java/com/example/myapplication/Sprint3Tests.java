package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class Sprint3Tests {
    @Test
    public void testScoreObstacle1() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 0, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 0, "Small");
        player.setPosY(v1.getPosY() + 10);
        player.moveUp(v1, v2, v3);
        assertEquals(20, player.getScore());
    }

    @Test
    public void testScoreNotUpdateDown() {
        Player player = new Player();
        player.setPosition(1000, 1000);
        player.moveDown();
        assertEquals(0, player.getScore());
    }

    @Test
    public void testScoreObstacle2() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");


        player.setPosY(v2.getPosY() + 10);
        player.moveUp(v1, v2, v3);
        assertEquals(40, player.getScore());
    }

    @Test
    public void testScoreObstacle3() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(v3.getPosY() + 10);
        player.moveUp(v1, v2, v3);
        assertEquals(100, player.getScore());
    }

    @Test
    public void testScoreNotUpdateRight() {
        Player player = new Player();
        player.setPosition(1000, 1000);
        player.moveRight();
        assertEquals(0, player.getScore());
    }

    @Test
    public void testScoreNotUpdateLeft() {
        Player player = new Player();
        player.setPosition(1000, 1000);
        player.moveLeft();
        assertEquals(0, player.getScore());
    }

    @Test
    public void testMoveUpZero() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(0);
        player.moveUp(v1, v2, v3);
        assertEquals(5, player.getScore());
    }

    @Test
    public void testMoveUpAndMoveDown() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(0);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        player.moveDown();
        player.moveDown();
        assertEquals(5, player.getScore());
    }

    @Test
    public void testPathScore() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(10000);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);

        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();

        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);

        assertEquals(20, player.getScore());
    }

    @Test
    public void testPathWithVehicle() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(v1.getPosY() + 10);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);

        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveLeft();
        player.moveLeft();
        player.moveRight();

        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);

        assertEquals(35, player.getScore());
    }

}
