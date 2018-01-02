package roller.coaster.tycoon.guests

import org.assertj.core.api.Assertions
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class MoveDirectionTest extends Specification {
    def "should convert valid char to move direction"() {
        when:
        MoveDirection direction = MoveDirection.fromChar('N' as char)

        then:
        assertThat(direction).isEqualTo(MoveDirection.NORTH)
    }

    def "should throw exception when passed char for MoveDirection is not valid"() {
        given:
        char notValidChar = 'x' as char

        when:
        MoveDirection direction = MoveDirection.fromChar(notValidChar)

        then:
        thrown IllegalArgumentException
    }
}
