import java.util.Random;
import java.util.stream.IntStream;

public class Sudoku extends Minigames implements SaveLoad {
    //Судоку

    private static int[][] origin;
    private static int[][] tmp;
    private int[][] sudoku;
    private int difficulty;

    static {
        origin = new int[9][];
        origin[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        origin[1] = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3};
        origin[2] = new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6};
        origin[3] = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1};
        origin[4] = new int[]{5, 6, 7, 8, 9, 1, 2, 3, 4};
        origin[5] = new int[]{8, 9, 1, 2, 3, 4, 5, 6, 7};
        origin[6] = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2};
        origin[7] = new int[]{6, 7, 8, 9, 1, 2, 3, 4, 5};
        origin[8] = new int[]{9, 1, 2, 3, 4, 5, 6, 7, 8};
        tmp = origin;
    }

    private static int[][] transpose(int[][] tmp) {
        int[][] tmp1 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp1[i][j] = tmp[j][i];
            }
        }
        return tmp1;
    }
    private static int[][] swap_rows(int[][] tmp) {
        Random random = new Random();
        int region_num = random.nextInt(3);
        int row1 = random.nextInt(3);
        int row2 = random.nextInt(3);
        while (row1 == row2) row2 = random.nextInt(3);
        row1 = region_num * 3 + row1;
        row2 = region_num * 3 + row2;
        for (int i = 0; i < 9; i++) {
            tmp[row1][i] ^= tmp[row2][i] ^= tmp[row1][i] ^= tmp[row2][i];
        }
        return tmp;
    }
    private static int[][] swap_columns(int[][] tmp) {
        tmp = transpose(tmp);
        tmp = swap_rows(tmp);
        tmp = transpose(tmp);
        return tmp;
    }
    private static int[][] swap_rows_region(int[][] tmp) {
        Random random = new Random();
        int region1 = random.nextInt(3);
        int region2 = random.nextInt(3);
        while (region1 == region2) region2 = random.nextInt(3);
        int c1 = region1 * 3;
        int c2 = region2 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[c1][j] ^= tmp[c2][j] ^= tmp[c1][j] ^= tmp[c2][j];
            }
            c1++;
            c2++;
        }
        return tmp;
    }
    private static int[][] swap_columns_region(int[][] tmp) {
        tmp = transpose(tmp);
        tmp = swap_rows_region(tmp);
        tmp = transpose(tmp);
        return tmp;
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }
    private static boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[81];
        return IntStream.range(0, 81)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }
    private static boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[81];
        return IntStream.range(0, 81)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }
    private static boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[81];
        int subsectionRowStart = (row / 3) * 3;
        int subsectionRowEnd = subsectionRowStart + 3;

        int subsectionColumnStart = (column / 3) * 3;
        int subsectionColumnEnd = subsectionColumnStart + 3;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }
    private static boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != 0) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }

}
