package com.craftinginjava.rollercoastertycoon.guest;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class GuestGraphics {

    private static final int MAXIMUM_PROGRESS_FOR_INDEX = 6;

    private final Map<Direction, BufferedImage[]> directionImages;

    public static GuestGraphicsBuilder builder() {
        return new GuestGraphicsBuilder();
    }

    public void draw(Graphics g, Direction direction, double drawX, double drawY, int progress) {
        int isoX = (int) drawX - 6;
        int isoY = (int) drawY - 19;
        g.drawImage(image(direction, progress), isoX, isoY, null);
    }

    private Image image(Direction direction, int progress) {
        int index = imageIndexFromProgress(progress);
        BufferedImage[] directionImages = this.directionImages.get(direction);
        return directionImages[index];
    }

    private int imageIndexFromProgress(int progress) {
        return progress < MAXIMUM_PROGRESS_FOR_INDEX ? progress : 0;
    }

    static class GuestGraphicsBuilder {

        private final Map<Direction, BufferedImage[]> directionImages = new HashMap<>();

        public GuestGraphics build() {
            return new GuestGraphics(directionImages);
        }

        public GuestGraphicsBuilder northImg(BufferedImage[] images) {
            addImages(Direction.NORTH, images);
            return this;
        }

        public GuestGraphicsBuilder eastImg(BufferedImage[] images) {
            addImages(Direction.EAST, images);
            return this;
        }

        public GuestGraphicsBuilder southImg(BufferedImage[] images) {
            addImages(Direction.SOUTH, images);
            return this;
        }

        public GuestGraphicsBuilder westImg(BufferedImage[] images) {
            addImages(Direction.WEST, images);
            return this;
        }

        private void addImages(Direction direction, BufferedImage[] images) {
            Preconditions.checkNotNull(images, "Images array can not be null!");
            this.directionImages.put(direction, images);
        }
    }
}
