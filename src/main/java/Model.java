import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.models.classes.ClassModel;

/**
 * Created by Wave on 3/2/2017.
 */
public class Model {
    public static void main(String[] arg) {
        ClassModel model = new ClassModel("swe443.assignment5.mancala");
        Clazz houseClass = model.createClazz("House")
                .withAttribute("stones", DataType.INT);
        //houseClass.withUniDirectional(houseClass,"rightNeighbor", Cardinality.ONE);
        model.generate();
    }

}
