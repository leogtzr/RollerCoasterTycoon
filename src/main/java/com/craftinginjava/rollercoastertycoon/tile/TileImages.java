package com.craftinginjava.rollercoastertycoon.tile;

import com.craftinginjava.rollercoastertycoon.handler.GameImageHandler;

import java.awt.*;

class TileImages {

    static final Image[] pavementTiles = new Image[]
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

    static final Image[] tiles = new Image[]
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

}
