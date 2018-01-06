package roller.coaster.tycoon.guest;

import roller.coaster.tycoon.tile.Tile;

import java.awt.image.BufferedImage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class GuestTestProvider {

    public static Guest guest(Tile tile) {
        GuestGraphicProvider imageProvider = mock(GuestGraphicProvider.class);
        when(imageProvider.generateGraphics()).thenReturn(graphics());

        GuestFactory guestFactory = new GuestFactory(imageProvider);
        return guestFactory.create(tile);
    }

    public static GuestGraphics graphics() {
        return GuestGraphics.builder()
                .northImg(new BufferedImage[0])
                .eastImg(new BufferedImage[0])
                .southImg(new BufferedImage[0])
                .westImg(new BufferedImage[0])
                .build();
    }

}
