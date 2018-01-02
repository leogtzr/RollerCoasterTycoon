package roller.coaster.tycoon.guest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;

@Getter
@RequiredArgsConstructor
public enum MoveDirection {

    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W'),
    UNDEFINED(' ');

    private final char directionChar;

    /**
     * This method and direction chars are temporary for backwards compability.
     * All chars representations will be replaced by this enum, but this changes
     * should be done incrementally.
     */
    public static MoveDirection fromChar(char directionChar) {
         return EnumSet.allOf(MoveDirection.class)
                    .stream()
                    .filter(dir -> dir.directionChar == directionChar)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Direction char is not valid. Could not cast to MoveDirection"));
    }

    public MoveDirection oppositeDirection() {
        MoveDirection opposite = UNDEFINED;
        switch (this) {
            case NORTH:
                opposite = SOUTH;
                break;
            case EAST:
                opposite = WEST;
                break;
            case SOUTH:
                opposite = NORTH;
                break;
            case WEST:
                opposite = EAST;
                break;
            case UNDEFINED:
                break;
        }

        return opposite;
    }

}
