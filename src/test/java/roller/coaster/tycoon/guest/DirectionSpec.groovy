package roller.coaster.tycoon.guest

import spock.lang.Specification

import static roller.coaster.tycoon.guest.Direction.*

class DirectionSpec extends Specification {

    def "should return opposite move direction"(Direction direction, Direction oppositeMoveDirection) {
        expect:
        direction.oppositeDirection() == oppositeMoveDirection

        where:
        direction || oppositeMoveDirection
        NORTH     || SOUTH
        SOUTH     || NORTH
        EAST      || WEST
        WEST      || EAST
        UNDEFINED || UNDEFINED
    }
}
