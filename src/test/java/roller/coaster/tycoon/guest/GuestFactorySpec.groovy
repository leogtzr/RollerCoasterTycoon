package roller.coaster.tycoon.guest

import roller.coaster.tycoon.tile.Tile
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.*
import static org.mockito.Mockito.*

class GuestFactorySpec extends Specification {

    def "should create valid guest with first id equals to one"() {
        given:
        GuestGraphicProvider graphicProvider = mock(GuestGraphicProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        assertThat(guest.guestId).isEqualTo(1)
    }

    def "should newly created guest progress equal to zero"() {
        given:
        GuestGraphicProvider graphicProvider = mock(GuestGraphicProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        assertThat(guest.progress).isEqualTo(0)
    }

    def "should guest have generated graphics"() {
        given:
        GuestGraphicProvider graphicProvider = mock(GuestGraphicProvider.class)
        GuestGraphics graphics = GuestTestProvider.graphics()
        when(graphicProvider.generateGraphics()).thenReturn(graphics)

        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        assertThat(guest.graphics).isEqualTo(graphics)
    }

    def "should throw NPE when start tile passed to factory is null"() {
        given:
        GuestGraphicProvider graphicProvider = mock(GuestGraphicProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(null)

        then:
        thrown NullPointerException
    }
}
