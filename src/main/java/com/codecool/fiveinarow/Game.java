package com.codecool.fiveinarow;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;
    private int nRows;
    private int nCols;
    private List<Character> alphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    public int[] moves;
    public int playerNumber = 1;
    public int howMany;
    public boolean hasWon = false;


    public Game(int nRows, int nCols, int howMany) {

        this.nRows = nRows;
        this.nCols = nCols;
        this.howMany = howMany;
        board = new int[nRows][nCols];
    }



    public  static  Game createGame () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the board (5-26)");
        boolean acceptedSize = false;

        int size = 0;
        while (!acceptedSize) {
            size=scanner.nextInt();
            if (size>=5 && size<=26) {
                acceptedSize = true;
            }else {
                System.out.println("Invalid size!");
            }
        }


        return new Game(size,size,5);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void switchPlayer(int number) {
        if (number == 1) {
            playerNumber = 2;
        } else {
            playerNumber = 1;
        }
    }

    public int[] getMove(int player) {
        System.out.println("Player " + player + "'s move:");
        Scanner scanner = new Scanner(System.in);

        int row=getRow(scanner);
        int col=getCol(scanner);


        if (board[row][col] == 0) {
            moves = new int[]{row, col};

        } else System.out.println("Wrong move");

        return moves; //never used
    }

    private int getCol(Scanner scanner) {
        int col = -1;
        do {
            while (scanner.hasNextLine()) {
                System.out.println("Please select a single column letter (or \"quit\" to exit the game):");
                String input=scanner.next().toUpperCase();
                Character columnChar = input.charAt(0);
                if (input.equals("QUIT")) {
                    System.exit(0);
                }

                if (alphabet.indexOf(columnChar)<=nCols-1 && alphabet.indexOf(columnChar)>=0) {
                    col=alphabet.indexOf(columnChar);
                    break;
                }else {
                    System.out.println("Wrong letter!");
                }


            }
        }while (col < 0);
        return col;
    }

    private int getRow(Scanner scanner) {
        int row = -1;
        do {
            System.out.println("Please select a positive row number:");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            row = scanner.nextInt();

            if (row<0) {
                System.out.println("That's not a positive number!");
            }
            if (row>nRows-1) {
                System.out.println("Too large number");
                row=-1;
            }
        } while (row < 0);
        return row;
    }

    public int[] getAiMove(int player) {
        return null;
    } //does not work

    public void mark(int player, int row, int col) {
        board[row][col] = player;

    }

    public boolean hasWon(int player, int howMany) { //does not work
        return false;
    }


    public boolean checkWinUpperLeftToLowerRightDiagonal(int player, int howMany, int row, int col) {
        int winningCondition = 1;


        for (int i = 1; i < howMany; i++) {

            if ((col + i > nCols - 1) || (row + i > nRows - 1) || !(board[row][col] == player)) {
                break;
            } else if (board[row + i][col + i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        for (int i = 1; i < howMany; i++) {

            if ((col - i < 0) || (row - i < 0) || !(board[row][col] == player)) {
                break;
            } else if (board[row - i][col - i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkWinLowerLeftToUpperRightDiagonal(int player, int howMany, int row, int col) {
        int winningCondition = 1;


        for (int i = 1; i < howMany; i++) {

            if ((col + i > nCols - 1) || (row - i < 0) || !(board[row][col] == player)) {
                break;
            } else if (board[row - i][col + i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        for (int i = 1; i < howMany; i++) {

            if ((col - i < 0) || (row + i > nRows - 1) || !(board[row][col] == player)) {
                break;
            } else if (board[row + i][col - i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean checkWinRow(int player, int howMany, int row, int col) { //KÉSZ
        int winningCondition = 1;


        for (int i = 1; i < howMany; i++) {

            if ((col + i > nCols - 1) || !(board[row][col] == player)) {
                break;
            } else if (board[row][col + i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        for (int i = 1; i < howMany; i++) {

            if ((col - i < 0) || !(board[row][col] == player)) {
                break;
            } else if (board[row][col - i] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkWinCol(int player, int howMany, int row, int col) {
        int winningCondition = 1;


        for (int i = 1; i < howMany; i++) {

            if ((row + i > nRows - 1) || !(board[row][col] == player)) {
                break;
            } else if (board[row + i][col] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        for (int i = 1; i < howMany; i++) {

            if ((row - i < 0) || !(board[row][col] == player)) {
                break;
            } else if (board[row - i][col] == player) {
                winningCondition++;
                if (winningCondition == howMany) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean isFull() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {  // OK
        System.out.print("   "); //gap for proper letter distancing

        for (int i = 0; i < nCols; i++) {
            System.out.print(" " + alphabet.get(i) + " ");
        }


        for (int i = 0; i < nRows; i++) {
            System.out.println();
            if (i < 10) {  //gap for 2 digit row distancing
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }

            for (int j = 0; j < nCols; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" . ");
                } else if (board[i][j] == 1) {
                    System.out.print(" X ");
                } else if (board[i][j] == 2) {
                    System.out.print(" O ");
                }


            }
        }
        System.out.println("");
    }

    public boolean hasWon(int player, int howMany, int row, int col) { //This has won might work

        return checkWinRow(player, howMany, row, col)
                || checkWinCol(player, howMany, row, col)
                || checkWinUpperLeftToLowerRightDiagonal(player, howMany, row, col)
                || checkWinLowerLeftToUpperRightDiagonal(player, howMany, row, col);
    }


    public void printResult(int player) {
    }


    public void printResult(int player, int howMany, int row, int col) {
        if (hasWon(player, howMany, row, col)) {
            System.out.println();
            System.out.println("Player " + player + " has won the game");
            hasWon = true;

        } else if (isFull()) {
            System.out.println();
            System.out.println("Its a draw!");
        }
    }


    public void enableAi(int player) {
    }

    public void play(int howMany) {
    }
}
