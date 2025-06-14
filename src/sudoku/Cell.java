package sudoku;

import java.util.LinkedList;

public abstract class Cell implements Comparable<Cell>, Cloneable {

    protected int value;
    protected boolean userAccess;
    protected int maxValue;
    protected LinkedList<Integer> possibleValues = new LinkedList();

    public Cell(int maxV) {

        maxValue = maxV;
        value = 0;
        userAccess = true;

        for (int i = 1; i <= maxValue; i++) {
            possibleValues.add(i);
        }
    }

    public boolean setValue(int value) {

        if (value > 0 && value <= maxValue) {
            this.value = value;
            possibleValues.clear();
            return true;
        }
        return false;
    }

    public void eraseValue() {
        this.value = 0;
        for (int i = 1; i <= maxValue; i++) {
            possibleValues.add(i);
        }
    }

    public int getValue() {
        return value;
    }

    public void setAccess(boolean acs) {
        userAccess = acs;
    }

    public boolean getAccess() {
        return userAccess;
    }

    public void excludeFromPossibles(int concurent) {
        possibleValues.removeFirstOccurrence(concurent);
    }

    public LinkedList<Integer> getPossibleValues() {
        return (LinkedList<Integer>) possibleValues.clone();
    }

    public boolean addToPossibleValues(int newPossible) {
        if (newPossible > 0 && newPossible <= maxValue && value == 0) {
            if (!possibleValues.contains(newPossible)) {
                possibleValues.add(newPossible);
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Cell c) {

        if (this.possibleValues.size() == c.possibleValues.size()) {
            return 0;
        }

        if (this.possibleValues.size() == 0) {
            return 1;
        }
        if (c.possibleValues.size() == 0) {
            return -1;
        }

        if (this.possibleValues.size() > c.possibleValues.size()) {
            return 1;
        }

        return -1;
    }
}
