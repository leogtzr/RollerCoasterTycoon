package handler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageHandler
{

    private static BufferedImage buttonImg, treeImg, ornamentImg, loadWindow, scenaryWindow, tiles;
    private static BufferedImage pavement, pavementStuff;
    private static BufferedImage guestImg1, guestImg2, guestImg3, guestImg4;

    public static BufferedImage getButtonImg(int x, int y)
    {
        return buttonImg.getSubimage(x * 30, y * 30, 30, 30);
    }

    public static BufferedImage getTreeImg(int x, int y)
    {
        return treeImg.getSubimage(x * 50, y * 115, 50, 115);
    }

    public static BufferedImage getOrnamentImg(int x, int y)
    {
        return ornamentImg.getSubimage(x * 50, y * 115, 50, 115);
    }

    public static BufferedImage getGuestImg(int number, int x, int y)
    {
        switch (number)
        {
            case (1):
            {
                return guestImg1.getSubimage(x * 12, y * 19, 12, 19);
            }
            case (2):
            {
                return guestImg2.getSubimage(x * 12, y * 19, 12, 19);
            }
            case (3):
            {
                return guestImg3.getSubimage(x * 12, y * 19, 12, 19);
            }
            default:
            {
                return guestImg4.getSubimage(x * 12, y * 19, 12, 19);
            }
        }
    }

    public static BufferedImage getLoadImg()
    {
        return loadWindow;
    }

    public static BufferedImage getTile(int x, int y, int width, int height)
    {
        return tiles.getSubimage(x * 64, y * 64, width, height);
    }

    public static BufferedImage getPavementTile(int x, int y, int width, int height)
    {
        return pavement.getSubimage(x * 64, y * 49, width, height);
    }

    public static BufferedImage getPavementStuff(int x, int y, int width, int height)
    {
        return pavementStuff.getSubimage(x, y, width, height);
    }

    public static BufferedImage getScenaryWindow(boolean main)
    {
        if (main)
        {
            return scenaryWindow.getSubimage(0, 0, 275, 149);
        }
        else
        {
            return scenaryWindow.getSubimage(275, 0, 68, 81);
        }

    }

    public void loadImage() throws IOException
    {
        tiles = ImageIO.read(this.getClass().getResourceAsStream("tiles.png"));
        treeImg = ImageIO.read(this.getClass().getResourceAsStream("treeset.png"));
        pavement = ImageIO.read(this.getClass().getResourceAsStream("pavement.png"));
        buttonImg = ImageIO.read(this.getClass().getResourceAsStream("tileset.png"));
        loadWindow = ImageIO.read(this.getClass().getResourceAsStream("SaveBox.png"));
        guestImg1 = ImageIO.read(this.getClass().getResourceAsStream("GuestSet1.png"));
        guestImg2 = ImageIO.read(this.getClass().getResourceAsStream("GuestSet2.png"));
        guestImg3 = ImageIO.read(this.getClass().getResourceAsStream("GuestSet3.png"));
        guestImg4 = ImageIO.read(this.getClass().getResourceAsStream("GuestSet4.png"));
        ornamentImg = ImageIO.read(this.getClass().getResourceAsStream("OrnamentSet.png"));
        pavementStuff = ImageIO.read(this.getClass().getResourceAsStream("PavementStuff.png"));
        scenaryWindow = ImageIO.read(this.getClass().getResourceAsStream("ScenaryWindow.png"));
    }
}
