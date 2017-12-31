package roller.coaster.tycoon.world

import org.assertj.core.api.Assertions

class WorldTest extends spock.lang.Specification {

    def "should place guest at chosen pavement tile"() {
        given:
        World world = new World()
        Tile tile = correctPavementTile(12.5, -2.5)
        world.tilesList.add(tile)

        when:
        world.addGuestAt(810, -80)

        then:
        Assertions.assertThat(tile.getGuestsOnTile()).isNotEmpty()
    }

    def "should not place guest at tile when tile has not pavement"() {
        given:
        World world = new World()
        Tile tile = correctPavementTile(12.5, -2.5)
        tile.pavement = false
        world.tilesList.add(tile)

        when:
        world.addGuestAt(810, -80)

        then:
        Assertions.assertThat(tile.getGuestsOnTile()).isEmpty()
    }

    def "should not place guest at tile when tile has no neighbours"() {
        given:
        World world = new World()
        Tile tile = correctPavementTile(12.5, -2.5)
        tile.removeNeighbor(0)
        world.tilesList.add(tile)

        when:
        world.addGuestAt(810, -80)

        then:
        Assertions.assertThat(tile.getGuestsOnTile()).isEmpty()
    }


    private Tile correctPavementTile(double xPos, double yPos) {
        Tile tile = new Tile(xPos, yPos)
        tile.pavement = true
        tile.x0 = -550
        tile.y0 = 300
        tile.addAsNeighbor(tile, 'N' as char)
        return tile;
    }
}
