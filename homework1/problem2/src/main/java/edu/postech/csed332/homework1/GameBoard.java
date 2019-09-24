package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A game board contains a set of units and a goal position. A game consists
 * of a number of rounds. For each round, all units perform their actions:
 * a monster can escape or move, and a tower can attack nearby monsters.
 * The following invariant conditions must be maintained throught the game:
 * <p>
 * (a) The position of each unit is inside the boundaries.
 * (b) Different ground units cannot be on the same tile.
 * (c) Different air units cannot be on the same tile.
 * <p>
 * TODO: implements all the unimplemented methods (marked with TODO)
 * NOTE: do not modify the signature of public methods (which will be
 * used for GUI and grading). But you can feel free to add new fields
 * or new private methods if needed.
 */
public class GameBoard {
    private final Position goal;
    private final int width, height;

    private HashSet<Tower> towers;
    private HashSet<Monster> monsters;
    private HashSet<Unit> units;
    private Iterator<Tower> towerIterator;
    private Iterator<Monster> monsterIterator;
    private Iterator<Unit> unitIterator;
    private int NumMobsKilled;
    private int NumMobsEscaped;

    /**
     * Creates a game board with a given width and height. The goal position
     * is set to the middle of the end column.
     *
     * @param width  of this board
     * @param height of this board
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        goal = new Position(width - 1, height / 2);


        this.towers = new HashSet<>();
        this.monsters = new HashSet<>();
        this.units = new HashSet<>();
        this.NumMobsEscaped=0;
        this.NumMobsKilled=0;
    }

    /**
     * Places a unit at a given position into this board.
     *
     * @param obj a unit to be placed
     * @param p   the position of obj
     * @throws IllegalArgumentException if p is outside the bounds of the board.
     */
    public void placeUnit(Unit obj, Position p) {
        if ((p.getX() > this.width) || (p.getY() > this.height) || (p.getX()<0) || (p.getY()<0)){ /*checking if p is on the board */
            throw new IllegalArgumentException("position not on game board");
        }
        else if (obj.isGround()){                               /*checking if no unit is in p*/
            unitIterator = units.iterator();
            while (unitIterator.hasNext()){
                if ((unitIterator.next().isGround()) && (unitIterator.next().getPosition().equals(p))){
                    throw new IllegalArgumentException("Ground unit already on this board space");
                }
            }
        }
        else if (!(obj.isGround())){
            unitIterator = units.iterator();
            while (unitIterator.hasNext()){
                if (!(unitIterator.next().isGround()) && (unitIterator.next().getPosition().equals(p))){
                    throw new IllegalArgumentException("Air unit already on this board space");
                }
            }
        }
        System.out.println("got to the object adding part");
        if (obj instanceof GroundTower){
            GroundTower newUnit = new GroundTower(this);
            towers.add((GroundTower)obj);
        }
        else if (obj instanceof AirTower){
            ((AirTower) obj).setPos(p);
            towers.add((AirTower)obj);
        }
        else if (obj instanceof AirMob){
            ((AirMob) obj).setPos(p);
            monsters.add((AirMob)obj);
        }
        else if (obj instanceof GroundMob){
            System.out.println("got to instanceof");
            ((GroundMob) obj).setPos(p);
            System.out.println("got to the first casting");
            Monster newUnit = (GroundMob)obj;
            System.out.println("created casted temp variable");
            monsters.add((Monster)obj);
            System.out.println("got to the second casting");
        }
        units.add(obj);

    }

    /**
     * Clears this game board. That is, all units are removed, and all numbers
     * for game statistics are reset to 0.
     */
    public void clear() {
        units.clear();
        monsters.clear();
        towers.clear();
        NumMobsKilled=0;
        NumMobsEscaped=0;
    }

    /**
     * Returns the set of units at a given position in the board. Note that
     * multiple units can exist at the same position (e.g., ground and air)
     *
     * @param p a position
     * @return the set of units at position p
     */
    public Set<Unit> getUnitsAt(Position p) {
        Set<Unit> unitsHere = new HashSet<>();
        unitIterator=units.iterator();
        while (unitIterator.hasNext()){
            if (unitIterator.next().getPosition().equals(p)) unitsHere.add(unitIterator.next());
        }
        return unitsHere;
    }

