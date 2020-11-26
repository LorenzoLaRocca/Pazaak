package nl.rug.oop.cardgame.view.buttons;

import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.controller.actions.TurnAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that change the turn of the game. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class NextTurn extends JButton {

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
     * Create the NextTurn button
     * @param game
     */
    public NextTurn(Game game) {
        super(new TurnAction(game));
        setButtonProperties();
    }
}
