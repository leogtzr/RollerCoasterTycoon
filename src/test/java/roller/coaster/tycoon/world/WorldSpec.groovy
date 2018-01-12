package roller.coaster.tycoon.world

import roller.coaster.tycoon.tile.Tile
import spock.lang.Specification

class WorldSpec extends Specification {

    def "should place guest at chosen pavement tile"() {
        given:
        World world = new World()
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        tile.getGuestsOnTile().isEmpty() != true
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
        tile.getGuestsOnTile().isEmpty() == true
    }

    def "should not place guest at tile when tile has no neighbours"() {
        given:
        World world = new World()
        Tile tile = TileTestProvider.pavementTile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.addGuestAt(810, -80)

        then:
        tile.getGuestsOnTile().isEmpty() == true
    }

    def "should place object when tile is selected for object placement and tile is in clicked range"() {
        given:
        World world = new World()
        Tile tile = new Tile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.placeObjectAt(810, -80, 16, 2, 1)

        then:
        tile.tileObject != null
    }

    def "should world be moved vertically"() {
        given:
        World world = new World()
        int offset = 100
        int expectedY = world.y0
        int expectedX = world.x0 + offset

        when:
        world.moveWorld(offset, 0)

        then:
        world.x0 == expectedX &&
                world.y0 == expectedY
    }

    def "should world be moved horizontally"() {
        given:
        World world = new World()
        int offset = 100
        int expectedY = world.y0 + offset
        int expectedX = world.x0

        when:
        world.moveWorld(0, offset)

        then:
        world.x0 == expectedX &&
                world.y0 == expectedY
    }

    def "should not highlight tile when coordinates do not meet any tile polygon"() {
        given:
        World world = new World()

        when:
        world.setHighlightedTile(9999, 9999)

        then:
        world.highlight == null
    }

    def "should set highlighted tile with tile polygon when coordinates meets tile polygon"() {
        given:
        World world = new World()
        Tile tile = new Tile(12.5, -2.5)
        world.tilesList.add(0, tile)

        when:
        world.setHighlightedTile(810, -80)

        then:
        world.highlight.tileShape == tile.tileShape
    }
}
