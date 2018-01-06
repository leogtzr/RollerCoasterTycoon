package roller.coaster.tycoon.detail;

import java.awt.*;

public class TileObject {

    private int xPos, yPos;
    private Image img;

    public TileObject(int type, int index, int roadNumber) {
        if (type == 1) {
            img = TileObjectImages.getPavementStuffImage(index);
            xPos = 0;
            yPos = 0;
        }
    }

    public TileObject(int type, int index) {
        if (type == 0) {
            img = TileObjectImages.getTreeImage(index);
            xPos = -25;
            yPos = -110;
        }
        if (type == 1) {
            img = TileObjectImages.getPavementStuffImage(index);
        }
        if (type == 2) {
            img = TileObjectImages.getOrnamentImage(index);
            xPos = -25;
            yPos = -102;
        }
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x + xPos, y + yPos, null);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setRoadNumber(int roadNumber) {
        System.out.println("Not supported by " + this);
    }
}
