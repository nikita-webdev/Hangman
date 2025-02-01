import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    Gallows gallows = new Gallows();
    Gamer gamer = new Gamer();

    public void start() throws FileNotFoundException {
        boolean gameCycle = true;

        // Загадываем число и выбираем слово в словаре
        String word = gallows.getRandomWord();
        int lettersInWord = word.length();

        // Создаём строку со словом
        StringBuilder wordMask = gallows.createWordMask(word);

        while (gameCycle) {
            Scanner inputLetter = new Scanner(System.in);

            if (gamer.getEnteredLetters().length() > 2) {
                System.out.println("Ранее вы вводили буквы: " + gamer.getEnteredLetters());
            }

            // Пользователь вводит букву
            System.out.printf("Введите букву из слова: %n%s%n", wordMask);
            String userLetter = inputLetter.nextLine().trim().toLowerCase();

            // Валидация
            Gallows.ValidationResult resultValidation = gallows.validation(userLetter);

            while (resultValidation != Gallows.ValidationResult.VALID) {
                if (resultValidation == Gallows.ValidationResult.INVALID_LETTER) {
                    System.out.println("Нужно ввести букву кириллицы");
                } else if (resultValidation == Gallows.ValidationResult.INVALID_LENGTH) {
                    System.out.println("Нужно ввести одну букву:");
                }
                userLetter = inputLetter.nextLine().trim().toLowerCase();
                resultValidation = gallows.validation(userLetter);
            }

            // Пустые строки в консоли вместо очистки
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }

            if (word.contains(userLetter)) {
                StringBuilder wordForReplace = new StringBuilder();
                wordForReplace.append(word);

                // Если буква введённая пользователем находится в слове
                if (!gamer.getEnteredLetters().contains(userLetter)) {
                    while (wordForReplace.indexOf(userLetter) != -1) {
                        int index = wordForReplace.indexOf(userLetter);
                        if (index != -1) {
                            wordMask.replace(index, index + 1, userLetter);
                            wordForReplace.replace(index, index + 1, " ");
                        }

                        lettersInWord--;
                        gamer.addEnteredLetter(userLetter);
                    }
                }

            } else {
                System.out.printf("Буквы \"%s\" нет в слове%n", userLetter);

                // Повторно вводимый символ, отсутствующий в секретном слове, не должен считаться за ошибку
                if (!gamer.hasEnteredLetter(userLetter)) {
                    gamer.addMistake();
                }

                gamer.addEnteredLetter(userLetter);
            }

            // Выводим состояние виселицы
            gallows.printGallows(gamer.getMistakesCounter());
            for (int i = 0; i < gallows.printGallows(gamer.getMistakesCounter()).length; i++) {
                System.out.println(gallows.printGallows(gamer.getMistakesCounter())[i]);
            }

            System.out.println("Число ошибок: " + gamer.getMistakesCounter());

            if (lettersInWord == 0 || gamer.getMistakesCounter() == 6) {
                if (lettersInWord == 0) {
                    System.out.printf("Вы выиграли! Загаданное слово: %s%n%n", word);
                } else {
                    System.out.printf("Вы проиграли. Загаданное слово: %s%n%n", word);
                }
                gamer.clearMistakes();
                gamer.clearEnteredLetters();
                gameCycle = false;
            }
        }
    }
}
