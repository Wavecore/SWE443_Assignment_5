/*
   Copyright (c) 2017 Wave
   
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
import swe443.assignment5.mancala.Board;
import de.uniks.networkparser.IdMap;
import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.Store;
import swe443.assignment5.mancala.Player;

public class BoardCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Board.PROPERTY_HOUSES,
      Board.PROPERTY_STORES,
      Board.PROPERTY_PLAYER,
      Board.PROPERTY_TURN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Board();
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

      if (Board.PROPERTY_HOUSES.equalsIgnoreCase(attribute))
      {
         return ((Board) target).getHouses();
      }

      if (Board.PROPERTY_STORES.equalsIgnoreCase(attribute))
      {
         return ((Board) target).getStores();
      }

      if (Board.PROPERTY_PLAYER.equalsIgnoreCase(attribute))
      {
         return ((Board) target).getPlayer();
      }

      if (Board.PROPERTY_TURN.equalsIgnoreCase(attribute))
      {
         return ((Board) target).isTurn();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Board.PROPERTY_TURN.equalsIgnoreCase(attrName))
      {
         ((Board) target).setTurn((Boolean) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Board.PROPERTY_HOUSES.equalsIgnoreCase(attrName))
      {
         ((Board) target).withHouses((House) value);
         return true;
      }
      
      if ((Board.PROPERTY_HOUSES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Board) target).withoutHouses((House) value);
         return true;
      }

      if (Board.PROPERTY_STORES.equalsIgnoreCase(attrName))
      {
         ((Board) target).withStores((Store) value);
         return true;
      }
      
      if ((Board.PROPERTY_STORES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Board) target).withoutStores((Store) value);
         return true;
      }

      if (Board.PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         ((Board) target).withPlayer((Player) value);
         return true;
      }
      
      if ((Board.PROPERTY_PLAYER + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Board) target).withoutPlayer((Player) value);
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
      ((Board) entity).removeYou();
   }
}
