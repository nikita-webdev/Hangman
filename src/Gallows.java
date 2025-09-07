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

    public String[] getGallowsStage(int numStage) {
        return GALLOWS_STAGES[numStage];
    }

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
        StringBuilder wordMask = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            wordMask.append("*");
        }

        return wordMask;
    }

    public enum ValidationResult {
        INVALID_LETTER,
        INVALID_LENGTH,
        VALID
    }

    public ValidationResult validation(String s) {
        s = s.trim();

        String regex = "[а-яА-ЯёЁ]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        if (!matcher.find()) {
            return ValidationResult.INVALID_LETTER;
        } else if (s.length() != 1) {
            return ValidationResult.INVALID_LENGTH;
        } else {
            return ValidationResult.VALID;
        }
    }
}
