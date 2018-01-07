package roller.coaster.tycoon.world

import roller.coaster.tycoon.guest.MoveDirection
import roller.coaster.tycoon.tile.Tile
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static roller.coaster.tycoon.guest.MoveDirection.*

class WorldSpec extends Specification {

    def "should place guest at chosen pavement tile"() {
        given:
        World world = new World()
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        assertThat(tile.getGuestsOnTile()).isNotEmpty()
    }

    def "should not place guest at tile when tile has not pavement"() {
        given:
        World world = new World()
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12.5, -2.5)
        tile.pavement = false
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        assertThat(tile.getGuestsOnTile()).isEmpty()
    }

    def "should not place guest at tile when tile has no neighbours"() {
        given:
        World world = new World()
        Tile tile = TileTestProvider.pavementTile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        assertThat(tile.getGuestsOnTile()).isEmpty()
    }

    def "should place object when tile is selected for object placement and tile is in clicked range"() {
        given:
        World world = new World()
        Tile tile = new Tile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.placeObjectAt(810, -80, 16, 2, 1)

        then:
        assertThat(tile.tileObject).isNotNull()
    }

}
