package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.controller.actions.RulesAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button shows the game rules. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class Rules extends JButton{

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties(){
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Rules");
    }

    /**
     * Constructor
     * Create the Rules button
     * @param game
     */
    public Rules(Game game) {
        super(new RulesAction(game));
        setButtonProperties();
    }
}
