package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VinayakTests {
    @Test
    public void testUpBoundsLower() {
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(40);
        player.moveUp();
        assertEquals(40, player.getPosY());
    }




    @Test
    public void testUpBoundsGreater() {
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(70);
        player.moveUp();
        assertEquals(70, player.getBoundsUp());
    }




}
