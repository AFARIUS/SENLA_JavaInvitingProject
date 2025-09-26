package task1;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Scaffold {
    private static final String[] words = {"Джава", "Собака", "Мыло", "Тетраэдр"};
    private String choosedWord;
    private int lives;
    private final Set<Character> guessedLetters;
    private final Set<Character> alphabet = Set.of(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    );
    private final Scanner scanner;

    public Scaffold() {
        this.guessedLetters = new HashSet<>();
        this.choosedWord = getRandomWord();
        this.lives = 13;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println("Игра началась! У вас " + lives + " жизней.");

        while (!isGameOver()) {
            printCurrentWord(); // Показываем текущий прогресс
            inputProcessing();      // Обрабатываем ввод пользователя
        }

        printFinalResult();
        scanner.close();
    }

    private String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    private void printCurrentWord() {
        StringBuilder currentWord = new StringBuilder();
        choosedWord = choosedWord.toLowerCase();
        for (int i = 0; i < choosedWord.length(); i++) {
            char currentChar = choosedWord.charAt(i);
            if (guessedLetters.contains(currentChar)) {
                currentWord.append(currentChar);
            }
            else {
                currentWord.append('_');
            }
        }
        System.out.println("Текущее слово: " + currentWord.substring(0, 1).toUpperCase() + currentWord.substring(1).toLowerCase());
        System.out.println("Осталось жизней: " + lives);
    }

    private boolean checkInAlphabet(String input) {
        for (char symbol : input.toCharArray()) {
            if (!alphabet.contains(symbol)) {
                return false;
            }
        }
        return true;
    }

    private void inputProcessing() {
        System.out.print("Введите букву или слово длиной в" + choosedWord.length() + "символов: ");
        String input = scanner.nextLine().toLowerCase();

        if (!(checkInAlphabet(input) && (input.length() == 1 || input.length() == choosedWord.length()))) {
            System.out.println("Ввод некорректен. Введите заново");
            return;
        }

        if (input.length() > 1) {
            if (input.equals(choosedWord)) {
                for(char symbol : input.toCharArray()) {
                    guessedLetters.add(symbol);
                    return;
                }
            }
            else {
                lives--;
                System.out.println("Это неверное слово");
                return;
            }
        }

        char letter = input.charAt(0);

        if (guessedLetters.contains(letter)) {
            System.out.println("Вы уже вводили эту букву!");
            return;
        }

        guessedLetters.add(letter);

        if (choosedWord.indexOf(letter) == -1) {
            lives--;
            System.out.println("Неверно! Буквы '" + letter + "' нет в слове.");
        } else {
            System.out.println("Верно! Буква '" + letter + "' есть в слове.");
        }

    }

    private boolean isWordGuessed() {
        for (char letter : choosedWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGameOver() {
        return lives <= 0 || isWordGuessed();
    }

    private void printFinalResult() {
        if (isWordGuessed()) {
            System.out.println("\nПоздравляем! Вы угадали слово: " + choosedWord);
        } else {
            System.out.println("\nИгра окончена! Загаданное слово было: " + choosedWord);
        }
    }

    public static void main(String[] args) {
        new Scaffold().play();
    }
}
