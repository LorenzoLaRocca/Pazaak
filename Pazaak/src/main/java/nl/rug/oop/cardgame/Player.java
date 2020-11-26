package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.deck.DrawDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static nl.rug.oop.cardgame.Card.Type.*;
import static nl.rug.oop.cardgame.Card.Value.*;

public class Player {

    Scanner scanner;
    protected String  name;
    Random random = new Random(); // needed for generating the random hand
    private ArrayList<Card> hand;

    /**
     * Constructor
     * @param name Player name
     */
    public Player(String name) {
       this.name = name;
       hand = new ArrayList<>();
    }

    /**
     * In case you want more options
     *
     * (x) = type, (y) = value
     * PLUS 1-6
     * MIN 1-6
     * MULTI 1-6
     * SPECIAL 1-5
     */
   public List<Card> getTempDeck(){
       scanner = new Scanner(System.in);
       List<Card> tempDeck = new ArrayList<>();
       DrawDeck drawDeck = new DrawDeck();
       int x,y;
       Card.Type type = null;
       Card.Value value = null;
       int number;
       while (tempDeck.size() < 10) {
           x = scanner.nextInt();
           y = scanner.nextInt();
           System.out.println(x + " " + y);
           switch (x) {
               case 1:
                   type = PLUS;
                   break;
               case 2:
                   type = MIN;
                   break;
               case 3:
                   type = MULTI;
                   break;
               case 4:
                   type = SPECIAL;
                   switch (y) {
                       case 1:
                           value = TIE;
                           break;
                       case 2:
                           value = DOUBLE;
                           break;
                       case 3:
                           value = TWO_FOUR;
                           break;
                       case 4:
                           value = THREE_SIX;
                           break;
                       case 5:
                           value = ONE_TWO;
                           break;
                   }
                   break;
           }
           if (x != 4) {
               number = y;
               Card temp = drawDeck.takeCard(type,number);
               if (temp != null) {
                   tempDeck.add(temp);
               }
           } else {
               Card temp = drawDeck.takeCard(type,value);
               if (temp != null) {
                   tempDeck.add(temp);
               }
           }
       }
       return tempDeck;
   }

    /**
     *  Player's hand randomly generated from the players's deck
     * @param tempDeck Player's deck
     */
    public void makeHand(List<Card> tempDeck) {
        for (int i=0; i<4; i++) {
            Card card = tempDeck.get(random.nextInt(tempDeck.size()));
            tempDeck.remove(card);
            hand.add(card);
        }
        System.out.println(hand);
    }

    /**
     * List the hand of the player
     * @return hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Remove and return a card from the hand
     * @param i the index of the hand
     * @return the card
     */
    public Card getCard(int i) {
        Card temp = hand.get(i);
        hand.remove(temp);
        return temp;
    }

    /**
     * Remove and return a card from the hand
     * @param card the card to remove
     * @return the card
     */
    public Card getCard(Card card) {
        hand.remove(card);
        return card;
    }

}
