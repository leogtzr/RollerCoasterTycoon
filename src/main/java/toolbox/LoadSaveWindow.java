package toolbox;

import handler.GameImageHandler;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import text.Writer;

public class LoadSaveWindow
{

    private BufferedImage img;
    private static final Writer WRITER = new Writer("", 157, 133);
    private int x;
    private int y;
    private Polygon[] polygons;
    private boolean enabled;

    public LoadSaveWindow()
    {
        enabled = false;
        polygons = new Polygon[3];
        img = GameImageHandler.getLoadImg();
        x = 100;
        y = 100;
        createPolygons();
    }

    public static Writer getWRITER()
    {
        return WRITER;
    }

    public void draw(Graphics g)
    {
        if (enabled)
        {
            g.drawImage(img, x, y, null);
            WRITER.draw(g);
        }
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    

    private void createPolygons()
    {
        int xPoint[] =
        {
            x + 1, x + 50, x + 50, x + 1
        };
        int yPoint[] =
        {
            y + 43, y + 43, y + 56, y + 56
        };

        polygons[0] = new Polygon(xPoint, yPoint, 4);


        for (int i = 0; i < yPoint.length; i++)
        {
            xPoint[i] = xPoint[i] + 56;
        }
        polygons[1] = new Polygon(xPoint, yPoint, 4);

        for (int i = 0; i < yPoint.length; i++)
        {
            xPoint[i] = xPoint[i] + 54;
        }
        polygons[2] = new Polygon(xPoint, yPoint, 4);
    }

    public int clickAt(int x, int y)
    {
        int temp = -1;
        for (int i = 0; i < polygons.length; i++)
        {
            if (polygons[i].contains(x, y))
            {
                temp = i;
            }

        }
        return temp;
    }
}
