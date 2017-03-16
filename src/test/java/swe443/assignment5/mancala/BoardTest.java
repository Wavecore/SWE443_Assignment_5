package swe443.assignment5.mancala;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniel on 3/15/17.
 */
public class BoardTest {
    @Test
    public void setUpBoard() throws Exception {
        Board board = new Board();

        Player player1 = board.createPlayer().withName("Player1").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Player2").withBoard(board).withMyTurn(false);

        board.setUpBoard();
        board.printPlayerList();
        board.printBoard();

//        board.checkOpposites();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player1 ");
        player1.getBoard().makeMove(player1, 0);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player2 ");
        player2.getBoard().makeMove(player2, 8);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player2 ");
        player2.getBoard().makeMove(player2, 9);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player1 ");
        player1.getBoard().makeMove(player1, 2);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player2 ");
        player2.getBoard().makeMove(player2, 6);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player1 ");
        player1.getBoard().makeMove(player1,4);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player2 ");
        player2.getBoard().makeMove(player2,8);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player1 ");
        player1.getBoard().makeMove(player1, 1);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Player2 ");
        player2.getBoard().makeMove(player2,6);
        board.printBoard();
    }
}