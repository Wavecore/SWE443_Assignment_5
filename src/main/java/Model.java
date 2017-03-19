import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
//import swe443.assignment5.mancala.*;


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
        houseClass.withMethod("lastSownEvent",DataType.VOID);
        //houseClass.withMethod("sow", DataType.VOID);

        Clazz storeClass = model.createClazz("Store");
        storeClass.withMethod("takeOppositePebbles", DataType.INT);
        storeClass.withMethod("lastSownEvent",DataType.VOID, new Parameter(DataType.INT).with("current"));
        storeClass.withSuperClazz(houseClass);


        Clazz boardClass = model.createClazz("Board")
                .withAttribute("turn", DataType.BOOLEAN);
        boardClass.withMethod("setUpBoard", DataType.VOID);
        boardClass.withMethod("isGameOver", DataType.BOOLEAN);
        boardClass.withMethod("checkGameStatus", DataType.VOID);
        //boardClass.withMethod("setUpBoardTest", DataType.VOID, new Parameter[]{});

        //Clazz gameClass = model.createClazz("Game")
        //        .withAttribute("gameDone", DataType.BOOLEAN);

        Clazz playerClass = model.createClazz("Player")
                .withAttribute("myTurn", DataType.BOOLEAN)
                .withAttribute("name", DataType.STRING);
        //playerClass.withMethod("ismyTurn", DataType.BOOLEAN);

        //Board has MANY houses and a house has ONE board
        houseClass.withBidirectional(boardClass, "board", Cardinality.ONE, "houses", Cardinality.MANY);
        //Board has MANY stores and store has ONE board
        storeClass.withBidirectional(boardClass, "board", Cardinality.ONE, "stores", Cardinality.MANY);
        //Board has ONE game and game has ONE board
     //   gameClass.withBidirectional(boardClass, "board", Cardinality.ONE, "game", Cardinality.ONE);
        //Game has MANY players and player has ONE board
      //  gameClass.withBidirectional(playerClass, "player", Cardinality.MANY, "game", Cardinality.ONE);
        //House has ONE house on its rightSide
        boardClass.withBidirectional(playerClass,"player", Cardinality.MANY, "board", Cardinality.ONE);

        houseClass.withBidirectional(houseClass,"rightSide", Cardinality.ONE, "leftSide", Cardinality.ONE);
        storeClass.withBidirectional(storeClass,"opposite",Cardinality.ONE,"opposite",Cardinality.ONE);

        model.generate();

        Storyboard storyboard = new Storyboard();
        storyboard.add("Mancala Storyboard");
        storyboard.addClassDiagram(model);


        //create object diagram//
        storyboard.add("setup the board and game");




    }

}
