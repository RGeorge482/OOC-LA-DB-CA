/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import enumspackage.UserData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.ImportingUtilities;
import SolveEquations.Solve2Equations;
import SolveEquations.Solve3Equations;
import enumspackage.AdminInfo;

/**
 *
 * @author welli This class is where every other class talks to each other
 */
public class UserController {

    private Administrator admin; //CREATING THIS ATTRIBUTES SO I CAN ACCESS THOSE METHODS
    private User user;
    private HeaderClass headers;
    private DatabaseWriter data_output;
    private boolean inDev = true;
    private UserData user_data;

    public UserController(Administrator admin, User user, HeaderClass headers, DatabaseWriter data_output, UserData user_data) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.headers = headers;
        this.user = user;
        this.admin = admin;
        this.data_output = data_output;
        this.user_data = user_data;
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Scanner mySc = new Scanner(System.in);

        data_output.database_setup();//SET UP SCHEMA
        admin.admin_datadb_setup();//SET UP ADMIN TABLE
        user.create_user_table();//SET UP USER TABLE
        data_output.three_var_equation_datadb_setup();
        data_output.two_var_equation_datadb_setup();

        ArrayList<UserInterface> users = new ArrayList<>(); // CREATING A NEW ARRAY LIST TO RECEIVE REGISTERED USERS

        ImportingUtilities myUt = new ImportingUtilities();//Utilities to validate all the inputs

        Solve2Equations sloveTwoEqu = new Solve2Equations();

        Solve3Equations sloveThreeEqu = new Solve3Equations();

        boolean valid = false;
        String programHault = "";
        int accessMenuUserChoice, userMenuChoice = -1, adminMenuChoice = -1, userSignLogin, adminInput;

