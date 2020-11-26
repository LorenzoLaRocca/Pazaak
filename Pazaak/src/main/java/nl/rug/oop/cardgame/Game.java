package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.controller.actions.PlayAction;
import nl.rug.oop.cardgame.deck.DrawDeck;
import nl.rug.oop.cardgame.deck.GameDeck;

import java.util.*;

import static nl.rug.oop.cardgame.Card.Type.MIN;
import static nl.rug.oop.cardgame.Card.Type.SPECIAL;

/**
 * The class that controls it all
 */
public class Game extends Observable implements Observer {

    /*
    players: players in the game
    values: the values of whats on the board
    handValues: the values of your hand
    points: amount of points/20
    scores: the victory score
    tiebreaker: displays player who played a tie card that round
    gameDeck: the deck of cards 1-10
    board: board
    done: is 1 if player is done in a round
    playerMoved: displays 1 if the player moved in that turn
     */
    private Player player_1;
    private Player player_2;
    private ArrayList<Integer>[] values;
    private ArrayList<Integer> handValues;
    private int[] points;
    private int[] scores;
    private int tieBreaker;
    private GameDeck gameDeck;
    List<Card>[] board;
    Scanner scanner;
    int[] done;
    int playerMoved;

    /**
     * Init
     */
    public Game () {
        player_1 = new Player("Player");
        player_2 = new Player("Bot");
        points = new int[]{0, 0, 0};
        scores = new int[]{0, 0};
        done = new int[]{0,0};
        handValues = new ArrayList<>();
        board = new List[]{new ArrayList<>(), new ArrayList<>()};
        values = new ArrayList[]{new ArrayList<>(), new ArrayList<>()};

        DrawDeck drawDeck = new DrawDeck();
        player_2.makeHand(drawDeck.takeRandomCards());
        scanner = new Scanner(System.in);
        player_1.makeHand(drawDeck.takeRandomCards());
        setHandValues();
        player_2.makeHand(drawDeck.takeRandomCards());
    }

    /**
     * Calculates next turn
     */
    public void nextTurn() {
        playerMoved = 0;

        if (done[1] == 0) {
            draw(1);
            botMove();
            System.out.println("The board: " + board[1] + "\n" + "Values: " + values[1] + " = " + scores[1]);
            if ((scores[1] > 17 && scores[1] <= 20) || board[1].size() >= 9 || scores[1] > 26) done[1] = 1;
        }

        if (board[0].size() >= 9) {
            stand();
            return;
        }
        draw(0);
        getHand();
    }

    /**
     * Calculates if player stands
     * Continues untill both players are done and then resets the game for the next round
     */
    public void stand() {

        done[0] = 1;
        while(done[0] != done[1]) {
            draw(1);
            botMove();
            setChanged();
            notifyObservers();
            System.out.println("The board: " + board[1] + "\n" + "Values: " + values[1] + " = " + scores[1]);
            if ((scores[1] > 17 && scores[1] <= 20) || board[1].size() >= 9 || scores[1] > 26) done[1] = 1;
        }

        if (scores[0] > 20) {
            if (scores[1] > 20) {
                points[2] += 1;
            } else {
                points[1] += 1;
            }
        } else if (scores[1] > 20) {
            points[0] += 1;
        } else if (scores[0] > scores[1]) {
            points[0] += 1;
        } else if (scores[0] < scores[1]) {
            points[1] += 1;
        } else {
            points[tieBreaker] += 1;
        }

        resetGame();
        if (points[0] == 3 || points[1] == 3) {
            setChanged();
            notifyObservers();
        } else {
            draw(0);
            getHand();
        }
    }

    /**
     * resets the variables for a next round
     */
    public void resetGame() {
        System.out.println("Scores:" + scores[0] + " vs " + scores[1]);
        System.out.println("P1:" + points[0] + " P2:" + points[1]);
        playerMoved = 0;
        tieBreaker = 2;
        values[0].clear();
        values[1].clear();
        gameDeck = new GameDeck();
        gameDeck.shuffle();
        board[0].clear();
        board[1].clear();
        setChanged();
        notifyObservers();
        scores[0] = 0;
        scores[1] = 0;
        done[0] = 0;
        done[1] = 0;
        playerMoved = 0;
    }

    /**
     * Draw a card
     * @param player who gets the card
     */
    public void draw(int player) {
        Card temp = gameDeck.draw();
        scores[player] += temp.getNumber();
        values[player].add(temp.getNumber());
        board[player].add(temp);
        setChanged();
        notifyObservers();
        System.out.println("The board: " + board[player] + "\n" + "Values: " + values[player] + " = " + scores[player]);
    }

