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

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private static Pattern pattern;
    private static Matcher matcher;
    
    public ImportingUtilities(){
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }
    
    public boolean email_validator(String email_address) {
        matcher = pattern.matcher(email_address);
        return matcher.matches(); 
    }

    public String get_user_valid_input(String prompt) {
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

    public String get_user_input(String prompt) {
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
    public int Get_user_int(String prompt, int minValue, int maxValue) {
        Scanner myScanner = new Scanner(System.in);
        int input = -1;
        boolean valid = false;
        do {
            System.out.print(prompt);
            try {
                input = myScanner.nextInt();
                if (input < minValue || input > maxValue) {
                    System.out.println("The number entered does not match requimentes. Please, try again:");
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
    public int get_user_min_number(String prompt, int minValue) {
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
    public String get_user_equation(String prompt) {
    Scanner myScanner = new Scanner(System.in);

        String input = "";
        do {
            System.out.println(prompt + " ");
            //System.out.print("Please only type x, y,+,- and numbers : ");
            input = myScanner.nextLine();

        } while (!input.matches("[a-z0-9+-= ]+"));

        return input;
    }

}
