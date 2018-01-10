package roller.coaster.tycoon.world;

import lombok.RequiredArgsConstructor;

import java.awt.*;

import static java.awt.Color.*;

@RequiredArgsConstructor
public class TileHoverHighlight {

    private final Polygon tileShape;

    public void draw(Graphics g) {
        g.setColor(YELLOW);
        g.drawPolygon(tileShape);
    }
}
