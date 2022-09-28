package com.codecool.fiveinarow;

import static com.codecool.fiveinarow.Game.createGame;

public class FiveInARow {

    public static void main(String[] args) {


        Game game = createGame();
        game.printBoard();


        while (!game.hasWon) {
            game.getMove(game.playerNumber);
            game.mark(game.playerNumber,game.moves[0],game.moves[1]);
            game.printBoard();
            game.printResult(game.playerNumber, game.howMany, game.moves[0], game.moves[1]);
            game.switchPlayer(game.playerNumber);
        }




    }
}
