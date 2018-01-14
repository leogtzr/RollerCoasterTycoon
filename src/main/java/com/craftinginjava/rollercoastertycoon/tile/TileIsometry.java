package com.craftinginjava.rollercoastertycoon.tile;

import lombok.Getter;

import java.awt.*;

@Getter
class TileIsometry {

    private static final int N_POINTS = 8;

    private final int x;
    private final int y;
    private final Polygon tileShape;

    TileIsometry(double xPos, double yPos) {
        this.x = (int) (xPos * 64);
        this.y = (int) (yPos * 32);
        this.tileShape = new Polygon(xPoints(), yPoints(), N_POINTS);
    }

    private int[] xPoints() {
        return new int[] {
                x - 32, x - 1, x, x + 31, x + 31, x, x - 1, x - 32
        };
    }

    private int[] yPoints() {
        return new int[] {
                y - 1, y - 16, y - 16, y - 1, y, y + 15, y + 15, y
        };
    }

}
