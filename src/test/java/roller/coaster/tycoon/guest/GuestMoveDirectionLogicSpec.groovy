package roller.coaster.tycoon.guest

import roller.coaster.tycoon.tile.Tile
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*

class GuestMoveDirectionLogicSpec extends Specification {

    def "should set direction from possible directions when no direction is set"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        currentTile.addAsNeighbor(TileTestProvider.pavementTile(12, 0), 'N' as char)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic();

        when:
        moveDirectionLogic.updateDirection(currentTile)

        then:
        assertThat(moveDirectionLogic.getDirection()).isEqualTo(NORTH)
    }

    def "should not take opposite direction when next tile for current direction is available"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        currentTile.addAsNeighbor(TileTestProvider.pavementTile(12, 0), 'N' as char)
        currentTile.addAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic()

        when:
        moveDirectionLogic.direction = NORTH
        moveDirectionLogic.updateDirection(currentTile)

        then:
        assertThat(moveDirectionLogic.getDirection()).isEqualTo(NORTH)
    }

    def "should take opposite direction when next tile for current direction is not available"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        currentTile.addAsNeighbor(TileTestProvider.pavementTile(12, 4), 'S' as char)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic()

        when:
        moveDirectionLogic.direction = NORTH
        moveDirectionLogic.updateDirection(currentTile)

        then:
        assertThat(moveDirectionLogic.getDirection()).isEqualTo(SOUTH)
    }

    def "should throw exception if destination is not set after destination set up method call"() {
        given:
        Tile tileWithNoNeighbours = TileTestProvider.pavementTile(12, 2)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic()

        when:
        moveDirectionLogic.updateDirection(tileWithNoNeighbours)

        then:
        thrown IllegalStateException
    }

    def "should return correct neighbour tile when direction is being set"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(10, 2)
        currentTile.addAsNeighbor(neighborTile, 'W' as char)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic()
        moveDirectionLogic.direction = WEST

        when:
        Tile destinationTile = moveDirectionLogic.getDestinationTileBasedOnDirectionFrom(currentTile)

        then:
        assertThat(destinationTile).isEqualTo(neighborTile)
    }
}
