import java.util.Arrays;
import java.util.Random;

public class MagicSquare extends Minigames implements SaveLoad{

    private int[][] square;
    private int[][] solved_square;
    private static int[][] tmp;
    private static int[][] origin_square;

    static{
        origin_square = new int[3][3];
        origin_square[0] = new int[]{2, 9, 4};
        origin_square[1] = new int[]{7, 5, 3};
        origin_square[2] = new int[]{6, 1, 8};
        tmp = Arrays.copyOf(origin_square, 3);
    }

    MagicSquare(){
        Random random = new Random();
        this.solved_square = randomize();
        this.solved_square = square_add(this.solved_square, random.nextInt(9));
        this.solved_square = square_multiply(this.solved_square, random.nextInt(9));
        this.square = del_numbers(this.solved_square);
    }

    public void show(){
        for(int i = 0; i<this.square.length; i++){
            System.out.print("|");
            for(int j = 0; j<this.square.length; j++){
                System.out.printf("%2s|", this.square[i][j]==0?" ":this.square[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] del_numbers(int [][] square){
        Random random = new Random();
        for(int i = 0; i<5; i++){
            int x = random.nextInt(2);
            int y = random.nextInt(2);
            if(square[x][y] == 0) {
                continue;
            }
            square[x][y] = 0;
        }
        int c_0 = 0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(square[i][j] == 0) c_0++;
            }
        }
        if(c_0<3) return del_numbers(square);
        return square;
    }
    private static int[][] randomize(){
        Random random = new Random();
        for(int i = 0; i<5+random.nextInt(5); i++){
            switch (random.nextInt(2)){
                case 0: transpose();
                break;
                case 1: swap_rows();
                break;
                case 2: swap_columns();
                break;
            }
        }
        return tmp;
    }
    private static void transpose(){
        int[][] tmp = new int[3][3];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                tmp[j][i]=origin_square[i][j];
            }
        }
        origin_square=Arrays.copyOf(tmp, 3);
    }
    private static void swap_rows(){
        int[] tmp = Arrays.copyOf(origin_square[0], 3);
        origin_square[0] = Arrays.copyOf(origin_square[2], 3);
        origin_square[2] = Arrays.copyOf(tmp, 3);
    }
    private static void swap_columns(){
        transpose();
        swap_rows();
    }
    private static int[][] square_add(int[][] square, int number){
        int[][] tmp1 = Arrays.copyOf(square, 3);
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(tmp1[i][j]+number>99){
                    return tmp;
                }
                tmp1[i][j]+=number;
            }
        }
        return tmp1;
    }
    private static int[][] square_multiply(int[][] square, int number){
        int[][] tmp1 = Arrays.copyOf(square, 3);
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(tmp1[i][j]*number>99) return square;
                tmp1[i][j]*=number;
            }
        }
        return tmp1;
    }
}
