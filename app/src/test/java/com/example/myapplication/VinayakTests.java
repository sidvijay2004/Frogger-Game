package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import android.widget.ImageView;
import android.view.View;

import org.junit.Test;

public class VinayakTests {








    @Test
    public void testUpBoundsLower() {
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 0, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 0, "Small");
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(40);
        player.moveUp(v1, v2, v3);
        assertEquals(40, player.getPosY());
    }




    @Test
    public void testUpBoundsGreater() {
        Vehicle v1 = new Vehicle(  R.id.vehicle1, 0, "Medium");
        Vehicle v2 = new Vehicle(  R.id.vehicle2, 0, "Big");
        Vehicle v3 = new Vehicle(  R.id.vehicle3, 0, "Small");
        Player player = new Player();
        player.setPosY(50);
        player.setBoundsTop(70);
        player.moveUp(v1, v2, v3);
        assertEquals(70, player.getBoundsUp());
    }




}
