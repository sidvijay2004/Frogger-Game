package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Sprint5Tests {

    @Test
    public void testIsPlayerDiedTrue() {
        int riverBorder = 500;
        Player player = new Player();
        player.setNumLives(3);
        player.decrementLives();
        player.decrementLives();
        player.decrementLives();
        assertEquals(true, player.isPlayerDied());
    }

    @Test
    public void testIsPlayerDiedFalse() {
        int riverBorder = 500;
        Player player = new Player();
        player.setNumLives(3);
        player.decrementLives();
        player.decrementLives();
        assertEquals(false, player.isPlayerDied());
    }


}
