package roller.coaster.tycoon.guest

import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*

class MoveDirectionSpec extends Specification {
    def "should convert valid char to move direction"() {
        when:
        MoveDirection direction = fromChar('N' as char)

        then:
        assertThat(direction).isEqualTo(NORTH)
    }

    def "should throw exception when passed char for MoveDirection is not valid"() {
        given:
        char notValidChar = 'x' as char

        when:
        MoveDirection direction = fromChar(notValidChar)

        then:
        thrown IllegalArgumentException
    }

    def "should return opposite move direction"(MoveDirection direction, MoveDirection oppositeMoveDirection) {
        expect:
        direction.oppositeDirection() == oppositeMoveDirection;

        where:
        direction || oppositeMoveDirection
        NORTH     || SOUTH
        SOUTH     || NORTH
        EAST      || WEST
        WEST      || EAST
        UNDEFINED || UNDEFINED
    }
}
