/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author welli
 */
public class ImportingUtilities {

    private static final String PASSWORD_PATTERN
            = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Receive user information in String format
     *
     * @param answer
     * @param prompt message to the user
     * @return user input with extra if user does not input anything answer to
     * instruct him
     */
    public String emailValidator(String prompt) {

        Scanner mySc = new Scanner(System.in);

        String email = " ";
        boolean validated = false;
        do {
            System.out.println(prompt);

            email = mySc.next();

            String regex = "^[A-Z0-0._%+-]+@[A-Z0-9.-]+\\/[A-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);//dunno what it does
            boolean matchFound = matcher.find();

            if (matchFound) {
                validated = true;
            } else {
                return ("Please, type your email again. Must contain @ numbers and .");

            }

        } while (!validated);

        return email;

    }

    public String getUserValidInput(String prompt) {
        Scanner myScanner = new Scanner(System.in);

        String input = "", inputToLowerCase;
        do {

            System.out.println(prompt);
            System.out.println("Only type y or n, please: ");
            input = myScanner.nextLine();
            inputToLowerCase = input.toLowerCase();

        } while ((!inputToLowerCase.equals("y")) && (!inputToLowerCase.equals("n")));

        return input;
    }

    public String getUserInput(String prompt) {
        Scanner myScanner = new Scanner(System.in);

        String input = "";
        do {
            System.out.println(prompt + " ");
            System.out.print("Please only type letters: ");
            input = myScanner.nextLine();
            System.out.println("");
        } while (!input.matches("[a-zA-Z ]+"));

        return input;
    }

    /**
     *
     * @param prompt message to the user
     * @param minValue Minimum value allowed
     * @param maxValue MAximum value allowed
     * @return user Integer value
     */
    public int GetUserInt(String prompt, int minValue, int maxValue) {
        Scanner myScanner = new Scanner(System.in);
        int input = -1;
        boolean valid = false;
        do {
            System.out.print(prompt);
            try {
                input = myScanner.nextInt();
                if (input < minValue || input > maxValue) {
                    System.out.println("The number entered does not correspond to the options above. Please, try again:");
                } else {

                    valid = true;
                }
            } catch (Exception e) {
                System.out.println("The value entered is not an integer. Please type only 1 or 2");
                myScanner.next();
            }
        } while (!valid);
        System.out.println("");
        return (input);
    }

    /**
     * Get the user to type an integer value if mistaken it is asked once again.
     *
     * @param prompt Message to user
     * @param minValue Minimum value created as a parameter
     * @return Answer for the user
     */
    public int getUserMinNumber(String prompt, int minValue) {
        boolean valid = false;
        int value = -1;
        Scanner myScanner = new Scanner(System.in);
        do {
            System.out.println(prompt);
            try {
                System.out.println("Type a number bigger or iqual than the minimun requires");
                value = myScanner.nextInt();

                if (value < minValue) {
                    System.out.println("You must type a number iqual or bigger than the minimun value.");

                } else {
                    valid = true;
                }
            } catch (Exception e) {
                System.out.println("The value entered must be an integer.");
            }
        } while (!valid);

        return (value);
    }

    int num;
    String prompt;

    public void line(int num, String prompt) {
        this.num = num;
        this.prompt = prompt;
    }
    
      /**
     * Get the user to type a equation if mistaken it is asked once again.
     * @param prompt Message to user
     * @return input, user equation
     */
    public String getUserEquation(String prompt) {
    Scanner myScanner = new Scanner(System.in);

        String input = "";
        do {
            System.out.println(prompt + " ");
            //System.out.print("Please only type x, y,+,- and numbers : ");
            input = myScanner.nextLine();

        } while (!input.matches("[a-z0-9+-= ]+"));

        return input;
    }

    public static void main(String[] args) {
        ImportingUtilities myNew = new ImportingUtilities();
        myNew.line(30, "-");
    }
    

}
