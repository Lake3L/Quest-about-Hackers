import java.util.Scanner;

public class qwe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean running = true;
        sc.nextLine();
        while(running){
            for(int i = 0; i<30; i++){
                input = sc.nextLine();
                TextPart.nextLine(i, input);
                if(input.equals("//quit")) running = false;
            }
        }
        //Для теста миниигр
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.getAnswer();
        Sudoku sudoku = new Sudoku();
        sudoku.getAnswer();
        GuessPassword guessPassword = new GuessPassword();
        guessPassword.show_coincidences();
    }
}
