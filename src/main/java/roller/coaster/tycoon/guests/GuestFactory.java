package roller.coaster.tycoon.guests;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import roller.coaster.tycoon.tile.Tile;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class GuestFactory {

    private static final AtomicInteger GUEST_ID_GENERATOR = new AtomicInteger(0);
    private static final int START_PROGRESS = 0;
    private static final char START_DIRECTION = ' ';

    private final GuestGraphicProvider graphicProvider;

    public Guest create(Tile startTile) {
        Preconditions.checkNotNull(startTile, "Starting tile for Guest mus not be null!");

        Guest guest = Guest.builder()
                .guestId(GUEST_ID_GENERATOR.incrementAndGet())
                .currentTile(startTile)
                .progress(START_PROGRESS)
                .direction(START_DIRECTION)
                .graphics(graphicProvider.generateGraphics())
                .build();

        guest.setUpDestination();
        return guest;
    }


}