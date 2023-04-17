package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


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
        player.setBoundsTop(-1000);
        player.setBoundsDown(1000, 50, 50);
        player.setPosY(0);
        player.moveUp(null, null, null);
        player.isGoal(0);
        assertEquals(1, player.getGameWinStatus());
    }

    @Test
    public void testPlayerScoreUpdateVehicle1() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 1000, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 2000, "Small");
        player.setPosY(v1.getPosY() + 10);
        player.setBoundsTop(-10000);
        player.moveUp(v1, v2, v3);
        assertEquals(500, player.getScore());
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


    @Test
    public void testPlayerScoreUpdateVehicleCombo1() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 60, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 90, "Small");
        player.setPosY(0);
        player.setBoundsTop(-10000);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        assertEquals(3505, player.getScore());
    }

    @Test
    public void testPlayerScoreUpdateVehicleCombo2() {
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 50, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 100, "Small");
        player.setPosY(0);
        player.setBoundsTop(-10000);
        player.moveUp(v1, v2, v3); // 50
        assertEquals(player.getScore(), 500);
        player.moveUp(v1, v2, v3); // 100
        assertEquals(player.getScore(), 1500);

        player.moveDown();

        player.moveDown();
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        assertEquals(player.getScore(), 1500);

    }


}