package sudoku;

import javax.swing.JFrame;
import sudoku.controller.ButtonsController;
import sudoku.model.SudokuGame;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Clive Sudoku v.1");
        //Set the Number of grids here
        SudokuGrid sg = new SudokuGrid(3);
        sg.initialValues(100);
        sg.confirmInitialValues();
        sg.printGrid();
        frame.add(new SudokuCanvas(frame, sg));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
