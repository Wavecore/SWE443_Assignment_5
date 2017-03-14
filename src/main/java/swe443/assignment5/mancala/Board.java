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
import swe443.assignment5.mancala.Store;
import swe443.assignment5.mancala.util.HouseSet;
import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.util.StoreSet;
import swe443.assignment5.mancala.util.PlayerSet;
import swe443.assignment5.mancala.Player;
   /**
    * 
    * @see <a href='../../../../../../src/main/java/Model.java'>Model.java</a>
 */
   public  class Board implements SendableEntity
{

   
   //==========================================================================
   public void setUpBoard(  )
   {
      
   }

   
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
      withoutHouses(this.getHouses().toArray(new House[this.getHouses().size()]));
      withoutStores(this.getStores().toArray(new Store[this.getStores().size()]));
      withoutPlayer(this.getPlayer().toArray(new Player[this.getPlayer().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Board ----------------------------------- House
    *              board                   houses
    * </pre>
    */
   
   public static final String PROPERTY_HOUSES = "houses";

   private HouseSet houses = null;
   
   public HouseSet getHouses()
   {
      if (this.houses == null)
      {
         return HouseSet.EMPTY_SET;
      }
   
      return this.houses;
   }

   public Board withHouses(House... value)
   {
      if(value==null){
         return this;
      }
      for (House item : value)
      {
         if (item != null)
         {
            if (this.houses == null)
            {
               this.houses = new HouseSet();
            }
            
            boolean changed = this.houses.add (item);

            if (changed)
            {
               item.withBoard(this);
               firePropertyChange(PROPERTY_HOUSES, null, item);
            }
         }
      }
      return this;
   } 

   public Board withoutHouses(House... value)
   {
      for (House item : value)
      {
         if ((this.houses != null) && (item != null))
         {
            if (this.houses.remove(item))
            {
               item.setBoard(null);
               firePropertyChange(PROPERTY_HOUSES, item, null);
            }
         }
      }
      return this;
   }

   public House createHouses()
   {
      House value = new House();
      withHouses(value);
      return value;
   } 

   public Store createHousesStore()
   {
      Store value = new Store();
      withHouses(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Board ----------------------------------- Store
    *              board                   stores
    * </pre>
    */
   
   public static final String PROPERTY_STORES = "stores";

   private StoreSet stores = null;
   
   public StoreSet getStores()
   {
      if (this.stores == null)
      {
         return StoreSet.EMPTY_SET;
      }
   
      return this.stores;
   }

   public Board withStores(Store... value)
   {
      if(value==null){
         return this;
      }
      for (Store item : value)
      {
         if (item != null)
         {
            if (this.stores == null)
            {
               this.stores = new StoreSet();
            }
            
            boolean changed = this.stores.add (item);

            if (changed)
            {
               item.withBoard(this);
               firePropertyChange(PROPERTY_STORES, null, item);
            }
         }
      }
      return this;
   } 

   public Board withoutStores(Store... value)
   {
      for (Store item : value)
      {
         if ((this.stores != null) && (item != null))
         {
            if (this.stores.remove(item))
            {
               item.setBoard(null);
               firePropertyChange(PROPERTY_STORES, item, null);
            }
         }
      }
      return this;
   }

   public Store createStores()
   {
      Store value = new Store();
      withStores(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Board ----------------------------------- Player
    *              board                   player
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

   public Board withPlayer(Player... value)
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
               item.withBoard(this);
               firePropertyChange(PROPERTY_PLAYER, null, item);
            }
         }
      }
      return this;
   } 

   public Board withoutPlayer(Player... value)
   {
      for (Player item : value)
      {
         if ((this.player != null) && (item != null))
         {
            if (this.player.remove(item))
            {
               item.setBoard(null);
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
