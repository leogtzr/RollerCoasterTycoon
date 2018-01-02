package roller.coaster.tycoon.guests;

import lombok.RequiredArgsConstructor;

import java.util.EnumSet;

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

}
