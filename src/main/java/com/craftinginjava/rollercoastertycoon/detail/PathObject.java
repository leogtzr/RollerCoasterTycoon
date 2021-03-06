package com.craftinginjava.rollercoastertycoon.detail;

import static com.craftinginjava.rollercoastertycoon.detail.TileObjectImages.*;

import java.awt.*;

public class PathObject extends TileObject {

    private int roadNumber;
    private int index;

    public PathObject(int type, int index, int roadNumber) {
        super(type, index);
        this.index = index;
        this.roadNumber = roadNumber;
    }

    @Override
    public void setRoadNumber(int roadNumber) {
        this.roadNumber = roadNumber;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        switch (index) {
            case 0:
                switch (roadNumber) {
                    case 0:
                        //NSEW
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 1:
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 2:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 3:
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 4:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 5:
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 6:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 7:
                        g.drawImage(getPavementStuffImage(6), x - 12, y - 5, null);
                        break;
                    case 8:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        break;
                    case 9:
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        break;
                    case 10:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        break;
                    case 11:
                        g.drawImage(getPavementStuffImage(5), x + 5, y - 15, null);
                        break;
                    case 12:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        break;
                    case 13:
                        g.drawImage(getPavementStuffImage(4), x + 5, y - 5, null);
                        break;
                    case 14:
                        g.drawImage(getPavementStuffImage(0), x - 12, y - 15, null);
                        break;
                }
                break;

            case 1:
                switch (roadNumber) {
                    case 0:
                        //NSEW
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        break;
                    case 1:
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 2:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        break;
                    case 3:
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        break;
                    case 4:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 5:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 6:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        break;
                    case 7:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 25, null);
                        break;
                    case 8:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 9:
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 10:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        break;
                    case 11:
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 35, null);
                        break;
                    case 12:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 13:
                        g.drawImage(getPavementStuffImage(1), x + 6, y - 25, null);
                        break;
                    case 14:
                        g.drawImage(getPavementStuffImage(1), x - 11, y - 35, null);
                        break;
                }
                break;

            case 2:
                switch (roadNumber) {
                    case 0:
                        //NSEW
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        break;
                    case 1:
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 2:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        break;
                    case 3:
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        break;
                    case 4:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 5:
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 6:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        break;
                    case 7:
                        g.drawImage(getPavementStuffImage(7), x - 12, y - 25, null);
                        break;
                    case 8:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 9:
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 10:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        break;
                    case 11:
                        g.drawImage(getPavementStuffImage(7), x + 5, y - 35, null);
                        break;
                    case 12:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 13:
                        g.drawImage(getPavementStuffImage(2), x + 5, y - 25, null);
                        break;
                    case 14:
                        g.drawImage(getPavementStuffImage(2), x - 12, y - 35, null);
                        break;
                }
                break;

            case 3:
                switch (roadNumber) {
                    case 0:
                        //NSEW
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 1:
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 2:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        break;
                    case 3:
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        break;
                    case 4:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 5:
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 6:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        break;
                    case 7:
                        g.drawImage(getPavementStuffImage(10), x - 17, y - 7, null);
                        break;
                    case 8:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 9:
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 10:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        break;
                    case 11:
                        g.drawImage(getPavementStuffImage(8), x, y - 17, null);
                        break;
                    case 12:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 13:
                        g.drawImage(getPavementStuffImage(9), x, y - 7, null);
                        break;
                    case 14:
                        g.drawImage(getPavementStuffImage(3), x - 17, y - 17, null);
                        break;
                }
                break;
        }
    }
}
