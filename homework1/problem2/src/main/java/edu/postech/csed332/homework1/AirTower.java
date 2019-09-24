package edu.postech.csed332.homework1;

import java.util.*;

/**
 * An air tower that can attack nearby air monsters within 1 tile of distance.
 * For example, an air tower at (x,y) can attack any air monsters at (x-1, y),
 * (x+1, y), (x, y-1), and (x, y+1). The class AirTower implements the interface
 * Tower. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 */

public class AirTower implements Tower {

    private Position pos;
    private GameBoard board;

    public AirTower(GameBoard board) {
        this.board=board;
    }

    public void setPos(Position pos){
        this.pos=pos;
    }

    @Override
    public Set<Monster> attack() {
        Set<Monster> killedMonsters = new TreeSet<>();
        TreeSet<Unit> up = (TreeSet) getBoard().getUnitsAt(pos.getRelativePosition(0,1)); /*get all units adjacent to the tower*/
        TreeSet<Unit> down = (TreeSet) getBoard().getUnitsAt(pos.getRelativePosition(0,-1));
        TreeSet<Unit> left = (TreeSet) getBoard().getUnitsAt(pos.getRelativePosition(-1,0));
        TreeSet<Unit> right = (TreeSet) getBoard().getUnitsAt(pos.getRelativePosition(1,0));
        for (int i=0; i<2; i++){                /*add all adjacent ground monsters to the list of killed monsters*/
            if (up.first() instanceof AirMob) {
                killedMonsters.add((Monster)up.pollFirst());
            }
            if (down.first() instanceof AirMob) {
                killedMonsters.add((Monster)down.pollFirst());
            }
            if (left.first() instanceof AirMob) {
                killedMonsters.add((Monster)left.pollFirst());
            }
            if (right.first() instanceof AirMob) {
                killedMonsters.add((Monster)right.pollFirst());
            }
        }
        return killedMonsters;
    }

    @Override
    public GameBoard getBoard() {
        return this.board;
    }
}
