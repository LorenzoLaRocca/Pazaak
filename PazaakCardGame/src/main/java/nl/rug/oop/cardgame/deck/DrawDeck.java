package nl.rug.oop.cardgame.deck;

import nl.rug.oop.cardgame.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawDeck extends AbstractDeck {

    /**
     * Adds 3 of every special card and 2 of every other card
     */
    @Override
    protected void addCards() {
        for (Card card: Card.values()) {
            switch (card.getType()) {
                case SPECIAL:
                    addOnTop(card);
                case MIN:
                case PLUS:
                case MULTI:
                    addOnTop(card);
                    addOnTop(card);
                    break;
                case CONSTANT:
                    break;
            }
        }
    }

    /**
     *
     * @param type Type of card
     * @param value card value
     * @return
     */
    public Card takeCard(Card.Type type, Card.Value value) {
        for (Card card: cards) {
            if (card.getType() == type && card.getValue() == value) {
                cards.remove(card);
                return card;
            }
        }
        return null;
    }

    /**
     *
     * @param type Card type
     * @param number
     * @return
     */
    public Card takeCard(Card.Type type, int number) {
        for (Card card: cards) {
            if (card.getType() == type && card.getNumber() == number) {
                cards.remove(card);
                return card;
            }
        }
        return null;
    }

    /**
     * 4 random card from a temporary are added to the hand
     * @return
     */
    public List<Card> takeRandomCards() {
        List<Card> tempDeck = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<10; i++) {
            Card card = cards.get(random.nextInt(cards.size()));
            tempDeck.add(card);
        }
        return tempDeck;
    }
}
