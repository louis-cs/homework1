package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A ground monster that moves towards to the goal position of the board, while
 * satisfying the game board invariants. The class GroundMob implements the interface
 * Monster. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 *
 * @see GameBoard#isValid
 */
public class GroundMob implements Monster {

    private Position pos;
    private GameBoard board;

    public GroundMob(GameBoard board) {
        this.board = board;
    }

    public void setPos(Position pos){
        this.pos=pos;
    }

    @Override
    public Position move() {
        Position next;
        if (board.getGoalPosition().getX()-this.pos.getX() > board.getGoalPosition().getY()-this.pos.getY()){ /*if there's more lateral that vertical movement to be done*/
            next = this.getPosition().getRelativePosition(1,0);
        }
        else{
            next = this.getPosition().getRelativePosition(0,1);
        }
        HashSet<Unit> nextUnits = (HashSet)board.getUnitsAt(next);
        Iterator<Unit> localIterator = nextUnits.iterator();
        while (localIterator.hasNext()){
            if (localIterator.next() instanceof GroundMob) next = this.getPosition(); /*if an airmob is where this one wants to go, it stands still*/
        }
        return next;
    }

    @Override
    public boolean isGround() {
        return true;
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
