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

}