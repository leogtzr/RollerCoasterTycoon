package roller.coaster.tycoon.tile

import roller.coaster.tycoon.guest.MoveDirection
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*
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

    def "should return north neighbor when north direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addAsNeighbor(neighborTile, 'N' as char)

        when:
        Tile returnedTile = tile.getNeighbor(NORTH)

        then:
        neighborTile == returnedTile
    }

    def "should return east neighbor when east direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addAsNeighbor(neighborTile, 'E' as char)

        when:
        Tile returnedTile = tile.getNeighbor(EAST)

        then:
        neighborTile == returnedTile
    }

    def "should return south neighbor when south direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addAsNeighbor(neighborTile, 'S' as char)

        when:
        Tile returnedTile = tile.getNeighbor(SOUTH)

        then:
        neighborTile == returnedTile
    }

    def "should return west neighbor when west direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addAsNeighbor(neighborTile, 'W' as char)

        when:
        Tile returnedTile = tile.getNeighbor(WEST)

        then:
        neighborTile == returnedTile
    }
}
