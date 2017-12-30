package detail;

import handler.ImageHandler;
import java.awt.image.BufferedImage;

public class TileObjectImages
{

    private static final BufferedImage[] pavementStuff = new BufferedImage[]
    {
        ImageHandler.getPavementStuff(0, 0, 7, 11),
        ImageHandler.getPavementStuff(0, 11, 9, 32),
        ImageHandler.getPavementStuff(9, 11, 14, 32),
        ImageHandler.getPavementStuff(0, 43, 18, 18),
        ImageHandler.getPavementStuff(7, 0, 7, 11),
        ImageHandler.getPavementStuff(14, 0, 7, 11),
        ImageHandler.getPavementStuff(21, 0, 7, 11),
        ImageHandler.getPavementStuff(24, 11, 15, 32),
        ImageHandler.getPavementStuff(19, 43, 17, 17),
        ImageHandler.getPavementStuff(19, 60, 17, 15),
        ImageHandler.getPavementStuff(0, 60, 19, 15)
    };
    private static final BufferedImage[] ornaments = new BufferedImage[]
    {
        ImageHandler.getOrnamentImg(0, 0),
        ImageHandler.getOrnamentImg(1, 0),
        ImageHandler.getOrnamentImg(2, 0),
        ImageHandler.getOrnamentImg(3, 0),
        ImageHandler.getOrnamentImg(0, 1),
        ImageHandler.getOrnamentImg(1, 1),
        ImageHandler.getOrnamentImg(2, 1),
        ImageHandler.getOrnamentImg(3, 1)
    };
    private static final BufferedImage[] trees = new BufferedImage[]
    {
        ImageHandler.getTreeImg(0, 0),
        ImageHandler.getTreeImg(1, 0),
        ImageHandler.getTreeImg(2, 0),
        ImageHandler.getTreeImg(3, 0),
        ImageHandler.getTreeImg(4, 0),
        ImageHandler.getTreeImg(5, 0),
        ImageHandler.getTreeImg(6, 0),
        ImageHandler.getTreeImg(7, 0),
        ImageHandler.getTreeImg(8, 0),
        ImageHandler.getTreeImg(0, 1),
        ImageHandler.getTreeImg(1, 1),
        ImageHandler.getTreeImg(2, 1),
        ImageHandler.getTreeImg(3, 1),
        ImageHandler.getTreeImg(4, 1),
        ImageHandler.getTreeImg(5, 1),
        ImageHandler.getTreeImg(6, 1),
        ImageHandler.getTreeImg(7, 1),
        ImageHandler.getTreeImg(8, 1),
        ImageHandler.getTreeImg(0, 2),
        ImageHandler.getTreeImg(1, 2),
        ImageHandler.getTreeImg(2, 2),
        ImageHandler.getTreeImg(3, 2),
        ImageHandler.getTreeImg(4, 2),
        ImageHandler.getTreeImg(5, 2),
        ImageHandler.getTreeImg(6, 2),
        ImageHandler.getTreeImg(7, 2),
        ImageHandler.getTreeImg(8, 2),
        ImageHandler.getTreeImg(0, 3),
        ImageHandler.getTreeImg(1, 3),
        ImageHandler.getTreeImg(2, 3),
        ImageHandler.getTreeImg(3, 3),
        ImageHandler.getTreeImg(4, 3),
        ImageHandler.getTreeImg(5, 3),
        ImageHandler.getTreeImg(6, 3),
        ImageHandler.getTreeImg(7, 3),
        ImageHandler.getTreeImg(8, 3)
    };

    public static BufferedImage getTreeImage(int index)
    {
        return trees[index];
    }

    public static BufferedImage getOrnamentImage(int index)
    {
        return ornaments[index];
    }

    public static BufferedImage getPavementStuffImage(int index)
    {
        return pavementStuff[index];
    }
}
