package com.craftinginjava.rollercoastertycoon.tile

import com.craftinginjava.rollercoastertycoon.detail.TileObject
import com.craftinginjava.rollercoastertycoon.guest.Direction
import com.craftinginjava.rollercoastertycoon.guest.Guest
import com.craftinginjava.rollercoastertycoon.world.TileTestProvider
import spock.lang.Specification

import static com.craftinginjava.rollercoastertycoon.guest.Direction.*
import static org.assertj.core.api.Assertions.assertThat

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
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(10, 2), EAST)
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 4), SOUTH)

        when:
        Set<Direction> moveDirections = tile.getPossibleDirectionsFromTile()

        then:
        assertThat(moveDirections).contains(EAST, SOUTH)
    }

    def "should return north neighbor when north direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, NORTH)

        when:
        Tile returnedTile = tile.getNeighbor(NORTH)

        then:
        neighborTile == returnedTile
    }

    def "should return east neighbor when east direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, EAST)

        when:
        Tile returnedTile = tile.getNeighbor(EAST)

        then:
        neighborTile == returnedTile
    }

    def "should return south neighbor when south direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, SOUTH)

        when:
        Tile returnedTile = tile.getNeighbor(SOUTH)

        then:
        neighborTile == returnedTile
    }

    def "should return west neighbor when west direction is passed on getNeighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 2)
        Tile neighborTile = TileTestProvider.pavementTile(12, 0)
        tile.addPavementTileAsNeighbor(neighborTile, WEST)

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
        tile.addPavementTileAsNeighbor(neighbor, NORTH)

        then:
        tile.getNeighbor(NORTH) == null
    }

    def "should not add tile as neighbor when source tile is not pavement tile"() {
        given:
        Tile tile = new Tile(12, 2)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        when:
        tile.addPavementTileAsNeighbor(neighbor, NORTH)

        then:
        tile.getNeighbor(NORTH) == null
    }

    def "should add pavement tile as neighbor with valid direction"(Direction direction) {
        given:
        Tile tile = TileTestProvider.pavementTile(14, 0)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        when:
        tile.addPavementTileAsNeighbor(neighbor, direction)

        then:
        tile.getNeighbor(direction) == neighbor

        where:
        direction << [NORTH, SOUTH, EAST, WEST]
    }

    def "should set road number when tileObject is not null during adding neighbor"() {
        given:
        Tile tile = TileTestProvider.pavementTile(14, 0)
        Tile neighbor = TileTestProvider.pavementTile(12, 0)

        TileObject tileObject = Mock(TileObject.class)
        tile.tileObject = tileObject

        when:
        tile.addPavementTileAsNeighbor(neighbor, NORTH)

        then:
        1 * tileObject.setRoadNumber(1)
    }

    def "should set pavement if left mouse clicked and no tile object on tile"() {
        given:
        Tile tile = new Tile(12, 12)

        when:
        tile.makePavement(16)

        then:
        tile.pavement
    }

    def "should not set pavement if not correct mouse number"() {
        given:
        Tile tile = new Tile(12, 12)

        when:
        tile.makePavement(17)

        then:
        !tile.pavement
    }

    def "should not set pavement if tile object is placed on tile"() {
        given:
        Tile tile = new Tile(12, 12)
        TileObject tileObject = Stub(TileObject.class)
        tile.tileObject = tileObject

        when:
        tile.makePavement(16)

        then:
        !tile.pavement
    }

    def "should remove pavement when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)

        when:
        tile.makePavement(4)

        then:
        !tile.pavement
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
        tile.guestsOnTile.isEmpty()
    }

    def "should remove tile object when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)
        tile.tileObject = Stub(TileObject.class)

        when:
        tile.makePavement(4)

        then:
        tile.tileObject == null
    }

    def "should remove all neighbors when right mouse clicked"() {
        given:
        Tile tile = TileTestProvider.pavementTile(12, 12)
        tile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 12), NORTH)

        when:
        tile.makePavement(4)

        then:
        tile.neighborsMap.isEmpty()
    }

    def "should return false if tile has not neighbors on doesHaveNeighbors method"() {
        given:
        Tile tile = new Tile(12, 12)

        when:
        boolean doesHaveNeighbors = tile.doesHaveNeighbors()

        then:
        !doesHaveNeighbors
    }

    def "should return true if tile has not neighbors on doesHaveNeighbors method"() {
        given:
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, 12)

        when:
        boolean doesHaveNeighbors = tile.doesHaveNeighbors()

        then:
        doesHaveNeighbors
    }

    def "should not place guest on tile which is pavement tile on placeExistingGuestOnTile action"() {
        given:
        Tile tile = new Tile(2, 2)

        when:
        tile.placeExistingGuestOnTile(
                Guest.builder().build()
        )

        then:
        tile.guestsOnTile.isEmpty()
    }

    def "should place guest on tile which is pavement tile on placeExistingGuestOnTile action"() {
        given:
        Tile tile = TileTestProvider.pavementTile(2, 2)

        when:
        tile.placeExistingGuestOnTile(
                Guest.builder().build()
        )

        then:
        !tile.guestsOnTile.isEmpty()
    }

    def "should remove guest from neighbors tiles during placeExistingGuestOnTile action"() {
        given:
        Tile tile = TileTestProvider.pavementTile(2, 2)
        Tile neighbor = TileTestProvider.pavementTile(2, 0)
        tile.addPavementTileAsNeighbor(neighbor, NORTH)

        Guest guest = Guest.builder().build()
        neighbor.addNewGuestToList(guest)

        when:
        tile.placeExistingGuestOnTile(guest)

        then:
        neighbor.guestsOnTile.isEmpty()
    }
}
