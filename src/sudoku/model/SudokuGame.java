package sudoku.model;

import sudoku.enums.UpdateAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author cmemela
 */
public class SudokuGame extends Observable {

    private int[][] solution;
    private int[][] game;
    private boolean[][] check;
    private int selectedNumber;
    private boolean help;

    public SudokuGame() {
        newGame();
        check = new boolean[9][9];
        help = true;
    }

    public void newGame() {
        solution = generateSolution(new int[9][9], 0);
        game = generateGame(copy(solution));
        setChanged();
        notifyObservers(sudoku.enums.UpdateAction.NEW_GAME);
    }

    public void checkGame() {
        selectedNumber = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                check[y][x] = game[y][x] == solution[y][x];
            }
        }
        setChanged();
        notifyObservers(sudoku.enums.UpdateAction.CHECK);
    }

    public void setHelp(boolean help) {
        this.help = help;
        setChanged();
        notifyObservers(UpdateAction.HELP);
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
        setChanged();
        notifyObservers(sudoku.enums.UpdateAction.SELECTED_NUMBER);
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isSelectedNumberCandidate(int x, int y) {
        return game[y][x] == 0 && isPossibleX(game, y, selectedNumber)
                && isPossibleY(game, x, selectedNumber) && isPossibleBlock(game, x, y, selectedNumber);
    }

    public void setNumber(int x, int y, int number) {
        game[y][x] = number;
    }

    public int getNumber(int x, int y) {
        return game[y][x];
    }

    public boolean isCheckValid(int x, int y) {
        return check[y][x];
    }

    /**
     * Returns whether given number is candidate on x axis for given game.
     *
     * @param game Game to check.
     * @param y Position of x axis to check.
     * @param number Number to check.
     * @return True if number is candidate on x axis, false otherwise.
     */
    private boolean isPossibleX(int[][] game, int y, int number) {
        for (int x = 0; x < 9; x++) {
            if (game[y][x] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether given number is candidate on y axis for given game.
     *
     * @param game Game to check.
     * @param x Position of y axis to check.
     * @param number Number to check.
     * @return True if number is candidate on y axis, false otherwise.
     */
    private boolean isPossibleY(int[][] game, int x, int number) {
        for (int y = 0; y < 9; y++) {
            if (game[y][x] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether given number is candidate in block for given game.
     *
     * @param game Game to check.
     * @param x Position of number on x axis in game to check.
     * @param y Position of number on y axis in game to check.
     * @param number Number to check.
     * @return True if number is candidate in block, false otherwise.
     */
    private boolean isPossibleBlock(int[][] game, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (game[yy][xx] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns next posible number from list for given position or -1 when list
     * is empty.
     *
     * @param game Game to check.
     * @param x X position in game.
     * @param y Y position in game.
     * @param numbers List of remaining numbers.
     * @return Next possible number for position in game or -1 when list is
     * empty.
     */
    private int getNextPossibleNumber(int[][] game, int x, int y, List<Integer> numbers) {
        while (numbers.size() > 0) {
            int number = numbers.remove(0);
            if (isPossibleX(game, y, number) && isPossibleY(game, x, number) && isPossibleBlock(game, x, y, number)) {
                return number;
            }
        }
        return -1;
    }

    /**
     * Generates Sudoku game solution.
     *
     * @param game Game to fill, user should pass 'new int[9][9]'.
     * @param index Current index, user should pass 0.
     * @return Sudoku game solution.
     */
    private int[][] generateSolution(int[][] game, int index) {
        if (index > 80) {
            return game;
        }

        int x = index % 9;
        int y = index / 9;

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        while (numbers.size() > 0) {
            int number = getNextPossibleNumber(game, x, y, numbers);
            if (number == -1) {
                return null;
            }

            game[y][x] = number;
            int[][] tmpGame = generateSolution(game, index + 1);
            if (tmpGame != null) {
                return tmpGame;
            }
            game[y][x] = 0;
        }

        return null;
    }

    /**
     * Generates Sudoku game from solution.
     *
     * @param game Game to be generated, user should pass a solution.
     * @return Generated Sudoku game.
     */
    private int[][] generateGame(int[][] game) {
        List<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions);
        return generateGame(game, positions);
    }

    /**
     * Generates Sudoku game from solution, user should use the other
     * generateGame method. This method simple removes a number at a position.
     * If the game isn't anymore valid after this action, the game will be
     * brought back to previous state.
     *
     * @param game Game to be generated.
     * @param positions List of remaining positions to clear.
     * @return Generated Sudoku game.
     */
    private int[][] generateGame(int[][] game, List<Integer> positions) {
        while (positions.size() > 0) {
            int position = positions.remove(0);
            int x = position % 9;
            int y = position / 9;
            int temp = game[y][x];
            game[y][x] = 0;

            if (!isValid(game)) {
                game[y][x] = temp;
            }
        }

        return game;
    }

    /**
     * Checks whether given game is valid.
     *
     * @param game Game to check.
     * @return True if game is valid, false otherwise.
     */
    private boolean isValid(int[][] game) {
        return isValid(game, 0, new int[]{0});
    }

    /**
     * Checks whether given game is valid, user should use the other isValid
     * method. There may only be one solution.
     *
     * @param game Game to check.
     * @param index Current index to check.
     * @param numberOfSolutions Number of found solutions. Int[] instead of int
     * because of pass by reference.
     * @return True if game is valid, false otherwise.
     */
    private boolean isValid(int[][] game, int index, int[] numberOfSolutions) {
        if (index > 80) {
            return ++numberOfSolutions[0] == 1;
        }

        int x = index % 9;
        int y = index / 9;

        if (game[y][x] == 0) {
            List<Integer> numbers = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++) {
                numbers.add(i);
            }

            while (numbers.size() > 0) {
                int number = getNextPossibleNumber(game, x, y, numbers);
                if (number == -1) {
                    break;
                }
                game[y][x] = number;

                if (!isValid(game, index + 1, numberOfSolutions)) {
                    game[y][x] = 0;
                    return false;
                }
                game[y][x] = 0;
            }
        } else if (!isValid(game, index + 1, numberOfSolutions)) {
            return false;
        }

        return true;
    }

    /**
     * Copies a game.
     *
     * @param game Game to be copied.
     * @return Copy of given game.
     */
    private int[][] copy(int[][] game) {
        int[][] copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                copy[y][x] = game[y][x];
            }
        }
        return copy;
    }
}
