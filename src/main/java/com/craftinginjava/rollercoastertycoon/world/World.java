package com.craftinginjava.rollercoastertycoon.world;

import com.craftinginjava.rollercoastertycoon.handler.GameImageHandler;
import com.craftinginjava.rollercoastertycoon.toolbox.LoadSaveWindow;
import com.craftinginjava.rollercoastertycoon.toolbox.ToolBox;
import com.craftinginjava.rollercoastertycoon.guest.Guest;
import com.craftinginjava.rollercoastertycoon.guest.GuestFactory;
import com.craftinginjava.rollercoastertycoon.guest.GuestImageProvider;
import com.craftinginjava.rollercoastertycoon.tile.Tile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.craftinginjava.rollercoastertycoon.guest.Direction.*;

public class World {

    private final int SIZE = 30;
    List<Tile> tilesList;
    private Point movePoint;
    private ToolBox toolbox;
    private Tile[][] tiles;
    private TileHoverHighlight highlight;
    private int x0, y0;
    private LoadSaveWindow loadSaveWindow;
    private GuestFactory guestFactory;

    public World() throws IOException {
        GameImageHandler imageHandler = new GameImageHandler();
        imageHandler.loadImages();

        x0 = -550;
        y0 = 300;

        initializeTiles();

        toolbox = new ToolBox();
        movePoint = new Point(0, 0);
        loadSaveWindow = new LoadSaveWindow();
        guestFactory = new GuestFactory(new GuestImageProvider());
    }

    private void initializeTiles() {
        tiles = new Tile[SIZE][SIZE];
        tilesList = new ArrayList<Tile>(SIZE * SIZE);

        double helperX = 0.0d;
        double helperY = 0.0d;

        for (int i = 1; i < tiles.length + 1; i++) {
            for (int j = 1; j < tiles[i - 1].length + 1; j++) {
                Tile createdTile = new Tile(helperX, helperY);
                tiles[i - 1][j - 1] = createdTile;
                tilesList.add(createdTile);

                helperX = helperX + 0.5d;
                helperY = helperY + 0.5d;
            }
            helperX = i / 2d;
            helperY = -i / 2d;
        }
    }

    public void draw(Graphics g) {
        drawTiles(g);
        drawHighlight(g);
        toolbox.draw(g);
        loadSaveWindow.draw(g);
    }

