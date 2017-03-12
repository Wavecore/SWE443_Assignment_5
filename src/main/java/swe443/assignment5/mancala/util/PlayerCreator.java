/*
   Copyright (c) 2017 hlope
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package swe443.assignment5.mancala.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import swe443.assignment5.mancala.Player;
import de.uniks.networkparser.IdMap;
import swe443.assignment5.mancala.Game;

public class PlayerCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_MYTURN,
      Player.PROPERTY_NAME,
      Player.PROPERTY_GAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Player();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Player.PROPERTY_MYTURN.equalsIgnoreCase(attribute))
      {
         return ((Player) target).isMyTurn();
      }

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getName();
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getGame();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Player.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setName((String) value);
         return true;
      }

      if (Player.PROPERTY_MYTURN.equalsIgnoreCase(attrName))
      {
         ((Player) target).setMyTurn((Boolean) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setGame((Game) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return swe443.assignment5.mancala.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Player) entity).removeYou();
   }
}
