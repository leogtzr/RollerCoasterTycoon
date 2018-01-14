/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.craftinginjava.rollercoastertycoon.guest;

import com.craftinginjava.rollercoastertycoon.handler.GameImageHandler;

import java.awt.image.BufferedImage;
import java.util.Random;

public class GuestImageProvider implements GuestGraphicsProvider {

    private static final Random RAN = new Random();
    private static final BufferedImage[] SOUTHIMG1 =
            {
                    GameImageHandler.getGuestImg(1, 1, 0),
                    GameImageHandler.getGuestImg(1, 1, 1),
                    GameImageHandler.getGuestImg(1, 1, 2),
                    GameImageHandler.getGuestImg(1, 1, 3),
                    GameImageHandler.getGuestImg(1, 1, 4),
                    GameImageHandler.getGuestImg(1, 1, 5),
            };
    private static final BufferedImage[] SOUTHIMG2 =
            {
                    GameImageHandler.getGuestImg(2, 1, 0),
                    GameImageHandler.getGuestImg(2, 1, 1),
                    GameImageHandler.getGuestImg(2, 1, 2),
                    GameImageHandler.getGuestImg(2, 1, 3),
                    GameImageHandler.getGuestImg(2, 1, 4),
                    GameImageHandler.getGuestImg(2, 1, 5),
            };
    private static final BufferedImage[] SOUTHIMG3 =
            {
                    GameImageHandler.getGuestImg(3, 1, 0),
                    GameImageHandler.getGuestImg(3, 1, 1),
                    GameImageHandler.getGuestImg(3, 1, 2),
                    GameImageHandler.getGuestImg(3, 1, 3),
                    GameImageHandler.getGuestImg(3, 1, 4),
                    GameImageHandler.getGuestImg(3, 1, 5),
            };
    private static final BufferedImage[] SOUTHIMG4 =
            {
                    GameImageHandler.getGuestImg(4, 1, 0),
                    GameImageHandler.getGuestImg(4, 1, 1),
                    GameImageHandler.getGuestImg(4, 1, 2),
                    GameImageHandler.getGuestImg(4, 1, 3),
                    GameImageHandler.getGuestImg(4, 1, 4),
                    GameImageHandler.getGuestImg(4, 1, 5),
            };
    private final BufferedImage[] NORTHIMG1 =
            {
                    GameImageHandler.getGuestImg(1, 0, 0),
                    GameImageHandler.getGuestImg(1, 0, 1),
                    GameImageHandler.getGuestImg(1, 0, 2),
                    GameImageHandler.getGuestImg(1, 0, 3),
                    GameImageHandler.getGuestImg(1, 0, 4),
                    GameImageHandler.getGuestImg(1, 0, 5),
            };
    private final BufferedImage[] NORTHIMG2 =
            {
                    GameImageHandler.getGuestImg(2, 0, 0),
                    GameImageHandler.getGuestImg(2, 0, 1),
                    GameImageHandler.getGuestImg(2, 0, 2),
                    GameImageHandler.getGuestImg(2, 0, 3),
                    GameImageHandler.getGuestImg(2, 0, 4),
                    GameImageHandler.getGuestImg(2, 0, 5),
            };
    private final BufferedImage[] NORTHIMG3 =
            {
                    GameImageHandler.getGuestImg(3, 0, 0),
                    GameImageHandler.getGuestImg(3, 0, 1),
                    GameImageHandler.getGuestImg(3, 0, 2),
                    GameImageHandler.getGuestImg(3, 0, 3),
                    GameImageHandler.getGuestImg(3, 0, 4),
                    GameImageHandler.getGuestImg(3, 0, 5),
            };
    private final BufferedImage[] NORTHIMG4 =
            {
                    GameImageHandler.getGuestImg(4, 0, 0),
                    GameImageHandler.getGuestImg(4, 0, 1),
                    GameImageHandler.getGuestImg(4, 0, 2),
                    GameImageHandler.getGuestImg(4, 0, 3),
                    GameImageHandler.getGuestImg(4, 0, 4),
                    GameImageHandler.getGuestImg(4, 0, 5),
            };
    private final BufferedImage[] EASTIMG1 =
            {
                    GameImageHandler.getGuestImg(1, 2, 0),
                    GameImageHandler.getGuestImg(1, 2, 1),
                    GameImageHandler.getGuestImg(1, 2, 2),
                    GameImageHandler.getGuestImg(1, 2, 3),
                    GameImageHandler.getGuestImg(1, 2, 4),
                    GameImageHandler.getGuestImg(1, 2, 5),
            };
    private final BufferedImage[] EASTIMG2 =
            {
                    GameImageHandler.getGuestImg(2, 2, 0),
                    GameImageHandler.getGuestImg(2, 2, 1),
                    GameImageHandler.getGuestImg(2, 2, 2),
                    GameImageHandler.getGuestImg(2, 2, 3),
                    GameImageHandler.getGuestImg(2, 2, 4),
                    GameImageHandler.getGuestImg(2, 2, 5),
            };
    private final BufferedImage[] EASTIMG3 =
            {
                    GameImageHandler.getGuestImg(3, 2, 0),
                    GameImageHandler.getGuestImg(3, 2, 1),
                    GameImageHandler.getGuestImg(3, 2, 2),
                    GameImageHandler.getGuestImg(3, 2, 3),
                    GameImageHandler.getGuestImg(3, 2, 4),
                    GameImageHandler.getGuestImg(3, 2, 5),
            };
    private final BufferedImage[] EASTIMG4 =
            {
                    GameImageHandler.getGuestImg(4, 2, 0),
                    GameImageHandler.getGuestImg(4, 2, 1),
                    GameImageHandler.getGuestImg(4, 2, 2),
                    GameImageHandler.getGuestImg(4, 2, 3),
                    GameImageHandler.getGuestImg(4, 2, 4),
                    GameImageHandler.getGuestImg(4, 2, 5),
            };

