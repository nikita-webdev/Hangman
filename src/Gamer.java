import java.util.ArrayList;
import java.util.List;

public class Gamer {
    // Счётчик ошибок
    private int counter;

    // Буквы, которые игрок уже вводил
    private List<String> alreadyEnteredLetters = new ArrayList<>();

    public int getCounter() {
        return counter;
    }

    public void setCounter(int error) {
        if (error == 0) {
            this.counter = 0;
        }

        this.counter += error;
    }

    public String getAlreadyEnteredLetter() {
        return alreadyEnteredLetters.toString();
    }

    public void addAlreadyEnteredLetter(String letter) {
        // Если введённой игроком буквы нет в листе - добавляем её
        if (hasAlreadyEnteredLetter(letter) == 0) {
            alreadyEnteredLetters.add(letter);
        }
    }

    public int hasAlreadyEnteredLetter(String letter) {
        // Проверяем, входит ли введённая игроком буква в лист
        if (alreadyEnteredLetters.indexOf(letter) == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public void clearAlreadyEnteredLetters() {
        alreadyEnteredLetters.clear();
    }
}
