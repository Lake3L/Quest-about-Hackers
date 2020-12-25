import java.util.Arrays;

public class Sudoku extends Minigames implements SaveLoad{
    //Судоку

    private static int[][] origin;
    private int[][] sudoku;

    static{
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

    }
}