        do {
            headers.access_menu();//USER OR ADMIN
            accessMenuUserChoice = myUt.Get_user_int("Type one of the options above: ", 1, 2);//GIVE THE USER ADMIN OR USER OPTIONS
            System.out.println("");
            switch (accessMenuUserChoice) {
                case 1://CASE USER SELECTS USER OPTION
                    headers.user_login();
                    userSignLogin = myUt.Get_user_int("Please type one of the options above: ", 1, 2);
                    System.out.println("");
                    switch (userSignLogin) {
                        case 1://USER SELECTS REGISTER 
                            String emailAddress="";
                            valid = false;
                            do{
                            System.out.println("Please, type your email address");
                            emailAddress = mySc.next();
                            if(myUt.email_validator(emailAddress)) valid = true;
                            else System.out.println("Try an email containing '@' and a domain such as 'gmail.com'");
                            System.out.println("");
                            }while(!valid);//loop breaks if user email address is valid
                            System.out.println("");
                            String name = myUt.get_user_input("Please, type your first name: ");
                            System.out.println("");
                            String surname = myUt.get_user_input("Please, type your surname: ");
                            System.out.println("");
                            int phoneNumber = myUt.Get_user_int("Please, type your phone number. Ex: 1234567 ", 00000000, 99999999);
                            System.out.println("");
                            System.out.print("Please type a password: ");
                            String userPassword = mySc.next();

                            user = new User(name, surname, phoneNumber, userPassword, emailAddress);

                            users.add(user);

                            user.register(user);//user registered
                            
                            System.out.println("User registered successfully!");
                            break;

                        case 2://USER SELECTS LOGIN 
                            String user_name_login = myUt.get_user_input("Please type your first name: ");
                            System.out.print("Please, type your password: ");
                            String user_password_login = mySc.nextLine();

                            boolean userExists = (user.user_login(user_name_login, user_password_login)); //method returns true if user exists and pass is correct

                            if (userExists) {
                                //if user exists maybe is a good idea to return an array list with all the info from this specific user 
                                headers.user_menu_options();
                                int loggedUserChoice = myUt.Get_user_int("Please type one of the options above: ", 1, 4);
                                System.out.println("");

                                switch (loggedUserChoice) {
                                    case 1://user wants to chenge info
                                        for (UserData value : UserData.values()) {
                                            System.out.println(value);
                                        }
                                        System.out.println("");

                                        String user_info_to_be_changed = myUt.get_user_input("Please type the info you would like to change -- ps. name");

                                        String dataToBeChangedLowerCase = user_info_to_be_changed.toLowerCase().replaceAll("\\s+", ""); //also removes all spaces

                                        switch (dataToBeChangedLowerCase) {
                                            case "name":
                                                String validation_user_name_case_change_name = myUt.get_user_input("Type your name: ");
                                                System.out.println("Type your email address: ");//both info to check if user exists
                                                String email_address_validation_case_change_name = mySc.next();
                                                String new_name = myUt.get_user_input("Type your new name: ");//type old info 
                                                System.out.println(user.update_userinfo("name", validation_user_name_case_change_name, email_address_validation_case_change_name, validation_user_name_case_change_name, new_name));
                                                break;
                                            case "surname":
                                                String validation_user_name_case_change_surname = myUt.get_user_input("Type your name: ");
                                                System.out.println("Type your email address: ");//both info to check if user exists
                                                String email_address_validation_case_change_surname = mySc.next();
                                                String old_surname = myUt.get_user_input("Type your old surname");
                                                String new_surname = myUt.get_user_input("Type your new surname: ");//type old info 
                                                System.out.println(user.update_userinfo("surname", validation_user_name_case_change_surname, email_address_validation_case_change_surname, old_surname, new_surname));
                                                break;
                                            case "phonenumber":
                                                String validation_user_name_case_change_phonenum = myUt.get_user_input("Type your name: ");
                                                System.out.println("Type your email address: ");//both info to check if user exists
                                                String email_address_validation_case_change_phonenum = mySc.next();
                                                int oldPhoneNumInt = myUt.Get_user_int("Type your old phone number: ", 0000000, 9999999);
                                                String oldPhoneNumString = String.valueOf(oldPhoneNumInt);
                                                int newPhoneNumInt = myUt.Get_user_int("Type your new phone number: ", 0000000, 9999999);
                                                String newPhoneNumString = String.valueOf(newPhoneNumInt);
                                                System.out.println(user.update_userinfo("phone_number", validation_user_name_case_change_phonenum, email_address_validation_case_change_phonenum, oldPhoneNumString, newPhoneNumString));
                                                break;
                                            case "password":
                                                String validation_user_name_case_change_password = myUt.get_user_input("Type your name: ");
                                                System.out.println("Type your email address: ");//both info to check if user exists
                                                String email_address_validation_case_change_password = mySc.next();
                                                System.out.println("");
                                                System.out.println("Type your old password");
                                                String old_user_password = mySc.next();
                                                System.out.println("Type your new password");
                                                String new_user_password = mySc.next();
                                                System.out.println(user.update_userinfo("user_password", validation_user_name_case_change_password, email_address_validation_case_change_password, old_user_password, new_user_password));
                                                break;
                                            case "email":
                                                String validation_user_name_case_change_email = myUt.get_user_input("Type your name: ");
                                                System.out.println("Type your email address: ");
                                                String email_address_validation_case_change_email = mySc.next();
                                                System.out.println("");
                                                System.out.println("Type your new email address: ");
                                                String new_email_address = mySc.next();
                                                System.out.println(user.update_userinfo("email_address", validation_user_name_case_change_email, email_address_validation_case_change_email, email_address_validation_case_change_email, new_email_address));
                                                break;
                                            default:
                                                System.out.println("The attribute you want to change was not found.");
                                                break;
                                        }
                                        break;
                                    case 2: // user want to solve equations with two variables
                                        System.out.println("Please enter the equations in format: x+y=2");
                                        String firstEquation = myUt.get_user_equation("Please enter first equation: ");
                                        String secondEquation = myUt.get_user_equation("Please enter second equation: ");
                                        //if we decide to have the initial equation in the databases we can work with this values above
                                        System.out.println("The equations are: " + firstEquation + " and " + secondEquation);
                                        String result_two_equation = sloveTwoEqu.twoVariableEquation(firstEquation, secondEquation); // print the result
                                        System.out.println(result_two_equation);
                                        data_output.save_two_var_equation(firstEquation, secondEquation, result_two_equation);
                                        break;                                 

                                    case 3: // user want to solve equations with three variables
                                        System.out.println("Please enter the equations in format: x+y+z=1");
                                        String firstEquation3 = myUt.get_user_equation("Please enter first equation: ");
                                        String secondEquation3 = myUt.get_user_equation("Please enter second equation: ");
                                        String thirdEquation = myUt.get_user_equation("Please enter third equation: ");
                                        System.out.println("The equations are: " + firstEquation3 + " and " + secondEquation3 + " and " + thirdEquation);
                                        System.out.println(sloveThreeEqu.threeVariableEquation(firstEquation3, secondEquation3, thirdEquation)); // print the result 
                                        String result_three_equation = sloveThreeEqu.threeVariableEquation(firstEquation3, secondEquation3, thirdEquation); // print the result                                                                        
                                        data_output.save_thee_var_equation(firstEquation3, secondEquation3, thirdEquation, result_three_equation);
                                        //System.out.println("My result is: " + result_three_equation);
                                        break;                                   
                                    case 4:
                                        user.review_operations();
                                        break;
                                    default:
                                        System.out.println("Option not available. Try again.");
                                        break;
                                }
                                break;
                            } else {
                                System.out.println("Your details seems to be incorrect, please try again!");
                            }
                            break;
                    }
                    break;//user menu choice break

                case 2: //CASE USER IS ADMIN
                    String admin_name_login = myUt.get_user_input("Please type your administrator name: ");
                    System.out.print("Please, type your password: ");
                    String admin_password_login = mySc.nextLine();

                    boolean admin_exists = admin.admin_login(admin_name_login, admin_password_login);

                    if (admin_exists) {
                        System.out.println("");
                        headers.admin_menu_options();
                        System.out.println("");
                        adminInput = myUt.Get_user_int("Please type one of the options above: ", 1, 4);

                        switch (adminInput) {
                            case 1:
                                for (AdminInfo info : AdminInfo.values()) {
                                    System.out.println(info);
                                }
                                System.out.println("");

                                String admin_info_to_be_changed = myUt.get_user_input("Please type the info you would like to change -- ps. name");
                                String admin_info_to_be_changed_to_lower_case = admin_info_to_be_changed.toLowerCase().replaceAll("\\s+", "");
                                switch (admin_info_to_be_changed_to_lower_case) {
                                    case "name":
                                        String old_admin_name = myUt.get_user_input("Type your old administrator name: ");
                                        String new_admin_name = myUt.get_user_input("Type your new administrator name: ");
                                        System.out.println(admin.update_admin_info("admin_name", old_admin_name, old_admin_name, new_admin_name));
                                        System.out.println("");
                                        break;
                                    case "password":
                                        String admin_name_case_password = myUt.get_user_input("Type your administrator name: ");
                                        String old_admin_password = myUt.get_user_input("Type your old password: ");
                                        String new_admin_password = myUt.get_user_input("Type your new password: ");
                                        System.out.println(admin.update_admin_info("admin_password", admin_name_case_password, old_admin_password, new_admin_password));
                                        System.out.println("");
                                        break;
                                    case "emailaddress":
                                        String admin_name_case_emailaddress = myUt.get_user_input("Type your administrator name: ");
                                        System.out.println("Type your old email address");
                                        String old_admin_email_address = mySc.next();
                                        System.out.println("Type your new email address");
                                        String new_admin_email_address = mySc.next();
                                        System.out.println(admin.update_admin_info("email_address", admin_name_case_emailaddress, old_admin_email_address, new_admin_email_address));
                                        System.out.println("");
                                        break;
                                }
                                break;
                            case 2://ADMIN CHOSE VIEW LIST OF USERS
                                System.out.println(users = admin.access_list());
                                break;
                            case 3:
                                int deleteUserByID = myUt.Get_user_int("Type the ID for the user you wish to exclude: ", 1, 100);
                                admin.delete(deleteUserByID);
                                break;
                            case 4:
                                admin.review_operations();
                                break;
                        }
                        break;
                    } else {
                        System.out.println("Your details are incorrect");
                    }

            }
            System.out.println("");
            programHault = myUt.get_user_valid_input("Would you like to return to the main menu? [y] or [n]");
        } while (programHault.equals("y"));//LOOP FOR THE USER TO RETURN TO MAIN HEADER
    }
}
