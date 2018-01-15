package com.craftinginjava.rollercoastertycoon.guest;

import com.craftinginjava.rollercoastertycoon.tile.Tile;
import lombok.Getter;

import java.util.Set;

import static java.util.stream.Collectors.*;

@Getter
class GuestMoveDirectionLogic {

    private Direction direction = Direction.UNDEFINED;

    void updateDirection(Tile currentTile) {
        Set<Direction> allPossibleDirections = currentTile.getPossibleDirectionsFromTile();

        if (tileHasNeighbours(allPossibleDirections)) {
            Set<Direction> directionsWithoutOpposite = moveDirectionsWithoutOppositeDirectionOfTargetDirection(allPossibleDirections);
            Set<Direction> directionsToSet = hasEncounterDeadEnd(directionsWithoutOpposite) ? allPossibleDirections : directionsWithoutOpposite;
            setRandomDirectionFromDirectionsSet(directionsToSet);
        }

        if (direction == Direction.UNDEFINED) {
            throw new IllegalStateException("Direction is not set! Guest can not move without direction!");
        }
    }

    private boolean tileHasNeighbours(Set<Direction> possibleDirections) {
        return !possibleDirections.isEmpty();
    }

    private Set<Direction> moveDirectionsWithoutOppositeDirectionOfTargetDirection(Set<Direction> possibleDirections) {
        Direction oppositeDirection = this.direction.oppositeDirection();
        return possibleDirections
                .stream()
                .filter(d -> d != oppositeDirection)
                .collect(toSet());
    }

    private boolean hasEncounterDeadEnd(Set<Direction> directionsWithoutOpposite) {
        return directionsWithoutOpposite.isEmpty();
    }

    private void setRandomDirectionFromDirectionsSet(Set<Direction> directions) {
        direction = directions
                .stream()
                .skip((int) (directions.size() * Math.random()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Directions set can not bew null in this place!"));
    }

    Tile getDestinationTileBasedOnDirectionFrom(Tile currentTile) {
        return currentTile.getNeighbor(direction);
    }

}
