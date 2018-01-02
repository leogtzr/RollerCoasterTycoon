package roller.coaster.tycoon.guests;

import java.awt.Graphics;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Builder;
import roller.coaster.tycoon.tile.Tile;

@Builder
public class Guest {

    private final static AtomicInteger GUEST_ID_GENERATOR = new AtomicInteger(0);
    private static final Random RAN = new Random();
    private static final double F = 0.0909d;

    private final int guestId;
    private final GuestGraphics graphics;
    private Tile currentTile;
    private Tile destinationTile;
    private int progress;
    private char direction; //TODO remove (safe) this variable from code
    private MoveDirection moveDirection; //TODO move this field to moveDirLogic
    private GuestMoveDirectionLogic moveDirectionLogic;

    public synchronized void draw(Graphics g) {
        int tempX0 = currentTile.getXOnMap();
        int tempY0 = currentTile.getYOnMap();

        int tempX1 = destinationTile.getXOnMap();
        int tempY1 = destinationTile.getYOnMap();

        double drawX = tempX0 * (1 - (F * progress)) + tempX1 * (F * progress);
        double drawY = tempY0 * (1 - (F * progress)) + tempY1 * (F * progress);

        switch (direction) {
            case ('N'): {
                g.drawImage(graphics.getNorthImg()[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('S'): {
                g.drawImage(graphics.getSouthImg()[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('E'): {
                g.drawImage(graphics.getEastImg()[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
                break;
            }
            case ('W'): {
                g.drawImage(graphics.getWestImg()[progressToIndex()], (int) drawX - 6, (int) drawY - 19, null);
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

    public synchronized void setNewDestination(Tile destinationTile) {
        this.destinationTile = destinationTile;

        if (direction == 'W' || direction == 'S') {
            this.destinationTile.addToList(this);
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
            setUpDestination();
        }
    }

    void setUpDestination() {
        destinationTile = moveDirectionLogic.setUpDirectionAndGetTargetTileFrom(currentTile);
        direction = moveDirectionLogic.getDirection();
        moveDirection = moveDirectionLogic.getMoveDirection();
    }

    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return "Guest " + guestId;
    }
}
