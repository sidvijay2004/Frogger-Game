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
}