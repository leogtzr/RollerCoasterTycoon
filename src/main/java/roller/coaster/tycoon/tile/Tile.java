package roller.coaster.tycoon.tile;

import roller.coaster.tycoon.detail.PathObject;
import roller.coaster.tycoon.detail.TileObject;
import roller.coaster.tycoon.guest.Direction;
import roller.coaster.tycoon.guest.Guest;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import static roller.coaster.tycoon.guest.Direction.*;

public class Tile {

    private LinkedList<Guest> guestsOnTile;
    private Map<Direction, Tile> neighborsMap;
    private int x, y;
    private int x0;
    private int y0;
    private Polygon tileShape;
    private int northHeight, southHeight, eastHeight, westHeight;
    private boolean pavement;
    TileObject tileObject;

    public Tile(double xPos, double yPos) {
        northHeight = 0;
        southHeight = 0;
        eastHeight = 0;
        westHeight = 0;

        neighborsMap = new HashMap<>(4);
        TileIsometry isometry = new TileIsometry(xPos, yPos);

        x = isometry.getX();
        y = isometry.getY();
        tileShape = isometry.getTileShape();
        guestsOnTile = new LinkedList<>();
    }

    public void addPavementTileAsNeighbor(Tile tile, Direction direction) {
        if (pavement && tile.isPavement()) {
            neighborsMap.put(direction, tile);

            if (tileObject != null) {
                tileObject.setRoadNumber(getNeighborValue());
            }
        }
    }

    public Tile getNeighbor(Direction direction) {
        return neighborsMap.get(direction);
    }

