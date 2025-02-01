import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gallows {
    private final List<String> words = new ArrayList<>();

    private static final String[][] GALLOWS_STAGES = {
            {
                    "      _____ ",
                    "     |     |",
                    "           |",
                    "           |",
                    "           |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "           |",
                    "           |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "     |     |",
                    "           |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "    /|     |",
                    "           |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "    /|\\    |",
                    "           |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "    /|\\    |",
                    "    /      |",
                    "          _|_",
            },
            {
                    "      _____",
                    "     |     |",
                    "     O     |",
                    "    /|\\    |",
                    "    / \\    |",
                    "          _|_",
            },
    };

    public String[] printGallows(int numStage) {
        return GALLOWS_STAGES[numStage];
    }

    // Загадать случайное слово из словаря
    public String getRandomWord() throws FileNotFoundException {
        FileReader file = new FileReader("dictionary.txt");
        Scanner scanner = null;
        scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            words.add(scanner.nextLine().trim());
        }

        scanner.close();

        Random randomIndex = new Random();
        int indexOfWord = randomIndex.nextInt(0, words.size());

        return words.get(indexOfWord);
    }

    public StringBuilder createWordMask(String word) {
        // Создаём StringBuilder и добавляем в него _ по количеству букв в слове
        StringBuilder wordMask = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            wordMask.append("_"); // Строка
        }

        return wordMask;
    }

    public enum ValidationResult {
        INVALID_LETTER,
        INVALID_LENGTH,
        VALID
    }

    public ValidationResult validation(String letter) {
        letter = letter.trim();

        String regex = "[а-яА-ЯёЁ]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(letter);

        // Валидация
        if (!matcher.find()) {
            return ValidationResult.INVALID_LETTER;
        } else if (letter.length() != 1) {
            return ValidationResult.INVALID_LENGTH;
        } else {
            return ValidationResult.VALID;
        }
    }
}
