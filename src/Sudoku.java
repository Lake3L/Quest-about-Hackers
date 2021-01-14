import java.util.ArrayList;
import java.util.Random;

public class Sudoku extends Minigames implements SaveLoad{
    //Судоку

    private static int[][] origin;
    private static int[][] tmp;
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
        tmp = origin;
    }

    private static int[][] transpose(int[][] tmp){
        int[][]tmp1 = new int[9][9];
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                tmp1[i][j]=tmp[j][i];
            }
        }
        return tmp1;
    }
    private static int[][] swap_rows(int[][] tmp){
        Random random = new Random();
        int region_num = random.nextInt(3);
        int row1 = random.nextInt(3);
        int row2 = random.nextInt(3);
        while (row1==row2) row2 = random.nextInt(3);
        row1=region_num*3+row1;
        row2=region_num*3+row2;
        for(int i = 0; i<9; i++){
            tmp[row1][i]^=tmp[row2][i]^=tmp[row1][i]^=tmp[row2][i];
        }
        return tmp;
    }
    private static int[][] swap_columns(int[][] tmp){
        tmp=transpose(tmp);
        tmp=swap_rows(tmp);
        tmp=transpose(tmp);
        return tmp;
    }
    private static int[][] swap_rows_region(int [][] tmp){
        Random random = new Random();
        int region1 = random.nextInt(3);
        int region2 = random.nextInt(3);
        while(region1==region2) region2 = random.nextInt(3);
        int c1 = region1*3;
        int c2 = region2*3;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<9; j++){
                tmp[c1][j]^=tmp[c2][j]^=tmp[c1][j]^=tmp[c2][j];
            }
            c1++;
            c2++;
        }
        return tmp;
    }
    private static int[][] swap_columns_region(int[][] tmp){
        tmp=transpose(tmp);
        tmp=swap_rows_region(tmp);
        tmp=transpose(tmp);
        return tmp;
    }
}