    /**
     * Returns the set of all monsters in this board.
     *
     * @return the set of all monsters
     */
    public Set<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Returns the set of all towers in this board.
     *
     * @return the set of all towers
     */
    public Set<Tower> getTowers() {
        return towers;
    }

    /**
     * Returns the position of a given unit
     *
     * @param obj a unit
     * @return the position of obj
     */
    public Position getPosition(Unit obj) {
        return obj.getPosition();
    }

    /**
     * Proceeds one round of a game. The behavior of this method is as follows:
     * (1) Every monster at the goal position escapes from the game.
     * (2) Each tower attacks nearby remaining monsters (using the attack method).
     * (3) All remaining monsters (neither escaped nor attacked) moves (using the goal method).
     */
    public void step() {
        monsterIterator=monsters.iterator();
        while (monsterIterator.hasNext()){
            if(monsterIterator.next().getPosition().equals(this.goal)){
                monsters.remove(monsterIterator.next());
                NumMobsEscaped+=1;
            }
        }
        towerIterator=towers.iterator();
        while (towerIterator.hasNext()){
            HashSet<Monster> killedMobs = (HashSet)towerIterator.next().attack();
            monsterIterator=killedMobs.iterator();
            while (monsterIterator.hasNext()) {
                monsters.remove(monsterIterator.next());
                NumMobsKilled += 1;
            }
        }
        monsterIterator=monsters.iterator();
        while (monsterIterator.hasNext()){
            if (monsterIterator.next() instanceof AirMob){
                Position nextMove = monsterIterator.next().move();
                ((AirMob) monsterIterator.next()).setPos(nextMove);
            }
            else if (monsterIterator.next() instanceof GroundMob){
                Position nextMove = monsterIterator.next().move();
                ((GroundMob) monsterIterator.next()).setPos(nextMove);
            }
        }
    }

    /**
     * Checks whether the following invariants hold in the current game board:
     * (a) All units are in the boundaries.
     * (b) Different ground units cannot be on the same tile.
     * (c) Different air units cannot be on the same tile.
     *
     * @return true if there is no problem. false otherwise.
     */
    public boolean isValid() {
        boolean isValid = true;
        unitIterator=units.iterator();
        while(unitIterator.hasNext()){
            if (unitIterator.next().getPosition().getX() > this.width){
                isValid = false;
                break;
            }
            else if (unitIterator.next().getPosition().getY() > this.height){
                isValid = false;
                break;
            }
            else{
                HashSet<Unit> localUnits = (HashSet)this.getUnitsAt(unitIterator.next().getPosition()); /*check every unit at the current position*/
                if (localUnits.size()>1){
                    localUnits.remove(unitIterator.next());
                    Iterator<Unit> localIterator = localUnits.iterator();
                    while (localIterator.hasNext()) {
                        if ((localIterator.next().isGround() && (unitIterator.next().isGround())) /*2 air units or ground units on the same tile*/
                                || !(localIterator.next().isGround()) && !(unitIterator.next().isGround())) {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
        }
        return isValid;
    }

    /**
     * Returns the number of the monsters in this board.
     *
     * @return the number of the monsters
     */
    public int getNumMobs() {
        return monsters.size();
    }

    /**
     * Returns the number of the towers in this board.
     *
     * @return the number of the towers
     */
    public int getNumTowers() {
return towers.size();
    }

    /**
     * Returns the number of the monsters removed so far in this game.
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters removed
     */
    public int getNumMobsKilled() {
        return NumMobsKilled;
    }

    /**
     * Returns the number of the monsters escaped so far in this game
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters escaped
     */
    public int getNumMobsEscaped() {
        return NumMobsEscaped;
    }

    /**
     * Returns the width of this board.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this board.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the goal position where the monster can escape.
     *
     * @return the goal position of this game
     */
    public Position getGoalPosition() {
        return goal;
    }
    
}
