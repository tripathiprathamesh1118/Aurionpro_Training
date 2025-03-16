package com.aurionpro.model;

import java.util.Scanner;

public class Facade {
    private Board board;
    private ResultAnalyzer resultAnalyzer;
    private MarkType currentPlayer;
    private Scanner scanner;

    public Facade() {
        scanner = new Scanner(System.in);
        board = new Board();
        resultAnalyzer = new ResultAnalyzer(board);
        currentPlayer = MarkType.X;
    }

    public void startGame() {
        while (true) {
            playGame();
            System.out.println("Do you want to play again? (yes/no):");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes")) {
                System.out.println("Thanks!");
                break;
            }
            resetGame();
        }
        scanner.close();
    }

    private void playGame() {
        while (true) {
            board.displayBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (0-8):");
            int move = getValidMove();

            try {
                boolean gameWon = board.makeMove(move, currentPlayer);
                String result = resultAnalyzer.analyzeResult();
                
                if (!result.equals("Game in Progress...")) {
                    board.displayBoard();
                    System.out.println(result);
                    break;
                }
                currentPlayer = (currentPlayer == MarkType.X) ? MarkType.O : MarkType.X;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void resetGame() {
        board.resetBoard();
        resultAnalyzer = new ResultAnalyzer(board); 
        currentPlayer = MarkType.X; 
    }

    private int getValidMove() {
        int move = -1;
        while (move < 0 || move > 8) {
            System.out.print("Enter your move (0-8): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 8.");
                scanner.next(); 
                continue;
            }
            move = scanner.nextInt();
            if (move < 0 || move > 8) {
                System.out.println("Invalid input. Please enter a number between 0 and 8.");
            }
        }
        return move;
    }
}
