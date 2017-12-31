package roller.coaster.tycoon.world;

import roller.coaster.tycoon.detail.PathObject;
import roller.coaster.tycoon.detail.TileObject;
import roller.coaster.tycoon.guests.Guest;
import roller.coaster.tycoon.handler.GameImageHandler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.util.LinkedList;

public class Tile {

    private static final Image[] pavementTiles = new Image[]
            {
                    GameImageHandler.getPavementTile(0, 0, 64, 32),
                    GameImageHandler.getPavementTile(1, 0, 64, 32),
                    GameImageHandler.getPavementTile(2, 0, 64, 32),
                    GameImageHandler.getPavementTile(3, 0, 64, 32),
                    GameImageHandler.getPavementTile(0, 1, 64, 32),
                    GameImageHandler.getPavementTile(1, 1, 64, 32),
                    GameImageHandler.getPavementTile(2, 1, 63, 32),
                    GameImageHandler.getPavementTile(3, 1, 64, 32),
                    GameImageHandler.getPavementTile(0, 2, 63, 32),
                    GameImageHandler.getPavementTile(1, 2, 64, 32),
                    GameImageHandler.getPavementTile(2, 2, 64, 32),
                    GameImageHandler.getPavementTile(3, 2, 64, 32),
                    GameImageHandler.getPavementTile(0, 3, 64, 32),
                    GameImageHandler.getPavementTile(1, 3, 64, 32),
                    GameImageHandler.getPavementTile(2, 3, 64, 32),
                    GameImageHandler.getPavementTile(3, 3, 64, 32),
                    GameImageHandler.getPavementTile(0, 4, 64, 49),
                    GameImageHandler.getPavementTile(1, 4, 64, 49),
                    GameImageHandler.getPavementTile(2, 4, 63, 16),
                    GameImageHandler.getPavementTile(3, 4, 63, 16)
            };
    private static final Image[] tiles = new Image[]
            {
                    GameImageHandler.getTile(0, 0, 64, 32),
                    GameImageHandler.getTile(1, 0, 64, 49),
                    GameImageHandler.getTile(2, 0, 64, 49),
                    GameImageHandler.getTile(3, 0, 63, 16),
                    GameImageHandler.getTile(0, 1, 63, 16),
                    GameImageHandler.getTile(1, 1, 63, 32),
                    GameImageHandler.getTile(2, 1, 63, 32),
                    GameImageHandler.getTile(3, 1, 64, 64),
                    GameImageHandler.getTile(0, 2, 63, 32),
                    GameImageHandler.getTile(1, 2, 64, 32),
                    GameImageHandler.getTile(2, 2, 64, 47),
                    GameImageHandler.getTile(3, 2, 62, 31),
                    GameImageHandler.getTile(0, 3, 64, 32),
                    GameImageHandler.getTile(1, 3, 64, 32),
                    GameImageHandler.getTile(2, 3, 64, 32),
                    GameImageHandler.getTile(3, 3, 64, 16),
                    GameImageHandler.getTile(0, 4, 64, 48)
            };
    private LinkedList<Guest> guestsOnTile;
    private Tile[] neighbors;
    private int x, y;
    private int x0, y0;
    private Polygon tileShape;
    private int northHeight, southHeight, eastHeight, westHeight;
    private boolean pavement;
    TileObject tileObject;

    public Tile(double xPos, double yPos) {
        northHeight = 0;
        southHeight = 0;
        eastHeight = 0;
        westHeight = 0;

        neighbors = new Tile[4];

        x = (int) (xPos * 64);
        y = (int) (yPos * 32);


        int[] xPoints =
                {
                        x - 32, x - 1, x, x + 31, x + 31, x, x - 1, x - 32
                };
        int[] yPoints =
                {
                        y - 1, y - 16, y - 16, y - 1, y, y + 15, y + 15, y
                };

        tileShape = new Polygon(xPoints, yPoints, 8);

        guestsOnTile = new LinkedList();
    }

    public void addAsNeighbor(Tile tile, char direction) {

        if (pavement && tile.isPavement()) {
//            System.out.println(x + ", " + y + " is now connected to " + direction + " - " + tile.getX() + ", "
//                    + tile.getY());


            switch (direction) {
                case ('N'): {
                    neighbors[0] = tile;
                    break;
                }
                case ('S'): {
                    neighbors[1] = tile;
                    break;
                }
                case ('E'): {
                    neighbors[2] = tile;
                    break;
                }
                case ('W'): {
                    neighbors[3] = tile;
                    break;
                }

            }
            if (tileObject != null) {
                tileObject.setRoadNumber(getNeighborValue());
            }
        }


    }

    public void removeNeighbor(int NSEW) {
        neighbors[NSEW] = null;
    }

    public Tile getNeighbor(int NSEW) {
        return neighbors[NSEW];
    }

