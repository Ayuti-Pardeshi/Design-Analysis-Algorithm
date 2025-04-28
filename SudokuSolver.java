
import java.util.logging.Logger;

public class SudokuSolver {
    private static final Logger logger = Logger.getLogger(SudokuSolver.class.getName());
    private int[][] board;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    public void printBoard() {
        for (int[] row : board) {
            StringBuilder rowOutput = new StringBuilder();
            for (int num : row) {
                rowOutput.append(num).append(" ");
            }
            logger.info(rowOutput.toString().trim());
        }
    }

    public int[] findEmpty() {
        int[] result = null;
        int minOptions = 10; // More than the max possible options

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    int options = 0;
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(i, j, num)) {
                            options++;
                        }
                    }
                    if (options < minOptions) {
                        minOptions = options;
                        result = new int[]{i, j};
                    }
                }
            }
        }
        return result;
    }

    public boolean isValid(int row, int col, int num) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // Check box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve() {
        int[] empty = findEmpty();
        if (empty == null) {
            return true; // Solved
        }
        int row = empty[0];
        int col = empty[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (solve()) {
                    return true;
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver(sudokuBoard);
        if (solver.solve()) {
            logger.info("Sudoku puzzle solved successfully.");
            solver.printBoard();
        } else {
            logger.severe("No solution exists for the given Sudoku puzzle.");
        }
    }
}
