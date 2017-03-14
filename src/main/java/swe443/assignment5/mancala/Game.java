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
   
package swe443.assignment5.mancala;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.PlayerSet;
import swe443.assignment5.mancala.Player;
   /**
    * 
    * @see <a href='../../../../../../src/main/java/Model.java'>Model.java</a>
 */
   public  class Game implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setBoard(null);
      withoutPlayer(this.getPlayer().toArray(new Player[this.getPlayer().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_GAMEDONE = "gameDone";
   
   private boolean gameDone;

   public boolean isGameDone()
   {
      return this.gameDone;
   }
   
   public void setGameDone(boolean value)
   {
      if (this.gameDone != value) {
      
         boolean oldValue = this.gameDone;
         this.gameDone = value;
         this.firePropertyChange(PROPERTY_GAMEDONE, oldValue, value);
      }
   }
   
   public Game withGameDone(boolean value)
   {
      setGameDone(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Game ----------------------------------- Board
    *              game                   board
    * </pre>
    */
   
   public static final String PROPERTY_BOARD = "board";

   private Board board = null;

   public Board getBoard()
   {
      return this.board;
   }

   public boolean setBoard(Board value)
   {
      boolean changed = false;
      
      if (this.board != value)
      {
         Board oldValue = this.board;
         
         if (this.board != null)
         {
            this.board = null;
            oldValue.setGame(null);
         }
         
         this.board = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         firePropertyChange(PROPERTY_BOARD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Game withBoard(Board value)
   {
      setBoard(value);
      return this;
   } 

   public Board createBoard()
   {
      Board value = new Board();
      withBoard(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- Player
    *              game                   player
    * </pre>
    */
   
   public static final String PROPERTY_PLAYER = "player";

   private PlayerSet player = null;
   
   public PlayerSet getPlayer()
   {
      if (this.player == null)
      {
         return PlayerSet.EMPTY_SET;
      }
   
      return this.player;
   }

   public Game withPlayer(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.player == null)
            {
               this.player = new PlayerSet();
            }
            
            boolean changed = this.player.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_PLAYER, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutPlayer(Player... value)
   {
      for (Player item : value)
      {
         if ((this.player != null) && (item != null))
         {
            if (this.player.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_PLAYER, item, null);
            }
         }
      }
      return this;
   }

   public Player createPlayer()
   {
      Player value = new Player();
      withPlayer(value);
      return value;
   } 
}
