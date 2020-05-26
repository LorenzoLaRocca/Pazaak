package nl.rug.oop.cardgame.controller;

import nl.rug.oop.cardgame.Card;
import nl.rug.oop.cardgame.Game;
import nl.rug.oop.cardgame.view.DrawPanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class ValueEditor extends MouseInputAdapter {

    private Game game;
    private DrawPanel panel;

    public ValueEditor(Game game, DrawPanel panel) {
        this.game = game;
        this.panel = panel;
        panel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getY() > panel.getHeight() - panel.cardHeight()) {
            int a = -1;
            int i = 0;
            for (Card card : game.getPlayer(0).getHand()) {
                if (panel.cardWidth() * i < e.getX() && e.getX() < panel.cardWidth() * (i + 1)) a = i;
                i++;
            }
            if (a != -1) {
                System.out.println(a);
                game.changeHandValues(a);
            }
        }
    }
}
