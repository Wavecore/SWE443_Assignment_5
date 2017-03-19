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
    *   2) second turn will be made by joe when he select store 10
    *   3) (pre) third turn will still be with joe because his last stone lands in his house.
    *
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
        System.out.println("Init board");
        board.printBoard();
        storyboard.assertEquals("Bob House has 0 stones", 0,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe House has 0 stones", 0,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertTrue("Bob goes first", player1.isMyTurn());
        storyboard.assertFalse("Joe goes second", player2.isMyTurn());

        storyboard.add("2. Bob (player1) select store position 1");
        player1.getBoard().makeMove(player1,0);
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("it is Joe turn now", "Joe", board.getPlayerTurn().toString());
        //storyboard.assertTrue("It is Joes turn", player2.isMyTurn());
        //System.out.println("Board after "+ board.getPlayerTurn() + " selects store 1");
        board.printBoard();


        storyboard.add("3. Joe (player2) select store position 10");
        player2.getBoard().makeMove(player2, 9);
        System.out.println("Board after Joe select store 9");
        board.printBoard();
        storyboard.addObjectDiagram("gameBoard", board);

        storyboard.add("4. (Post-condition) turn is still with Joe because he got his last stone in the houses");
        //storyboard.assertTrue("It is still Joe's turn", player2.isMyTurn());
        storyboard.assertEquals("It is still Joe turn", "Joe", board.getPlayerTurn().toString());
        storyboard.assertEquals("Joe has 1 stone in his house", 1, Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Bob has 0 stone in his house", 0, Integer.parseInt(board.getHouses().get(0).toString()));
        System.out.println("====================================================================");


        storyboard.dumpHTML();

    }

    /*
    * 0) (post) game has already been playing and both plays has 2 stones in their house. The board currently looks like this
    *  |--------------|
     * | 0 0 0 0 3 0  |
       |2           2 |
       | 0 4 0 2 1 0  |
       |--------------|
       1) It is currently Bob's turn and select store 4
       2) (post) It is currently Joe's turn and select store 8, since store 8 only has 3 stone the last stone will land
           on store 11 with 0 stone. Because of this he gets to steal the stone on the opposite of him and put in
           his house.

 * @see <a href='../../../../../../doc/TakeOpposite.html'>TakeOpposite.html</a>
 */
   @Test
    public void testTakeOpposite(){
       System.out.println("[[test 2]]");
       Storyboard storyboard = new Storyboard();
       Board board = new Board();
       int[] stores = {0,4,0,2,1,0,0,3,0,0,0,0};
       int[] houses = {2,2};
       board.setUpCustomBoard(stores, houses);
       Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
       Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);
       board.printBoard();
       storyboard.add("1. (Pre-condition) the game has already been going and it is currently Bob's turn.  Bob has 2 stone in his house and Joe has 2");
       storyboard.addObjectDiagram("gameBoard", board);

       board.makeMove(player1, 3);
       board.printBoard();
       storyboard.add("2. It was Bob's turn and he selected store 4 next turn goes to Joe");
       storyboard.addObjectDiagram("gameBoard", board);


       board.makeMove(player2, 7);
       board.printBoard();
       storyboard.add("3.(Post-condition) I was Joe's turn and he selected store 8");
       storyboard.addObjectDiagram("gameBoard", board);
       storyboard.assertEquals("Store 11 should have 0 stones", 0, Integer.parseInt(board.getStores().get(10).toString()));
       storyboard.assertEquals("Store 2 should have 0 stones", 0, Integer.parseInt(board.getStores().get(1).toString()));
       storyboard.assertEquals("Joe's House should have 7 stones", 7, Integer.parseInt(board.getHouses().get(1).toString()));

       storyboard.dumpHTML();

       System.out.println("====================================================================");

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
 * @see <a href='../../../../../../doc/Winning.html'>Winning.html</a>
 */
   @Test
    public void testWinning(){
       System.out.println("[[test 3]]");
       Storyboard storyboard = new Storyboard();
       Board board = new Board();
       int[] stores = {1,3,2,1,0,0,0,0,0,0,2,1};
       int[] houses = {17,21};
       board.setUpCustomBoard(stores, houses);
       Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
       Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);
       board.printBoard();
       storyboard.add("1. (Pre-condition) The game is currently going on and it is Bob's turn.  Bob currently has 17 stone in his house and Joe has 21 ");
       storyboard.addObjectDiagram("gameBoard", board);

       board.makeMove(player1, 0);
       board.printBoard();
       storyboard.add("2. It was Bob's turn he select position 1 the next turn is Joe's");
       storyboard.addObjectDiagram("gameBoard", board);


       board.makeMove(player2, 10);
       board.printBoard();
       storyboard.add("3. It was Joe's turn and he select position 11, the next turn still is Joe's");
       storyboard.addObjectDiagram("gameBoard", board);


       board.makeMove(player2, 11);
       board.printBoard();
       storyboard.add("4. (Post-Condition) It was Joe's turn and selected postion 12, since all of his stores are 0 he wins");
       storyboard.addObjectDiagram("gameBoard", board);
       storyboard.add("5. (post) it is currently Joe's turn and he will select store 12 since all his store are zero he will win the game!");
       storyboard.addObjectDiagram("gameBoard", board);
       storyboard.assertEquals("store 7 should be 0", 0, board.getStores().get(6).getStones());
       storyboard.assertEquals("store 8 should be 0", 0, board.getStores().get(7).getStones());
       storyboard.assertEquals("store 9 should be 0", 0, board.getStores().get(8).getStones());
       storyboard.assertEquals("store 10 should be 0", 0, board.getStores().get(9).getStones());
       storyboard.assertEquals("store 11 should be 0", 0, board.getStores().get(10).getStones());
       storyboard.assertEquals("store 12 should be 0", 0, board.getStores().get(11).getStones());
       storyboard.assertEquals("The winner of the game is Joe!", 2, board.checkWinner());

       storyboard.dumpHTML();
       board.checkGameStatus();

       System.out.println("====================================================================");

   }


    /* Title: Bob and Joe end their Mancala game with a tie.
  * 0) (pre-condition) Bob and Joe are playing a game of Mancala. They have been playing for a
  *     30 minutes and are nearing the end of the game with the last three moves. Bob has 15 stones in his
  *     house and Joe has 18 stones in his house. The board is displayed below.
  *  |--------------|
   * | 0 0 0 0 0 0  |  -> JOE
     |18          15|
     | 0 0 0 0 2 1  |  -> BOB
     |--------------|
     Actions:
     1) It is Bob's turn to select a store and so he grabs the stones in store number 6
     2) Bob sows counterclockwise and drops the one stone in store 6 into his House"
     3) Bob now has 16 stones in his House and is gaining on Joe's score.
     4) It is still Bob's turn since he landed his last stone on his House, so he selects stones in store number 5
     5) Bob has 2 stones in his hand and sows counterclockwise
     6) Bob drops one stone in store number 6
     7) Bob drops the last stone in his House, increasing his stone counter to 17
     8) It is Bobs turn again, and he selected the one stone in store number 6
     9) Bob drops the last stone into his House, increasing his score to 18
     10) (post-condition) The game has ended as there are no more stones in any stores. Bob has 18 stones in his house.
               Joe has 18 stones in his house. Since they have the same amount, Bob and Joe end the game in a tie.

  * @see <a href='../../../../../../doc/2.html'>2.html</a>
  * @see <a href='../../../../../../doc/ing_take_opposite.html'>ing_take_opposite.html</a>
* @see <a href='../../../../../../doc/TakeOpposite.html'>TakeOpposite.html</a>
* @see <a href='../../../../../../doc/gameEndsInaTie.html'>gameEndsInaTie.html</a>
*/
    @Test
    public void gameEndsInaTie(){
        System.out.println("[[test 4]]");
        Storyboard storyboard = new Storyboard();
        Board board = new Board();
        //ADD ARRAY OF [0,0,0,0,2,1,0,0,0,0,0,0] TO SETUPBoard
        int[] stores = {0,0,0,0,0,1,0,0,0,0,0,0};
        int[] houses = {17,18};
        board.setUpCustomBoard(stores, houses);
        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);




        storyboard.add("1. Precondition:  Bob and Joe are playing a game of Mancala. They have been playing for \n" +
                "   *     30 minutes and are nearing the end of the game with the last three moves. Bob has 17 stones in his\n" +
                "   *     house and Joe has 18 stones in his house. The board is displayed below.");
        storyboard.addObjectDiagram("gameBoard", board);
        board.printBoard();
        storyboard.assertEquals("Bob's House contains 17 stones", 17,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe's House contains 18 stones", 18,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Store number 6 contains 1 stones", 1,Integer.parseInt(board.getStores().get(5).toString()));
        storyboard.assertEquals("It Bob's turn", player1,board.getPlayerTurn());

        System.out.println("It is "+board.getPlayerTurn() + "'s turn");

        System.out.print("Bob ");
        player1.getBoard().makeMove(player1,5);
        board.printBoard();
        storyboard.add("2. It is Bob's turn to select a store and so he grabs the stones in store number 6\n" +
                "       3. Bob sows counterclockwise and drops the one stone in store 6 into his House " +
                "       4. Bob now has 18 stones in his House and ties with Joe's score.");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("It Bob's turn", player1 ,board.getPlayerTurn() );
       storyboard.assertEquals("Bob has 18 stones in his house", 18, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Stones in Bob's Store 6 is 0", 0, Integer.parseInt(board.getStores().get(5).toString()));


        storyboard.add("11. Post-condition: The game has ended as there are no more stones in any stores. Bob has 18 stones in his house.\n" +
                "                Joe has 18 stones in his house. Since they have the same amount, Bob and Joe end the game in a tie ");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("Joe has 18 stone in his house", 18,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Bob has 18 stone in his house", 18, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertTrue("The game has ended", board.isGameOver());
        System.out.println();
        storyboard.assertEquals("There is no winner", board.checkWinner(),0);


        System.out.println("====================================================================");


        storyboard.dumpHTML();

    }



    /* Title: Bob prevents Joe from stealing opposite stones
  * 0) (pre-condition) Precondition:  Bob and Joe are playing a game of Mancala. Bob has a score of 9 and Joe a score of 10." +
                "It is Bob's turn and he is pondering all the moves he could take with 16 stones on his side of the board and one of Joe's side.
                 The board is displayed below.
  *  |--------------|
   * | 0 0 1 0 0 0  |  -> JOE
     |10          9 |
     | 0 8 0 0 3 6  |  -> BOB
     |--------------|
     Actions:
     1) Bob can choose to grab two stones in store 5, adding one to his score and also add to his house by sowing from store 6
     2) Bob wants to maximize his chances of winning. It is his turn and he selects store 6 with 6 stones in it
     3) Bob sows counterclockwise, adding one stone to his house. This brings his score to 10 stones.
     4) Bob drops his last stone in store number 11
     5) Bob's turn ends since he lands on an empty store on the opponents side (Assume rules of game dont allow player to steal opposite stores from his own side)
     6) It is Joe's turn. Had Bob sowed from store number 5, Joe could have sows from store number 10 and move store 10's
     one stone to store 11, allowing him to steal Bob's 4 stones from store 2
     7) Joe selects to sow from store number 10
     8) Joe's last stone lands in store 12, ending his turn
     10) (post-condition) Joe's turn has ended. Bob and Joe both have 10 stones in their houses.
     Bob has prevented Joe from stealing the 4 stone's in store number 2

  * @see <a href='../../../../../../doc/2.html'>2.html</a>
  * @see <a href='../../../../../../doc/ing_take_opposite.html'>ing_take_opposite.html</a>
* @see <a href='../../../../../../doc/TakeOpposite.html'>TakeOpposite.html</a>
* @see <a href='../../../../../../doc/gameEndsInaTie.html'>gameEndsInaTie.html</a>
* @see <a href='../../../../../../doc/bobPreventsJoeFromStealingOppositeStones.html'>bobPreventsJoeFromStealingOppositeStones.html</a>
 */
    @Test
    public void bobPreventsJoeFromStealingOppositeStones(){
        System.out.println("[[test 5]]");
        Storyboard storyboard = new Storyboard();
        Board board = new Board();
        //ADD ARRAY OF [0,8,0,0,3,6,0,0,0,1,0,0] TO SETUPBoard  -> Houses Bob = 9, Joe -> 10
        int[] stores = {0,8,0,0,7,2,0,0,0,1,0,0};
        int[] houses = {9,10};
        board.setUpCustomBoard(stores, houses);
        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);

        board.printBoard();


        storyboard.add("1. Precondition:  Bob and Joe are playing a game of Mancala. Bob has a score of 9 and Joe a score of 10." +
                "It is Bob's turn and he is pondering all the moves he could take with 16 stones on his side of the board and one of Joe's side.");
        storyboard.addObjectDiagram("gameBoard", board);

        storyboard.assertEquals("Bob's House contains 9 stones", 9,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe's House contains 10 stones", 10,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Store number 2 contains 8 stones", 8,Integer.parseInt(board.getStores().get(1).toString()));
        storyboard.assertEquals("Store number 6 contains 2 stones", 2,Integer.parseInt(board.getStores().get(5).toString()));
        storyboard.assertEquals("Store number 5 contains 7 stones", 7,Integer.parseInt(board.getStores().get(4).toString()));
        storyboard.assertEquals("Store number 10 contains 1 stones", 1,Integer.parseInt(board.getStores().get(9).toString()));
        storyboard.assertEquals("It Bob's turn", board.getPlayerTurn(),player1);


        System.out.println("It is "+board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1,4);
        board.printBoard();
        storyboard.add("2. Bob can choose to grab seven stones in store 5, adding one to his score and also add to his house by sowing from store 6\n" +
                "     3. Bob wants to maximize his chances of winning. It is his turn and he selects store 5 with 7 stones in it");
        storyboard.addObjectDiagram("gameBoard", board);
        storyboard.assertEquals("It Joes's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Bob now has 10 stones in his house", 10, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Stones in Bob's Store 5 is 0", 0, Integer.parseInt(board.getStores().get(4).toString()));



        System.out.println("It is "+board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 4);
        board.printBoard();
        storyboard.add(" 4. Bob sows counterclockwise, adding one stone to his house. This brings his score to 10 stones.\n" +
                "     5. Bob drops his last stone in store number 11\n" +
                "     6. Bob's turn ends since he lands on an empty store on the opponents side");





        storyboard.assertEquals("Board states its Joe's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Bob has 10 stones in his house", 10, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Stones in Store 11 is 1", 1, Integer.parseInt(board.getStores().get(10).toString()));
        storyboard.addObjectDiagram("gameBoard", board);

        System.out.println("It is "+board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player2.getBoard().makeMove(player2, 9);
        board.printBoard();
        storyboard.add(" 6) It is Joe's turn. Had Bob sowed from store number 5, Joe could have sows from store number 10 and move store 10's\n" +
                "     one stone to store 11, allowing him to steal Bob's 8 stones from store 2\n" +
                "     7) Joe selects to sow from store number 10\n" +
                "     8) Joe's last stone lands in store 12, ending his turn\n" +
                "     10) (post-condition) Joe's turn has ended. Bob and Joe both have 10 stones in their houses.\n" +
                "     Bob has prevented Joe from stealing the 8 stone's in store number 2");


        storyboard.assertEquals("Board states its Bob's turn", board.getPlayerTurn(),player1);
        storyboard.assertEquals("Bob has 10 stones in his house", 10, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.addObjectDiagram("gameBoard", board);



        System.out.println("====================================================================");


        storyboard.dumpHTML();

    }

    /* Title: Bob tries to take a turn when it is Joe's turn
  * 1) Precondition:  Bob and Joe are playing a game of Mancala. Bob has a score of 9 and Joe a score of 10." +
                "It is Bob's and he is trying to add as many stones to his house as possible. has 17 stones is his side and Joe has 1.
                The inital Board is displayed below
  *  |--------------|
   * | 0 0 1 0 0 0  |  -> JOE
     |10          9 |
     | 0 8 0 1 2 6  |  -> BOB
     |--------------|
     Actions:
     1) Bob decides that he wants to sow stones in store number 4
     2) Bob grabs the stones in store 4 and sows counterclockwise
     3) Bob drops the only stone in store number 5
     4) Bob ends his turn
     5) It is Joe's turn now
     6) Bob gets really excited that he wants to sow again
     7) (post-condition) Bob's attempt to sow fails as it is Joe's turn to sow. The score remains the same with Bob having 9 stones and Joe having 10 stones.

  * @see <a href='../../../../../../doc/2.html'>2.html</a>
  * @see <a href='../../../../../../doc/ing_take_opposite.html'>ing_take_opposite.html</a>
* @see <a href='../../../../../../doc/TakeOpposite.html'>TakeOpposite.html</a>
* @see <a href='../../../../../../doc/gameEndsInaTie.html'>gameEndsInaTie.html</a>
* @see <a href='../../../../../../doc/bobTriesToTakeATurnWhenItIsJoesTurn.html'>bobTriesToTakeATurnWhenItIsJoesTurn.html</a>
 */
    @Test
    public void bobTriesToTakeATurnWhenItIsJoesTurn(){
        System.out.println("[[test 6]]");
        Storyboard storyboard = new Storyboard();
        Board board = new Board();
        //ADD ARRAY OF [0,8,0,1,2,6,0,0,0,1,0,0] TO SETUPBoard  -> Houses Bob = 9, Joe -> 10
        int[] stores = {0,8,0,1,2,6,0,0,0,1,0,0};
        int[] houses = {9,10};
        board.setUpCustomBoard(stores, houses);
        Player player1 = board.createPlayer().withName("Bob").withBoard(board).withMyTurn(true);
        Player player2 = board.createPlayer().withName("Joe").withBoard(board).withMyTurn(false);

        board.printBoard();





        storyboard.add("1. Precondition:   Bob and Joe are playing a game of Mancala. Bob has a score of 9 and Joe a score of 10.\" +\n" +
                "                \"It is Bob's and he is trying to add as many stones to his house as possible. has 17 stones is his side and Joe has 1. \n" +
                "                The inital Board is displayed below");
        storyboard.addObjectDiagram("gameBoard", board);

        storyboard.assertEquals("Bob's House contains 9 stones", 9,Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe's House contains 10 stones", 10,Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.assertEquals("Store number 2 contains 8 stones", 8,Integer.parseInt(board.getStores().get(1).toString()));
        storyboard.assertEquals("Store number 4 contains 1 stones", 1,Integer.parseInt(board.getStores().get(3).toString()));
        storyboard.assertEquals("Store number 5 contains 2 stones", 2,Integer.parseInt(board.getStores().get(4).toString()));
        storyboard.assertEquals("Store number 6 contains 6 stones", 6,Integer.parseInt(board.getStores().get(5).toString()));
        storyboard.assertEquals("Store number 10 contains 1 stones", 1,Integer.parseInt(board.getStores().get(9).toString()));
        storyboard.assertEquals("It Bob's turn", board.getPlayerTurn(),player1);


        System.out.println("It is " +board.getPlayerTurn() + "'s turn");
        System.out.print("Bob ");
        player1.getBoard().makeMove(player1, 3);
        board.printBoard();
        storyboard.add("2. Bob decides that he wants to sow stones in store number 4\n" +
                "     3. Bob grabs the stones in store 4 and sows counterclockwise\n" +
                "     4. Bob drops the only stone in store number 5\n" +
                "     5. Bob ends his turn");
        storyboard.assertEquals("It Joe's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Stones in Bob's Store 4 is 0", 0, Integer.parseInt(board.getStores().get(3).toString()));
        storyboard.assertEquals("Stones in Bob's Store 5 is 0", 3, Integer.parseInt(board.getStores().get(4).toString()));
        storyboard.assertEquals("It Joe's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Board states its Joe's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Bob has 9 stones in his house", 9, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Stones in Store 10 is 1", 1, Integer.parseInt(board.getStores().get(9).toString()));
        storyboard.addObjectDiagram("gameBoard", board);

        System.out.println("It is "+board.getPlayerTurn() + "'s turn");
        System.out.print("Joe ");
        player1.getBoard().makeMove(player1, 9);
        board.printBoard();
        storyboard.add(" 6. Its Joe's turn now, but Bob gets really excited that he wants to sow again\n" +
                "     7. (post-condition) Bob's attempt to sow fails as it is Joe's turn to sow. The score remains the same with Bob having 9 stones and Joe having 10 stones.");


        storyboard.assertEquals("Board states its Joe's turn", board.getPlayerTurn(),player2);
        storyboard.assertEquals("Bob has 9 stones in his house", 9, Integer.parseInt(board.getHouses().get(0).toString()));
        storyboard.assertEquals("Joe has 10 stones in his house", 10, Integer.parseInt(board.getHouses().get(1).toString()));
        storyboard.addObjectDiagram("gameBoard", board);



        System.out.println("====================================================================");


        storyboard.dumpHTML();

    }
}
