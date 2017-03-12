package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.assignment5.mancala.Game;

public class GamePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GamePO(new Game[]{});
      } else {
          return new GamePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.assignment5.mancala.util.CreatorCreator.createIdMap(sessionID);
   }
}
