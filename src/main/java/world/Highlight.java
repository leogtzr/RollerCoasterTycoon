package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Highlight
{

    private Polygon tileShape;

    public Highlight(Polygon tileShape)
    {
        this.tileShape = tileShape;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.drawPolygon(tileShape);
    }
}
