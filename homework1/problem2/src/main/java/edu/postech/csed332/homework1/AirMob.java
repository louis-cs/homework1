package edu.postech.csed332.homework1;

/**
 * An air monster that moves towards to the goal position of the board, while
 * satisfying the game board invariants. The class AirMob implements the interface
 * Monster. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 *
 * @see GameBoard#isValid
 */
public class AirMob implements Monster {

    private Position pos;
    private GameBoard board;

    public AirMob(GameBoard board) {
        this.board=board;
    }

    public void setPos(Position pos){
        this.pos=pos;
    }

    @Override
    public Position move() {
        // TODO: implement this
        return null;
    }

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public GameBoard getBoard() {
        // TODO: implement this
        return null;
    }
}
