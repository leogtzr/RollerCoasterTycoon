package roller.coaster.tycoon.guests;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import roller.coaster.tycoon.world.Tile;

public class Guest {

    private static int totalGuests;
    private final int guestID;
    private static final Random RAN = new Random();
    private static final double F = 0.0909d;
    private Tile currentTile;
    private Tile destinationTile;
    private int progress;
    private char direction;
    private BufferedImage[] northImg = GuestImage.getNorthImage(RAN.nextInt(4) + 1);
    private BufferedImage[] southImg = GuestImage.getSouthImage(RAN.nextInt(4) + 1);
    private BufferedImage[] eastImg = GuestImage.getEastImage(RAN.nextInt(4) + 1);
    private BufferedImage[] westImg = GuestImage.getWestImage(RAN.nextInt(4) + 1);

    public Guest(Tile startTile) {
        totalGuests++;
        guestID = totalGuests;
        currentTile = startTile;
        destinationTile = null;
        progress = 0;
        direction = ' ';
        getDestination();

        int variation = RAN.nextInt(4) + 1;
        northImg = GuestImage.getNorthImage(variation);
        southImg = GuestImage.getSouthImage(variation);
        eastImg = GuestImage.getEastImage(variation);
        westImg = GuestImage.getWestImage(variation);
    }

    public synchronized void draw(Graphics g) {
        int tempX0 = currentTile.getXOnMap();
        int tempY0 = currentTile.getYOnMap();

        int tempX1 = destinationTile.getXOnMap();
        int tempY1 = destinationTile.getYOnMap();

        double drawX = tempX0 * (1 - (F * progress)) + tempX1 * (F * progress);
        double drawY = tempY0 * (1 - (F * progress)) + tempY1 * (F * progress);


        switch (direction) {
            case ('N'): {
                g.drawImage(northImg[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('S'): {
                g.drawImage(southImg[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('E'): {
                g.drawImage(eastImg[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('W'): {
                g.drawImage(westImg[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
        }
    }

    private int progressToIndex() {
        if (progress <= 5) {
            return progress;
        } else {
            return progress - 6;
        }

    }

    public synchronized void setNewDestination(Tile to) {

        destinationTile = to;

        if (direction == 'W' || direction == 'S') {
            destinationTile.addToList(this);
        }
    }

    public void move() {
        progress++;

        if (progress >= 12) {
            progress = 0;
            if (direction == 'E' || direction == 'N') {
                destinationTile.addToList(this);
            }
            currentTile = destinationTile;
            getDestination();
        }
    }

    private void getDestination() {
        String choises = "";

        if (currentTile.getNeighbor(0) != null) {
            choises = choises + "N";
        }
        if (currentTile.getNeighbor(1) != null) {
            choises = choises + "S";
        }
        if (currentTile.getNeighbor(2) != null) {
            choises = choises + "E";
        }
        if (currentTile.getNeighbor(3) != null) {
            choises = choises + "W";
        }


        if (direction == ' ') {
            direction = choises.charAt(RAN.nextInt(choises.length()));
            setNewDestination(currentTile.getNeighbor(direction));
        } else {
            switch (direction) {
                case ('N'): {
                    choises = choises.replace("S", "");
                    break;
                }
                case ('S'): {
                    choises = choises.replace("N", "");
                    break;
                }
                case ('E'): {
                    choises = choises.replace("W", "");
                    break;
                }
                case ('W'): {
                    choises = choises.replace("E", "");
                    break;
                }
            }
            if (choises.length() == 0) {
                //System.out.println("Hit a dead end. Trying again");
                if (currentTile.getNeighbor(0) != null) {
                    choises = choises + "N";
                }
                if (currentTile.getNeighbor(1) != null) {
                    choises = choises + "S";
                }
                if (currentTile.getNeighbor(2) != null) {
                    choises = choises + "E";
                }
                if (currentTile.getNeighbor(3) != null) {
                    choises = choises + "W";
                }
            }
            if (choises.length() != 0) {
                //System.out.println("Going " + direction + ". Can go " + choises);
                direction = choises.charAt(RAN.nextInt(choises.length()));
                setNewDestination(currentTile.getNeighbor(direction));
            }
        }
    }

    public int getGuestID() {
        return guestID;
    }

    public String getName() {
        return "Guest " + guestID;
    }
}
