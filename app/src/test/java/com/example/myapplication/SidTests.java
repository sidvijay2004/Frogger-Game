package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

public class SidTests {
    @Test
    public void testDown() {
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(0);
        player.setBoundsDown(1000,100,10);
        player.moveDown();
        assertEquals(60, player.getPosY());
    }

    @Test
    public void testDownBoundsLower() {
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(30);
        player.moveUp();
        assertEquals(30, player.getBoundsUp());
    }

}
