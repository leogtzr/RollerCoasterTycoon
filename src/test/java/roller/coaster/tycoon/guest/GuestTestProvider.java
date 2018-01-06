package roller.coaster.tycoon.guest;

import java.awt.image.BufferedImage;

public final class GuestTestProvider {

    public static GuestGraphics graphics() {
        return GuestGraphics.builder()
                .northImg(new BufferedImage[0])
                .eastImg(new BufferedImage[0])
                .southImg(new BufferedImage[0])
                .westImg(new BufferedImage[0])
                .build();
    }

}
