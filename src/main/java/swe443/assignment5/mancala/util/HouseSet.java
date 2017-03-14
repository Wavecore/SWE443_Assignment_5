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

import de.uniks.networkparser.list.SimpleSet;
import swe443.assignment5.mancala.House;
import de.uniks.networkparser.interfaces.Condition;
import swe443.assignment5.mancala.Store;
import swe443.assignment5.mancala.util.StoreSet;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import swe443.assignment5.mancala.util.BoardSet;
import swe443.assignment5.mancala.Board;

public class HouseSet extends SimpleSet<House>
{
	protected Class<?> getTypClass() {
		return House.class;
	}

   public HouseSet()
   {
      // empty
   }

   public HouseSet(House... objects)
   {
      for (House obj : objects)
      {
         this.add(obj);
      }
   }

   public HouseSet(Collection<House> objects)
   {
      this.addAll(objects);
   }

   public static final HouseSet EMPTY_SET = new HouseSet().withFlag(HouseSet.READONLY);


   public HousePO createHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.House";
   }


   @Override
   public HouseSet getNewList(boolean keyValue)
   {
      return new HouseSet();
   }


   public HouseSet filter(Condition<House> condition) {
      HouseSet filterList = new HouseSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public StoreSet instanceOfStore()
   {
      StoreSet result = new StoreSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Store)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   @SuppressWarnings("unchecked")
   public HouseSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<House>)value);
      }
      else if (value != null)
      {
         this.add((House) value);
      }
      
      return this;
   }
   
   public HouseSet without(House value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public HouseSet sow()
   {
      return HouseSet.EMPTY_SET;
   }


   /**
    * Loop through the current set of House objects and collect a list of the stones attribute values. 
    * 
    * @return List of int objects reachable via stones attribute
    */
   public NumberList getStones()
   {
      NumberList result = new NumberList();
      
      for (House obj : this)
      {
         result.add(obj.getStones());
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and collect those House objects where the stones attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of House objects that match the parameter
    */
   public HouseSet filterStones(int value)
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         if (value == obj.getStones())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and collect those House objects where the stones attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of House objects that match the parameter
    */
   public HouseSet filterStones(int lower, int upper)
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         if (lower <= obj.getStones() && obj.getStones() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and assign value to the stones attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of House objects now with new attribute values.
    */
   public HouseSet withStones(int value)
   {
      for (House obj : this)
      {
         obj.setStones(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the Board objects reached via board. 
    * 
    * @return Set of Board objects reachable via board
    */
   public BoardSet getBoard()
   {
      BoardSet result = new BoardSet();
      
      for (House obj : this)
      {
         result.with(obj.getBoard());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference board pointing to the object passed as parameter. 
    * 
    * @param value The object required as board neighbor of the collected results. 
    * 
    * @return Set of Board objects referring to value via board
    */
   public HouseSet filterBoard(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getBoard()) || (neighbors.isEmpty() && obj.getBoard() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the Board attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Board attributes.
    */
   public HouseSet withBoard(Board value)
   {
      for (House obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

}
