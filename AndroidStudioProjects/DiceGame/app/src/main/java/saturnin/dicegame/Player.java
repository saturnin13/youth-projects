package saturnin.dicegame;

/**
 * Created by saturnin on 25/9/2015.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import saturnin.dicegame.ElementInexistantException;

public class Player {

    private List<Dice> dices;
    private int STARTING_NUMBER_OF_DICE = 6;
    private boolean canBeReRolledByplayer = true;
    private boolean canbeReRolledByOtherPlayer = true;

    public Player() {
        dices = new LinkedList<Dice>();
        for(int i = 0; i < STARTING_NUMBER_OF_DICE; i ++) {
            dices.add(new Dice());
        }
    }

    public void remove(Dice dice) throws ElementInexistantException{
        Iterator<Dice> iterator = dices.iterator();
        boolean exist = true;
        while(iterator.hasNext()) {
            exist = true;
            if (iterator.next().equals(dice)) {
                iterator.remove();
                break;
            }
            exist = false;
        }
        if(!exist) {
            throw new ElementInexistantException("cannot remove this dice, does not exist");
        }
    }

    public void add(Dice dice) {
        dices.add(dice);
    }

    public void reRollAll() {
        Iterator<Dice> iterator = dices.iterator();
        while(iterator.hasNext()) {
            iterator.next().reRoll();
        }
    }

    public void reRoll(Dice dice) throws ElementInexistantException{
        Iterator<Dice> iterator = dices.iterator();
        boolean exist = false;
        Dice valueDice;
        while(iterator.hasNext()) {
            exist = true;
            valueDice = iterator.next();
            if (valueDice.equals(dice)) {
                valueDice.reRoll();
                break;
            }
            exist = false;
        }
        if(!exist) {
            throw new ElementInexistantException("cannot reroll this dice, does not exist");
        }
    }

    public boolean getCanBeReRolledByplayer() {
        return canBeReRolledByplayer;
    }

    public void setCanBeReRolledByplayer(boolean canBeReRolledByplayer) {
        this.canBeReRolledByplayer = canBeReRolledByplayer;
    }

    public boolean getCanbeReRolledByOtherPlayer() {
        return canbeReRolledByOtherPlayer;
    }

    public void setCanbeReRolledByOtherPlayer(boolean canbeReRolledByOtherPlayer) {
        this.canbeReRolledByOtherPlayer = canbeReRolledByOtherPlayer;
    }

    public List<Dice> getDices() {
        return dices;
    }

}
