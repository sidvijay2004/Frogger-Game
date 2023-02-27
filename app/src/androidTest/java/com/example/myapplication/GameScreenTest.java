package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GameScreenTest {

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



