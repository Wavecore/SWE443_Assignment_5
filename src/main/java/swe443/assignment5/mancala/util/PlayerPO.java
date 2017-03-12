package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.assignment5.mancala.util.GamePO;
import swe443.assignment5.mancala.Game;
import swe443.assignment5.mancala.util.PlayerPO;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{

    public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PlayerPO(){
      newInstance(null);
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public boolean ismyTurn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).ismyTurn();
      }
      return false;
   }

   public PlayerPO createMyTurnCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_MYTURN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createMyTurnAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_MYTURN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getMyTurn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).isMyTurn();
      }
      return false;
   }
   
   public PlayerPO withMyTurn(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setMyTurn(value);
      }
      return this;
   }
   
   public PlayerPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
