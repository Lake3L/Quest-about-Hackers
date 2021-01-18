import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessPassword extends Minigames implements SaveLoad{
    //Быки и коровы
    private int number;
    private int attempts;
    private static Scanner sc;
    static{
        sc = new Scanner(System.in);
    }
    public GuessPassword(){
        Random random = new Random();
        this.number = 1000+random.nextInt(10000);
        show_password();
        this.attempts = 5+(TextPart.has_circuit()?1:0);
    }
    public boolean show_coincidences() {
        int attempts_tmp = this.attempts;
        for(int i = 0; i<this.attempts; i++) {
        boolean is_right = true;
        String answer = sc.nextLine();
        if (answer.length() != (this.number+"").length()) {
            System.out.println("Неверный ввод");
            show_coincidences();
        }
        for (String j : answer.split("")) {
            if (!j.matches("\\d")) {
                System.out.println("Неверный ввод");
                is_right = false;
                break;
            }
        }
        if (is_right == false) show_coincidences();
            if (answer.equals(this.number + "")) {
                System.out.println("Доступ получен");
                return true;
            }
            String[] s = answer.split("");
            String[] s1 = (this.number + "").split("");
            String[] coincidences = new String[s1.length];
            Arrays.fill(coincidences, "c");
            int a = 0;
            int b = 0;
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals(s1[j])) coincidences[j] = "b";
            }
            for (int j = 0; j < s.length; j++) {
                if ((this.number + "").contains(s[j]) && !coincidences[j].equals("b")) coincidences[j] = "a";
            }
            for (String j : coincidences) {
                if (j.equals("a")) a++;
                if (j.equals("b")) b++;
            }
            System.out.printf("%dA%dB%n" +
                    "Попыток осталось: %d.%n", a, b, --attempts_tmp);
        }
        return false;
    }
    public void show_password(){
        for(int i = 0; i<(this.number+"").length(); i++){
            System.out.print("*");
        }
        System.out.println();
    }
}
