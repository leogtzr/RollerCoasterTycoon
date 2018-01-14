package com.craftinginjava.rollercoastertycoon.handler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameImageHandler {

    private static final String IMAGES_PATH = "/images/";

    private static BufferedImage buttonImg, treeImg, ornamentImg, loadWindow, scenaryWindow, tiles;
    private static BufferedImage pavement, pavementStuff;
    private static BufferedImage guestImg1, guestImg2, guestImg3, guestImg4;

    public static BufferedImage getButtonImg(int x, int y) {
        return buttonImg.getSubimage(x * 30, y * 30, 30, 30);
    }

    public static BufferedImage getTreeImg(int x, int y) {
        return treeImg.getSubimage(x * 50, y * 115, 50, 115);
    }

    public static BufferedImage getOrnamentImg(int x, int y) {
        return ornamentImg.getSubimage(x * 50, y * 115, 50, 115);
    }

    public static BufferedImage getGuestImg(int number, int x, int y) {
        switch (number) {
            case (1): {
                return guestImg1.getSubimage(x * 12, y * 19, 12, 19);
            }
            case (2): {
                return guestImg2.getSubimage(x * 12, y * 19, 12, 19);
            }
            case (3): {
                return guestImg3.getSubimage(x * 12, y * 19, 12, 19);
            }
            default: {
                return guestImg4.getSubimage(x * 12, y * 19, 12, 19);
            }
        }
    }

    public static BufferedImage getLoadImg() {
        return loadWindow;
    }

    public static BufferedImage getTile(int x, int y, int width, int height) {
        return tiles.getSubimage(x * 64, y * 64, width, height);
    }

    public static BufferedImage getPavementTile(int x, int y, int width, int height) {
        return pavement.getSubimage(x * 64, y * 49, width, height);
    }

    public static BufferedImage getPavementStuff(int x, int y, int width, int height) {
        return pavementStuff.getSubimage(x, y, width, height);
    }

    public static BufferedImage getScenaryWindow(boolean main) {
        if (main) {
            return scenaryWindow.getSubimage(0, 0, 275, 149);
        } else {
            return scenaryWindow.getSubimage(275, 0, 68, 81);
        }
    }

    public void loadImages() throws IOException {
        tiles = loadImages("grassTiles.png");
        treeImg = loadImages("treeset.png");
        pavement = loadImages("pavement.png");
        buttonImg = loadImages("tileset.png");
        loadWindow = loadImages("saveBox.png");
        guestImg1 = loadImages("guestSet1.png");
        guestImg2 = loadImages("guestSet2.png");
        guestImg3 = loadImages("guestSet3.png");
        guestImg4 = loadImages("guestSet4.png");
        ornamentImg = loadImages("ornamentSet.png");
        pavementStuff = loadImages("pavementStuff.png");
        scenaryWindow = loadImages("scenaryWindow.png");
    }

    private BufferedImage loadImages(String imageName) throws IOException {
        return ImageIO.read(this.getClass().getResourceAsStream(IMAGES_PATH + imageName));
    }
}
