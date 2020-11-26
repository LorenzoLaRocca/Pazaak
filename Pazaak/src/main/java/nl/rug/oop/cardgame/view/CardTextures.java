package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

/**
 * For each card, this class loads a texture. It does so
 * statically to prevent duplicate loading of large images. It also does it
 * eagerly (at startup) to prevent loading times from slowing the program down
 * at runtime.
 */
public class CardTextures {

    private static EnumMap<Card, BufferedImage> textures;

    /*
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(Card.class);
        for (Card card : Card.values()) {
            BufferedImage texture = null;
            String fileName = "target/classes/textures/" + card + ".png";
            //String fileName = "assignment_2/src/main/resources/textures/" + card + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName);
            }
            textures.put(card, texture);
        }
    }

    /**
     * Find a texture for a card.
     *
     * @param card The cart in question.
     */
    public static BufferedImage getTexture(Card card) {
        return textures.get(card);
    }

}
