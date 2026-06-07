import java.util.Scanner;

public class TemperatureConverter {

    public static double convertTemperature(double temperature, String unit) {
        if (unit.equalsIgnoreCase("C")) {
            return (temperature * 9.0 / 5.0) + 32.0;
        } else {
            return (temperature - 32.0) * 5.0 / 9.0;
        }
    }

    private static boolean isValidNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int start = 0;
        boolean hasDecimal = false;

        if (s.charAt(0) == '-') {
            if (s.length() == 1) {
                return false;
            }
            start = 1;
        }

        boolean validSoFar = true;
        int i = start;
        while (validSoFar && i < s.length()) {
            char c = s.charAt(i);
            if (c == '.') {
                if (hasDecimal) {
                    validSoFar = false;
                } else {
                    hasDecimal = true;
                }
            } else if (c < '0' || c > '9') {
                validSoFar = false;
            }
            i++;
        }
        return validSoFar;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char degree = '\u00B0';
        boolean running = true;

        while (running) {
            System.out.print("Enter a temperature (or type 'stop' to quit): ");
            String tempInput = scan.nextLine().trim();

            if (tempInput.equalsIgnoreCase("stop")) {
                running = false;
            } else if (!isValidNumber(tempInput)) {
                System.out.println("Invalid input: please enter a numeric temperature value.");
            } else {
                double temperature = Double.parseDouble(tempInput);

                System.out.print("Enter unit (C or F): ");
                String unit = scan.nextLine().trim().toUpperCase();

                if (!unit.equals("C") && !unit.equals("F")) {
                    System.out.println("Invalid unit: please enter C (Celsius) or F (Fahrenheit).");
                } else {
                    double converted = convertTemperature(temperature, unit);
                    String toUnit = unit.equals("C") ? "F" : "C";

                    System.out.printf("%.2f%s%s is equal to %.2f%s%s%n",
                            temperature, degree, unit,
                            converted, degree, toUnit);
                }
            }
        }

        System.out.println("Goodbye!");
        scan.close();
    }
}