    public Tile getNeighbor(char NSEW) {
        Tile tempTile = null;
        switch (NSEW) {
            case ('N'): {
                tempTile = neighbors[0];
                break;
            }
            case ('S'): {
                tempTile = neighbors[1];
                break;
            }
            case ('E'): {
                tempTile = neighbors[2];
                break;
            }
            case ('W'): {
                tempTile = neighbors[3];
                break;
            }
        }
        return tempTile;
    }

    public void draw(Graphics g, int x0, int y0) {
        this.x0 = x0;
        this.y0 = y0;
        remakeTile(x0, y0);

        //if (!(getYOnMap() >= 600 || getYOnMap() <= -15 || getXOnMap() <= -30 || getXOnMap() >= 800))
        {
            if (pavement) {
                drawPavement(g);
                for (int i = 0; i < guestsOnTile.size(); i++) {
                    if (guestsOnTile.get(i) != null) {
                        guestsOnTile.get(i).draw(g);
                    }
                }
            } else {
                drawTile(g);
            }
            if (tileObject != null) {
                tileObject.draw(g, x + x0, y + y0 + getDetailYOffset());
            }
        }
    }

    public int getXOnMap() {
        return x + x0;
    }

    public int getYOnMap() {
        return y + y0 + getAverageHeight();
    }

    public Polygon getPolygon() {
        return tileShape;
    }

    public void makePavement(int mouse) {
        if (mouse == 16 && tileObject == null) {
            pavement = true;
        }
        if (mouse == 4 && pavement) {
            pavement = false;
            for (int i = 0; i < neighbors.length; i++) {
                if (neighbors[i] != null) {
                    neighbors[i].removeMe(this);
                }
                neighbors[i] = null;
            }
            guestsOnTile.clear();
            tileObject = null;
        }
    }

    public boolean isPavement() {
        return pavement;
    }

    public void click(int n, int s, int e, int w) {
        changeNorth(n);
        changeSouth(s);
        changeEast(e);
        changeWest(w);

        remakeTile(0, 0);

        if (northHeight > 127) {
            northHeight = 127;
        }
        if (southHeight > 127) {
            southHeight = 127;
        }
        if (eastHeight > 127) {
            eastHeight = 127;
        }
        if (westHeight > 127) {
            westHeight = 127;
        }

        if (northHeight < -127) {
            northHeight = -127;
        }
        if (southHeight < -127) {
            southHeight = -127;
        }
        if (eastHeight < -127) {
            eastHeight = -127;
        }
        if (westHeight < -127) {
            westHeight = -127;
        }
    }

    private void remakeTile(int x0, int y0) {
        int[] xPoints =
                {
                        x - 32, x - 1, x, x + 31, x + 31, x, x - 1, x - 32
                };
        int[] yPoints =
                {
                        y - 1 + westHeight, y - 16 + northHeight, y - 16 + northHeight, y - 1 + eastHeight, y + eastHeight, y
                        + southHeight + 15, y + 15 + southHeight, y + westHeight
                };

        for (int i = 0; i < yPoints.length; i++) {
            yPoints[i] = yPoints[i] + y0;
            xPoints[i] = xPoints[i] + x0;
        }

//        tileShape.xpoints = xPoints;
//        tileShape.ypoints = yPoints;
        tileShape = new Polygon(xPoints, yPoints, 8);
    }

    public void changeNorth(int i) {
        northHeight = northHeight + i;

    }

    public boolean canGuestBePlaced(int x, int y) {
        return contains(x, y) && doesHaveNeighbors() && isPavement();
    }

    public boolean contains(int x, int y) {
        return getPolygon().contains(x, y);
    }

    public void changeSouth(int i) {
        southHeight = southHeight + i;
    }

    public void changeEast(int i) {
        eastHeight = eastHeight + i;
    }

    public void changeWest(int i) {
        westHeight = westHeight + i;
    }

    private int getDetailYOffset() {
        return (northHeight + southHeight + eastHeight + westHeight) / 4;
    }

