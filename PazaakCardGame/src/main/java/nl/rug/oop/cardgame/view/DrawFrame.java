package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.controller.ButtonBar;
import nl.rug.oop.cardgame.controller.ValueEditor;


import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class DrawFrame extends JFrame {

    /**
     * Constructor
     * @param game
     */
    public DrawFrame(Game game) {
        /* Create a frame for the GUI */
        super("Pazaak");
        /* Make sure our program exits when we close the frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Add a menu to the frame */
        setJMenuBar(new ButtonBar(game));
        /* Create a view for the game */
        DrawPanel panel = new DrawPanel(game);
        /* Mouse thing */
        new ValueEditor(game,panel);
        /* Add the view to the frame */
        add(panel);
        /* Set the size of the frame */
        setPreferredSize(new Dimension(800, 600));
        /* Try to make all the components at or above their preferred size */
        pack();
        /* Center the frame on the screen */
        setLocationRelativeTo(null);
        /* Make sure we can actually see the frame */
        setVisible(true);
    }
}
