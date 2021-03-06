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
import swe443.assignment5.mancala.Store;
import de.uniks.networkparser.IdMap;
import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.Board;

public class StoreCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      House.PROPERTY_STONES,
      Store.PROPERTY_BOARD,
      Store.PROPERTY_LEFTSIDE,
      Store.PROPERTY_RIGHTSIDE,
      Store.PROPERTY_OPPOSITE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Store();
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

      if (House.PROPERTY_STONES.equalsIgnoreCase(attribute))
      {
         return ((House) target).getStones();
      }

      if (Store.PROPERTY_BOARD.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getBoard();
      }

      if (Store.PROPERTY_LEFTSIDE.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getLeftSide();
      }

      if (Store.PROPERTY_RIGHTSIDE.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getRightSide();
      }

      if (Store.PROPERTY_OPPOSITE.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getOpposite();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (House.PROPERTY_STONES.equalsIgnoreCase(attrName))
      {
         ((House) target).setStones(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Store.PROPERTY_BOARD.equalsIgnoreCase(attrName))
      {
         ((Store) target).setBoard((Board) value);
         return true;
      }

      if (Store.PROPERTY_LEFTSIDE.equalsIgnoreCase(attrName))
      {
         ((Store) target).setLeftSide((House) value);
         return true;
      }

      if (Store.PROPERTY_RIGHTSIDE.equalsIgnoreCase(attrName))
      {
         ((Store) target).setRightSide((House) value);
         return true;
      }

      if (Store.PROPERTY_OPPOSITE.equalsIgnoreCase(attrName))
      {
         ((Store) target).setOpposite((Store) value);
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
      ((Store) entity).removeYou();
   }
}
