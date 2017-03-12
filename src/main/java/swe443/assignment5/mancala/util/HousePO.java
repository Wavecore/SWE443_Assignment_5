package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.House;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.assignment5.mancala.util.BoardPO;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.HousePO;

public class HousePO extends PatternObject<HousePO, House>
{

    public HouseSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HouseSet matches = new HouseSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((House) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HousePO(){
      newInstance(null);
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public HousePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void sow()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).sow();
      }
   }

   public HousePO createStonesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createStonesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createStonesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
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
         return ((House) getCurrentMatch()).getStones();
      }
      return 0;
   }
   
   public HousePO withStones(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((House) getCurrentMatch()).setStones(value);
      }
      return this;
   }
   
   public BoardPO createBoardPO()
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_BOARD, result);
      
      return result;
   }

   public HousePO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_BOARD);
   }

   public HousePO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_BOARD, modifier);
   }

   public Board getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

}
