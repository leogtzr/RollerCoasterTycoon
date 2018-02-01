package com.craftinginjava.rollercoastertycoon.world;

import com.craftinginjava.rollercoastertycoon.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class used to save and load worlds.
 *
 * @author René
 */
public class WorldGenerator {

    /**
     * Image used when loading a map.
     */
    private BufferedImage img;
    /**
     * The tiles that will be changed when saving a map.
     */
    private Tile[][] tiles;

    /**
     * The constructor used when loading a map.
     */
    public WorldGenerator() {
    }

    /**
     * The constructor used when saving a map.
     *
     * @param tiles The tiles that must be saved.
     */
    public WorldGenerator(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Called when loading a map. The parameter imgName is the name of the map
     * to load. It should not have a file extension such as .png as it by default
     * loads .png files.
     * <p>Must be called before the loadWorld(Tile[][] roller.coaster.tycoon.world) method.
     *
     * @param imgName the name of the image to load
     * @throws IOException              If loading the image fails.
     * @throws IllegalArgumentException If the image is not found.
     */
    public void loadImage(String imgName) throws IOException, IllegalArgumentException {

        img = ImageIO.read(this.getClass().getResource(imgName + ".png"));
    }

    /**
     * Creates a <code>ColorModel, DataBuffer, WriteableRaster</code> and a <code>BufferedImage</code>
     * and then saves it.
     * <p>The name parameter is the name the map will be saved to. It should not have a
     * file extension as it is saved as a .png file.
     * <p>The map is saved in the same folder as the .jar file.
     *
     * @param name The name of the map.
     */
    public void saveWorld(String name) {
        ColorModel colorModel = generateColorModel();

        int bitMasks[] = new int[]
                {
                        (byte) 0xf
                };

        byte[] pixels = generatePixels(31, 31);
        DataBuffer dbuf = new DataBufferByte(pixels, 31 * 31, 0);
        SampleModel sampleModel = new SinglePixelPackedSampleModel(DataBuffer.TYPE_BYTE, 31, 31, bitMasks);
        WritableRaster raster = Raster.createWritableRaster(sampleModel, dbuf, null);
        BufferedImage saveImg = new BufferedImage(colorModel, raster, false, null);


        try {
            ImageIO.write(saveImg, "png", new File(name + ".png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveWorldNew(String name) {
        DirectColorModel colorModel = new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff);

        WritableRaster raster2 = WritableRaster.createPackedRaster(DataBuffer.TYPE_INT,
                31, 31,
                new int[]
                        {
                                0x00ff0000, 0x0000ff00, 0x000000ff
                        }, null);
        BufferedImage saveImg = new BufferedImage(colorModel, raster2, false, null);

        generatePixels2(saveImg);

        try {
            ImageIO.write(saveImg, "png", new File(name + ".png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Runs through the tiles in the map saving the height information in a x+1,y+1 array.
     * They are then moved to an <code>ArrayList</code> and further on to an array which is returned
     *
     * @param x Width of the map + 1
     * @param y Height of the map + 1
     * @return An array of bytes representing all conors in the map.
     */
    private byte[] generatePixels(int x, int y) {
        byte[][] mapArray = new byte[31][31];
        byte n;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                n = (byte) ((tiles[i][j].getNorthHeight() + 128) / 16);

                mapArray[i + 1][j] = n;

                if (i == 0) {
                    mapArray[0][j] = n;
                }

                if (j == tiles.length - 1) {
                    mapArray[i + 1][j + 1] = n;
                }

                if (i == 0 && j == tiles.length - 1) {
                    mapArray[0][j + 1] = n;
                }
            }
        }


        ArrayList<Byte> byteList = new ArrayList<>(x * y);
        byte[] temp = new byte[x * y];

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                byteList.add(mapArray[j][i]);
            }
        }

        for (int i = 0; i < byteList.size(); i++) {
            temp[i] = byteList.get(i);
        }
        return temp;
    }

    /**
     * Generates a 16 slot indexed <code>ColorModel</code> filled with greyscale colors
     * from RGB 0,0,0 to RGB 255,255,255.
     * <p>Pure white being the lowest and pure black the highest.
     *
     * @return A 16 slot indexed <code>ColorModel</code>.
     */
    private ColorModel generateColorModel() {
        byte[] r = new byte[16];
        byte[] g = new byte[16];
        byte[] b = new byte[16];

        for (int i = 0; i < b.length; i++) {
            r[i] = (byte) (i * 16);
            g[i] = (byte) (i * 16);
            b[i] = (byte) (i * 16);

        }
        return new IndexColorModel(4, 16, r, g, b);
    }

    /**
     * The image is loaded and every pixel has it's red color loaded.
     * All the tiles are then edited to corrospond to the color value.
     * <p>Pure white being the lowest and pure black the highest.
     *
     * @param world The tiles the roller.coaster.tycoon.world should be loaded into.
     */
    public void loadWorld(Tile[][] world) {
        int n;
        int s;
        int e;
        int w;

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                n = ((img.getRGB(i + 1, j) >> 16) & 0x000000FF) - 127;
                s = ((img.getRGB(i, j + 1) >> 16) & 0x000000FF) - 127;
                e = ((img.getRGB(i + 1, j + 1) >> 16) & 0x000000FF) - 127;
                w = ((img.getRGB(i, j) >> 16) & 0x000000FF) - 127;

                world[i][j].click(n, s, e, w);
            }
        }
    }

    private void generatePixels2(BufferedImage saveImg) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                saveImg.setRGB(i + 1, j, getRgbAtNorth(i, j));

                if (i == 0) {
                    saveImg.setRGB(0, j, getRgbAtWest(i, j));
                }

                if (j == tiles.length - 1) {
                    saveImg.setRGB(i + 1, j + 1, getRgbAtNorth(i, j));
                }

                if (i == 0 && j == tiles.length - 1) {
                    saveImg.setRGB(0, j + 1, getRgbAtNorth(i, j));
                }
            }
        }
    }

    private int getRgbAtNorth(int x, int y) {
        int color = tiles[x][y].getNorthHeight() + 127;
        int value = ((255 & 0xFF) << 24) | ((color & 0xFF) << 16) | ((color & 0xFF) << 8) | ((color & 0xFF) << 0);
        return value;
    }

    private int getRgbAtWest(int x, int y) {
        int color = tiles[x][y].getWestHeight() + 127;
        int value = ((255 & 0xFF) << 24) | ((color & 0xFF) << 16) | ((color & 0xFF) << 8) | ((color & 0xFF) << 0);
        return value;
    }
}
