package task3;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PasswordGenerator {
    private final List<String> lowercase = List.of(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );

    private final List<String> uppercase = List.of(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );

    private final List<String> digits = List.of(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    );

    private final List<String> specialSymbols = List.of(
            "!", "@", "#", "$", "%", "^", "&", "*", "-", "_", "+",
            ";", ":", ",", ".", "?", "~", "`", "§"
    );
    private final List<String> allCharacters;
    private final SecureRandom random;
    private final Scanner scanner;

    public PasswordGenerator() {
        this.scanner = new Scanner(System.in);
        this.random = new SecureRandom();

        List<String> allChars = new ArrayList<>();
        allChars.addAll(lowercase);
        allChars.addAll(uppercase);
        allChars.addAll(digits);
        allChars.addAll(specialSymbols);
        this.allCharacters = Collections.unmodifiableList(allChars);

    }

    private String getRandomCharacter(List<String> characterList) {
        int randomIndex = random.nextInt(characterList.size());
        return characterList.get(randomIndex);
    }

    private String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters, random);
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }

        return shuffled.toString();
    }

    public void generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder();

        List<String> requiredCharacters = new ArrayList<>();

        requiredCharacters.add(getRandomCharacter(lowercase));
        requiredCharacters.add(getRandomCharacter(uppercase));
        requiredCharacters.add(getRandomCharacter(digits));
        requiredCharacters.add(getRandomCharacter(specialSymbols));

        for (String character : requiredCharacters) {
            password.append(character);
        }

        for (int i = 0; i < length - 4; i++) {
            password.append(getRandomCharacter(allCharacters));
        }

        String finalPassword = shuffleString(password.toString());
        System.out.println("Ваш пароль: " + finalPassword);
    }

    public void start() {
        int passLength;
        while (true) {
            System.out.print("Введите желаемое значение длины пароля (от 9 до 12 символов): ");

            if (scanner.hasNextInt()) {
                passLength = scanner.nextInt();

                if (passLength >= 9 && passLength <= 12) {
                    break;
                } else {
                    System.out.println("Ошибка: длина пароля должна быть от 9 до 12 символов. Попробуйте снова.");
                }
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
            }
        }
        generateRandomPassword(passLength);
    }

    public static void main(String[] args) {
        new PasswordGenerator().start();
    }
}
