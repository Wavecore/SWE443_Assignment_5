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
        board.setUpBoard();

        System.out.println(board.getPlayer());
        System.out.println(board.getStores());
        System.out.println(board.getHouses());

    }
}