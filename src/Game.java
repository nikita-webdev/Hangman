import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    Gallows gallows = new Gallows();
    Gamer gamer = new Gamer();

    public void start() throws FileNotFoundException {
        boolean gameCycle = true;
        String word = gallows.getRandomWord();
        int lettersInWord = word.length();
        StringBuilder wordMask = gallows.createWordMask(word);

        while (gameCycle) {
            Scanner inputLetter = new Scanner(System.in);

            for (int i = 0; i < gallows.printGallows(gamer.getMistakesCounter()).length; i++) {
                System.out.println(gallows.printGallows(gamer.getMistakesCounter())[i]);
            }

            System.out.println("Число ошибок: " + gamer.getMistakesCounter());

            if (gamer.getEnteredLetters().length() > 2) {
                System.out.println("Ранее вы вводили буквы: " + gamer.getEnteredLetters());
            }

            System.out.printf("Введите букву из слова: %n%s%n", wordMask);
            String userLetter = inputLetter.nextLine().trim().toLowerCase();

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

            if (word.contains(userLetter)) {
                StringBuilder wordForReplace = new StringBuilder(word);

                if (!gamer.getEnteredLetters().contains(userLetter)) {
                    while (wordForReplace.indexOf(userLetter) != -1) {
                        int index = wordForReplace.indexOf(userLetter);
                            wordMask.replace(index, index + 1, userLetter);
                            wordForReplace.replace(index, index + 1, " ");

                        lettersInWord--;
                    }
                    gamer.addEnteredLetter(userLetter);
                }
            } else {
                if (!gamer.hasEnteredLetter(userLetter)) {
                    gamer.addMistake();
                }

                gamer.addEnteredLetter(userLetter);
            }

            if (lettersInWord == 0 || gamer.getMistakesCounter() == 6) {
                if (lettersInWord == 0) {
                    System.out.printf("Вы выиграли! Загаданное слово: %s%n%n", word);
                } else {
                    System.out.printf("Вы проиграли. Загаданное слово: %s%n%n", word);
                }
                gamer.clearMistakes();
                gamer.clearEnteredLetters();
                gameCycle = false;
            } else {
                addSpaces();

                if (!word.contains(userLetter)) {
                    System.out.printf("Буквы \"%s\" нет в слове%n", userLetter);
                }
            }
        }
    }

    void addSpaces() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
