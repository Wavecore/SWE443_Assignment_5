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
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
    public void setUpBoard(  ) {
        House house1 = createHouses().withBoard(this);
        House house2 = createHouses().withBoard(this);


        ArrayList<Store> storeSide1 = new ArrayList<Store>();
        Store previous = null;
        for (int i = 0; i < 6; i++) {
            Store store1 = createStores().withBoard(this);
            store1.withStones(3);
            if(previous == null){
                store1.setLeftSide(house2);
                previous = store1;
            }
            else{
                store1.setLeftSide(previous);
                previous = store1;
            }
            storeSide1.add(store1);
        }
        previous.setRightSide(house1);
        previous = null;
        for (int i = 0; i < 6; i++) {
            //Store store2 = storeSide1.get(storeSide1.size()-1-i).createOpposite().withBoard(this).withOpposite(storeSide1.get(i));
            //store2.setOpposite(storeSide1.get(storeSide1.size()-1-i));

            Store store2 = createStores().withBoard(this);
            store2.withStones(3);
            storeSide1.get(storeSide1.size()-1-i).setOpposite(store2);
            if(previous == null){
                store2.setLeftSide(house1);
                previous = store2;
            }
            else{
                store2.setLeftSide(previous);
                previous = store2;
            }
        }
        previous.setRightSide(house2);
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

    // Below is for testing purposes
    public void checkOpposites()
    {
        for(int i = 0; i < getStores().size(); i++)
        {
            System.out.println(getStores().get(i) + " --> " + getStores().get(i).getOpposite());
        }
    }

    public void printPlayerList()
    {
        System.out.println(getPlayer());
    }

    public void printBoard()
    {
        StoreSet s = getStores();
        HouseSet h = getHouses();
        for(int x = s.size()-1; x >= s.size()/2;x--)
            System.out.print(" "+ s.get(x));
        System.out.println();
        System.out.println(h.get(1)+"           "+h.get(0));
        for(int x = 0; x < s.size()/2;x++)
            System.out.print(" "+ s.get(x));
        System.out.println();
        System.out.println();
    }



    public boolean makeMove(Player player, int i) {

        if(isGameOver())
            return false;

        if(!player.isMyTurn())
            return false;

        System.out.println("selects position " + (i + 1));
        if(i < 0 || i > 11)
            return false;
        if(getStores().get(i).getStones() < 1)
            return false;

        int stoneCount = getStores().get(i).getStones();
        getStores().get(i).setStones(0);

        int j = i + 1;
        int current = 0;

//        System.out.println("i: " + i);
//        System.out.println("Stone count: " + stoneCount);


        if ((current == 5 && getPlayerTurn().toString().equals("Player1")) || (current == 11 && getPlayerTurn().toString().equals("Player2")))
        {
            current = i % (getStores().size());
            System.out.println("Next available spot is a House");
            if (stoneCount > 0) {
                getStores().get(current).getRightSide().setStones(getStores().get(current).getRightSide().getStones() + 1);
                stoneCount--;
                if (stoneCount < 1) {
                    System.out.println("lastEvent position: Home");
                    getStores().get(current).getRightSide().lastSownEvent();
                    return true;
                }
            }
        }


            while (stoneCount > 0) {
                current = j % (getStores().size());

                getStores().get(current).setStones(getStores().get(current).getStones() + 1);
                stoneCount--;
                if ((current == 5 && getPlayerTurn().toString().equals("Player1")) || (current == 11 && getPlayerTurn().toString().equals("Player2"))){
                    if (stoneCount > 0) {
                        getStores().get(current).getRightSide().setStones(getStores().get(current).getRightSide().getStones() + 1);
                        stoneCount--;
                        if (stoneCount < 1) {
                            System.out.println("lastEvent position: Home");
//                        System.out.println("Player gets to play again");
                            getStores().get(current).getRightSide().lastSownEvent();
                            return true;
                        }
                    }
                }
                j++;
            }

            System.out.println("lastEvent position:  " + (current + 1));
            getStores().get(current).lastSownEvent(current);

        return false;
    }

    private boolean isGameOver = false;

    public boolean isGameOver()
    {
        return this.isGameOver;
    }

    public boolean setIsGameOver(boolean isGameOver)
    {
        this.isGameOver = isGameOver;
        return isGameOver();
    }

    public int checkWinner()
    {

        if(getHouses().get(0).getStones() > getHouses().get(1).getStones())
        {
            return 1;
        } else if (getHouses().get(0).getStones() < getHouses().get(1).getStones())
        {
            return 2;

        }else{
            return 0;
        }
    }

    public boolean checkGameStatus()
    {
        boolean side1 = true;
        boolean side2 = true;

        for (int i = 0; i < 6; i++)
        {
//            System.out.println(i + ": " + getStores().get(i).getStones());
            if(getStores().get(i).getStones() > 0) {
                side1 = false;
                break;
            }
        }
        for (int j = 6; j < 12; j++)
        {
//            System.out.println(j + ": " + getStores().get(j).getStones());
            if(getStores().get(j).getStones() > 0) {
                side2 = false;
            }
        }

        if(side1 || side2)
        {
            System.out.println("\nGame Over!");

            int winner = checkWinner();

            if(!(winner == 0))
            {
                System.out.println("Player" + winner + " Wins!\n");
            }
            else
            {
                System.out.println("Tie Game!\n");
            }
            System.out.println("Resulting board:");
            printBoard();
            return setIsGameOver(true);
        }

        return isGameOver();
    }

    public Player getPlayerTurn() {
        if(getPlayer().get(0).isMyTurn())
            return getPlayer().get(0);
        return getPlayer().get(1);
    }

    public boolean setPlayerTurn(Player player)
    {
        if(getPlayer().get(0).equals(player)) {
            getPlayer().get(0).setMyTurn(true);
            getPlayer().get(1).setMyTurn(false);
            return true;
        }
        else if(getPlayer().get(1).equals(player)) {
            getPlayer().get(0).setMyTurn(false);
            getPlayer().get(1).setMyTurn(true);
            return true;
        }
        return false;
    }

    public void setUpCustomBoard(int[] stores, int[] homes) {

        House house1 = createHouses().withBoard(this).withStones(homes[0]);
        House house2 = createHouses().withBoard(this).withStones(homes[1]);

        ArrayList<Store> storeSide1 = new ArrayList<Store>();
        Store previous = null;
        for (int i = 0; i < 6; i++) {
            Store store1 = createStores().withBoard(this);
            store1.withStones(stores[i]);
            if(previous == null){
                store1.setLeftSide(house2);
                previous = store1;
            }
            else{
                store1.setLeftSide(previous);
                previous = store1;
            }
            storeSide1.add(store1);
        }
        previous.setRightSide(house1);
        previous = null;
        for (int i = 0; i < 6; i++) {
            //Store store2 = storeSide1.get(storeSide1.size()-1-i).createOpposite().withBoard(this).withOpposite(storeSide1.get(i));
            //store2.setOpposite(storeSide1.get(storeSide1.size()-1-i));

            Store store2 = createStores().withBoard(this);
            store2.withStones(stores[i+6]);
            storeSide1.get(storeSide1.size()-1-i).setOpposite(store2);
            if(previous == null){
                store2.setLeftSide(house1);
                previous = store2;
            }
            else{
                store2.setLeftSide(previous);
                previous = store2;
            }
        }
        previous.setRightSide(house2);
    }
}
