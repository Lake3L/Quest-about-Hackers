import java.util.Scanner;

public class qwe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean running = true;
        while(running){
            for(int i = 0; i<31; i++){
                TextPart.nextLine(i);
                input = sc.nextLine();
            }
            if(input.equals("//quit")) running = false;
        }
    }
}
