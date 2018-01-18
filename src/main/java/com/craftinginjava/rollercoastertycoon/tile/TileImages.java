package com.craftinginjava.rollercoastertycoon.tile;

import java.awt.*;

import static com.craftinginjava.rollercoastertycoon.handler.GameImageHandler.getPavementTile;
import static com.craftinginjava.rollercoastertycoon.handler.GameImageHandler.getTile;

class TileImages {

    static final Image[] pavementTiles = new Image[]
            {
                    getPavementTile(0, 0, 64, 32),
                    getPavementTile(1, 0, 64, 32),
                    getPavementTile(2, 0, 64, 32),
                    getPavementTile(3, 0, 64, 32),
                    getPavementTile(0, 1, 64, 32),
                    getPavementTile(1, 1, 64, 32),
                    getPavementTile(2, 1, 63, 32),
                    getPavementTile(3, 1, 64, 32),
                    getPavementTile(0, 2, 63, 32),
                    getPavementTile(1, 2, 64, 32),
                    getPavementTile(2, 2, 64, 32),
                    getPavementTile(3, 2, 64, 32),
                    getPavementTile(0, 3, 64, 32),
                    getPavementTile(1, 3, 64, 32),
                    getPavementTile(2, 3, 64, 32),
                    getPavementTile(3, 3, 64, 32),
                    getPavementTile(0, 4, 64, 49),
                    getPavementTile(1, 4, 64, 49),
                    getPavementTile(2, 4, 63, 16),
                    getPavementTile(3, 4, 63, 16)
            };

    static final Image[] tiles = new Image[]
            {
                    getTile(0, 0, 64, 32),
                    getTile(1, 0, 64, 49),
                    getTile(2, 0, 64, 49),
                    getTile(3, 0, 63, 16),
                    getTile(0, 1, 63, 16),
                    getTile(1, 1, 63, 32),
                    getTile(2, 1, 63, 32),
                    getTile(3, 1, 64, 64),
                    getTile(0, 2, 63, 32),
                    getTile(1, 2, 64, 32),
                    getTile(2, 2, 64, 47),
                    getTile(3, 2, 62, 31),
                    getTile(0, 3, 64, 32),
                    getTile(1, 3, 64, 32),
                    getTile(2, 3, 64, 32),
                    getTile(3, 3, 64, 16),
                    getTile(0, 4, 64, 48)
            };

}
