package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Sprint4Tests {

    @Test
    public void testIsGoalFalse() {
        int riverBorder = 500;
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(riverBorder + 10);
        player.moveUp(v1, v2, v3);
        assertEquals(false, player.isGoal(riverBorder));
    }

    @Test
    public void testIsGoalTrue() {
        int riverBorder = 500;
        Player player = new Player();
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 100, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 200, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 300, "Small");
        player.setPosY(riverBorder + 10);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        player.moveUp(v1, v2, v3);
        assertEquals(true, player.isGoal(riverBorder));
    }


}
