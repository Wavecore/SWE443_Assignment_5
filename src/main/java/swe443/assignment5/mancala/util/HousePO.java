package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.House;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

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
   
}
