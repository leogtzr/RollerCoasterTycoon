package roller.coaster.tycoon.guest;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static roller.coaster.tycoon.guest.MoveDirection.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GuestGraphics {

    private static final int MAXIMUM_PROGRESS_FOR_INDEX = 6;

    private final Map<MoveDirection, BufferedImage[]> directionImages;

    public static GuestGraphicsBuilder builder() {
        return new GuestGraphicsBuilder();
    }

    public void draw(Graphics g, MoveDirection direction, double drawX, double drawY, int progress) {
        int isoX = (int) drawX - 6;
        int isoY = (int) drawY - 19;
        g.drawImage(image(direction, progress), isoX, isoY, null);
    }

    private Image image(MoveDirection direction, int progress) {
        int index = imageIndexFromProgress(progress);
        BufferedImage[] directionImages = this.directionImages.get(direction);
        return directionImages[index];
    }

    private int imageIndexFromProgress(int progress) {
        return progress < MAXIMUM_PROGRESS_FOR_INDEX ? progress : 0;
    }

    static class GuestGraphicsBuilder {

        private final Map<MoveDirection, BufferedImage[]> directionImages = new HashMap<>();

        public GuestGraphics build() {
            return new GuestGraphics(directionImages);
        }

        public GuestGraphicsBuilder northImg(BufferedImage[] images) {
            addImages(NORTH, images);
            return this;
        }

        public GuestGraphicsBuilder eastImg(BufferedImage[] images) {
            addImages(EAST, images);
            return this;
        }

        public GuestGraphicsBuilder southImg(BufferedImage[] images) {
            addImages(SOUTH, images);
            return this;
        }

        public GuestGraphicsBuilder westImg(BufferedImage[] images) {
            addImages(WEST, images);
            return this;
        }

        private void addImages(MoveDirection direction, BufferedImage[] images) {
            Preconditions.checkNotNull(images, "Images array can not be null!");
            this.directionImages.put(direction, images);
        }
    }
}