    public void draw(Graphics g, int x0, int y0) {
        this.x0 = x0;
        this.y0 = y0;
        remakeTile(x0, y0);

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
            removePavementFromTile();
        }
    }

    private void removePavementFromTile() {
        pavement = false;
        clearNeighbors();
        guestsOnTile.clear();
        tileObject = null;
    }

    private void clearNeighbors() {
        neighborsMap.values().forEach(neighbor -> neighbor.removeMe(this));
        neighborsMap.clear();
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

    public Set<Direction> getPossibleDirectionsFromTile() {
        return neighborsMap.keySet();
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

    public LinkedList getGuestsOnTile() {
        return guestsOnTile;
    }

    public void addNewGuestToList(Guest guest) {
        guestsOnTile.add(guest);
    }

    public void placeExistingGuestOnTile(Guest guest) {
        if (pavement) {
            guestsOnTile.add(guest);
            neighborsMap.values().forEach(neighbor -> neighbor.removeFromList(guest));
        }
    }

    public void removeFromList(Guest guest) {
        guestsOnTile.remove(guest);
    }

    private void removeMe(Tile tile) {
        for (Direction neighborsKey : neighborsMap.keySet()) {
            if (tile.equals(neighborsMap.get(neighborsKey))) {
                neighborsMap.remove(neighborsKey);
                break;
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

    public boolean doesHaveNeighbors() {
        return !neighborsMap.isEmpty();
    }

    private boolean isFlat() {
        return northHeight == southHeight && northHeight == eastHeight && northHeight == westHeight;
    }

    private void drawTile(Graphics g) {
        if (isFlat()) {
            g.drawImage(TileImages.tiles[0], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((northHeight == westHeight) && (southHeight == eastHeight) && (northHeight < southHeight)) {
            g.drawImage(TileImages.tiles[1], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((northHeight == eastHeight) && (southHeight == westHeight) && (northHeight < southHeight)) {
            g.drawImage(TileImages.tiles[2], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((northHeight == westHeight) && (southHeight == eastHeight) && (westHeight > southHeight)) {
            g.drawImage(TileImages.tiles[3], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }
        if ((northHeight == eastHeight) && (southHeight == westHeight) && (northHeight > southHeight)) {
            g.drawImage(TileImages.tiles[4], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight > westHeight) && (northHeight != westHeight) && (northHeight
                != eastHeight)) {
            g.drawImage(TileImages.tiles[5], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight < westHeight) && (northHeight != westHeight) && (northHeight
                != eastHeight)) {
            g.drawImage(TileImages.tiles[6], getXOnMap() - 32, getYOnMap() - 16, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (northHeight != eastHeight) && (southHeight
                != westHeight)) {
            g.drawImage(TileImages.tiles[7], getXOnMap() - 32, getYOnMap() - 32, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight < westHeight) && (northHeight != eastHeight)) {
            g.drawImage(TileImages.tiles[8], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight > westHeight) && (northHeight != westHeight)) {
            g.drawImage(TileImages.tiles[9], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (eastHeight != northHeight)) {
            g.drawImage(TileImages.tiles[10], getXOnMap() - 32, getYOnMap() - 27, null);
            return;
        }
        if ((northHeight == southHeight) && (eastHeight == westHeight) && (northHeight > eastHeight)) {
            g.drawImage(TileImages.tiles[11], getXOnMap() - 32, getYOnMap() - 8, null);
            return;
        }

        if ((northHeight == southHeight) && (eastHeight == westHeight) && (northHeight < eastHeight)) {
            g.drawImage(TileImages.tiles[12], getXOnMap() - 32, getYOnMap() - 24, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight == westHeight) && (southHeight < eastHeight)) {
            g.drawImage(TileImages.tiles[0], getXOnMap() - 32, getYOnMap() - 12, null);
            return;
        }
        if ((northHeight == southHeight) && (westHeight > eastHeight) && (northHeight == eastHeight)) {
            g.drawImage(TileImages.tiles[13], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }
        if ((northHeight == southHeight) && (westHeight < eastHeight) && (northHeight == westHeight)) {
            g.drawImage(TileImages.tiles[14], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight > southHeight) && (southHeight == westHeight)) {
            g.drawImage(TileImages.tiles[15], getXOnMap() - 32, getYOnMap() - 5, null);
            return;
        }
        if ((eastHeight == westHeight) && (northHeight < southHeight) && (northHeight == westHeight)) {
            g.drawImage(TileImages.tiles[16], getXOnMap() - 32, getYOnMap() - 20, null);
            return;
        }

    }

    private void drawPavement(Graphics g) {
        int value = getNeighborValue();
        switch (value) {
            case (0): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[2], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight && northHeight == westHeight) {
                        g.drawImage(TileImages.pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        if (southHeight == eastHeight) {
                            g.drawImage(TileImages.pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                            break;
                        }
                        if (northHeight == eastHeight && northHeight < southHeight) {
                            g.drawImage(TileImages.pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                        } else {
                            g.drawImage(TileImages.pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                        }

                    }
                }

                break;
            }
            case (1): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[7], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (2): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[5], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (3): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[0], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[17], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[18], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }
                break;
            }
            case (4): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[4], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (5): {
                g.drawImage(TileImages.pavementTiles[9], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (6): {
                g.drawImage(TileImages.pavementTiles[10], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (7): {
                g.drawImage(TileImages.pavementTiles[14], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (8): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[6], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (9): {
                g.drawImage(TileImages.pavementTiles[8], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (10): {
                g.drawImage(TileImages.pavementTiles[11], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (11): {
                g.drawImage(TileImages.pavementTiles[12], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (12): {
                if (isFlat()) {
                    g.drawImage(TileImages.pavementTiles[1], getXOnMap() - 32, getYOnMap() - 16, null);
                } else {
                    if (northHeight < southHeight) {
                        g.drawImage(TileImages.pavementTiles[16], getXOnMap() - 32, getYOnMap() - 24, null);
                    } else {
                        g.drawImage(TileImages.pavementTiles[19], getXOnMap() - 32, getYOnMap() - 8, null);
                    }
                }

                break;
            }
            case (13): {
                g.drawImage(TileImages.pavementTiles[13], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (14): {
                g.drawImage(TileImages.pavementTiles[15], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
            case (15): {
                g.drawImage(TileImages.pavementTiles[3], getXOnMap() - 32, getYOnMap() - 16, null);
                break;
            }
        }
    }

    private int getNeighborValue() {
        int value = 0;

        if (neighborsMap.containsKey(NORTH)) {
            value = value + 1;
        }
        if (neighborsMap.containsKey(SOUTH)) {
            value = value + 2;
        }
        if (neighborsMap.containsKey(EAST)) {
            value = value + 4;
        }
        if (neighborsMap.containsKey(WEST)) {
            value = value + 8;
        }
        return value;
    }
}
