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

        Player player1 = board.createPlayer().withName("Player1").withBoard(board);
        Player player2 = board.createPlayer().withName("Player2").withBoard(board);

        board.setUpBoard();
        board.printBoard();
    }
}