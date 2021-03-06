package com.craftinginjava.rollercoastertycoon.guest

import com.craftinginjava.rollercoastertycoon.tile.Tile
import com.craftinginjava.rollercoastertycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static Direction.*

class GuestMoveDirectionLogicSpec extends Specification {

    def "should set direction from possible directions when no direction is set"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        currentTile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 0), NORTH)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic();

        when:
        moveDirectionLogic.updateDirection(currentTile)

        then:
        assertThat(moveDirectionLogic.getDirection()).isEqualTo(NORTH)
    }

    def "should not take opposite direction when next tile for current direction is available"() {
        given:
        Tile currentTile = TileTestProvider.pavementTile(12, 2)
        currentTile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 0), NORTH)
        currentTile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 4), SOUTH)
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
        currentTile.addPavementTileAsNeighbor(TileTestProvider.pavementTile(12, 4), SOUTH)
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
        currentTile.addPavementTileAsNeighbor(neighborTile, WEST)
        GuestMoveDirectionLogic moveDirectionLogic = new GuestMoveDirectionLogic()
        moveDirectionLogic.direction = WEST

        when:
        Tile destinationTile = moveDirectionLogic.getDestinationTileBasedOnDirectionFrom(currentTile)

        then:
        destinationTile == neighborTile
    }
}
