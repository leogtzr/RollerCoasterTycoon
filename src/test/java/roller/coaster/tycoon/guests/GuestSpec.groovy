package roller.coaster.tycoon.guests

import org.assertj.core.api.Assertions
import roller.coaster.tycoon.tile.Tile
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.*

class GuestSpec extends Specification {

    def "should set direction from possible directions when no direction set"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        char ONLY_POSSIBLE_NEIGHBOUR_DIR = 'N' as char
        startTile.addAsNeighbor(TileTestProvider.pavementTile(12, 0), ONLY_POSSIBLE_NEIGHBOUR_DIR)
        Guest guest = guest(startTile)

        when:
        guest.setUpDestination()

        then:
        assertThat(guest.direction).isEqualTo(ONLY_POSSIBLE_NEIGHBOUR_DIR)
    }

    def "should not take opposite direction when next tile for current direction is available"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        startTile.addAsNeighbor(TileTestProvider.pavementTile(12, 0), 'N' as char)
        startTile.addAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)
        Guest guest = guest(startTile)

        when:
        guest.direction = 'N' as char
        guest.moveDirectionLogic.direction = 'N' as char
        guest.moveDirectionLogic.moveDirection = MoveDirection.NORTH
        guest.setUpDestination()

        then:
        assertThat(guest.direction).isEqualTo('N' as char)
    }

    def "should take opposite direction when next tile for current direction is not available"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        startTile.addAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)
        Guest guest = guest(startTile)

        when:
        guest.direction = 'N' as char
        guest.moveDirectionLogic.direction = 'N' as char
        guest.moveDirectionLogic.moveDirection = MoveDirection.NORTH
        guest.setUpDestination()

        then:
        assertThat(guest.direction).isEqualTo('S' as char)
    }

    def "should taken direction tile be set as destination tile"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile northTile = TileTestProvider.pavementTileWithNeighbour(10, 2)

        startTile.addAsNeighbor(northTile, 'N' as char)
        Guest guest = guest(startTile)

        when:
        guest.setUpDestination()

        then:
        assertThat(guest.destinationTile).isEqualTo(northTile)
    }

    def "should throw exception if destination is not set after destination set up method call"() {
        given:
        Tile tileWithNoNeighbours = TileTestProvider.pavementTile(12, 2)
        Guest guest = guest(tileWithNoNeighbours)

        when:
        guest.setUpDestination()

        then:
        thrown IllegalStateException
    }

    private Guest guest(Tile startTile) {
        return Guest.builder()
                .guestId(1)
                .currentTile(startTile)
                .progress(0)
                .direction(' ' as char)
                .graphics(GuestTestProvider.graphics())
                .moveDirectionLogic(new GuestMoveDirectionLogic())
                .build()
    }


}
