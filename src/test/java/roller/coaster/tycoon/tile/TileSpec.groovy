package roller.coaster.tycoon.tile

import roller.coaster.tycoon.guest.MoveDirection
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.EAST
import static roller.coaster.tycoon.guest.MoveDirection.SOUTH

class TileSpec extends Specification {

    def "should return empty set of possible directions when there are no possible directions from that tile"() {
        when:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        then:
        assertThat(tile.getPossibleDirectionsFromTile()).isEmpty()
    }

    def "should return set of possible move directions based on neighbour tiles"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        tile.addAsNeighbor(TileTestProvider.pavementTile(10, 2), 'E' as char)
        tile.addAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)

        when:
        Set<MoveDirection> moveDirections = tile.getPossibleDirectionsFromTile()

        then:
        assertThat(moveDirections).contains(EAST, SOUTH)
    }
}
