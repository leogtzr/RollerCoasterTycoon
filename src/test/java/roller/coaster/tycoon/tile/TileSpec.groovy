package roller.coaster.tycoon.tile

import roller.coaster.tycoon.detail.TileObject
import roller.coaster.tycoon.guest.Guest
import roller.coaster.tycoon.guest.MoveDirection
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*

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
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(10, 2), 'E' as char)
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)

        when:
        Set<MoveDirection> moveDirections = tile.getPossibleDirectionsFromTile()

        then:
        assertThat(moveDirections).contains(EAST, SOUTH)
    }

    def "should return north neighbor when north direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, 'N' as char)

        when:
        Tile returnedTile = tile.getNeighbor(NORTH)

        then:
        neighborTile == returnedTile
    }

    def "should return east neighbor when east direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, 'E' as char)

        when:
        Tile returnedTile = tile.getNeighbor(EAST)

        then:
        neighborTile == returnedTile
    }

    def "should return south neighbor when south direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, 'S' as char)

        when:
        Tile returnedTile = tile.getNeighbor(SOUTH)

        then:
        neighborTile == returnedTile
    }

    def "should return west neighbor when west direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, 'W' as char)

        when:
        Tile returnedTile = tile.getNeighbor(WEST)

        then:
        neighborTile == returnedTile
    }

    def "should not add tile as neighbor when added tile is not pavement tile"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighbor = new Tile(12, 0)

        when:
        tile.addPavementTileAsNeighbor(neighbor, 'N' as char)

        then:
        assertThat(tile.getNeighbor(NORTH)).isNull()
    }

    def "should not add tile as neighbor when source tile is not pavement tile"() {
        given:
        Tile tile = new Tile(12, 2)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        when:
        tile.addPavementTileAsNeighbor(neighbor, 'N' as char)

        then:
        assertThat(tile.getNeighbor(NORTH)).isNull()
    }

    def "should add pavement tile as neighbor with valid direction"(char directionAsChar, MoveDirection direction) {
        given:
        Tile tile = TileTestProvider.pavementTile(14, 0)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        when:
        tile.addPavementTileAsNeighbor(neighbor, directionAsChar)

        then:
        tile.getNeighbor(direction) == neighbor

        where:
        directionAsChar || direction
        'N' as char     || NORTH
        'S' as char     || SOUTH
        'E' as char     || EAST
        'W' as char     || WEST
    }

    def "should set road number when tileObject is not null during adding neighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(14, 0)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        TileObject tileObject = Mock(TileObject.class)
        tile.tileObject = tileObject

        when:
        tile.addPavementTileAsNeighbor(neighbor, 'N' as char)

        then:
        1 * tileObject.setRoadNumber(1)
    }

    def "should set pavement if left mouse clicked and no tile object on tile"() {
        given:
        Tile tile = new Tile(12, 12)

        when:
        tile.makePavement(16)

        then:
        assertThat(tile.pavement).isTrue()
    }

    def "should not set pavement if not correct mouse number"() {
        given:
        Tile tile = new Tile(12, 12)

        when:
        tile.makePavement(17)

        then:
        assertThat(tile.pavement).isFalse()
    }

    def "should not set pavement if tile object is placed on tile"() {
        given:
        Tile tile = new Tile(12, 12)
        TileObject tileObject = Stub(TileObject.class)
        tile.tileObject = tileObject

        when:
        tile.makePavement(16)

        then:
        assertThat(tile.pavement).isFalse()
    }

    def "should remove pavement when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)

        when:
        tile.makePavement(4)

        then:
        assertThat(tile.pavement).isFalse()
    }

    def "should remove all guests when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)
        tile.addNewGuestToList(
                Guest.builder().build()
        )

        when:
        tile.makePavement(4)

        then:
        assertThat(tile.guestsOnTile).isEmpty()
    }

    def "should remove tile object when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)
        tile.tileObject = Stub(TileObject.class)

        when:
        tile.makePavement(4)

        then:
        assertThat(tile.tileObject).isNull()
    }

    def "should remove all neighbors when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 12), 'N' as char)

        when:
        tile.makePavement(4)

        then:
        assertThat(tile.neighborsMap).isEmpty()
    }
}
