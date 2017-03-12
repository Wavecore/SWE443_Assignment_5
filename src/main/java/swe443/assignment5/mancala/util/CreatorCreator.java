package swe443.assignment5.mancala.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      jsonIdMap.with(new StoreCreator());
      jsonIdMap.with(new StorePOCreator());
      jsonIdMap.with(new BoardCreator());
      jsonIdMap.with(new BoardPOCreator());
      jsonIdMap.with(new GameCreator());
      jsonIdMap.with(new GamePOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      return jsonIdMap;
   }
}
