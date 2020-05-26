package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.controller.actions.StandAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that stands the game. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class StandButton extends JButton {

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
     * Create the Stand button
     * @param game
     */
    public StandButton(Game game) {
        super(new StandAction(game));
        setButtonProperties();
    }
}
