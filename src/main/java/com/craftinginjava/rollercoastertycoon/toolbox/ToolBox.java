package com.craftinginjava.rollercoastertycoon.toolbox;

<<<<<<< HEAD:src/main/java/roller/coaster/tycoon/toolbox/ToolBox.java
import static roller.coaster.tycoon.handler.GameImageHandler.*;
=======
import com.craftinginjava.rollercoastertycoon.handler.GameImageHandler;
>>>>>>> 914dd15ecce39390ca30c5fae156b6a89740e497:src/main/java/com/craftinginjava/rollercoastertycoon/toolbox/ToolBox.java

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolBox {

    private final int SLOTS = 6;
    private Polygon[] polygons;
    private BufferedImage[] icons;
    private BufferedImage buttonUp, buttonDown;
    private int primarySelected;
    private ScenaryWindow scenaryWindow;

    public ToolBox() {
        primarySelected = -1;
        scenaryWindow = new ScenaryWindow();
        buttonUp = getButtonImg(1, 0);
        buttonDown = getButtonImg(0, 0);
        icons = new BufferedImage[SLOTS];

        polygons = new Polygon[SLOTS];
        loadIcons();
        createPolygons();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < SLOTS; i++) {
            if (i == primarySelected) {
                g.drawImage(buttonDown, i * 30, 0, null);
            } else {
                g.drawImage(buttonUp, i * 30, 0, null);
            }

            g.drawImage(icons[i], i * 30, 0, null);
        }

        scenaryWindow.draw(g);
    }

    private void createPolygons() {
        for (int i = 0; i < polygons.length; i++) {
            int[] xPoints =
                    {
                            i * 30,
                            (i + 1) * 30,
                            (i + 1) * 30,
                            i * 30
                    };

            int[] yPoints =
                    {
                            00,
                            00,
                            30,
                            30
                    };

            polygons[i] = new Polygon(xPoints, yPoints, 4);
        }
    }

    public void click(int x, int y) {
        for (int i = 0; i < polygons.length; i++) {
            if (polygons[i].contains(x, y)) {
                primarySelected = i;
            }
        }
    }

    private void loadIcons() {
        icons[0] = getButtonImg(0, 1);
        icons[1] = getButtonImg(1, 1);
        icons[2] = getButtonImg(2, 1);
        icons[3] = getButtonImg(3, 1);
        icons[4] = getButtonImg(4, 1);
        icons[5] = getButtonImg(2, 0);
    }

    public int getSlots() {
        return SLOTS;
    }

    public int getPrimarySelected() {
        return primarySelected;
    }

    public void unSelectAll() {
        primarySelected = -1;
    }

    public ScenaryWindow getScenaryWindow() {
        return scenaryWindow;
    }

    public void diableAllWindows() {
        scenaryWindow.setVisible(false);
    }

    public boolean dragAnyWindows(int x, int y) {
        return scenaryWindow.isVisible() && scenaryWindow.dragClickAt(x, y);
    }
}
