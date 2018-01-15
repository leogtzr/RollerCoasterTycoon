package com.craftinginjava.rollercoastertycoon.guest;

public enum Direction {

    NORTH {
        @Override public Direction oppositeDirection() {
            return SOUTH;
        }
    },
    EAST {
        @Override public Direction oppositeDirection() {
            return WEST;
        }
    },
    SOUTH {
        @Override public Direction oppositeDirection() {
            return NORTH;
        }
    },
    WEST {
        @Override public Direction oppositeDirection() {
            return EAST;
        }
    },
    UNDEFINED {
        @Override public Direction oppositeDirection() {
            return UNDEFINED;
        }
    };

    public abstract Direction oppositeDirection();

}
