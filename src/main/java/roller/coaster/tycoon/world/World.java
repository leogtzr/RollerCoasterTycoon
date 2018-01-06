package roller.coaster.tycoon.world;

import roller.coaster.tycoon.guest.Guest;
import roller.coaster.tycoon.guest.GuestFactory;
import roller.coaster.tycoon.guest.GuestImageProvider;
import roller.coaster.tycoon.handler.GameImageHandler;
import roller.coaster.tycoon.tile.Tile;
import roller.coaster.tycoon.toolbox.LoadSaveWindow;
import roller.coaster.tycoon.toolbox.ToolBox;
import roller.coaster.tycoon.worldGen.WorldGen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class World {

    private final int SIZE = 30;
    List<Tile> tilesList;
    private Point movePoint;
    private ToolBox toolbox;
    private Tile[][] tiles;
    private Highlight highlight;
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
        for (int i = 0; i < tiles.length; i++) {
            for (int j = tiles[i].length - 1; j >= 0; j--) {
                if (tiles[j][i] != null) {
                    tiles[j][i].draw(g, x0, y0);
                }
            }
        }
        if (highlight != null) {
            highlight.draw(g);
        }

        toolbox.draw(g);
        loadSaveWindow.draw(g);
    }

    public void makePavement(int x, int y, int mouse) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getPolygon().contains(x, y)) {
                    tiles[i][j].makePavement(mouse);


                    if (mouse == 16) {
                        if (j + 1 < 30) {
                            tiles[i][j + 1].addAsNeighbor(tiles[i][j], 'N');
                            tiles[i][j].addAsNeighbor(tiles[i][j + 1], 'S');
                        }

                        if (j - 1 >= 0) {
                            tiles[i][j - 1].addAsNeighbor(tiles[i][j], 'S');
                            tiles[i][j].addAsNeighbor(tiles[i][j - 1], 'N');
                        }

                        if (i + 1 < 30) {
                            tiles[i + 1][j].addAsNeighbor(tiles[i][j], 'W');
                            tiles[i][j].addAsNeighbor(tiles[i + 1][j], 'E');
                        }
                        if (i - 1 >= 0) {
                            tiles[i - 1][j].addAsNeighbor(tiles[i][j], 'E');
                            tiles[i][j].addAsNeighbor(tiles[i - 1][j], 'W');
                        }
                        return;
                    }
                }
            }
        }
    }

    public void raise(int x, int y, int mouse) {
        int ammount = 0;
        if (mouse == 16) {
            ammount = -16;
        } else {
            ammount = 16;
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getPolygon().contains(x, y)) {
                    tiles[i][j].click(ammount, ammount, ammount, ammount);


                    if (i + 1 < 30 && j + 1 < 30) {
                        tiles[i + 1][j + 1].click(0, 0, 0, ammount);
                    }

                    if (i - 1 >= 0 && j + 1 < 30) {
                        tiles[i - 1][j + 1].click(ammount, 0, 0, 0);
                    }

                    if (j + 1 < 30) {
                        tiles[i][j + 1].click(ammount, 0, 0, ammount);
                    }

                    if (i + 1 < 30 && j - 1 >= 0) {
                        tiles[i + 1][j - 1].click(0, ammount, 0, 0);
                    }

                    if (j - 1 >= 0) {
                        tiles[i][j - 1].click(0, ammount, ammount, 0);
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        tiles[i - 1][j - 1].click(0, 0, ammount, 0);
                    }
                    if (i + 1 < 30) {
                        tiles[i + 1][j].click(0, ammount, 0, ammount);
                    }
                    if (i - 1 >= 0) {
                        tiles[i - 1][j].click(ammount, 0, ammount, 0);
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

    public void moveWorld(int x0, int y0) {
        this.x0 = this.x0 + x0;
        this.y0 = this.y0 + y0;
    }

    public void clickToolBox(int x, int y) {
        toolbox.diableAllwindows();

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
        WorldGen worldGen = new WorldGen();
        worldGen.loadImage(worldName);
        worldGen.loadWorld(tiles);
    }

    public void setHighlightedTile(int x, int y) {
        highlight = null;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getPolygon().contains(x, y)) {
                    highlight = new Highlight(tiles[i][j].getPolygon());
                    return;
                }
            }
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


    public void saveWorld(String string) {
        if (string.isEmpty()) {
            string = "map";
        }
        WorldGen worldGen = new WorldGen(tiles);
        worldGen.saveWorldNew(string);
    }

    public void dragAt(int x, int y, int modifier) {
        if (!toolbox.dragAnyWindows(x, y)) {
            // <editor-fold defaultstate="collapsed" desc="Object placement - 3">
            if (toolbox.getPrimarySelected() == 3) {
                placeObjectAt(x, y, modifier, toolbox.getScenaryWindow().getSelectedButton(), toolbox.getScenaryWindow().
                        getSelectedIndex());
            }// </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Movement - 2">
            if (toolbox.getPrimarySelected() == 2) {
                moveWorld((int) (x - movePoint.getX()), (int) (y - movePoint.getY()));
                movePoint.setLocation(x, y);
            }// </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Pavement - 1">
            if (toolbox.getPrimarySelected() == 1) {
                makePavement(x, y, modifier);
            }// </editor-fold>
//
//            // <editor-fold defaultstate="collapsed" desc="Raising/Lowing - 0">
//            if (roller.coaster.tycoon.toolbox.getPrimarySelected() == 0)
//            {
//                raise(x, y, modifier);
//            }// </editor-fold>
        }

    }

    public void pressAt(int x, int y, int modifier) {
        if (!toolbox.dragAnyWindows(x, y)) {
            // <editor-fold defaultstate="collapsed" desc="Guests - 4">
            if (toolbox.getPrimarySelected() == 4) {
                addGuestAt(x, y);
            }// </editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Movement - 2">
            if (toolbox.getPrimarySelected() == 2) {
                movePoint.setLocation(x, y);
            }// </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Pavement - 1">
            if (toolbox.getPrimarySelected() == 1) {
                makePavement(x, y, modifier);
            }// </editor-fold>
        }
    }

    public void pressAndClickAt(int x, int y, int modifier) throws Exception {
        boolean hitWindow = false;

        if (x <= toolbox.getSlots() * 30 && y <= 30) {
            toolbox.diableAllwindows();
            if (x >= (toolbox.getSlots() - 1) * 30) {
                loadSaveWindow.setEnabled(true);
            } else {
                loadSaveWindow.setEnabled(false);
            }

            clickToolBox(x, y);

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
            if (toolbox.getPrimarySelected() == 4) {
                //check if hit guest windiw
            }
            if (toolbox.getPrimarySelected() == 3) {
                hitWindow = toolbox.getScenaryWindow().clickAt(x, y);
            }
            if (toolbox.getPrimarySelected() == 2) {
                //Check if hit movement window
            }
            if (toolbox.getPrimarySelected() == 1) {
                //Check if hit pavement window
            }
            if (toolbox.getPrimarySelected() == 0) {
                //Check if hit landscape window
            }

            if (!hitWindow) {
                // <editor-fold defaultstate="collapsed" desc="Object placement - 3">
                if (toolbox.getPrimarySelected() == 3) {
                    placeObjectAt(x, y, modifier, toolbox.getScenaryWindow().getSelectedButton(), toolbox.getScenaryWindow().
                            getSelectedIndex());
                }// </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Pavement - 1">
                if (toolbox.getPrimarySelected() == 1) {
                    makePavement(x, y, modifier);
                }// </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Raising/Lowing - 0">
                if (toolbox.getPrimarySelected() == 0) {
                    raise(x, y, modifier);
                }// </editor-fold>
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