    private void drawTiles(Graphics g) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = tiles[i].length - 1; j >= 0; j--) {
                if (tiles[j][i] != null) {
                    tiles[j][i].draw(g, x0, y0);
                }
            }
        }
    }

    private void drawHighlight(Graphics g) {
        if (highlight != null) {
            highlight.draw(g);
        }
    }

    public void makePavement(int x, int y, int mouse) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getPolygon().contains(x, y)) {
                    tiles[i][j].makePavement(mouse);


                    if (mouse == 16) {
                        if (j + 1 < 30) {
                            tiles[i][j + 1].addPavementTileAsNeighbor(tiles[i][j], NORTH);
                            tiles[i][j].addPavementTileAsNeighbor(tiles[i][j + 1], SOUTH);
                        }

                        if (j - 1 >= 0) {
                            tiles[i][j - 1].addPavementTileAsNeighbor(tiles[i][j], SOUTH);
                            tiles[i][j].addPavementTileAsNeighbor(tiles[i][j - 1], NORTH);
                        }

                        if (i + 1 < 30) {
                            tiles[i + 1][j].addPavementTileAsNeighbor(tiles[i][j], WEST);
                            tiles[i][j].addPavementTileAsNeighbor(tiles[i + 1][j], EAST);
                        }
                        if (i - 1 >= 0) {
                            tiles[i - 1][j].addPavementTileAsNeighbor(tiles[i][j], EAST);
                            tiles[i][j].addPavementTileAsNeighbor(tiles[i - 1][j], WEST);
                        }
                        return;
                    }
                }
            }
        }
    }

    public void raiseTile(int x, int y, int mouse) {
        int amount = mouse == 16 ? -16 : 16;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getPolygon().contains(x, y)) {
                    tiles[i][j].click(amount, amount, amount, amount);


                    if (i + 1 < 30 && j + 1 < 30) {
                        tiles[i + 1][j + 1].click(0, 0, 0, amount);
                    }

                    if (i - 1 >= 0 && j + 1 < 30) {
                        tiles[i - 1][j + 1].click(amount, 0, 0, 0);
                    }

                    if (j + 1 < 30) {
                        tiles[i][j + 1].click(amount, 0, 0, amount);
                    }

                    if (i + 1 < 30 && j - 1 >= 0) {
                        tiles[i + 1][j - 1].click(0, amount, 0, 0);
                    }

                    if (j - 1 >= 0) {
                        tiles[i][j - 1].click(0, amount, amount, 0);
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        tiles[i - 1][j - 1].click(0, 0, amount, 0);
                    }
                    if (i + 1 < 30) {
                        tiles[i + 1][j].click(0, amount, 0, amount);
                    }
                    if (i - 1 >= 0) {
                        tiles[i - 1][j].click(amount, 0, amount, 0);
                    }
                    return;
                }
            }
        }
    }

    public void reset() {
        double helperX = 0.0d;
        double helperY = 0.0d;

        for (int i = 1; i < tiles.length + 1; i++) {
            for (int j = 1; j < tiles[i - 1].length + 1; j++) {
                tiles[i - 1][j - 1] = new Tile(helperX, helperY);
                helperX = helperX + 0.5d;
                helperY = helperY + 0.5d;
            }
            helperX = i / 2d;
            helperY = -i / 2d;
        }
    }

    public void moveWorld(int xOffset, int yOffset) {
        this.x0 += xOffset;
        this.y0 += yOffset;
    }

    public void clickOnToolBox(int x, int y) {
        toolbox.diableAllWindows();
        toolbox.click(x, y);

        if (toolbox.getPrimarySelected() == 3) {
            toolbox.getScenaryWindow().setVisible(true);
        }
    }

    public void placeObjectAt(int x, int y, int mouse, int type, int index) {
        Optional<Tile> tileForObjectPlacement = tilesList.stream()
                .filter(t -> t.contains(x, y))
                .findFirst();
        if (tileForObjectPlacement.isPresent()) {
            tileForObjectPlacement.get().placeObject(mouse, type, index);
        }
    }

    public void loadWorld(String worldName) throws IOException {
        WorldGenerator worldGen = new WorldGenerator();
        worldGen.loadImage(worldName);
        worldGen.loadWorld(tiles);
    }

    public void setHighlightedTile(int x, int y) {
        highlight = null;

        Optional<Tile> highlightedTile = tilesList.stream()
                .filter(t -> t.contains(x, y))
                .findFirst();

        if (highlightedTile.isPresent()) {
            highlight = new TileHoverHighlight(highlightedTile.get().getPolygon());
        }
    }

    public void addGuestAt(int x, int y) {
        tilesList.stream()
                .filter(t -> t.canGuestBePlaced(x, y))
                .forEach(t -> {
                    Guest guest = guestFactory.create(t);
                    t.addNewGuestToList(guest);
                });
    }


    public void saveWorld(String saveName) {
        if (saveName.isEmpty()) {
            saveName = "map";
        }
        WorldGenerator worldGen = new WorldGenerator(tiles);
        worldGen.saveWorldNew(saveName);
    }

    public void dragAt(int x, int y, int modifier) {
        if (toolbox.dragAnyWindows(x, y)) {
            return;
        }

        if (toolbox.getPrimarySelected() == 3) {
            placeObjectAt(x, y, modifier, toolbox.getScenaryWindow().getSelectedButton(), toolbox.getScenaryWindow().
                    getSelectedIndex());
        }

        if (toolbox.getPrimarySelected() == 2) {
            moveWorld((int) (x - movePoint.getX()), (int) (y - movePoint.getY()));
            movePoint.setLocation(x, y);
        }

        if (toolbox.getPrimarySelected() == 1) {
            makePavement(x, y, modifier);
        }
    }

    public void pressAt(int x, int y, int modifier) {
        if (!toolbox.dragAnyWindows(x, y)) {
            if (toolbox.getPrimarySelected() == 4) {
                addGuestAt(x, y);
            }

            if (toolbox.getPrimarySelected() == 2) {
                movePoint.setLocation(x, y);
            }

            if (toolbox.getPrimarySelected() == 1) {
                makePavement(x, y, modifier);
            }
        }
    }

    public void pressAndClickAt(int x, int y, int modifier) throws Exception {
        boolean hitWindow = false;

        if (x <= toolbox.getSlots() * 30 && y <= 30) {
            toolbox.diableAllWindows();
            if (x >= (toolbox.getSlots() - 1) * 30) {
                loadSaveWindow.setEnabled(true);
            } else {
                loadSaveWindow.setEnabled(false);
            }

            clickOnToolBox(x, y);

            if (toolbox.getPrimarySelected() == 3) {
                toolbox.getScenaryWindow().setVisible(true);
            }
        } else {
            if (toolbox.getPrimarySelected() == 5) {
                switch (loadSaveWindow.clickAt(x, y)) {
                    case (0): {
                        loadSaveWindow.setEnabled(false);
                        saveWorld(LoadSaveWindow.getWRITER().getString());
                        toolbox.unSelectAll();
                        break;
                    }
                    case (1): {
                        try {
                            reset();
                            loadWorld(LoadSaveWindow.getWRITER().getString());
                        } catch (Exception ex) {
                            throw new Exception("World not found.");
                        }
                        toolbox.unSelectAll();
                        loadSaveWindow.setEnabled(false);
                        break;
                    }
                    case (2): {
                        toolbox.unSelectAll();
                        loadSaveWindow.setEnabled(false);
                        break;
                    }
                }
            }
            if (toolbox.getPrimarySelected() == 3) {
                hitWindow = toolbox.getScenaryWindow().clickAt(x, y);
            }

            if (!hitWindow) {
                if (toolbox.getPrimarySelected() == 3) {
                    placeObjectAt(x, y, modifier, toolbox.getScenaryWindow().getSelectedButton(), toolbox.getScenaryWindow().
                            getSelectedIndex());
                }

                if (toolbox.getPrimarySelected() == 1) {
                    makePavement(x, y, modifier);
                }

                if (toolbox.getPrimarySelected() == 0) {
                    raiseTile(x, y, modifier);
                }
            }
        }
    }

    public LoadSaveWindow getLoadSaveWindow() {
        return loadSaveWindow;
    }

    public void movePeople() {
        tilesList.forEach(Tile::movePeople);
    }
}
