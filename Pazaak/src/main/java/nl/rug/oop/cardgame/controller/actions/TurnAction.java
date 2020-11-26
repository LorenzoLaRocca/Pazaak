package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class TurnAction extends AbstractAction implements Observer {

    private Game game;


    public TurnAction(Game game) {
        super("Next turn");
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
     * Next Turn of the game
     *
     * @param e ActionEvent that is raised from the user action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.nextTurn();
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
