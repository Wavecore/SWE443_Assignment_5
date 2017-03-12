package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.HousePO;
import swe443.assignment5.mancala.House;
import swe443.assignment5.mancala.util.BoardPO;
import swe443.assignment5.mancala.util.HouseSet;
import swe443.assignment5.mancala.util.StorePO;
import swe443.assignment5.mancala.Store;
import swe443.assignment5.mancala.util.StoreSet;
import swe443.assignment5.mancala.util.GamePO;
import swe443.assignment5.mancala.Game;

public class BoardPO extends PatternObject<BoardPO, Board>
{

    public BoardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BoardSet matches = new BoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Board) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BoardPO(){
      newInstance(null);
   }

   public BoardPO(Board... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BoardPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void setUpBoard()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Board) getCurrentMatch()).setUpBoard();
      }
   }

   public HousePO createHousesPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Board.PROPERTY_HOUSES, result);
      
      return result;
   }

   public HousePO createHousesPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(Board.PROPERTY_HOUSES, result);
      
      return result;
   }

   public BoardPO createHousesLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_HOUSES);
   }

   public BoardPO createHousesLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_HOUSES, modifier);
   }

   public HouseSet getHouses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) this.getCurrentMatch()).getHouses();
      }
      return null;
   }

   public StorePO createStoresPO()
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Board.PROPERTY_STORES, result);
      
      return result;
   }

   public StorePO createStoresPO(String modifier)
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(modifier);
      super.hasLink(Board.PROPERTY_STORES, result);
      
      return result;
   }

   public BoardPO createStoresLink(StorePO tgt)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_STORES);
   }

   public BoardPO createStoresLink(StorePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_STORES, modifier);
   }

   public StoreSet getStores()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) this.getCurrentMatch()).getStores();
      }
      return null;
   }

   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Board.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Board.PROPERTY_GAME, result);
      
      return result;
   }

   public BoardPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_GAME);
   }

   public BoardPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
