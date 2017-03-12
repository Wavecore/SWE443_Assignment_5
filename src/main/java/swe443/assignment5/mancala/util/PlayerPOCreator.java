package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.assignment5.mancala.Player;

public class PlayerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PlayerPO(new Player[]{});
      } else {
          return new PlayerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.assignment5.mancala.util.CreatorCreator.createIdMap(sessionID);
   }
}
