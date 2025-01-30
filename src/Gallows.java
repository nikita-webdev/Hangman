import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gallows {
    public Gallows() {

    }
    // Загадать случайное слово из словаря
    public String guessAWord() throws FileNotFoundException {
        FileReader file = new FileReader("dictionary.txt");
        Scanner scanner = null;

        scanner = new Scanner(file);

        List<String> words = new ArrayList<>();
        while(scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }

        scanner.close();

        Random randomIndex = new Random();
        int indexOfWord = randomIndex.nextInt(0, words.size());

        return words.get(indexOfWord);
    }

    public StringBuilder createALineWithAWord(String word) {
        // Создаём StringBuilder и добавляем в него _ по количеству букв в слове
        StringBuilder stringWithWord = new StringBuilder ("");
        for (int i = 0; i < word.length(); i++) {
            stringWithWord.append("_"); // Строка
        }

        return stringWithWord;
    }

    public int validation(String letter) {
        int validationResult = -1;
        letter = letter.trim();

        String regex = "[а-я]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(letter);

        // Валидация
        if (!matcher.find()) {
            validationResult = 1; // Не кириллица и не в нижнем регистре
        } else if (letter.length() > 1 | letter.isEmpty()) {
            validationResult = 2; // Больше одной буквы или пустое значение
        } else {
            validationResult = 0; // Всё в порядке
        }

        return validationResult;
    }
}
