package com.craftinginjava.rollercoastertycoon.guest;

import com.craftinginjava.rollercoastertycoon.tile.Tile;
import lombok.Builder;

import java.awt.*;
import java.util.Random;

@Builder
public class Guest {

    public static final int PROGRESS_REQUIRED_FOR_MOVE_TO_DESTINATION_FILE = 12;
    // private final static AtomicInteger GUEST_ID_GENERATOR = new AtomicInteger(0);
    private static final Random RAN = new Random();
    private static final double F = 0.0909d;

    private final int guestId;
    private final GuestGraphics graphics;
    private final GuestMoveDirectionLogic moveDirectionLogic;
    private Tile currentTile;
    private Tile destinationTile;
    private int progress;

    public synchronized void draw(Graphics g) {
        int tempX0 = currentTile.getXOnMap();
        int tempY0 = currentTile.getYOnMap();
        int tempX1 = destinationTile.getXOnMap();
        int tempY1 = destinationTile.getYOnMap();

        double drawX = tempX0 * (1 - (F * progress)) + tempX1 * (F * progress);
        double drawY = tempY0 * (1 - (F * progress)) + tempY1 * (F * progress);

        graphics.draw(g, moveDirectionLogic.getDirection(), drawX, drawY, progress);
    }

    public synchronized void setNewDestinationTile(Tile destinationTile) {
        this.destinationTile = destinationTile;

        Direction direction = moveDirectionLogic.getDirection();
        if (direction == Direction.WEST || direction == Direction.SOUTH) {
            this.destinationTile.placeExistingGuestOnTile(this);
        }
    }

    public void move() {
        progress++;

        if (progress >= PROGRESS_REQUIRED_FOR_MOVE_TO_DESTINATION_FILE) {
            progress = 0;
            Direction direction = moveDirectionLogic.getDirection();
            if (direction == Direction.EAST || direction == Direction.NORTH) {
                destinationTile.placeExistingGuestOnTile(this);
            }
            currentTile = destinationTile;
            setUpDestination();
        }
    }

    void setUpDestination() {
        moveDirectionLogic.updateDirection(currentTile);
        Tile destinationTile = moveDirectionLogic.getDestinationTileBasedOnDirectionFrom(currentTile);
        setNewDestinationTile(destinationTile);
    }

}
