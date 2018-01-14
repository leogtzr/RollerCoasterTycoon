package com.craftinginjava.rollercoastertycoon.guest

import com.craftinginjava.rollercoastertycoon.tile.Tile
import com.craftinginjava.rollercoastertycoon.world.TileTestProvider
import spock.lang.Specification

import static Direction.NORTH
import static Direction.SOUTH

class GuestSpec extends Specification {

    def "should taken direction tile be set as destination tile"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile northTile = TileTestProvider.pavementTileWithNeighbour(10, 2)

        GuestMoveDirectionLogic moveDirectionLogic = Stub(GuestMoveDirectionLogic.class)
        moveDirectionLogic.getDestinationTileBasedOnDirectionFrom(startTile) >> northTile

        Guest guest = Guest.builder()
                .guestId(1)
                .currentTile(startTile)
                .progress(0)
                .graphics(GuestTestProvider.graphics())
                .moveDirectionLogic(moveDirectionLogic)
                .build()

        when:
        guest.setUpDestination()

        then:
        guest.destinationTile == northTile
    }

    def "should increase progress on move when progress is not complete to move to destination tile"() {
        given:
        Guest guest = guest(TileTestProvider.pavementTile(12, 2))
        guest.progress = 0

        when:
        guest.move()

        then:
        guest.progress == 1
    }

    def "should reset progress when progress is complete"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile destinationTile = TileTestProvider.pavementTile(10, 2)
        startTile.addPavementTileAsNeighbor(destinationTile, NORTH)
        destinationTile.addPavementTileAsNeighbor(startTile, SOUTH)

        Guest guest = guest(startTile)
        guest.destinationTile = destinationTile
        guest.progress = Guest.PROGRESS_REQUIRED_FOR_MOVE_TO_DESTINATION_FILE - 1

        when:
        guest.move()

        then:
        guest.progress == 0
    }

    def "should set destinationTile as currentTile when progress is complete"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile destinationTile = TileTestProvider.pavementTile(10, 2)
        startTile.addPavementTileAsNeighbor(destinationTile, NORTH)
        destinationTile.addPavementTileAsNeighbor(startTile, SOUTH)

        Guest guest = guest(startTile)
        guest.destinationTile = destinationTile
        guest.progress = Guest.PROGRESS_REQUIRED_FOR_MOVE_TO_DESTINATION_FILE - 1

        when:
        guest.move()

        then:
        guest.currentTile == destinationTile
    }

    def "should set direction to new destinationTile when progress is complete"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile destinationTile = TileTestProvider.pavementTile(10, 2)
        startTile.addPavementTileAsNeighbor(destinationTile, NORTH)
        destinationTile.addPavementTileAsNeighbor(startTile, SOUTH)

        Guest guest = guest(startTile)
        guest.moveDirectionLogic.direction = NORTH
        guest.destinationTile = destinationTile
        guest.progress = Guest.PROGRESS_REQUIRED_FOR_MOVE_TO_DESTINATION_FILE - 1

        when:
        guest.move()

        then:
        guest.moveDirectionLogic.direction == SOUTH
    }

    private Guest guest(Tile startTile) {
        return Guest.builder()
                .guestId(1)
                .currentTile(startTile)
                .progress(0)
                .graphics(GuestTestProvider.graphics())
                .moveDirectionLogic(new GuestMoveDirectionLogic())
                .build();
    }

}
