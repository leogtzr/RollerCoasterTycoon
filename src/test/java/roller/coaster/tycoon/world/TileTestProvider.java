package roller.coaster.tycoon.world;

import roller.coaster.tycoon.tile.Tile;

public final class TileTestProvider {

    public static Tile pavementTileWithNeighbour(double xPos, double yPos) {
        Tile tile = new Tile(xPos, yPos);
        tile.makePavement(16);
        tile.addAsNeighbor(tile, 'N');
        return tile;
    }
}