    /**
     * Get playerMoved
     * @return if the player has moved
     */
    public int getPlayerMoved() {
        return playerMoved;
    }

    /**
     * Get the player hand
     */
    public void getHand() {
        setChanged();
        notifyObservers();
        System.out.println("The board: " + board[0] + "\n" + "Values: " + values[0] + " = " + scores[0]);
        int i = 0;
        for (Card card: player_1.getHand()) {
            System.out.println("    (" + i + ") " + card);
            i++;
        }
    }





    /*
    BELOW ARE THE ACTIONS FOR THE PLAYER AND THE BOT
     */


    /**
     * player turn
     */
    public void action(int input) {
        playerMoved = 1;
        if (-1 < input && input < (player_1.getHand().size())) {
            Card temp = player_1.getCard(input);
            System.out.println(temp.getType());
            switch (temp.getType()) {
                case PLUS:
                case MIN:
                case MULTI:
                    scores[0] += handValues.get(input);
                    values[0].add(handValues.get(input));
                    break;
                case SPECIAL:
                    System.out.println(temp.getValue());
                    switch (temp.getValue()) {
                        case TIE:
                            scores[0] += handValues.get(input);
                            values[0].add(handValues.get(input));
                            _tie(0);
                            break;
                        case DOUBLE:
                            _double(0);
                            break;
                        case TWO_FOUR:
                            _a_b(0,2,4);
                            break;
                        case THREE_SIX:
                            _a_b(0,3,6);
                            break;
                        case ONE_TWO:
                            scores[0] += handValues.get(input);
                            values[0].add(handValues.get(input));
                            break;
                    }

            }
            board[0].add(temp);
            handValues.remove(input);
            setChanged();
            notifyObservers();
        }

    }

