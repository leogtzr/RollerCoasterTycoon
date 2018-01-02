package roller.coaster.tycoon.guests;

import lombok.Getter;
import roller.coaster.tycoon.tile.Tile;

import java.util.Set;
import java.util.stream.Collectors;

import static roller.coaster.tycoon.guests.MoveDirection.*;

@Getter
class GuestMoveDirectionLogic {

    private char direction = ' '; //TODO remove (safe) this variable from code
    private MoveDirection moveDirection = UNDEFINED;

    Tile setUpDirectionAndGetTargetTileFrom(Tile currentTile) {
        Set<MoveDirection> allPossibleMoveDirections = currentTile.getPossibleDirectionsFromTile();

        if (tileHasNeighbours(allPossibleMoveDirections)) {
            Set<MoveDirection> moveDirectionsWithoutOpposite = moveDirectionsWithoutOppositeDirectionOfTargetDirection(allPossibleMoveDirections);
            Set<MoveDirection> directionsToSet = hasEncounterDeadEnd(moveDirectionsWithoutOpposite) ? allPossibleMoveDirections : moveDirectionsWithoutOpposite;
            setRandomDirectionFromDirectionsSet(directionsToSet);
        }

        if (moveDirection == UNDEFINED || direction == ' ') {
            throw new IllegalStateException("Direction is not set! Guest can not move without direction!");
        }
        return currentTile.getNeighbor(direction);
    }

    private boolean tileHasNeighbours(Set<MoveDirection> possibleMoveDirections) {
        return !possibleMoveDirections.isEmpty();
    }

    private Set<MoveDirection> moveDirectionsWithoutOppositeDirectionOfTargetDirection(Set<MoveDirection> possibleMoveDirections) {
        MoveDirection oppositeDirection = this.moveDirection.oppositeDirection();
        return possibleMoveDirections
                .stream()
                .filter(d -> d != oppositeDirection)
                .collect(Collectors.toSet());
    }

    private boolean hasEncounterDeadEnd(Set<MoveDirection> moveDirectionsWithoutOpposite) {
        return moveDirectionsWithoutOpposite.isEmpty();
    }

    private void setRandomDirectionFromDirectionsSet(Set<MoveDirection> directions) {
        moveDirection = directions
                .stream()
                .skip((int) (directions.size() * Math.random()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Directions set can not bew null in this place!"));

        direction = moveDirection.getDirectionChar();
    }

}
