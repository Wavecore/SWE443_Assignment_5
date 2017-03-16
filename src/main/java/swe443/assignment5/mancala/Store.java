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

import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.StoreSet;
   /**
    * 
    * @see <a href='../../../../../../src/main/java/Model.java'>Model.java</a>
 */
   public  class Store extends House
{

   
   //==========================================================================
   public int takeOppositePebbles(int current)
   {
       Store opp = getBoard().getStores().get(current).getOpposite();
       System.out.println("Opposite: " + opp);

       int opposite = opp.getStones();
       getBoard().getStores().get(current).getOpposite().setStones(0);
       System.out.println("Opposite side stolen!");
       return opposite;
   }

   
   //==========================================================================
   public void lastSownEvent(int current)
   {
       System.out.println("Stone count = " + getStones());

       if(getStones() == 1) {
           int opposite = takeOppositePebbles(current);
           int currentStore = getBoard().getStores().get(current).getStones();

           System.out.println("Current Store Count: " + currentStore + ", Opposite Store Count: " + opposite);

           getBoard().getStores().get(current).setStones(0);
           System.out.println(getBoard().getStores().get(current).getRightSide());
//           getBoard().getStores().get(current).getRightSide().setStones(getBoard().getStores().get(current).getRightSide().getStones() + currentStore + opposite);
       }

       if(getBoard().getPlayer().get(0).isMyTurn()) {
           System.out.println("Next turn: " + getBoard().getPlayer().get(1));
           getBoard().getPlayer().get(0).setMyTurn(false);
           getBoard().getPlayer().get(1).setMyTurn(true);
       }
       else
       {
           System.out.println("Next turn: " + getBoard().getPlayer().get(0));
           getBoard().getPlayer().get(0).setMyTurn(true);
           getBoard().getPlayer().get(1).setMyTurn(false);
       }
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setBoard(null);
      setLeftSide(null);
      setRightSide(null);
      setOpposite(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getStones());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Store ----------------------------------- Board
    *              stores                   board
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
            oldValue.withoutStores(this);
         }
         
         this.board = value;
         
         if (value != null)
         {
            value.withStores(this);
         }

         firePropertyChange(PROPERTY_BOARD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Store withBoard(Board value)
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
    *              one                       one
    * Store ----------------------------------- Store
    *              opposite                   opposite
    * </pre>
    */
   
   public static final String PROPERTY_OPPOSITE = "opposite";

   private Store opposite = null;

   public Store getOpposite()
   {
      return this.opposite;
   }
   public StoreSet getOppositeTransitive()
   {
      StoreSet result = new StoreSet().with(this);
      return result.getOppositeTransitive();
   }


   public boolean setOpposite(Store value)
   {
      boolean changed = false;
      
      if (this.opposite != value)
      {
         Store oldValue = this.opposite;
         
         if (this.opposite != null)
         {
            this.opposite = null;
            oldValue.setOpposite(null);
         }
         
         this.opposite = value;
         
         if (value != null)
         {
            value.withOpposite(this);
         }
         
         firePropertyChange(PROPERTY_OPPOSITE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Store withOpposite(Store value)
   {
      setOpposite(value);
      return this;
   } 

   public Store createOpposite()
   {
      Store value = new Store();
      withOpposite(value);
      return value;
   } 
}
