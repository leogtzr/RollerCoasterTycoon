package roller.coaster.tycoon.world;

import java.awt.*;

public class Highlight {

    private Polygon tileShape;

    public Highlight(Polygon tileShape) {
        this.tileShape = tileShape;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawPolygon(tileShape);
    }
}
