package com.craftinginjava.rollercoastertycoon.world;

import com.craftinginjava.rollercoastertycoon.guest.Direction;
import com.craftinginjava.rollercoastertycoon.tile.Tile;

public final class TileTestProvider {

    public static Tile pavementTileWithNeighbour(double xPos, double yPos) {
        Tile tile = pavementTile(xPos, yPos);
        tile.addPavementTileAsNeighbor(tile, Direction.NORTH);
        return tile;
    }

    public static Tile pavementTile(double xPos, double yPos) {
        Tile tile = new Tile(xPos, yPos);
        tile.makePavement(16);
        return tile;
    }
}
