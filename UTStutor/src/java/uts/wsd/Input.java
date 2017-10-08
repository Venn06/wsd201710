package uts.wsd;

import java.util.*;

/**
 * The Input class provides a set of static methods for easily asking the user
 * questions and reading their answers.
 */
public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    private Input() {
    }

    /**
     * Asks the user the given question, waits for the user to enter a string at
     * the keyboard, and then returns this string.
     *
     * @param question the question to be printed
     * @return the string that the user entered as an answer to the question
     */
    public static String askString(String question) {
        System.out.print(question + " ");
        return scanner.nextLine();
    }

    /**
     * Asks the user the given question, waits for the user to enter a integer
     * at the keyboard, and then returns this integer.
     *
     * @param question the question to be printed
     * @return the integer that the user entered as an answer to the question
     */
    public static int askInt(String question) {
        System.out.print(question + " ");
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    /**
     * Asks the user the given question, waits for the user to enter a double at
     * the keyboard, and then returns this double.
     *
     * @param question the question to be printed
     * @return the double that the user entered as an answer to the question
     */
    public static double askDouble(String question) {
        System.out.print(question + " ");
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    /**
     * Asks the user the given question, waits for the user to enter a single
     * character at the keyboard, and then returns this character.
     *
     * @param question the question to be printed
     * @return the character that the user entered as an answer to the question
     */
    public static char askChar(String question) {
        System.out.print(question + " ");
        return scanner.nextLine().charAt(0);
    }

    /**
     * Asks the user the given question, waits for the user to enter a boolean
     * at the keyboard, and then returns this boolean.
     *
     * @param question the question to be printed
     * @return the boolean that the user entered as an answer to the question
     */
    public static boolean askBoolean(String question) {
        System.out.print(question + " ");
        boolean result = scanner.nextBoolean();
        scanner.nextLine();
        return result;
    }
}
