package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class SudokuGrid implements Cloneable {

    private Map<Integer, SudokuCell> rows;
    private Map<Integer, SudokuCell> columns;
    private Map<Integer, SudokuCell> squares;
    private ArrayList<SudokuCell> possibleValues;
    private int cellNumbers;
    private int negativeCells;
    private int level;
    private int neighbors;

    public SudokuGrid() {

        this(3);

    }

    public SudokuGrid(int lev) {

        rows = new TreeMap();
        columns = new TreeMap();
        squares = new TreeMap();

        possibleValues = new ArrayList();

        if (lev >= 2 && lev <= 6) {
            level = lev;
        } else {
            level = 3;
        }

        neighbors = level * level;
        cellNumbers = level * level * level * level;
        negativeCells = cellNumbers;

        for (int i = 0; i < cellNumbers; i++) {

            int rowN = i;
            int colN = columnNumberFromRowNumber(rowN);
            int sqrN = sqrFromRow(rowN);

            SudokuCell c = new SudokuCell(rowN, colN, sqrN, neighbors);

            rows.put(rowN, c);
            columns.put(colN, c);
            squares.put(sqrN, c);

            possibleValues.add(c);
        }
    }

    public SudokuGrid(TreeMap copy) {

        rows = copy;
        columns = new TreeMap();
        squares = new TreeMap();

        possibleValues = new ArrayList();

        level = (int) Math.pow(copy.size(), 0.25);

        neighbors = level * level;
        cellNumbers = level * level * level * level;
        negativeCells = cellNumbers;

        for (int i = 0; i < cellNumbers; i++) {

            int rowN = i;
            int colN = columnNumberFromRowNumber(rowN);
            int sqrN = sqrFromRow(rowN);

            SudokuCell c = rows.get(i);

            columns.put(colN, c);
            squares.put(sqrN, c);

            possibleValues.add(c);

            if (c.getValue() != 0) {
                negativeCells--;
            }
        }
    }

    public boolean initialValues(int n) {

        int startingNum = negativeCells;

        for (int i = 0; i < n; i++) {

            Collections.sort(possibleValues, new NextCellCompare());

            int cellNumber = (int) (Math.random() * negativeCells);
            SudokuCell c = possibleValues.get(cellNumber);
            if (c.getValue() != 0) {
                System.out.println("Nonempty cell encountered ");
                break;
            }

            int index = (int) (c.getPossibleValues().size() * Math.random());

            if (c.getPossibleValues().size() == 0) {
                System.out.println("Found cell with no possible values");
                break;
            }

            int inputValue = c.getPossibleValues().get(index);

            if (!insertValue(c.getRowPosition(), inputValue, true)) {
                break;
            }
        }
        if (startingNum == negativeCells + n) {
            return true;
        }
        return false;
    }

    public class NextCellCompare implements Comparator<SudokuCell> {

        @Override
        public int compare(SudokuCell c1, SudokuCell c2) {
            if (c1.getValue() == 0) {

                if (c2.getValue() == 0) {
                    return 0;
                }
                return -1;
            }
            if (c2.getValue() == 0) {
                return 1;
            }
            return 0;
        }
    }

    private int columnNumberFromRowNumber(int rN) {
        return (rN % neighbors) * neighbors + (rN / neighbors);
    }

    private int sqrFromRow(int rN) {
        return (rN / (neighbors * level)) * (neighbors * level)
                + ((rN % neighbors) / level) * neighbors + ((rN / neighbors) % level) * level + (rN % level);
    }

    public boolean userInsertValue(int rowN, int value) {
        if (rows.get(rowN).getAccess()) {
            return insertValue(rowN, value, true);
        }
        return false;
    }

    private boolean insertValue(int rowN, int value, boolean access) {
        if (rows.get(rowN).getValue() == 0
                && rows.get(rowN).getPossibleValues().contains(value)) {

            rows.get(rowN).setValue(value);
            rows.get(rowN).setAccess(access);

            int rN = (rowN / neighbors) * neighbors;
            for (int i = 0; i < neighbors; i++) {
                rows.get(rN + i).excludeFromPossibles(value);
            }

            int rC = (columnNumberFromRowNumber(rowN) / neighbors) * neighbors;
            for (int i = 0; i < neighbors; i++) {
                columns.get(rC + i).excludeFromPossibles(value);
            }

            int rS = (sqrFromRow(rowN) / neighbors) * neighbors;
            for (int i = 0; i < neighbors; i++) {
                squares.get(rS + i).excludeFromPossibles(value);
            }
            negativeCells--;
            return true;
        }
        return false;
    }

    public boolean userEraseValue(int rowN) {
        if (rows.get(rowN).getAccess() && rows.get(rowN).getValue() != 0) {
            eraseValue(rowN);
            return true;
        }
        return false;
    }

    private void eraseValue(int row) {
        int value = rows.get(row).getValue();

        rows.get(row).eraseValue();
        rows.get(row).setAccess(true);
        negativeCells++;

        int rowStartNumber = (row / neighbors) * neighbors;
        for (int i = 0; i < neighbors; i++) {

            if (rowStartNumber + i != row) {
                if (rows.get(rowStartNumber + i).getValue() != 0) {
                    rows.get(row).excludeFromPossibles(rows.get(rowStartNumber + i).getValue());
                } else {
                    boolean found = false;
                    int j = 0;
                    int _columnStNum = (columnNumberFromRowNumber(rowStartNumber + i) / neighbors) * neighbors;
                    int _squareStNum = (sqrFromRow(rowStartNumber + i) / neighbors) * neighbors;
                    while (!found && j < neighbors) {
                        if (columns.get(_columnStNum + j).getValue() == value) {
                            found = true;
                        }
                        if (squares.get(_squareStNum + j).getValue() == value) {
                            found = true;
                        }
                        j++;
                    }
                    if (!found) {
                        rows.get(rowStartNumber + i).addToPossibleValues(value);
                    }
                }
            }
        }

        int columnStartNumber = (columnNumberFromRowNumber(row) / neighbors) * neighbors;
        for (int i = 0; i < neighbors; i++) {

            if (columnStartNumber + i != columnNumberFromRowNumber(row)) {
                if (columns.get(columnStartNumber + i).getValue() != 0) {
                    columns.get(columnNumberFromRowNumber(row)).excludeFromPossibles(columns.get(columnStartNumber + i).getValue());
                } else {
                    boolean found = false;
                    int j = 0;
                    int _rowStNum = (columns.get(columnStartNumber + i).getRowPosition() / neighbors) * neighbors;
                    int _squareStNum = (columns.get(columnStartNumber + i).getSqrPosition() / neighbors) * neighbors;
                    while (!found && j < neighbors) {
                        if (rows.get(_rowStNum + j).getValue() == value) {
                            found = true;
                        }
                        if (squares.get(_squareStNum + j).getValue() == value) {
                            found = true;
                        }
                        j++;
                    }
                    if (!found) {
                        columns.get(columnStartNumber + i).addToPossibleValues(value);
                    }
                }
            }
        }

        int squareStartNumber = (sqrFromRow(row) / neighbors) * neighbors;
        for (int i = 0; i < neighbors; i++) {

            if (squareStartNumber + i != sqrFromRow(row)) {
                if (squares.get(squareStartNumber + i).getValue() != 0) {
                    squares.get(sqrFromRow(row)).excludeFromPossibles(squares.get(squareStartNumber + i).getValue());
                } else {
                    boolean found = false;
                    int j = 0;
                    int _rowStNum = (squares.get(squareStartNumber + i).getRowPosition() / neighbors) * neighbors;
                    int _columnStNum = (squares.get(squareStartNumber + i).getColumnPosition() / neighbors) * neighbors;
                    while (!found && j < neighbors) {
                        if (rows.get(_rowStNum + j).getValue() == value) {
                            found = true;
                        }
                        if (columns.get(_columnStNum + j).getValue() == value) {
                            found = true;
                        }
                        j++;
                    }
                    if (!found) {
                        squares.get(squareStartNumber + i).addToPossibleValues(value);
                    }
                }
            }
        }
    }

    public void printGrid() {
        int ch = 0;
        int ln = 0;
        for (int i = 0; i < (level * level * level * level); i++) {
            int value = rows.get(i).getValue();

            if (value != 0) {
                if (value / 10 == 0) {
                    System.out.print(" ");
                }
                System.out.print(value + " ");
            } else {
                System.out.print("-- ");
            }
            ch++;
            if (ch % level == 0) {
                System.out.print("| ");
            }
            if (ch == (level * level)) {
                System.out.println();
                ch = 0;
                ln++;
            }
            if (ln == level) {
                for (int j = 1; j <= (level * level); j++) {
                    System.out.print("---");
                    if (j % level == 0) {
                        System.out.print("+-");
                    }
                }
                ln = 0;
                System.out.println();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public int getNegativeCells() {
        return negativeCells;
    }

    public LinkedList<Integer> getValues() {
        LinkedList<Integer> list = new LinkedList();

        for (int i = 0; i < cellNumbers; i++) {
            list.add(rows.get(i).getValue());
        }

        return list;
    }

    public LinkedList<Boolean> getAccess() {
        LinkedList<Boolean> list = new LinkedList();

        for (int i = 0; i < cellNumbers; i++) {
            list.add(rows.get(i).getAccess());
        }

        return list;
    }

    @Override
    public SudokuGrid clone() {

        TreeMap<Integer, SudokuCell> copyMap = new TreeMap();

        for (int i = 0; i < cellNumbers; i++) {
            copyMap.put(i, rows.get(i).clone());
        }

        SudokuGrid copy = new SudokuGrid(copyMap);

        return copy;
    }

    public TreeMap<Integer, SudokuCell> getCopyGridMap() {

        TreeMap<Integer, SudokuCell> copyMap = new TreeMap();

        for (int i = 0; i < cellNumbers; i++) {
            copyMap.put(i, rows.get(i).clone());
        }

        return copyMap;
    }

    public SudokuCell getCopyCell(int row) {

        SudokuCell copy = rows.get(row).clone();

        return copy;
    }

    public void randomCellErase(int n) {
        for (int i = 0; i < n; i++) {
            Collections.sort(possibleValues, new NextCellCompare());
            eraseValue(((int) (Math.random() * (cellNumbers - negativeCells))) + negativeCells);
        }
    }

    public void confirmInitialValues() {
        for (int i = 0; i < cellNumbers; i++) {
            SudokuCell c = rows.get(i);
            if (c.getValue() != 0) {
                c.setAccess(false);
            }
        }
    }
}