    /**
     * bot turn
     */
    private void botMove() {
        if (scores[1] < 10) return;
        List<Card> hand = player_2.getHand();
        for (Card temp : hand) {
            switch (temp.getType()) {
                case PLUS:
                    if (scores[1] + temp.getNumber() <= 20 && scores[1] + temp.getNumber() > 18) {
                        scores[1] += temp.getNumber();
                        values[1].add(temp.getNumber());
                        player_2.getCard(temp);
                        board[1].add(temp);
                        return;
                    }
                    break;
                case MIN:
                    if (scores[1] - temp.getNumber() <= 20 && scores[1] - temp.getNumber() > 18) {
                        scores[1] -= temp.getNumber();
                        values[1].add(-temp.getNumber());
                        player_2.getCard(temp);
                        board[1].add(temp);
                        return;
                    }
                    break;
                case MULTI:
                    if (scores[1] + temp.getNumber() <= 20 && scores[1] + temp.getNumber() > 18) {
                        scores[1] += temp.getNumber();
                        values[1].add(temp.getNumber());
                        player_2.getCard(temp);
                        board[1].add(temp);
                        return;
                    }
                    if (scores[1] - temp.getNumber() <= 20 && scores[1] - temp.getNumber() > 18) {
                        scores[1] -= temp.getNumber();
                        values[1].add(-temp.getNumber());
                        player_2.getCard(temp);
                        board[1].add(temp);
                        return;
                    }
                    break;
                case SPECIAL:
                    switch (temp.getValue()) {
                        case TIE:
                            if (scores[1] + 1 <= 20 && scores[1] + temp.getNumber() > 18) {
                                _tie(1);
                                values[1].add(1);
                                scores[1] += 1;
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            break;
                        case DOUBLE:
                            int last = values[1].get(values[1].size() - 1);
                            if (last <= 20 && scores[1] + last > 18) {
                                _double(1);
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            break;
                        case TWO_FOUR:
                        case THREE_SIX:
                            break;
                        case ONE_TWO:
                            if (scores[1] + 2 <= 20 && scores[1] + 2 > 18) {
                                _one_two(1, 3);
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            if (scores[1] - 2 <= 20 && scores[1] - 2 > 18) {
                                _one_two(1, 0);
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            if (scores[1] + 1 <= 20 && scores[1] + 1 > 18) {
                                _one_two(1, 2);
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            if (scores[1] - 1 <= 20 && scores[1] - 1 > 18) {
                                _one_two(1, 1);
                                player_2.getCard(temp);
                                board[1].add(temp);
                                return;
                            }
                            break;
                    }
            }
        }
    }

    /**
     * Special card tie
     * @param player player
     */
    private void _tie(int player) {
        tieBreaker = player;
    }

    /**
     * Special card double
     * @param player player
     */
    private void _double(int player) {
        scores[player] += board[player].get(board[player].size() - 1).getNumber();
        values[player].add(values[player].get(values[player].size()-1));
    }

    /**
     * Special card a+-b
     * Make a -a and b -b
     * @param player player
     * @param a first number
     * @param b second number
     */
    private void _a_b (int player,int a,int b) {
        int cnt = 0;
        values[player].add(0);
        System.out.println(values[player]);
        for (int j = 0; j < values[player].size(); j++) {
            int i = values[player].get(j);
            System.out.println(i);
            if (i == a || i == -a || i == b || i == -b) {
                values[player].remove(cnt);
                scores[player] -= 2*i;
                values[player].add(cnt,-i);
                if (i < 0) {
                    switch (i) {
                        case -2:
                            board[player].set(cnt,Card.PLUS_TWO);
                            break;
                        case -3:
                            board[player].set(cnt,Card.PLUS_THREE);
                            break;
                        case -4:
                            board[player].set(cnt,Card.PLUS_FOUR);
                            break;
                        case -6:
                            board[player].set(cnt,Card.PLUS_SIX);
                            break;
                    }
                } else if (i > 0) {
                    switch (i) {
                        case 2:
                            board[player].set(cnt,Card.MIN_TWO);
                            break;
                        case 3:
                            board[player].set(cnt,Card.MIN_THREE);
                            break;
                        case 4:
                            board[player].set(cnt,Card.MIN_FOUR);
                            break;
                        case 6:
                            board[player].set(cnt,Card.MIN_SIX);
                            break;
                    }
                }
            }
            cnt++;
        }
    }

    /**
     * Special card 1+-2
     * @param player player
     * @param option 0:-2, 1:-1, 2:1, 3:2
     */
    private void _one_two (int player,int option) {
        if (option == 0) {
            scores[player] -= 2;
            values[player].add(-2);
        }
        if (option == 1) {
            scores[player] -= 1;
            values[player].add(-1);
        }
        if (option == 2) {
            scores[player] += 1;
            values[player].add(1);
        }
        if (option == 3) {
            scores[player] += 2;
            values[player].add(2);
        }
    }



    /**
     * Get player
     * @param player the player
     */
    public Player getPlayer(int player){
        if (player == 0) return player_1;
        return player_2;
    }

    /**
     * Get the board
     * @return the board
     */
    public List<Card>[] getBoard() {
        return board;
    }

    /**
     * Set the original values of the cards in handValues so that they can be changed later
     */
    public void setHandValues() {
        int nr = 0;
        List<Card> hand = player_1.getHand();
        for (Card card: hand) {
            handValues.add(card.getNumber());
            if (card.getType() == MIN) {
                handValues.remove(nr);
                handValues.add(-card.getNumber());
            }
            if (card.getType() == SPECIAL) {
                switch (card.getValue()) {
                    case TIE:
                    case ONE_TWO:
                        handValues.remove(nr);
                        handValues.add(1);
                        break;
                }
            }
            nr += 1;
        }

    }

    /**
     * Change the value of a card like multi or one_two
     * @param nr the index of the card in the hand of the player
     */
    public void changeHandValues(int nr) {
        Card card = player_1.getHand().get(nr);
        int temp = handValues.get(nr);
        switch (card.getType()) {
            case PLUS:
            case MIN:
                break;
            case MULTI:
                handValues.set(nr,-temp);
                break;
            case SPECIAL:
                switch (card.getValue()) {
                    case TIE:
                        handValues.set(nr,-temp);
                        break;
                    case DOUBLE:
                        break;
                    case TWO_FOUR:
                        break;
                    case THREE_SIX:
                        break;
                    case ONE_TWO:
                        if (temp == -2) handValues.set(nr,-1);
                        if (temp == -1) handValues.set(nr,1);
                        if (temp == 1) handValues.set(nr,2);
                        if (temp == 2) handValues.set(nr,-2);
                        break;
                }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Get the values of the cards in the player hand
     * @return handValues
     */
    public ArrayList<Integer> getHandValues(){
        return handValues;
    }

    /**
     * Get the current scores
     * @return scores
     */
    public int[] getScores(){
        return scores;
    }

    /**
     * Get the current points
     * @return points
     */
    public int[] getPoints() {
        return points;
    }

    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }
}
