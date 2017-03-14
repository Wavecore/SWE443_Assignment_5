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
import swe443.assignment5.mancala.Board;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import swe443.assignment5.mancala.util.HouseSet;
import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.util.StoreSet;
import swe443.assignment5.mancala.Store;
import swe443.assignment5.mancala.util.GameSet;
import swe443.assignment5.mancala.Game;

public class BoardSet extends SimpleSet<Board>
{
	protected Class<?> getTypClass() {
		return Board.class;
	}

   public BoardSet()
   {
      // empty
   }

   public BoardSet(Board... objects)
   {
      for (Board obj : objects)
      {
         this.add(obj);
      }
   }

   public BoardSet(Collection<Board> objects)
   {
      this.addAll(objects);
   }

   public static final BoardSet EMPTY_SET = new BoardSet().withFlag(BoardSet.READONLY);


   public BoardPO createBoardPO()
   {
      return new BoardPO(this.toArray(new Board[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.Board";
   }


   @Override
   public BoardSet getNewList(boolean keyValue)
   {
      return new BoardSet();
   }


   public BoardSet filter(Condition<Board> condition) {
      BoardSet filterList = new BoardSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BoardSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Board>)value);
      }
      else if (value != null)
      {
         this.add((Board) value);
      }
      
      return this;
   }
   
   public BoardSet without(Board value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public BoardSet setUpBoard()
   {
      return BoardSet.EMPTY_SET;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the House objects reached via houses. 
    * 
    * @return Set of House objects reachable via houses
    */
   public HouseSet getHouses()
   {
      HouseSet result = new HouseSet();
      
      for (Board obj : this)
      {
         result.with(obj.getHouses());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference houses pointing to the object passed as parameter. 
    * 
    * @param value The object required as houses neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via houses
    */
   public BoardSet filterHouses(Object value)
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
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHouses()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Houses attributes.
    */
   public BoardSet withHouses(House value)
   {
      for (Board obj : this)
      {
         obj.withHouses(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutHouses(House value)
   {
      for (Board obj : this)
      {
         obj.withoutHouses(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Store objects reached via stores. 
    * 
    * @return Set of Store objects reachable via stores
    */
   public StoreSet getStores()
   {
      StoreSet result = new StoreSet();
      
      for (Board obj : this)
      {
         result.with(obj.getStores());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference stores pointing to the object passed as parameter. 
    * 
    * @param value The object required as stores neighbor of the collected results. 
    * 
    * @return Set of Store objects referring to value via stores
    */
   public BoardSet filterStores(Object value)
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
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStores()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Stores attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Stores attributes.
    */
   public BoardSet withStores(Store value)
   {
      for (Board obj : this)
      {
         obj.withStores(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Stores attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutStores(Store value)
   {
      for (Board obj : this)
      {
         obj.withoutStores(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Board obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public BoardSet filterGame(Object value)
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
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public BoardSet withGame(Game value)
   {
      for (Board obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

}
