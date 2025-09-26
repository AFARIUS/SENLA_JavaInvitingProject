package task2;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class exchangeRate {
    private final Map<String, Double> coefficients = Map.of(
            "USD", 83.61,
            "EUR", 98.15,
            "BYN", 27.48,
            "GBP", 112.46,
            "RON", 19.35,
            "RUB", 1.0
    );
    private String inputValue;
    private String outputValue;
    private final Scanner scanner;
    private final DecimalFormat round;

    public exchangeRate() {
        this.scanner = new Scanner(System.in);
        this.round = new DecimalFormat("#.####");
        this.inputValue = "0";
        this.outputValue = "0";
    }

    public void sendAvailable() {
        System.out.print("Выберите валютную пару; Доступные валюты: ");
        for(String currency : coefficients.keySet()) {
            System.out.print(currency + ", ");
        }
        System.out.print("\n");
    }

    public void forexEntering() {
        System.out.print("\nИсходная валюта: ");

        while (Objects.equals(inputValue, "0")) {
            inputValue = forexChecking();
        }

        System.out.print("Выводная валюта: ");

        while (Objects.equals(outputValue, "0")) {
            outputValue = forexChecking();
        }

        System.out.println("Вы выбрали пару " + inputValue + " - " + outputValue);


    }

    public String forexChecking() {
        String input = scanner.nextLine().toUpperCase();

        if (!coefficients.containsKey(input)) {
            System.out.println("Валюты нет в массиве! Повторите ввод");
            return "0";
        }
        return input;
    }

    public void valueRate() {
        System.out.print("Введите количество " + inputValue + ": ");
        Double input = Double.valueOf(scanner.nextLine());

        System.out.print("Это равно " + round.format(input * (coefficients.get(inputValue) / coefficients.get(outputValue))) + " " + outputValue);
    }

    public void start() {
        sendAvailable();
        forexEntering();
        valueRate();
    }

    public static void main(String[] args) {
        new exchangeRate().start();
    }
}
