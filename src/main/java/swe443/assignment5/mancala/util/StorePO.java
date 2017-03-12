package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.Store;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.assignment5.mancala.util.BoardPO;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.StorePO;

public class StorePO extends PatternObject<StorePO, Store>
{

    public StoreSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoreSet matches = new StoreSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Store) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StorePO(){
      newInstance(null);
   }

   public StorePO(Store... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StorePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void takeOppositePebbles()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Store) getCurrentMatch()).takeOppositePebbles();
      }
   }

   public StorePO createStonesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StorePO createStonesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StorePO createStonesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getStones()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Store) getCurrentMatch()).getStones();
      }
      return 0;
   }
   
   public StorePO withStones(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Store) getCurrentMatch()).setStones(value);
      }
      return this;
   }
   
   public BoardPO createBoardPO()
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(Store.PROPERTY_BOARD, result);
      
      return result;
   }

   public StorePO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_BOARD);
   }

   public StorePO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_BOARD, modifier);
   }

   public Board getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Store) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

}
