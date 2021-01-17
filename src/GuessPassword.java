import java.util.Random;

public class GuessPassword extends Minigames implements SaveLoad{
    //Быки и коровы
    private int number;

    public GuessPassword(int difficulty){
        Random random = new Random();
        this.number = difficulty+random.nextInt(3);
    }
    public void show_coincidences(String answer){
        if(answer.equals(this.number+"")) {
            System.out.println("Доступ получен");
            return;
        }
        String[] s = answer.split("");
        String[] s1 = (this.number+"").split("");
        String[] coincidences = new String[s1.length];
        int a = 0;
        int b = 0;
        for(int i = 0; i<s.length; i++){
            if(s[i].equals(s1[i])) coincidences[i] = "b";
        }
        for(int i = 0; i<s.length; i++){
            if((this.number+"").contains(s[i])&&!coincidences[i].equals("b")) coincidences[i]="a";
        }
        for(String i:coincidences){
            if(i.equals("a")) a++;
            if(i.equals("b")) b++;
        }
        System.out.printf("%dA%dB%n", a, b);
    }
}
