package roller.coaster.tycoon.detail;

import static roller.coaster.tycoon.handler.GameImageHandler.*;

import java.awt.image.BufferedImage;

public class TileObjectImages {

    private TileObjectImages() {}

    private static final BufferedImage[] pavementStuff = new BufferedImage[]
            {
                    getPavementStuff(0, 0, 7, 11),
                    getPavementStuff(0, 11, 9, 32),
                    getPavementStuff(9, 11, 14, 32),
                    getPavementStuff(0, 43, 18, 18),
                    getPavementStuff(7, 0, 7, 11),
                    getPavementStuff(14, 0, 7, 11),
                    getPavementStuff(21, 0, 7, 11),
                    getPavementStuff(24, 11, 15, 32),
                    getPavementStuff(19, 43, 17, 17),
                    getPavementStuff(19, 60, 17, 15),
                    getPavementStuff(0, 60, 19, 15)
            };
    private static final BufferedImage[] ornaments = new BufferedImage[]
            {
                    getOrnamentImg(0, 0),
                    getOrnamentImg(1, 0),
                    getOrnamentImg(2, 0),
                    getOrnamentImg(3, 0),
                    getOrnamentImg(0, 1),
                    getOrnamentImg(1, 1),
                    getOrnamentImg(2, 1),
                    getOrnamentImg(3, 1)
            };
    private static final BufferedImage[] trees = new BufferedImage[]
            {
                    getTreeImg(0, 0),
                    getTreeImg(1, 0),
                    getTreeImg(2, 0),
                    getTreeImg(3, 0),
                    getTreeImg(4, 0),
                    getTreeImg(5, 0),
                    getTreeImg(6, 0),
                    getTreeImg(7, 0),
                    getTreeImg(8, 0),
                    getTreeImg(0, 1),
                    getTreeImg(1, 1),
                    getTreeImg(2, 1),
                    getTreeImg(3, 1),
                    getTreeImg(4, 1),
                    getTreeImg(5, 1),
                    getTreeImg(6, 1),
                    getTreeImg(7, 1),
                    getTreeImg(8, 1),
                    getTreeImg(0, 2),
                    getTreeImg(1, 2),
                    getTreeImg(2, 2),
                    getTreeImg(3, 2),
                    getTreeImg(4, 2),
                    getTreeImg(5, 2),
                    getTreeImg(6, 2),
                    getTreeImg(7, 2),
                    getTreeImg(8, 2),
                    getTreeImg(0, 3),
                    getTreeImg(1, 3),
                    getTreeImg(2, 3),
                    getTreeImg(3, 3),
                    getTreeImg(4, 3),
                    getTreeImg(5, 3),
                    getTreeImg(6, 3),
                    getTreeImg(7, 3),
                    getTreeImg(8, 3)
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
