import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import swe443.assignment5.mancala.*;


/**
 * Created by Wave on 3/2/2017.
 */
public class Model {
      /**
    * 
    * @see <a href='../../../doc/Model.html'>Model.html</a>
 */
   public static void main(String[] arg) {
        ClassModel model = new ClassModel("swe443.assignment5.mancala");
        Clazz houseClass = model.createClazz("House")
                .withAttribute("stones", DataType.INT);
        houseClass.withMethod("sow", DataType.VOID);

        Clazz storeClass = model.createClazz("Store")
                .withAttribute("stones",DataType.INT);
        storeClass.withMethod("takeOppositePebbles", DataType.VOID);

        Clazz boardClass = model.createClazz("Board");
        boardClass.withMethod("setUpBoard", DataType.VOID);

        Clazz gameClass = model.createClazz("Game")
                .withAttribute("gameDone", DataType.BOOLEAN);

        Clazz playerClass = model.createClazz("Player")
                .withAttribute("myTurn", DataType.BOOLEAN)
                .withAttribute("name", DataType.STRING);;
        playerClass.withMethod("ismyTurn", DataType.BOOLEAN);

        //Board has MANY houses and a house has ONE board
        houseClass.withBidirectional(boardClass, "board", Cardinality.ONE, "houses", Cardinality.MANY);
        //Board has MANY stores and store has ONE board
        storeClass.withBidirectional(boardClass, "board", Cardinality.ONE, "stores", Cardinality.MANY);
        //Board has ONE game and game has ONE board
        gameClass.withBidirectional(boardClass, "board", Cardinality.ONE, "game", Cardinality.ONE);
        //Game has MANY players and player has ONE board
        gameClass.withBidirectional(playerClass, "player", Cardinality.MANY, "game", Cardinality.ONE);

        model.generate();

        Storyboard storyboard = new Storyboard();
        storyboard.add("Mancala Storyboard");
        storyboard.addClassDiagram(model);


        //create object diagram//
        storyboard.add("setup the board and game");
        //Initalize the Game and board
        Game mancala = new Game();
        Board mainBoard = new Board().withGame(mancala);

        //Create two players
        Player abe = new Player().withName("Abe").withGame(mancala).withMyTurn(true);
        Player bob = new Player().withName("Bob").withGame(mancala).withMyTurn(false);


        //Initalize 12 Stores
        Store sOne = new Store().withBoard(mainBoard).withStones(3);
        Store sTwo = new Store().withBoard(mainBoard).withStones(3);
        Store sThree = new Store().withBoard(mainBoard).withStones(3);
        Store sFour = new Store().withBoard(mainBoard).withStones(3);
        Store sFive = new Store().withBoard(mainBoard).withStones(3);
        Store sSix = new Store().withBoard(mainBoard).withStones(3);
        Store sSeven = new Store().withBoard(mainBoard).withStones(3);
        Store sEight = new Store().withBoard(mainBoard).withStones(3);
        Store sNine = new Store().withBoard(mainBoard).withStones(3);
        Store sTen = new Store().withBoard(mainBoard).withStones(3);
        Store sEleven = new Store().withBoard(mainBoard).withStones(3);
        Store sTwelve = new Store().withBoard(mainBoard).withStones(3);

        //Initialize 2 Houses
        House hAbe = new House().withBoard(mainBoard).withStones(0);
        House hBob = new House().withBoard(mainBoard).withStones(0);



        storyboard.addObjectDiagram(mancala, mainBoard, abe, bob);
        storyboard.dumpHTML();





    }

}
