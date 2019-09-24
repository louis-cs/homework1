package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A ground tower that can attack nearby ground monsters within 1 tile of distance.
 * For example, a ground tower at (x,y) can attack any ground monsters at (x-1, y),
 * (x+1, y), (x, y-1), and (x, y+1). The class GroundTower implements the interface
 * Tower. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 */
public class GroundTower implements Tower {

    private Position pos;
    private GameBoard board;

    public GroundTower(GameBoard board) {
        // TODO: implement this
        this.board=board;
    }

    public void setPos(Position pos){
        this.pos=pos;
    }

    @Override
    public Set<Monster> attack() {
        Set<Monster> killedMonsters = new HashSet<>();
        HashSet<Unit> up = (HashSet) getBoard().getUnitsAt(pos.getRelativePosition(0,1)); /*get all units adjacent to the tower*/
        HashSet<Unit> down = (HashSet) getBoard().getUnitsAt(pos.getRelativePosition(0,-1));
        HashSet<Unit> left = (HashSet) getBoard().getUnitsAt(pos.getRelativePosition(-1,0));
        HashSet<Unit> right = (HashSet) getBoard().getUnitsAt(pos.getRelativePosition(1,0));
        Iterator<Unit> localIterator;           /*add all adjacent ground monsters to the list of killed monsters, max 2 per position*/
        localIterator=up.iterator();
        Unit i;
        while (localIterator.hasNext()){
            i = localIterator.next();
            if (i instanceof GroundMob) killedMonsters.add((Monster)i);
        }
        localIterator=down.iterator();
        while (localIterator.hasNext()){
            i = localIterator.next();
            if (i instanceof GroundMob) killedMonsters.add((Monster)i);
        }
        localIterator=left.iterator();
        while (localIterator.hasNext()){
            i = localIterator.next();
            if (i instanceof GroundMob) killedMonsters.add((Monster)i);
        }
        localIterator=right.iterator();
        while (localIterator.hasNext()){
            i = localIterator.next();
            if (i instanceof GroundMob) killedMonsters.add((Monster)i);
        }
        return killedMonsters;
    }

    @Override
    public GameBoard getBoard() {
        return this.board;
    }

    @Override
    public Position getPosition(){
        return this.pos;
    }
}
