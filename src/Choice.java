import java.util.Scanner;

public class Choice {
    static Scanner scanner = new Scanner(System.in);
    Player player;

    public static int readInt(String prompt, int userChoice) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception exc) {
                input = -1;
                System.out.println("Ведите число");
            }
        } while (input < 1 || input > userChoice);
        return input;
    }
}
