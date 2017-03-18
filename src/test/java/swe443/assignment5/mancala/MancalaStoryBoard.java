package swe443.assignment5.mancala;

import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by Truong on 3/17/17.
 */
public class MancalaStoryBoard {
    /*  0) (post) test1 starts with a new game and 2 turns will be made.
    *   1) first turn is made by bob when he select store 1
    *   2) second turn will be made by joe when he select store 9
    *   3) (pre) third turn will still be with joe because his last stone lands in his house.
    *
    *  @see <a href='../../../../../../doc/1.html'>1.html</a>
    * @see <a href='../../../../../../doc/1.html'>1.html</a>
 * @see <a href='../../../../../../doc/_init_game_with_2_moves.html'>_init_game_with_2_moves.html</a>
 * @see <a href='../../../../../../doc/InitBoard.html'>InitBoard.html</a>
 * @see <a href='../../../../../../doc/InitBoardWith2Moves.html'>InitBoardWith2Moves.html</a>
 */
    @Test
    public void testInitBoardWith2Moves(){
        System.out.println("[[test 1]]");
        Storyboard storyboard = new Storyboard();
        storyboard.add("1. (Pre-condition) new game is started and it is Bob's turn");
        Board board = new Board();
        board.setUpBoard();
        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);
        storyboard.addObjectDiagram("gameBoard", board);
        board.printBoard();
        storyboard.assertEquals("Bob House has 0 stones", 0,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe House has 0 stones", 0,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertTrue("Bob goes first", player1.isMyTurn());
        storyboard.assertFalse("Joe goes second", player2.isMyTurn());

        storyboard.add("2. Bob (player1) select store position 1");
        player1.getBoard().makeMove(player1,0);
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertTrue("It is Joes turn", player2.isMyTurn());
        board.printBoard();


        storyboard.add("3. Joe (player2) select store position 9");
        player2.getBoard().makeMove(player2, 8);
        board.printBoard();
        storyboard.addObjectDiagram("gameBoard", board);

        storyboard.add("4. (Post-condition) turn is still with Joe because he got his last stone in the houses");
        storyboard.assertTrue("It is still Joe's turn", player2.isMyTurn());
        storyboard.assertEquals("Joe has 1 stone in his house", 1, Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Bob has 0 stone in his house", 0, Integer.parseInt(board.getHouses().get(0).toString()));
        System.out.println("====================================================================");


        storyboard.dumpHTML();

    }

    /*
    * 0) (post) game has already been playing and both plays has 2 stones in their house. The board currently looks like this
    *  |--------------|
     * | 7 7 2 2 6 1  |
       |2           2 |
       | 1 6 0 6 0 6  |
       |--------------|
       1) It is currently Joe's turn and select store 9
       2) It is currently Bob's turn and select store 2
       3) (post) It is currently Joe's turn and select store 7, since store 7 only has 2 stone the last stone will land
           on store 9 with 0 stone. Because of this he gets to steal the stone on the opposite of him and put in
           his house.

    * @see <a href='../../../../../../doc/2.html'>2.html</a>
    * @see <a href='../../../../../../doc/ing_take_opposite.html'>ing_take_opposite.html</a>
 * @see <a href='../../../../../../doc/TakeOpposite.html'>TakeOpposite.html</a>
 */
   @Test
    public void testTakeOpposite(){
        System.out.println("[[test 2]]");
        Storyboard storyboard = new Storyboard();
        Board board = new Board();
        board.setUpBoard();
        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 0);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 8);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 9);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 2);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 6);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");          //start here
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1,4);
        board.printBoard();
        storyboard.add("1. (Pre-condition) Game has already been playing and it is currently Bobs turns and he selects store position 5.  The current score is that both player house has 2 stones");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("Bob House has 2 stones", 2,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe House has 2 stones", 2,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertTrue("It is currently Joe's turn", player2.isMyTurn());


        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,8);
        board.printBoard();
        storyboard.add("2. It is currently Joe's turn and select store position 9");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertTrue("It is currently Bob's turn", player1.isMyTurn());

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 1);
        board.printBoard();
        storyboard.add("3. It is currently Bob's turn and selects store position 2");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertTrue("It is currently Joe's turn", player2.isMyTurn());
        storyboard.assertEquals("store 7 has 2 stones", 2, board.getStores().get(6).getStones());
        storyboard.assertEquals("store 9 has 0 stones", 0, board.getStores().get(8).getStones());

        System.out.println(board.getPlayerTurn() + "'s turn");          //this here is going to post condition
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,6);
        board.printBoard();
        storyboard.add("4. (Post-condition) It is currently Joe's turn and he will select position 7 with 2 stones since his last stone will fall in to store 9 with no other stone he will take his opposite stone opposite of his and put it in his house");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("Joe now has 10 stone in his house", 10, Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("store 9 has 0 stones", 0, board.getStores().get(8).getStones());
        storyboard.assertEquals("store 4 has 0 stones", 0, board.getStores().get(3).getStones());
        System.out.println("====================================================================");


       storyboard.dumpHTML();

    }

    /* 0) (post) game has already been playing and Joe has 21 stone in his house and Bob has 17 in his. The board currently looks like this
    *  |--------------|
    *  | 1 2 0 0 0 0  |
       |21         17 |
       | 1 3 2 1 0 0  |
       |--------------|
       1) It is currently Bob's turn and he select position 2
       2) It is currently Joe's turn and he select position 11
       3) (post) it is still Joe's turn and select position 12, and since 12 only has 2 stone after his
          move all of his stores will be empty. Because of this he wins!
    * 
    * @see <a href='../../../../../../doc/3.html'>3.html</a>
    * @see <a href='../../../../../../doc/ing_winning.html'>ing_winning.html</a>
 * @see <a href='../../../../../../doc/Winning.html'>Winning.html</a>
 */
   @Test
    public void testWinning(){
        System.out.println("[[test 3]]");
        Storyboard storyboard = new Storyboard();
        Board board = new Board();

        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);

        board.setUpBoard();
        board.printPlayerList();
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 0);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 8);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 9);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 2);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 6);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1,4);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,8);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 1);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,6);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 0);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,7);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 2);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,11);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 4);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 5);
        board.printBoard();

        System.out.println(board.getPlayerTurn() + "'s turn");  //start
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 0);
        board.printBoard();
        storyboard.add("1. (Pre-condition) Game has already been playing and it is currently Bobs turns and he selects store position 1.  The current score is joe has 20 stone in his house and bob has 17 stone his his house");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("Joe now has 20 stone in his house", 20, Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Bob now has 17 stone in his house", 17, Integer.parseInt(board.getHouses().get(0).toString()));


        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,9);
        board.printBoard();
        storyboard.add("2. It is currently Joe's turn and select store 10");
        storyboard.addObjectDiagram("gameBoard", board);


        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 1);
        board.printBoard();
        storyboard.add("3. It is currently Bob's turn and he select store 2");
        storyboard.addObjectDiagram("gameBoard", board);


        System.out.println(board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,10);
        board.printBoard();
        storyboard.add("4. it is currently Joe's turn and he select store 11");
        storyboard.addObjectDiagram("gameBoard", board);


        System.out.println(board.getPlayerTurn() + "'s turn"); //post(winning!)
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2,11);
        board.printBoard();
        storyboard.add("5. (post) it is currently Joe's turn and he will select store 12 since all his store are zero he will win the game!");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("store 7 should be 0", 0, board.getStores().get(6).getStones());
        storyboard.assertEquals("store 8 should be 0", 0, board.getStores().get(7).getStones());
        storyboard.assertEquals("store 9 should be 0", 0, board.getStores().get(8).getStones());
        storyboard.assertEquals("store 10 should be 0", 0, board.getStores().get(9).getStones());
        storyboard.assertEquals("store 11 should be 0", 0, board.getStores().get(10).getStones());
        storyboard.assertEquals("store 12 should be 0", 0, board.getStores().get(11).getStones());
        storyboard.assertEquals("The winner of the game is Joe!", player2, board.checkWinner());


       storyboard.dumpHTML();

    }
}
