package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

public class SidTests {
    @Test
    public void testDown() {
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsDown(40,10,10);
        player.moveDown();
        assertEquals(40, player.getPosY());
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
