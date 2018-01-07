package roller.coaster.tycoon.world;

import roller.coaster.tycoon.guest.MoveDirection;
import roller.coaster.tycoon.tile.Tile;

import static roller.coaster.tycoon.guest.MoveDirection.*;

public final class TileTestProvider {

    public static Tile pavementTileWithNeighbour(double xPos, double yPos) {
        Tile tile = pavementTile(xPos, yPos);
        tile.addPavementTileAsNeighbor(tile, NORTH);
        return tile;
    }

    public static Tile pavementTile(double xPos, double yPos) {
        Tile tile = new Tile(xPos, yPos);
        tile.makePavement(16);
        return tile;
    }
}
