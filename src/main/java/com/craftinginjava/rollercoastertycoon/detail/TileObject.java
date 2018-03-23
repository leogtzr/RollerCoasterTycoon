package com.craftinginjava.rollercoastertycoon.detail;

import java.awt.*;

public class TileObject {

    private int xPos, yPos;
    private Image img;

    public TileObject(int type, int index) {

        switch (type) {
            case 0:
                img = TileObjectImages.getTreeImage(index);
                xPos = -25;
                yPos = -110;
                break;
            case 1:
                img = TileObjectImages.getPavementStuffImage(index);
                break;
            case 2:
                img = TileObjectImages.getOrnamentImage(index);
                xPos = -25;
                yPos = -102;
                break;
        }
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x + xPos, y + yPos, null);
    }

    public void setRoadNumber(int roadNumber) {
        System.out.println("Not supported by " + this);
    }
}
