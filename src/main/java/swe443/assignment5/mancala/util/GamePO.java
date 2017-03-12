package swe443.assignment5.mancala.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.assignment5.mancala.Game;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.assignment5.mancala.util.BoardPO;
import swe443.assignment5.mancala.Board;
import swe443.assignment5.mancala.util.GamePO;
import swe443.assignment5.mancala.util.PlayerPO;
import swe443.assignment5.mancala.Player;
import swe443.assignment5.mancala.util.PlayerSet;

public class GamePO extends PatternObject<GamePO, Game>
{

    public GameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GameSet matches = new GameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Game) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GamePO(){
      newInstance(null);
   }

   public GamePO(Game... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GamePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GamePO createGameDoneCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_GAMEDONE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GamePO createGameDoneAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_GAMEDONE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getGameDone()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).isGameDone();
      }
      return false;
   }
   
   public GamePO withGameDone(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setGameDone(value);
      }
      return this;
   }
   
   public BoardPO createBoardPO()
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_BOARD, result);
      
      return result;
   }

   public GamePO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_BOARD);
   }

   public GamePO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_BOARD, modifier);
   }

   public Board getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

   public PlayerPO createPlayerPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayerPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_PLAYER, result);
      
      return result;
   }

   public GamePO createPlayerLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_PLAYER);
   }

   public GamePO createPlayerLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_PLAYER, modifier);
   }

   public PlayerSet getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

}
