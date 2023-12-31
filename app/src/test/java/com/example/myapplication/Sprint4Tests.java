package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class Sprint4Tests {

    @Test
    public void testIsGoalFalse() {
        int riverBorder = 500;
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(riverBorder + 10);
        player.moveUp(v1, v2, v3);
        assertEquals(false, player.isGoal(riverBorder));
    }

    @Test
    public void testIsGoalTrue() {
        int riverBorder = 500;
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(riverBorder + 10);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        assertEquals(true, player.isGoal(riverBorder));
    }

    @Test
    public void testDecrementLives() {
        Player player = new Player();
        player.setDifficulty("EASY", 10);
        player.decrementLives();
        assertEquals(player.getNumLives(), 9);
        player.decrementLives();
        player.decrementLives();
        assertEquals(player.getNumLives(), 7);
    }

    @Test
    public void testDead() {
        Player player = new Player();
        player.setDifficulty("ONE_LIFE", 0);
        assertEquals(false, player.isDead());
        player.decrementLives();
        assertEquals(true, player.isDead());
    }

    @Test
    public void testMoveUpAfterDeath() {
        Player player = new Player();
        player.setBoundsTop(0);
        player.setBoundsDown(100000000, 10, 50);
        player.setPosition(500, 500);
        player.moveUp(null, null, null);
        assertEquals(490, player.getPosY());
        player.setDead();
        player.moveUp(null, null, null);
        assertEquals(490, player.getPosY());
    }
    @Test
    public void testMoveDownAfterDeath() {
        Player player = new Player();
        player.setBoundsTop(0);
        player.setBoundsDown(100000000, 10, 50);
        player.setPosition(500, 500);
        player.moveDown();
        assertEquals(510, player.getPosY());
        player.setDead();
        player.moveDown();
        assertEquals(510, player.getPosY());
    }

    @Test
    public void testMoveLeftAfterDeath() {
        Player player = new Player();
        player.setBoundsLeft(-1000);
        player.setPosition(500, 500);
        player.moveLeft();
        assertEquals(490, player.getPosX());
        player.setDead();
        player.moveLeft();
        assertEquals(490, player.getPosX());
    }

    @Test
    public void testMoveRightAfterDeath() {
        Player player = new Player();
        player.setBoundsRight(1000, 10);
        player.setPosition(500, 500);
        player.moveRight();
        assertEquals(510, player.getPosX());
        player.setDead();
        player.moveRight();
        assertEquals(510, player.getPosX());
    }

    @Test
    public void testRiverCollisionScore() {
        Player player = new Player();
        player.setScore(100);
        player.riverCollisionPenalty();
        assertTrue(player.getScore() <= 50);
        assertTrue(player.getScore() >= 0);
    }


    @Test
    public void testVehicleCollisionScore() {
        Player player = new Player();
        player.setScore(100);
        player.addVehicleCollisionPenalty();
        assertTrue(player.getScore() <= 50);
        assertTrue(player.getScore() >= 0);
    }




}
