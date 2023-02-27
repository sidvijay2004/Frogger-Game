package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

public class PlayerTest extends TestCase {
    @Test
    public void testDiff() {
        Player player = new Player();
        player.setDifficulty("MIDIUM", 7);

        assertEquals("MIDIUM", player.getDifficulty());
        assertEquals(7, player.getNumLives());
    }

    @Test
    public void testChar() {
        Player player = new Player();
        player.setImageResourceId(R.drawable.character_two);
        assertEquals(R.drawable.character_two, player.getImageResourceId());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player();
        player.setPosition(1000, 1000);

        //Since we are testing movement, we shall give it a lot of clear space.
        player.setBoundsRight(1000000, 50);
        player.setBoundsLeft(0);
        player.setBoundsTop(10000000);
        player.setBoundsDown(0, 50,50);

        player.moveRight();

        assertEquals(1010, player.getPosX());
        assertEquals(1000, player.getPosY());
    }

    @Test
    public void testRightBounds() {
        Player player = new Player();

        //Test if the player remains within bounds from the right side part of the screen
        player.setPosX(95);
        player.setPosY(95);
        player.setBoundsRight(109, 10);
        player.moveRight();
        // This simulates right side collision.
        // To remain within bounds, player positionX (the left side of character sprite) must stay at posX = min(95 + 10, 109 - 10) = 99 when it attempts to move to the right.
        player.moveRight();
        assertEquals(99, player.getPosX());
    }


}