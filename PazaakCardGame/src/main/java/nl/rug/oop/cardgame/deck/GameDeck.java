package nl.rug.oop.cardgame.deck;

import nl.rug.oop.cardgame.Card;

public class GameDeck extends AbstractDeck {

    /**
     * Players deck
     */
    @Override
    protected void addCards() {
        for (Card card: Card.values()) {
            if (card.getType() == Card.Type.CONSTANT) {
                addOnTop(card);
                addOnTop(card);
                addOnTop(card);
                addOnTop(card);
            }
        }
    }
}
