package com.craftinginjava.rollercoastertycoon.toolbox;

<<<<<<< HEAD:src/main/java/roller/coaster/tycoon/toolbox/ScenaryWindow.java
import roller.coaster.tycoon.detail.TileObjectImages;
import static roller.coaster.tycoon.handler.GameImageHandler.*;
=======
import com.craftinginjava.rollercoastertycoon.handler.GameImageHandler;
import com.craftinginjava.rollercoastertycoon.detail.TileObjectImages;
>>>>>>> 914dd15ecce39390ca30c5fae156b6a89740e497:src/main/java/com/craftinginjava/rollercoastertycoon/toolbox/ScenaryWindow.java

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScenaryWindow {

    private final int x = 50;
    private final int y = 50;
    private final int ICONS = 3;
    private Polygon shape;
    private Polygon[] buttons;
    private Polygon[] indexes;
    private Polygon[] leftRight;
    private int group;
    private BufferedImage[] icons;
    private BufferedImage window;
    private BufferedImage selectButtonUp, selectButtonDown;
    private BufferedImage indexButtonDown;
    private boolean visible;
    private int selectedButton;
    private int selectedIndex;

    public ScenaryWindow() {
        leftRight = new Polygon[2];
        group = 0;
        indexes = new Polygon[4];
        buttons = new Polygon[ICONS];
        createPolygon();
        selectButtonUp = getButtonImg(4, 0);
        selectButtonDown = getButtonImg(3, 0);
        indexButtonDown = getScenaryWindow(false);

        selectedButton = 0;
        selectedIndex = 0;
        visible = false;
        window = getScenaryWindow(true);
        icons = new BufferedImage[ICONS];

        loadImages();
    }

    public void draw(Graphics g) {
        if (visible) {
            g.drawImage(window, 50, 50, null);

            for (int i = 0; i < icons.length; i++) {
                if (selectedButton == i) {
                    g.drawImage(selectButtonDown, x + 4 + (i * 30), y + 18, null);
                } else {
                    g.drawImage(selectButtonUp, x + 4 + (i * 30), y + 18, null);
                }

                g.drawImage(icons[i], x + 4 + (i * 30), y + 17, null);
            }

            for (int i = 0; i < indexes.length; i++) {
                if (selectedIndex == i) {
                    g.drawImage(indexButtonDown, x + 4 + (i * 66), y + 51, null);
                }
            }

            for (int i = 0; i < indexes.length; i++) {
                if (selectedButton == 0) {
                    g.drawImage(TileObjectImages.getTreeImage(i + (group * 4)).getScaledInstance(34, 78,
                            BufferedImage.SCALE_FAST), x + 25 + (65 * i), y + 53, null);
                }
                if (selectedButton == 1) {
                    g.drawImage(TileObjectImages.getPavementStuffImage(i + (group * 4)).getScaledInstance(34, 78,
                            BufferedImage.SCALE_FAST), x + 25 + (65 * i), y + 53, null);
                }
                if (selectedButton == 2) {
                    g.drawImage(TileObjectImages.getOrnamentImage(i + (group * 4)).getScaledInstance(34, 78,
                            BufferedImage.SCALE_FAST), x + 25 + (65 * i), y + 53, null);
                }
            }
        }
    }

    private void createPolygon() {
        int[] xPoints =
                {
                        x,
                        x + 275,
                        x + 275,
                        x
                };

        int[] yPoints =
                {
                        y,
                        y,
                        y + 149,
                        y + 149
                };

        shape = new Polygon(xPoints, yPoints, 4);


        for (int i = 0; i < buttons.length; i++) {
            yPoints = new int[]
                    {
                            y + 18, y + 18, y + 48, y + 48
                    };
            xPoints = new int[]
                    {
                            x + 4 + (i * 30), x + 33 + (i * 30), x + 33 + (i * 30), x + 4 + (i * 30)
                    };

            buttons[i] = new Polygon(xPoints, yPoints, 4);
        }


        for (int i = 0; i < indexes.length; i++) {
            yPoints = new int[]
                    {
                            y + 51, y + 51, y + 131, y + 131
                    };
            xPoints = new int[]
                    {
                            x + 5 + (i * 66), x + 70 + (i * 66), x + 70 + (i * 66), x + 5 + (i * 66)
                    };

            indexes[i] = new Polygon(xPoints, yPoints, 4);
        }

        for (int i = 0; i < leftRight.length; i++) {
            yPoints = new int[]
                    {
                            y + 131, y + 131, y + 141, y + 141
                    };
            xPoints = new int[]
                    {
                            x + 5 + (i * 132), x + 137 + (i * 132), x + 137 + (i * 132), x + 5 + (i * 132)
                    };

            leftRight[i] = new Polygon(xPoints, yPoints, 4);

        }

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean dragClickAt(int x, int y) {
        return shape.contains(x, y);
    }

    public boolean clickAt(int x, int y) {
        boolean b = false;
        if (shape.contains(x, y)) {
            b = true;
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].contains(x, y)) {
                    selectedButton = i;
                    group = 0;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (indexes[i].contains(x, y)) {
                    selectedIndex = i;
                }

            }
            if (leftRight[0].contains(x, y)) {
                group--;
                switch (selectedButton) {
                    case 0:
                        if (group <= -1) {
                            group = 8;
                        }
                        break;
                    case 1:
                        if (group <= -1) {
                            group = 0;
                        }
                        break;
                    case 2:
                        if (group <= -1) {
                            group = 1;
                        }
                        break;
                }

            }
            if (leftRight[1].contains(x, y)) {
                group++;
                switch (selectedButton) {
                    case 0:
                        if (group >= 9) {
                            group = 0;
                        }
                        break;
                    case 1:
                        if (group >= 1) {
                            group = 0;
                        }
                        break;
                    case 2:
                        if (group >= 2) {
                            group = 0;
                        }
                        break;
                }
            }
        }
        return b;
    }

    public int getSelectedButton() {
        return selectedButton;
    }

    public int getSelectedIndex() {
        return selectedIndex + (group * 4);
    }

    private void loadImages() {
        icons[0] = getButtonImg(0, 2);
        icons[1] = getButtonImg(1, 2);
        icons[2] = getButtonImg(2, 2);
    }
}
