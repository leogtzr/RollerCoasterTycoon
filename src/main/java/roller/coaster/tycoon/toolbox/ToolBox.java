package roller.coaster.tycoon.toolbox;

import roller.coaster.tycoon.handler.GameImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolBox {

    private Polygon[] polygons;
    private BufferedImage[] icons;
    private BufferedImage buttonUp, buttonDown;
    private final int SLOTS = 6;
    private int primarySelected;
    private ScenaryWindow scenaryWindow;

    public ToolBox() {
        primarySelected = -1;
        scenaryWindow = new ScenaryWindow();
        buttonUp = GameImageHandler.getButtonImg(1, 0);
        buttonDown = GameImageHandler.getButtonImg(0, 0);
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
        icons[0] = GameImageHandler.getButtonImg(0, 1);
        icons[1] = GameImageHandler.getButtonImg(1, 1);
        icons[2] = GameImageHandler.getButtonImg(2, 1);
        icons[3] = GameImageHandler.getButtonImg(3, 1);
        icons[4] = GameImageHandler.getButtonImg(4, 1);
        icons[5] = GameImageHandler.getButtonImg(2, 0);
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

    public void diableAllwindows() {
        scenaryWindow.setVisible(false);
    }

    public boolean hitAnyWindows(int x, int y) {
        boolean b = false;

        if (scenaryWindow.isVisible()) {
            if (scenaryWindow.clickAt(x, y)) {
                b = true;
            }
        }
        return b;
    }

    public boolean dragAnyWindows(int x, int y) {
        boolean b = false;

        if (scenaryWindow.isVisible()) {
            if (scenaryWindow.dragClickAt(x, y)) {
                b = true;
            }
        }
        return b;
    }
}
