import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private final static String START = "y";
    private final static String EXIT = "n";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру \"Виселица\"!");

        while(true) {
            System.out.printf("Введите %s, чтобы начать новую игру. Введите %s, чтобы выйти из игры.%n", START, EXIT);

            String start = scanner.nextLine().trim().toLowerCase();

            if (start.equals(START)) {
                Game game = new Game();
                System.out.printf("Начинаем игру.%n");
                game.start();
            } else if (start.equals(EXIT)) {
                System.out.print("Игра завершена.");
                scanner.close();
                break;
            }
        }
    }
}
