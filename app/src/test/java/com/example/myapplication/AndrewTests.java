package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AndrewTests {
    @Test
    public void testMoveLeft() {
        Player player = new Player();
        player.setPosition(1000, 1000);

        //Since we are testing movement, we shall give it a lot of clear space.
        player.setBoundsRight(1000000, 50);
        player.setBoundsLeft(0);
        player.setBoundsTop(10000000);
        player.setBoundsDown(0, 50,50);

        player.moveLeft();

        assertEquals(990, player.getPosX());
        assertEquals(1000, player.getPosY());
    }

    @Test
    public void testLeftBounds() {
        Player player = new Player();

        //Test if the player remains within bounds from the left side part of the screen
        player.setPosX(0);
        player.setPosY(100);
        player.setBoundsLeft(0);
        player.moveLeft();
        // This simulates left side collision.
        // To remain within bounds, player positionX (the left side of character sprite) must stay at posX = 0 when it attempts to move to the left.
        player.moveLeft();
        assertEquals(0, player.getPosX());
    }
}
