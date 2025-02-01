import java.util.ArrayList;
import java.util.List;

public class Gamer {
    // Счётчик ошибок
    private int mistakesCounter;

    // Буквы, которые игрок уже вводил
    private final List<String> enteredLetters = new ArrayList<>();

    public int getMistakesCounter() {
        return mistakesCounter;
    }

    public void addMistake() {
        mistakesCounter++;
    }

    public void clearMistakes() {
        mistakesCounter = 0;
    }

    public String getEnteredLetters() {
        return enteredLetters.toString();
    }

    public void addEnteredLetter(String letter) {
        // Если введённой игроком буквы нет в листе - добавляем её
        if (!hasEnteredLetter(letter)) {
            enteredLetters.add(letter);
        }
    }

    public boolean hasEnteredLetter(String letter) {
        // Проверяем, входит ли введённая игроком буква в лист
        return enteredLetters.contains(letter);
    }

    public void clearEnteredLetters() {
        enteredLetters.clear();
    }
}
