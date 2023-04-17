package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Random;


public class Sprint5Tests {

    @Test
    public void testIsPlayerDiedTrue() {
        int riverBorder = 500;
        Player player = new Player();
        player.setNumLives(3);
        player.diePlayer();
        player.diePlayer();
        player.diePlayer();
        assertEquals(true, player.isPlayerDied());
    }

    @Test
    public void testIsPlayerDiedFalse() {
        int riverBorder = 500;
        Player player = new Player();
        player.setNumLives(3);
        player.diePlayer();
        player.diePlayer();
        assertEquals(false, player.isPlayerDied());
    }

    @Test
    public void testPlayerInGame() {
        Player player = new Player();
        assertEquals(-1, player.getGameWinStatus());
    }

    @Test
    public void testPlayerLostGame() {
        Player player = new Player();
        player.setDead();
        assertEquals(0, player.getGameWinStatus());
    }

    @Test
    public void testPlayerWinGame() {
        Player player = new Player();
        Random rand = new Random();
        int yPos = rand.nextInt(200);
        player.setPosY(yPos);
        if (player.isGoal(100)) {
            assertEquals(1, player.getGameWinStatus());
        } else {
            assertEquals(-1, player.getGameWinStatus());
        }
    }

    @Test
    public void testWinScoreUpdate() {
        Player player = new Player();
        Random rand = new Random();
        int yPos = rand.nextInt(200);
        int score = player.getScore();
        player.setPosY(yPos);
        if (player.isGoal(100)) {
            assertEquals(score + 5000, player.getScore());
        } else {
            assertEquals(score, player.getScore());
        }
    }



    @Test
    public void testPlayerScoreUpdateVehicle2() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 1000, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 2000, "Small");
        player.setPosY(v2.getPosY() + 10);
        player.setBoundsTop(-10000);
        player.moveUp(v1, v2, v3);
        assertEquals(1000, player.getScore());
    }

    @Test
    public void testPlayerScoreUpdateVehicle3() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 1000, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 2000, "Small");
        player.setPosY(v3.getPosY() + 10);
        player.setBoundsTop(-10000);
        player.moveUp(v1, v2, v3);
        assertEquals(2000, player.getScore());
    }





}