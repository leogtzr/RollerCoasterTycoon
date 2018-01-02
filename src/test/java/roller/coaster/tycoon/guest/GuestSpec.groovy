package roller.coaster.tycoon.guest

import roller.coaster.tycoon.tile.Tile
import roller.coaster.tycoon.world.TileTestProvider
import spock.lang.Specification

import static org.assertj.core.api.Assertions.*
import static org.mockito.Mockito.*

class GuestSpec extends Specification {

    def "should taken direction tile be set as destination tile"() {
        given:
        Tile startTile = TileTestProvider.pavementTile(12, 2)
        Tile northTile = TileTestProvider.pavementTileWithNeighbour(10, 2)

        GuestMoveDirectionLogic moveDirectionLogic = mock(GuestMoveDirectionLogic.class);
        when(moveDirectionLogic.getDestinationTileBasedOnDirectionFrom(startTile)).thenReturn(northTile);

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
        assertThat(guest.destinationTile).isEqualTo(northTile)
    }

}
