package roller.coaster.tycoon.detail;

import roller.coaster.tycoon.handler.GameImageHandler;

import java.awt.image.BufferedImage;

public class TileObjectImages {

    private static final BufferedImage[] pavementStuff = new BufferedImage[]
            {
                    GameImageHandler.getPavementStuff(0, 0, 7, 11),
                    GameImageHandler.getPavementStuff(0, 11, 9, 32),
                    GameImageHandler.getPavementStuff(9, 11, 14, 32),
                    GameImageHandler.getPavementStuff(0, 43, 18, 18),
                    GameImageHandler.getPavementStuff(7, 0, 7, 11),
                    GameImageHandler.getPavementStuff(14, 0, 7, 11),
                    GameImageHandler.getPavementStuff(21, 0, 7, 11),
                    GameImageHandler.getPavementStuff(24, 11, 15, 32),
                    GameImageHandler.getPavementStuff(19, 43, 17, 17),
                    GameImageHandler.getPavementStuff(19, 60, 17, 15),
                    GameImageHandler.getPavementStuff(0, 60, 19, 15)
            };
    private static final BufferedImage[] ornaments = new BufferedImage[]
            {
                    GameImageHandler.getOrnamentImg(0, 0),
                    GameImageHandler.getOrnamentImg(1, 0),
                    GameImageHandler.getOrnamentImg(2, 0),
                    GameImageHandler.getOrnamentImg(3, 0),
                    GameImageHandler.getOrnamentImg(0, 1),
                    GameImageHandler.getOrnamentImg(1, 1),
                    GameImageHandler.getOrnamentImg(2, 1),
                    GameImageHandler.getOrnamentImg(3, 1)
            };
    private static final BufferedImage[] trees = new BufferedImage[]
            {
                    GameImageHandler.getTreeImg(0, 0),
                    GameImageHandler.getTreeImg(1, 0),
                    GameImageHandler.getTreeImg(2, 0),
                    GameImageHandler.getTreeImg(3, 0),
                    GameImageHandler.getTreeImg(4, 0),
                    GameImageHandler.getTreeImg(5, 0),
                    GameImageHandler.getTreeImg(6, 0),
                    GameImageHandler.getTreeImg(7, 0),
                    GameImageHandler.getTreeImg(8, 0),
                    GameImageHandler.getTreeImg(0, 1),
                    GameImageHandler.getTreeImg(1, 1),
                    GameImageHandler.getTreeImg(2, 1),
                    GameImageHandler.getTreeImg(3, 1),
                    GameImageHandler.getTreeImg(4, 1),
                    GameImageHandler.getTreeImg(5, 1),
                    GameImageHandler.getTreeImg(6, 1),
                    GameImageHandler.getTreeImg(7, 1),
                    GameImageHandler.getTreeImg(8, 1),
                    GameImageHandler.getTreeImg(0, 2),
                    GameImageHandler.getTreeImg(1, 2),
                    GameImageHandler.getTreeImg(2, 2),
                    GameImageHandler.getTreeImg(3, 2),
                    GameImageHandler.getTreeImg(4, 2),
                    GameImageHandler.getTreeImg(5, 2),
                    GameImageHandler.getTreeImg(6, 2),
                    GameImageHandler.getTreeImg(7, 2),
                    GameImageHandler.getTreeImg(8, 2),
                    GameImageHandler.getTreeImg(0, 3),
                    GameImageHandler.getTreeImg(1, 3),
                    GameImageHandler.getTreeImg(2, 3),
                    GameImageHandler.getTreeImg(3, 3),
                    GameImageHandler.getTreeImg(4, 3),
                    GameImageHandler.getTreeImg(5, 3),
                    GameImageHandler.getTreeImg(6, 3),
                    GameImageHandler.getTreeImg(7, 3),
                    GameImageHandler.getTreeImg(8, 3)
            };

    public static BufferedImage getTreeImage(int index) {
        return trees[index];
    }

    public static BufferedImage getOrnamentImage(int index) {
        return ornaments[index];
    }

    public static BufferedImage getPavementStuffImage(int index) {
        return pavementStuff[index];
    }
}
