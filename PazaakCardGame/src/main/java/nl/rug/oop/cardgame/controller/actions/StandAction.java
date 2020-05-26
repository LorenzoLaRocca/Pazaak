package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class StandAction extends AbstractAction implements Observer {

    private Game game;


    /**
     * Constructor
     * Creates a new action to stand the game.
     *
     * @param game The actual Game
     */
    public StandAction(Game game) {
        super("Stand");
        this.game = game;
        game.addObserver(this);
        fixEnabled();
    }

    /**
     * Makes sure the availability of the action reflects the availability of
     * the resource it acts on, namely, draw.
     */
    private void fixEnabled() {
        if (game.getPlayerMoved() == -1) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }

    /**
     * Stand the current game
     *
     * @param e ActionEvent that is raised from the user action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.stand();
    }

    /**
     * Since availability of this action depends on the state of the
     * resources it itself depends on, this action verifies
     * after every update of draw if it can still be performed.
     */
    @Override
    public void update(Observable observed, Object message) {
        fixEnabled();
    }
}
