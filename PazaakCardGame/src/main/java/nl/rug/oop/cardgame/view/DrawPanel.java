package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.Card;
import nl.rug.oop.cardgame.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View of Draw
 */
public class DrawPanel extends JPanel implements Observer {

    /**
     * Use double to prevent mistakes with integer division
     * Should match the texture dimensions for best results
     */
    private static final double CARD_WIDTH = 172;
    private static final double CARD_HEIGHT = 237;

    /**
     * Background color
     */
    private static final Color BACKGROUND_COLOR = new Color(0x7E, 0x35, 0x4D);

    private Game game;


    /**
     * Constructor
     * Create a new DrawPanel
     */
    public DrawPanel(Game game) {
        this.game = game;
        game.addObserver(this);
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
    }
    /**
     * Paint the areas in which deck and discard pile can be found
     */
    private void paintAreas(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() / 2, getHeight() - 1);
        g.drawString("Deck Player", getWidth() / 10, 350);
        g.drawRect(getWidth() / 2, 0, getWidth() / 2 - 1, getHeight() - 1);
        g.drawLine(0,360,1000,360);
        g.drawString("Deck Cpu", 3 * (getWidth() / 4), 350);
        g.setColor(Color.BLACK);
    }

    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / CARD_HEIGHT);
    }

    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        /*
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) <= 1.0)
            return (int) ((cardHeight() * CARD_WIDTH) / CARD_HEIGHT);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;

         */
        return getWidth()/8;
    }

    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardHeight() {
        /*
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) > 1.0)
            return (int) ((cardWidth() * CARD_HEIGHT) / CARD_WIDTH);
        return (getHeight() - getSpacing() * 2 - 2 * Card.values().length);

         */
        return (int)((double)getHeight()/4.5);
    }

    /**
     * How the cards are going to be displayed on the board
     * @param g
     */
    private void paintBoard(Graphics g) {
        int i = 0;
        for (Card card:game.getBoard()[0]) {
            int posX=0,posY=0;
            if (i < 3) {
                posX = i*cardWidth();
                posY = 0;
            } else if (i < 7) {
                posX = (i-3)*cardWidth();
                posY = cardHeight();
            } else {
                posX = (i-7)*cardWidth();
                posY = 2*cardHeight();
            }
            g.drawImage(CardTextures.getTexture(card),posX,posY,cardWidth(),cardHeight(),this);
            g.drawRect(posX,posY,cardWidth(),cardHeight());
            i++;
        }
        g.drawString("Wins:" + String.valueOf(game.getPoints()[0]), (int)(cardWidth()*3.3), (int)(cardHeight()*0.4));
        g.drawString("Point:" + String.valueOf(game.getScores()[0]), (int)(cardWidth()*3.3), (int)(cardHeight()*0.5));

        i = 0;
        for (Card card:game.getBoard()[1]) {
            int posX=0,posY=0;
            if (i < 3) {
                posX = getWidth()/2 + (i+1)*cardWidth();
                posY = 0;
            } else if (i < 7) {
                posX = getWidth()/2 + (i-3)*cardWidth();
                posY = cardHeight();
            } else {
                posX = getWidth()/2 + (i-7)*cardWidth();
                posY = 2*cardHeight();
            }
            g.drawImage(CardTextures.getTexture(card),posX,posY,cardWidth(),cardHeight(),this);
            g.drawRect(posX,posY,cardWidth(),cardHeight());
            i++;
        }
        g.drawString("Wins:" + String.valueOf(game.getPoints()[1]), (int)(cardWidth()*4.3), (int)(cardHeight()*0.4));
        g.drawString("Point:" + String.valueOf(game.getScores()[1]), (int)(cardWidth()*4.3), (int)(cardHeight()*0.5));

        if (game.getPoints()[0] == 3) {
            g.drawString("YOU WIN!", (int)(cardWidth()*3.3), (int)(cardHeight()*0.6));
        }
        if (game.getPoints()[1] == 3) {
            g.drawString("YOU LOSE!", (int)(cardWidth()*3.3), (int)(cardHeight()*0.6));
        }
    }

    /**
     * How the cards are displayed on the players deck
     * @param g
     */
    private void paintHand(Graphics g) {
        int depth = 0;
        for (Card card:game.getPlayer(0).getHand()) {
            int posX = depth*cardWidth();
            int posY = getHeight()-cardHeight();
            g.drawString(String.valueOf(game.getHandValues().get(depth)), posX+cardWidth()/2, posY - 10);
            g.drawImage(CardTextures.getTexture(card),posX,posY,cardWidth(),cardHeight(),this);
            g.drawRect(posX,posY,cardWidth(),cardHeight());
            ++depth;
        }
    }

    /**
     * Paint the items that this class alone is responsible for.
     * <p>
     * This method is part of a template method that calls
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintHand(g);
        paintBoard(g);
    }

    /**
     * Tell this DrawPanel that the object it displays has changed
     */
    @Override
    public void update(Observable observable, Object message) {
        repaint();
    }
}
