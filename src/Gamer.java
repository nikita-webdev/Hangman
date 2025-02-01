import java.util.ArrayList;
import java.util.List;

public class Gamer {
    private int mistakesCounter;
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
        if (!hasEnteredLetter(letter)) {
            enteredLetters.add(letter);
        }
    }

    public boolean hasEnteredLetter(String letter) {
        return enteredLetters.contains(letter);
    }

    public void clearEnteredLetters() {
        enteredLetters.clear();
    }
}
