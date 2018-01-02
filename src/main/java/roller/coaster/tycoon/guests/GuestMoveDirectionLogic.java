package roller.coaster.tycoon.guests;

import lombok.Getter;
import roller.coaster.tycoon.tile.Tile;

import java.util.Set;
import java.util.stream.Collectors;

import static roller.coaster.tycoon.guests.MoveDirection.*;

@Getter
class GuestMoveDirectionLogic {

    private MoveDirection direction = UNDEFINED;

    void updateDirection(Tile currentTile) {
        Set<MoveDirection> allPossibleMoveDirections = currentTile.getPossibleDirectionsFromTile();

        if (tileHasNeighbours(allPossibleMoveDirections)) {
            Set<MoveDirection> moveDirectionsWithoutOpposite = moveDirectionsWithoutOppositeDirectionOfTargetDirection(allPossibleMoveDirections);
            Set<MoveDirection> directionsToSet = hasEncounterDeadEnd(moveDirectionsWithoutOpposite) ? allPossibleMoveDirections : moveDirectionsWithoutOpposite;
            setRandomDirectionFromDirectionsSet(directionsToSet);
        }

        if (direction == UNDEFINED) {
            throw new IllegalStateException("Direction is not set! Guest can not move without direction!");
        }
    }

    private boolean tileHasNeighbours(Set<MoveDirection> possibleMoveDirections) {
        return !possibleMoveDirections.isEmpty();
    }

    private Set<MoveDirection> moveDirectionsWithoutOppositeDirectionOfTargetDirection(Set<MoveDirection> possibleMoveDirections) {
        MoveDirection oppositeDirection = this.direction.oppositeDirection();
        return possibleMoveDirections
                .stream()
                .filter(d -> d != oppositeDirection)
                .collect(Collectors.toSet());
    }

    private boolean hasEncounterDeadEnd(Set<MoveDirection> moveDirectionsWithoutOpposite) {
        return moveDirectionsWithoutOpposite.isEmpty();
    }

    private void setRandomDirectionFromDirectionsSet(Set<MoveDirection> directions) {
        direction = directions
                .stream()
                .skip((int) (directions.size() * Math.random()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Directions set can not bew null in this place!"));
    }

    Tile getDestinationTileBasedOnDirectionFrom(Tile currentTile) {
        return currentTile.getNeighbor(direction.getDirectionChar());
    }

}
