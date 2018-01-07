package roller.coaster.tycoon.guest

import spock.lang.Specification

import java.awt.*
import java.awt.image.BufferedImage

import static Direction.*

class GuestGraphicsSpec extends Specification {

    def "should draw north image when direction is north"() {
        given:
        BufferedImage img = img()
        BufferedImage[] northImages = [img, img, img, img, img, img]

        GuestGraphics graphics = GuestGraphics.builder()
                .northImg(northImages)
                .build()

        Graphics g = Mock(Graphics.class)

        when:
        graphics.draw(g, NORTH, 12.0, 12.0, 1)

        then:
        1 * g.drawImage(img, 6, -7, null)
    }

    def "should draw east image when direction is east"() {
        given:
        BufferedImage img = img()
        BufferedImage[] eastImages = [img, img, img, img, img, img]

        GuestGraphics graphics = GuestGraphics.builder()
                .eastImg(eastImages)
                .build()

        Graphics g = Mock(Graphics.class)

        when:
        graphics.draw(g, EAST, 12.0, 12.0, 1)

        then:
        1 * g.drawImage(img, 6, -7, null)
    }

    def "should draw south image when direction is south"() {
        given:
        BufferedImage img = img()
        BufferedImage[] southImages = [img, img, img, img, img, img]

        GuestGraphics graphics = GuestGraphics.builder()
                .southImg(southImages)
                .build()

        Graphics g = Mock(Graphics.class)

        when:
        graphics.draw(g, SOUTH, 12.0, 12.0, 1)

        then:
        1 * g.drawImage(img, 6, -7, null)
    }

    def "should draw west image when direction is west"() {
        given:
        BufferedImage img = img()
        BufferedImage[] westImages = [img, img, img, img, img, img]

        GuestGraphics graphics = GuestGraphics.builder()
                .westImg(westImages)
                .build()

        Graphics g = Mock(Graphics.class)

        when:
        graphics.draw(g, WEST, 12.0, 12.0, 1)

        then:
        1 * g.drawImage(img, 6, -7, null)
    }

    private BufferedImage img() {
        return new BufferedImage(1, 1, 1);
    }

}
