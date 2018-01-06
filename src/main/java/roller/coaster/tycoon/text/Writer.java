package roller.coaster.tycoon.text;

import java.awt.*;

public class Writer {

    private static final Font FONT = new Font("Swis721 BlkEx BT", Font.PLAIN, 12);
    private String string;
    private int x, y;

    public Writer(String string, int x, int y) {
        this.string = string;
        this.x = x;
        this.y = y;
    }

    public void addToString(char toAdd) {
        if (toAdd == '') {
            if (string.length() >= 1) {
                string = string.substring(0, string.length() - 1);
            }
        } else {
            if (string.length() < 10) {
                string = string + toAdd;
            }

        }

    }

    public String getString() {
        return string;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(FONT);
        g2d.setColor(Color.BLACK);
        g2d.drawString(string, x, y);
        g2d.setColor(Color.WHITE);
        g2d.drawString(string, x - 1, y - 1);
    }
}
