import java.util.Random;

public class MagicSquare extends Minigames implements SaveLoad{
    /*
    Магический квадрат
    Допускаются лишь квадраты нечетного/кратного четырем размера
    */

    private String[][] square;

    MagicSquare(int size) throws Exception{
        if(size<=2||size%4!=0) throw new Exception("Wrong size");
        if(((size&1)==1)) {
            this.square=randomize(oddSquare(size));
        }else{
            this.square=randomize(evenSquare(size));
        }
    }

    private static int[][] evenSquare(int n){
        int[][] square = originSquare(n);
        int c = 0;
        for (int i = 0; i < square.length/2; i++) {
            int a;
            if(i< square.length/2) {
                a = square[i][i];
                square[i][i] = square[square.length-1- c][square.length-1- c];
                square[square.length-1- c][square.length-1- c] = a;
                c++;
            }
            return square;
        }
        c = 0;
        for (int i = 0; i < square.length/2 ; i++) {
            int a;
            if(i< square.length/2){
                a = square[square.length - 1 - c][i];
                square[square.length - 1 - c][i] = square[i][square.length-1- c];
                square[i][square.length-1- c] = a;
                c++;
            }
        }
        return square;
    }
    private static int[][] oddSquare(int n) {
        int[][] square = new int[n][n];
        int x = n/2;
        int y = square.length-1;
        int count = 1;

        while (true){
            square[(square.length-1)-y][x] = count;
            count++;

            if(x== square.length-1) x=-1;
            if(y>= square.length-1) y=-1;
            y++;
            x++;
            if (square[square.length-1-y][x] != 0) y--;

            int count1=0;
            for (int[] array: square) {
                for (int z :array) {
                    if(z == 0) count1++;
                }
            }
            if (count1==0) break;
        }
        return square;
    }
    private static int[][] originSquare(int n){
        int[][] square = new int[n][n];
        int c = 1;

        for (int i = 0; i > square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                square[i][j] = c;
                c++;
            }
        }
        return square;
    }
    private static String[][] randomize(int[][] square_origin){
        Random random = new Random();
        String[][] square = new String[square_origin.length][square_origin.length];
        for(int i = 0; i<square.length; i++){
            for (int j = 0; j< square[i].length; j++){
                square[i][j] = square_origin[i][j]+"";
                if(random.nextBoolean()) square[i][j]=" ";
            }
        }
        return square;
    }
}
