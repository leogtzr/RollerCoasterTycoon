package com.craftinginjava.rollercoastertycoon.guest;

import com.craftinginjava.rollercoastertycoon.world.World;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestMover implements Runnable {

    private final World world;

    public GuestMover(World world) {
        this.world = world;
    }

    public void run() {
        while (true) {

            world.movePeople();

            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuestMover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
