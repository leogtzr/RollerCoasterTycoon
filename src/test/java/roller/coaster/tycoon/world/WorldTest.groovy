package roller.coaster.tycoon.world

import org.assertj.core.api.Assertions
import spock.lang.Specification

import static org.assertj.core.api.Assertions.*

class WorldTest extends Specification {

    def "should place guest at chosen pavement tile"() {
        given:
        World world = new World()
        Tile tile = pavementTileWithNeighbour(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        assertThat(tile.getGuestsOnTile()).isNotEmpty()
    }

    def "should not place guest at tile when tile has not pavement"() {
        given:
        World world = new World()
        Tile tile = pavementTileWithNeighbour(12.5, -2.5)
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
        Tile tile = pavementTileWithNeighbour(12.5, -2.5)
        tile.removeNeighbor(0)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        assertThat(tile.getGuestsOnTile()).isEmpty()
    }

    def "should place object when tile is selected for object placement and tile is in clicked range"() {
        given:
        World world = new World()
        Tile tile = tile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.placeObjectAt(810, -80, 16, 2, 1);

        then:
        assertThat(tile.tileObject).isNotNull()

    }


    private Tile tile(double xPos, double yPos) {
        Tile tile = new Tile(xPos, yPos)
        tile.x0 = -550
        tile.y0 = 300
        return tile;
    }

    private Tile pavementTileWithNeighbour(double xPos, double yPos) {
        Tile tile = tile(xPos, yPos)
        tile.pavement = true
        tile.addAsNeighbor(tile, 'N' as char)
        return tile;
    }
}
