package bullscows;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get user input of number of requested digits to
        // appear in the secret code and possible symbols.

        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        String digit = scanner.next();
        for (int i = 0; i < digit.length(); i++) {
            if (!"0123456789".contains(Character.toString(digit.charAt(i)))) {
                System.out.println("Error: " + digit + "  isn't a valid number.");
                System.exit(1);
            }
        }

        // Check that digit length <= 36
        int digits = Integer.parseInt(digit);
        if (digits > 36 || digits < 1) {
            System.out.print("Error: can't generate a secret number with a length of " + digits + " because there aren't enough unique digits.");
            System.exit(1);
        }
        System.out.println("Input the number of possible symbols in the code:");
        String stringNumberOfSymbols = scanner.next();
        if (Integer.parseInt(stringNumberOfSymbols) > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        }
        int numberOfSymbols = Integer.parseInt(stringNumberOfSymbols);
        String allSymbols = "0123456789abcdefghijklmnopqrstuvwxyz ";
        String usedSymbols = allSymbols.substring(0, numberOfSymbols - 1);
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < digits; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-");
        if (numberOfSymbols < 11) {
            System.out.println(numberOfSymbols - 1 + ").");
        } else if (numberOfSymbols == 11) {
            System.out.print("9, a).");
        } else {
            System.out.println("9, a-" + allSymbols.charAt(numberOfSymbols - 1) + ").");
        }
        // Check to see whether the number of symbols is less than the length of code
        if (usedSymbols.length() < digits) {
            System.out.println("Error: There are not enough symbols for code of length " + digits + "." );
            System.exit(1);
        }

        // Prepare secret code
        System.out.println("Okay, let's start a game!");

        String stringResult = "";
        StringBuilder stringBuilderResult = new StringBuilder(stringResult);

        // Build up a string of symbols from usedSymbols
        do {
            Random randomIndex = new Random();
            char randomSymbol = usedSymbols.charAt(randomIndex.nextInt(usedSymbols.length()));
            String newDigit = String.valueOf(randomSymbol);
            if (!stringResult.contains(newDigit)) {
                stringBuilderResult.append(newDigit);
                stringResult = stringBuilderResult.toString();
            }
        } while (stringBuilderResult.length() < digits);

        var secretCode = stringResult;
        int gameCounter = 0;
        boolean solved = false;

        while (!solved) {
            System.out.println("Turn " + ++gameCounter + ":");
            String userInput = scanner.next();
            // Check validity of user input length
            if (userInput.length() != secretCode.length()) {
                System.out.print("error: User guess of " + userInput.length());
                System.out.println(" does not equal " + secretCode.length() + ".");
                System.exit(1);
            }
            // Check to see if there are invalid symbols in the user input
            for (int i = 0; i < userInput.length(); i++) {
                var userInputChar = userInput.charAt(i);
                String userInputLetter = Character.toString(userInputChar);
                if (!usedSymbols.contains(userInputLetter)) {
                    System.out.print("error: User guess has invalid symbol ");
                    System.out.println(userInputLetter + ".");
                    System.exit(1);
                }
            }

                if (userInput.equals(secretCode)) {
                solved = true;
            }
            int bullCount = 0;
            int cowCount = 0;

            for (int i = 0; i < secretCode.length(); i++) {
                String secretDigit = secretCode.substring(i, i + 1);
//                System.out.println("secret digit: " + secretDigit);
                String guessDigit = userInput.substring(i, i + 1);
//                System.out.println("guess digit: " + guessDigit);
                if (secretDigit.equals(guessDigit)) {
                    bullCount++;
                } else if (userInput.contains(secretDigit)) {
                    cowCount++;
                }

            }
            // Grade the input
            System.out.print("Grade: ");
            if (bullCount > 0) {
                System.out.print(bullCount + " bull(s)");
            }
            if (bullCount > 0 && cowCount > 0) {
                System.out.print(" and ");
            }
            if (cowCount > 0) {
                System.out.print(cowCount + " cow(s)");
            }
            if (cowCount > 0 || bullCount > 0) {
                System.out.print(". ");
            }
            if (cowCount == 0 && bullCount == 0) {
                System.out.print("None. ");
            }
            System.out.println();
            if (solved) {
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }
    }
}