    public void placeObject(int mouse, int type, int index) {
        if (mouse != 16) {
            tileObject = null;
        } else {
            if (pavement && type == 1) {
                tileObject = new PathObject(type, index, getNeighborValue());
            } else {
                if (!pavement && type != 1) {
                    tileObject = new TileObject(type, index);
                }
            }

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public LinkedList getGuestsOnTile() {
        return guestsOnTile;
    }

    public void addNewGuestToList(Guest guest) {
        guestsOnTile.add(guest);
    }

    public void addToList(Guest aThis) {
        if (pavement) {
            guestsOnTile.add(aThis);

            for (int i = 0; i < neighbors.length; i++) {
                if (neighbors[i] != null) {
                    neighbors[i].removeFromList(aThis);
                }
            }
        }
    }

    public void removeFromList(Guest guest) {
        guestsOnTile.remove(guest);
    }

    private void removeMe(Tile tile) {
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] != null) {
                if (neighbors[i].equals(tile)) {
//                    System.out.println(neighbors[i] + ", " + tile);
//
//                    System.out.println(x + ", " + y
//                            + " is no longer connected to direction '" + i + "'");
                    neighbors[i] = null;
                }
            }
        }
        if (tileObject != null) {
            tileObject.setRoadNumber(getNeighborValue());
        }
    }

    private int getAverageHeight() {
        return (northHeight + southHeight + eastHeight + westHeight) / 4;
    }

    public int getEastHeight() {
        return eastHeight;
    }

    public int getNorthHeight() {
        return northHeight;
    }

    public int getSouthHeight() {
        return southHeight;
    }

    public int getWestHeight() {
        return westHeight;
    }

    public void movePeople() {
        for (int i = 0; i < guestsOnTile.size(); i++) {
            guestsOnTile.get(i).move();
        }
    }

    public Tile[] getNeighbors() {
        return neighbors;
    }

    public boolean doesHaveNeighbors() {
        boolean b = false;
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] != null) {
                b = true;
            }
        }
        return b;
    }

    private boolean isFlat() {
        return northHeight == southHeight && northHeight == eastHeight && northHeight == westHeight;
    }

    private void drawTile(Graphics g) {
        if (isFlat()) {
            g.drawImage(tiles[0], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((northHeight == westHeight) && (southHeight == eastHeight) && (northHeight < southHeight)) {
            g.drawImage(tiles[1], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((northHeight == eastHeight) && (southHeight == westHeight) && (northHeight < southHeight)) {
            g.drawImage(tiles[2], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((northHeight == westHeight) && (southHeight == eastHeight) && (westHeight > southHeight)) {
            g.drawImage(tiles[3], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }
        if ((northHeight == eastHeight) && (southHeight == westHeight) && (northHeight > southHeight)) {
            g.drawImage(tiles[4], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight > westHeight) && (northHeight != westHeight) && (northHeight
                != eastHeight)) {
            g.drawImage(tiles[5], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight < westHeight) && (northHeight != westHeight) && (northHeight
                != eastHeight)) {
            g.drawImage(tiles[6], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (northHeight != eastHeight) && (southHeight
                != westHeight)) {
            g.drawImage(tiles[7], getXOnMap() - 32, getYOnMap() - 32, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight < westHeight) && (northHeight != eastHeight)) {
            g.drawImage(tiles[8], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight > westHeight) && (northHeight != westHeight)) {
            g.drawImage(tiles[9], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (eastHeight != northHeight)) {
            g.drawImage(tiles[10], getXOnMap() - 32, getYOnMap() - 27, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight == westHeight) && (northHeight > eastHeight)) {
            g.drawImage(tiles[11], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }

        if ((northHeight == southHeight) && (eastHeight == westHeight) && (northHeight < eastHeight)) {
            g.drawImage(tiles[12], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight == westHeight) && (southHeight < eastHeight)) {
            g.drawImage(tiles[0], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((northHeight == southHeight) && (westHeight > eastHeight) && (northHeight == eastHeight)) {
            g.drawImage(tiles[13], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }
        if ((northHeight == southHeight) && (westHeight < eastHeight) && (northHeight == westHeight)) {
            g.drawImage(tiles[14], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight > southHeight) && (southHeight == westHeight)) {
            g.drawImage(tiles[15], getXOnMap() - 32, getYOnMap() - 5, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (northHeight == westHeight)) {
            g.drawImage(tiles[16], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }

    }

    private void drawPavement(Graphics g) {
        int value = getNeighborValue();
        switch (value) {
            case (0): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[2], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight && northHeight == westHeight) {
                        g.drawImage(pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        if (southHeight == eastHeight) {
                            g.drawImage(pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                            break;
                        }
                        if (northHeight == eastHeight && northHeight < southHeight) {
                            g.drawImage(pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                        } else {
                            g.drawImage(pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                        }

                    }
                }

                break;
            }
            case (1): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[7], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (2): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[5], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (3): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[0], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }
                break;
            }
            case (4): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[4], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (5): {
                g.drawImage(pavementTiles[9], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (6): {
                g.drawImage(pavementTiles[10], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (7): {
                g.drawImage(pavementTiles[14], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (8): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[6], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (9): {
                g.drawImage(pavementTiles[8], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (10): {
                g.drawImage(pavementTiles[11], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (11): {
                g.drawImage(pavementTiles[12], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (12): {
                if (isFlat()) {
                    g.drawImage(pavementTiles[1], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (13): {
                g.drawImage(pavementTiles[13], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (14): {
                g.drawImage(pavementTiles[15], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (15): {
                g.drawImage(pavementTiles[3], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
        }
    }

    private int getNeighborValue() {
        int value = 0;


        if (neighbors[0] != null) {
            value = value + 1;
        }
        if (neighbors[1] != null) {
            value = value + 2;
        }
        if (neighbors[2] != null) {
            value = value + 4;
        }
        if (neighbors[3] != null) {
            value = value + 8;
        }

        return value;
    }
}