    private final BufferedImage[] WESTIMG1 =
            {
                    GameImageHandler.getGuestImg(1, 3, 0),
                    GameImageHandler.getGuestImg(1, 3, 1),
                    GameImageHandler.getGuestImg(1, 3, 2),
                    GameImageHandler.getGuestImg(1, 3, 3),
                    GameImageHandler.getGuestImg(1, 3, 4),
                    GameImageHandler.getGuestImg(1, 3, 5),
            };
    private final BufferedImage[] WESTIMG2 =
            {
                    GameImageHandler.getGuestImg(2, 3, 0),
                    GameImageHandler.getGuestImg(2, 3, 1),
                    GameImageHandler.getGuestImg(2, 3, 2),
                    GameImageHandler.getGuestImg(2, 3, 3),
                    GameImageHandler.getGuestImg(2, 3, 4),
                    GameImageHandler.getGuestImg(2, 3, 5),
            };
    private final BufferedImage[] WESTIMG3 =
            {
                    GameImageHandler.getGuestImg(3, 3, 0),
                    GameImageHandler.getGuestImg(3, 3, 1),
                    GameImageHandler.getGuestImg(3, 3, 2),
                    GameImageHandler.getGuestImg(3, 3, 3),
                    GameImageHandler.getGuestImg(3, 3, 4),
                    GameImageHandler.getGuestImg(3, 3, 5),
            };
    private final BufferedImage[] WESTIMG4 =
            {
                    GameImageHandler.getGuestImg(4, 3, 0),
                    GameImageHandler.getGuestImg(4, 3, 1),
                    GameImageHandler.getGuestImg(4, 3, 2),
                    GameImageHandler.getGuestImg(4, 3, 3),
                    GameImageHandler.getGuestImg(4, 3, 4),
                    GameImageHandler.getGuestImg(4, 3, 5),
            };

    @Override
    public GuestGraphics generateGraphics() {
        int variation = RAN.nextInt(4) + 1;

        return GuestGraphics.builder()
                .northImg(getNorthImage(variation))
                .eastImg(getEastImage(variation))
                .southImg(getSouthImage(variation))
                .westImg(getWestImage(variation))
                .build();
    }

    private BufferedImage[] getNorthImage(int variation) {
        switch (variation) {
            case 1:
                return NORTHIMG1;
            case 2:
                return NORTHIMG2;
            case 3:
                return NORTHIMG3;
            default:
                return NORTHIMG4;
        }
    }

    private BufferedImage[] getSouthImage(int variation) {
        switch (variation) {
            case 1:
                return SOUTHIMG1;
            case 2:
                return SOUTHIMG2;
            case 3:
                return SOUTHIMG3;
            default:
                return SOUTHIMG4;
        }
    }

    private BufferedImage[] getEastImage(int variation) {
        switch (variation) {
            case 1:
                return EASTIMG1;
            case 2:
                return EASTIMG2;
            case 3:
                return EASTIMG3;
            default:
                return EASTIMG4;
        }
    }

    private BufferedImage[] getWestImage(int variation) {
        switch (variation) {
            case 1:
                return WESTIMG1;
            case 2:
                return WESTIMG2;
            case 3:
                return WESTIMG3;
            default:
                return WESTIMG4;
        }
    }
}
