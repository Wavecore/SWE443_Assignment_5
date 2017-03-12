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

import de.uniks.networkparser.list.SimpleSet;
import swe443.assignment5.mancala.Game;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import swe443.assignment5.mancala.util.BoardSet;
import swe443.assignment5.mancala.Board;
import java.util.Collections;
import swe443.assignment5.mancala.util.PlayerSet;
import swe443.assignment5.mancala.Player;

public class GameSet extends SimpleSet<Game>
{
	protected Class<?> getTypClass() {
		return Game.class;
	}

   public GameSet()
   {
      // empty
   }

   public GameSet(Game... objects)
   {
      for (Game obj : objects)
      {
         this.add(obj);
      }
   }

   public GameSet(Collection<Game> objects)
   {
      this.addAll(objects);
   }

   public static final GameSet EMPTY_SET = new GameSet().withFlag(GameSet.READONLY);


   public GamePO createGamePO()
   {
      return new GamePO(this.toArray(new Game[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.Game";
   }


   @Override
   public GameSet getNewList(boolean keyValue)
   {
      return new GameSet();
   }


   public GameSet filter(Condition<Game> condition) {
      GameSet filterList = new GameSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public GameSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Game>)value);
      }
      else if (value != null)
      {
         this.add((Game) value);
      }
      
      return this;
   }
   
   public GameSet without(Game value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Game objects and collect a list of the gameDone attribute values. 
    * 
    * @return List of boolean objects reachable via gameDone attribute
    */
   public BooleanList getGameDone()
   {
      BooleanList result = new BooleanList();
      
      for (Game obj : this)
      {
         result.add(obj.isGameDone());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Game objects and collect those Game objects where the gameDone attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Game objects that match the parameter
    */
   public GameSet filterGameDone(boolean value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.isGameDone())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Game objects and assign value to the gameDone attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Game objects now with new attribute values.
    */
   public GameSet withGameDone(boolean value)
   {
      for (Game obj : this)
      {
         obj.setGameDone(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Board objects reached via board. 
    * 
    * @return Set of Board objects reachable via board
    */
   public BoardSet getBoard()
   {
      BoardSet result = new BoardSet();
      
      for (Game obj : this)
      {
         result.with(obj.getBoard());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference board pointing to the object passed as parameter. 
    * 
    * @param value The object required as board neighbor of the collected results. 
    * 
    * @return Set of Board objects referring to value via board
    */
   public GameSet filterBoard(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getBoard()) || (neighbors.isEmpty() && obj.getBoard() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Board attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Board attributes.
    */
   public GameSet withBoard(Board value)
   {
      for (Game obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Player objects reached via player. 
    * 
    * @return Set of Player objects reachable via player
    */
   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Game obj : this)
      {
         result.with(obj.getPlayer());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference player pointing to the object passed as parameter. 
    * 
    * @param value The object required as player neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via player
    */
   public GameSet filterPlayer(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Player attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Player attributes.
    */
   public GameSet withPlayer(Player value)
   {
      for (Game obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Game object passed as parameter from the Player attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GameSet withoutPlayer(Player value)
   {
      for (Game obj : this)
      {
         obj.withoutPlayer(value);
      }
      
      return this;
   }

}
