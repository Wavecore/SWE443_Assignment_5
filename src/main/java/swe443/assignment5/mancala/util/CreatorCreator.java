package swe443.assignment5.mancala.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      return jsonIdMap;
   }
}
