import java.io.*;

public class FileWork {

    static String[] loadFromFile(String file) {
        try {
            BufferedReader b = new BufferedReader(
                    new FileReader(
                            new File(file)));
            return b.lines().toArray(size -> new String[size]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        } catch (IOException e) {
            System.out.println("Error File Read");
            return null;
        }
    }

    static void saveToFile(String file, String[] lines) {
        try(PrintStream printStream = new PrintStream(file))
        {
            for (String line: lines)
                printStream.println(line);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
