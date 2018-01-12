package roller.coaster.tycoon.guest

import roller.coaster.tycoon.tile.Tile
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class GuestFactorySpec extends Specification {

    def "should create valid guest not null id"() {
        given:
        GuestGraphicsProvider graphicProvider = mock(GuestGraphicsProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        guest.guestId != null
    }

    def "should newly created guest progress equal to zero"() {
        given:
        GuestGraphicsProvider graphicProvider = mock(GuestGraphicsProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        guest.progress == 0
    }

    def "should guest have generated graphics"() {
        given:
        GuestGraphicsProvider graphicProvider = mock(GuestGraphicsProvider.class)
        GuestGraphics graphics = GuestTestProvider.graphics()
        when(graphicProvider.generateGraphics()).thenReturn(graphics)

        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(tile)

        then:
        guest.graphics == graphics
    }

    def "should throw NPE when start tile passed to factory is null"() {
        given:
        GuestGraphicsProvider graphicProvider = mock(GuestGraphicsProvider.class)
        GuestFactory factory = new GuestFactory(graphicProvider)
        Tile tile = TileTestProvider.pavementTileWithNeighbour(12, -2)

        when:
        Guest guest = factory.create(null)

        then:
        thrown NullPointerException
    }
}
