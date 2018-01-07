package roller.coaster.tycoon.guest;

import lombok.Getter;
import roller.coaster.tycoon.tile.Tile;

import java.util.Set;
import java.util.stream.Collectors;

import static roller.coaster.tycoon.guest.Direction.UNDEFINED;

@Getter
class GuestMoveDirectionLogic {

    private Direction direction = UNDEFINED;

    void updateDirection(Tile currentTile) {
        Set<Direction> allPossibleDirections = currentTile.getPossibleDirectionsFromTile();

        if (tileHasNeighbours(allPossibleDirections)) {
            Set<Direction> directionsWithoutOpposite = moveDirectionsWithoutOppositeDirectionOfTargetDirection(allPossibleDirections);
            Set<Direction> directionsToSet = hasEncounterDeadEnd(directionsWithoutOpposite) ? allPossibleDirections : directionsWithoutOpposite;
            setRandomDirectionFromDirectionsSet(directionsToSet);
        }

        if (direction == UNDEFINED) {
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
                .collect(Collectors.toSet());
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
