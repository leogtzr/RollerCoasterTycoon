package roller.coaster.tycoon.guest

import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*

class MoveDirectionSpec extends Specification {

    def "should return opposite move direction"(MoveDirection direction, MoveDirection oppositeMoveDirection) {
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
