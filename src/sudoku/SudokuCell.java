package sudoku;

public class SudokuCell extends Cell {

    private int rowPosition;
    private int columnPosition;
    private int squarePosition;

    public SudokuCell(int rowN, int colN, int sqrN, int maxV) {

        super(maxV);

        rowPosition = rowN;
        columnPosition = colN;
        squarePosition = sqrN;

    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int getSqrPosition() {
        return squarePosition;
    }

    @Override
    public SudokuCell clone() {

        SudokuCell copy = new SudokuCell(this.rowPosition, this.columnPosition,
                this.squarePosition, maxValue);

        copy.setValue(this.getValue());
        copy.setAccess(this.getAccess());

        for (int i = 1; i <= maxValue; i++) {
            if (!possibleValues.contains(i)) {
                copy.excludeFromPossibles(i);
            }
        }

        return copy;
    }
}
