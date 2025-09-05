import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    Gallows gallows = new Gallows();
    GameState gameState = new GameState();

    private final static int MAX_MISTAKES = 6;

    public void start() throws FileNotFoundException {
        boolean gameCycle = true;
        String word = gallows.getRandomWord();
        int lettersInWord = word.length();
        StringBuilder wordMask = gallows.createWordMask(word);

        while (gameCycle) {
            Scanner inputLetter = new Scanner(System.in);

            int rows = gallows.getGallowsStage(gameState.getMistakesCounter()).length;
            for (int i = 0; i < rows; i++) {
                System.out.println(gallows.getGallowsStage(gameState.getMistakesCounter())[i]);
            }

            printMistakesNumber();

            if (gameState.getEnteredLetters().length() > 2) {
                System.out.println("Ранее вы вводили буквы: " + gameState.getEnteredLetters());
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

                if (!gameState.getEnteredLetters().contains(userLetter)) {
                    while (wordForReplace.indexOf(userLetter) != -1) {
                        int index = wordForReplace.indexOf(userLetter);
                            wordMask.replace(index, index + 1, userLetter);
                            wordForReplace.replace(index, index + 1, " ");

                        lettersInWord--;
                    }
                    gameState.addEnteredLetter(userLetter);
                }
            } else {
                if (!gameState.hasEnteredLetter(userLetter)) {
                    gameState.addMistake();
                }

                gameState.addEnteredLetter(userLetter);
            }

            if (isWin(lettersInWord) || gameState.getMistakesCounter() == MAX_MISTAKES) {
                if (isWin(lettersInWord)) {
                    printWinMessage(word);
                } else {
                    for (int i = 0; i < rows; i++) {
                        System.out.println(gallows.getGallowsStage(gameState.getMistakesCounter())[i]);
                    }
                    printMistakesNumber();
                    printLoseMessage(word);
                }
                gameState.clearMistakes();
                gameState.clearEnteredLetters();
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

    private void printMistakesNumber() {
        System.out.println("Число ошибок: " + gameState.getMistakesCounter());
    }

    private boolean isWin(int lettersInWord) {
        return lettersInWord == 0;
    }

    private void printWinMessage(String word) {
        System.out.printf("Вы выиграли! Загаданное слово: %s%n%n", word);
    }

    private void printLoseMessage(String word) {
        System.out.printf("Вы проиграли! Загаданное слово: %s%n%n", word);
    }
}
