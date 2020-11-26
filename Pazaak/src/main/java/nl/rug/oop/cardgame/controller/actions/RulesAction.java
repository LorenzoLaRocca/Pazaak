package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RulesAction extends AbstractAction implements Observer {


    private Game game;

    /**
     * Constructor
     * Creates a new action to the game rules.
     *
     * @param game The actual game
     */
    public RulesAction(Game game) {
        super("Rules");
        this.game = game;
        game.addObserver(this);
    }

    /**
     * Since availability of this action depends on the state of the
     * resources it itself depends on, this action verifies
     * after every update of draw if it can still be performed.
     */
    @Override
    public void update(Observable o, Object arg) {
    }

    /**
     * Shows the rules of Pazaak!
     *
     * @param e ActionEvent that is raised from the user action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"Welcome to Pazaak! " +
                "\nTo win this game your score " +
                "must be 20 or less and higher than your opponent. \nYou only have 4 cards" +
                " in your hands. Use them wisely! Pay attention that " +
                "some cards are special. Use them to discover their " +
                " value! You can change \n" +
                "some values card by  " +
                "clicking on them!","Rules",JOptionPane.INFORMATION_MESSAGE);

    }
}
