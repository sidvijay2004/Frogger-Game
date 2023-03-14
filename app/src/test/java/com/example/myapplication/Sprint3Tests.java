package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Sprint3Tests {
    @Test
    public void testScoreObstacles() {
        Player player = new Player();
        player.setPosition(1000, 1900);
        player.moveUp();
        assertEquals(40, player.getScore());
        player.setPosition(1000, 1400);
        player.moveUp();
        assertEquals(50, player.getScore());
        player.setPosition(1000, 1100);
        player.moveUp();
        assertEquals(70, player.getScore());
    }

    @Test
    public void testScoreNotUpdate() {
        Player player = new Player();
        player.setPosition(1000, 1000);
        player.moveDown();
        assertEquals(0, player.getScore());
        player.moveRight();
        assertEquals(0, player.getScore());
        player.moveLeft();
        assertEquals(0, player.getScore());
    }
}
