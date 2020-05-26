package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.view.DrawFrame;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        game.resetGame();
        game.draw(0);
        game.getHand();
        /*
        int i = 0;
        while(true) {
            game.getHand();
            try{
                i = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("didnt work man");
            }
            switch (i){
                case 0:
                case 1:
                case 2:
                case 3:
                    game.action(i);
                    break;
                case 4:
                    game.nextTurn();
                    break;
                case 5:
                    game.stand();
                    break;
            }
        }
        */

        new DrawFrame(game);


        //game.start();
    }
}