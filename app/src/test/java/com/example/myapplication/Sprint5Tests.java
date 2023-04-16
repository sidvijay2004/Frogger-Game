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


}
