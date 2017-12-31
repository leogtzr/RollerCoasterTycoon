package roller.coaster.tycoon.guests;

import lombok.Builder;
import lombok.Value;

import java.awt.image.BufferedImage;

@Value
@Builder
public class GuestGraphics {

    private final BufferedImage[] northImg;
    private final BufferedImage[] southImg;
    private final BufferedImage[] eastImg;
    private final BufferedImage[] westImg;

}
