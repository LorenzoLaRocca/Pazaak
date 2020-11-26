package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class PlayAction extends AbstractAction implements Observer {

    private Game game;
    private int nr;

    /**
     * Constructor
     * Creates a new action to play a card.
     *
     * @param game The actual game
     * @param nr Play card number
     */
    public PlayAction(Game game,int nr) {
        super("Play card " + nr);
        this.game = game;
        this.nr = nr;
        game.addObserver(this);
        fixEnabled();
    }

    /**
     * Makes sure the availability of the action reflects the availability of
     * the resource it acts on, namely, draw.
     */
    private void fixEnabled() {
        if (game.getPlayer(0).getHand().size() < nr-1 || game.getPlayerMoved() == 1 || game.getScores()[0] == 3 || game.getScores()[1] == 3) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }

    /**
     * Plays a card
     *
     * @param e ActionEvent that is raised from the user action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.action(nr);
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
