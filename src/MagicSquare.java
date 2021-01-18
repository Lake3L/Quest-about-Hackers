import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MagicSquare {

    private int[][] square;
    private int[][] solved_square;
    private static int[][] tmp;
    private static int[][] origin_square;
    private int c_0;
    private int attempts;
    private static Scanner sc;

    static{
        sc = new Scanner(System.in);
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
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(this.square[i][j]==0) this.c_0++;
            }
        }
        this.attempts = 1 + (TextPart.has_circuit()?1:0);
        show();
    }

    private void show(){
        for(int i = 0; i<10; i++){
            System.out.print("\u0332");
        }
        System.out.println();
        for(int i = 0; i<this.square.length; i++){
            System.out.print("|");
            for(int j = 0; j<this.square.length; j++){
                System.out.printf("%2s|", this.square[i][j]==0?" ":this.square[i][j]);
            }
            System.out.println();
        }
        for(int i = 0; i<10; i++){
            System.out.print("\u0305");
        }
        System.out.println();
    }
    public boolean getAnswer(){
        int[][] tmp_square = Arrays.copyOf(this.square, 3);
        for(int i = 0; i<this.c_0; i++) {
            String ans = sc.nextLine();
            if (!ans.matches("[1-3] [1-3] \\d+")) {
                System.out.println("Неверный ввод");
                return getAnswer();
            }
            int x = Integer.parseInt(ans.substring(0, 1))-1;
            int y = Integer.parseInt(ans.substring(2, 3))-1;
            int ans_ = Integer.parseInt(ans.substring(4));
            if (ans_ < 0 || ans_ > 99) {
                System.out.println("Неверный ввод");
                return getAnswer();
            }
            if (this.square[x][y] != 0) {
                System.out.println("Неверный ввод");
                return getAnswer();
            }
            tmp_square[x][y] = ans_;
            show();
        }
        if(this.solved_square.equals(tmp_square)){
            System.out.println("Взлом успешен");
            return true;
        }else{
            System.out.printf("Взлом провален.\n"+
                    "Попыток осталось: %d.",--this.attempts);
            return this.attempts==0?false:getAnswer();
        }
    }

    private static int[][] del_numbers(int [][] square){
        int c_0 = 0;
        Random random = new Random();
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(square[i][j] == 0) c_0++;
            }
        }
        for(int i = 0; i<4; i++){
            if(c_0==4) return square;
            int x = random.nextInt(2);
            int y = random.nextInt(2);
            if(square[x][y] == 0) {
                continue;
            }
            square[x][y] = 0;
            c_0++;
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
