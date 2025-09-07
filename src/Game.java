import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private final Gallows gallows = new Gallows();
    private final GameState gameState = new GameState();

    private final static int MAX_MISTAKES = 6;
    private String word;
    private int lettersInWord;
    private StringBuilder wordMask;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            initializeGame();
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary not found.");
            System.exit(1);
        }

        String userLetter;
        boolean gameCycle = true;

        while (gameCycle) {
            printGallowsState();
            printMistakesNumber();

            if (gameState.getEnteredLetters().length() > 2) {
                printPreviouslyEnteredLetters();
            }

            userLetter = inputUserLetter();

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

            if (isGameOver()) {
                if (isWin(lettersInWord)) {
                    printWinMessage(word);
                } else {
                    printGallowsState();
                    printMistakesNumber();
                    printLoseMessage(word);
                }
                gameCycle = false;
            } else {
                addSpaces();

                if (!word.contains(userLetter)) {
                    System.out.printf("Буквы \"%s\" нет в слове%n", userLetter);
                }
            }
        }
    }

    private void addSpaces() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private void printMistakesNumber() {
        System.out.println("Число ошибок: " + gameState.getMistakesCounter());
    }

    private boolean isGameOver() {
        return isWin(lettersInWord) || gameState.getMistakesCounter() == MAX_MISTAKES;
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

    private void initializeGame() throws FileNotFoundException {
        word = gallows.getRandomWord();
        wordMask = gallows.createWordMask(word);
        lettersInWord = word.length();
        gameState.clearMistakes();
        gameState.clearEnteredLetters();
    }

    private void printGallowsState() {
        int rows = gallows.getGallowsStage(gameState.getMistakesCounter()).length;
        for (int i = 0; i < rows; i++) {
            System.out.println(gallows.getGallowsStage(gameState.getMistakesCounter())[i]);
        }
    }

    private void printPreviouslyEnteredLetters() {
        System.out.println("Ранее вы вводили буквы: " + gameState.getEnteredLetters());
    }

    private String inputUserLetter() {
        Scanner inputLetter = scanner;
        System.out.printf("Введите букву из слова: %n%s%n", wordMask);
        String letter = inputLetter.nextLine().trim().toLowerCase();
        Gallows.ValidationResult resultValidation = gallows.validation(letter);

        while (resultValidation != Gallows.ValidationResult.VALID) {
            if (resultValidation == Gallows.ValidationResult.INVALID_LETTER) {
                System.out.println("Нужно ввести букву кириллицы");
            } else if (resultValidation == Gallows.ValidationResult.INVALID_LENGTH) {
                System.out.println("Нужно ввести одну букву:");
            }
            letter = inputLetter.nextLine().trim().toLowerCase();
            resultValidation = gallows.validation(letter);
        }

        return letter;
    }
}
