import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gallows gallows = new Gallows();
        Gamer gamer = new Gamer();
        boolean newGame = true;

        while(newGame) {
            if (newGame == true) {
                // 1. Старт
                System.out.println("Добро пожаловать в игру \"Виселица\"!");
                System.out.println("Нажмите Enter, чтобы начать новую игру. Введите q, чтобы выйти из игры.");

                Scanner start = new Scanner(System.in);
                String s = start.nextLine();

                if (s.contains("q")) {
                    System.out.println("Игра завершена.");
                    start.close();
                    break;
                } else {
                    System.out.println("Начинаем игру.");
                    start.reset();
                }
                // end of 1. Старт
            }

            boolean gameCycle = true;

            // 2. Начало новой игры
            // Загадываем число и выбираем слово в словаре
            String word = gallows.guessAWord();
            int numberOfLettersInAWord = word.length();

            // Создаём строку со словом
            StringBuilder stringWithWord = gallows.createALineWithAWord(word);

            while (gameCycle) {
                // Просим пользователя ввести букву
                Scanner inputLetter = new Scanner(System.in);

                if (gamer.getAlreadyEnteredLetter().length() > 2) {
                    System.out.println("Ранее вы вводили буквы: " + gamer.getAlreadyEnteredLetter());
                }

                System.out.println("Введите букву из слова:");

                // Выводим строку со словом
                System.out.println(stringWithWord);

                // Пользователь вводит букву
                String userLetter = inputLetter.nextLine().trim();

                // Валидация
                int userLetterResultValidation = gallows.validation(userLetter);
                while (userLetterResultValidation != 0) {
                    if (userLetterResultValidation == 1) {
                        System.out.println("Нужно ввести букву кириллицы в нижнем регистре");
                    } else {
                        System.out.println("Нужно ввести одну букву:");
                    }
                    userLetter = inputLetter.nextLine().trim();
                    userLetterResultValidation = gallows.validation(userLetter);
                }

                // Пустые строки в консоли вместо очистки
                for (int i = 0; i < 10; i++) {
                    System.out.println();
                }
                if (word.contains(userLetter)) {
                    StringBuilder wordForDelete = new StringBuilder();
                    wordForDelete.append(word);

                    // Если буква введённая пользователем находится в слове
                    while (wordForDelete.indexOf(userLetter) != -1) {
                        int index = wordForDelete.indexOf(userLetter);
                        if (index != -1) {
                            stringWithWord.replace(index, index + 1, userLetter);
                            wordForDelete.replace(index, index + 1, " ");
                        }

                        numberOfLettersInAWord--;
                        gamer.addAlreadyEnteredLetter(userLetter);
                    }

                } else {
                    System.out.println("Буквы \"" + userLetter + "\" нет в слове");

                    // Повторно вводимый символ, отсутствующий в секретном слове, не должен считаться за ошибку
                    if (gamer.hasAlreadyEnteredLetter(userLetter) != 1) {
                        gamer.setCounter(1);
                    }

                    gamer.addAlreadyEnteredLetter(userLetter);
                }

                switch(gamer.getCounter()) {
                    case 1 :
                        System.out.println("      _____ ");
                        System.out.println("     |     |");
                        System.out.println("           |");
                        System.out.println("           |");
                        System.out.println("           |");
                        System.out.println("          _|_");
                        break;

                    case 2 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("           |");
                        System.out.println("           |");
                        System.out.println("          _|_");
                        break;

                    case 3 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("     |     |");
                        System.out.println("           |");
                        System.out.println("          _|_");
                        break;

                    case 4 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("    /|     |");
                        System.out.println("           |");
                        System.out.println("          _|_");
                        break;

                    case 5 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("    /|\\    |");
                        System.out.println("           |");
                        System.out.println("          _|_");
                        break;

                    case 6 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("    /|\\    |");
                        System.out.println("    /      |");
                        System.out.println("          _|_");
                        break;

                    case 7 :
                        System.out.println("      _____");
                        System.out.println("     |     |");
                        System.out.println("     O     |");
                        System.out.println("    /|\\    |");
                        System.out.println("    / \\    |");
                        System.out.println("          _|_");
                        break;
                }

                System.out.println("Число ошибок: " + gamer.getCounter());

                if (numberOfLettersInAWord == 0 | gamer.getCounter() == 7) {
                    if (numberOfLettersInAWord == 0) {
                        System.out.println("Вы победили! Загаданное слово: \"" + word + "\".");
                    } else {
                        System.out.println("Вы проиграли");
                        System.out.println("Загаданное слово: \"" + word + "\"");
                    }
                    gamer.setCounter(0);
                    gamer.clearAlreadyEnteredLetters();
                    gameCycle = false;
                }
            }
        }
    }
}
