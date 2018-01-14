package com.craftinginjava.rollercoastertycoon.guest;

import com.craftinginjava.rollercoastertycoon.world.World;
import lombok.RequiredArgsConstructor;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class GuestMover implements Runnable {

    private final World world;

    public void run() {
        while (true) {
            world.movePeople();
            takeABreath();
        }
    }

    private void takeABreath() {
        try {
            Thread.sleep(80);
        } catch (InterruptedException ex) {
            Logger.getLogger(GuestMover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
