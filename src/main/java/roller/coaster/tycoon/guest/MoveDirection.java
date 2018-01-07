package roller.coaster.tycoon.guest;

public enum MoveDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST,
    UNDEFINED;

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
