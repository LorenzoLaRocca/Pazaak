package nl.rug.oop.cardgame.view.buttons;

import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.controller.actions.PlayAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that plays the card. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class PlayCard extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties(){
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Play card");
    }

    /**
     * Constructor
     * Create the PlayCard button
     * @param game
     * @param nr
     */
    public PlayCard(Game game,int nr) {
        super(new PlayAction(game,nr));
        setButtonProperties();
    }
}
