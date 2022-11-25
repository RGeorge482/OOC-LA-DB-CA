/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.ImportingUtilities;

/**
 *
 * @author welli
 */
public class UserController {

    private Administrator admin; //CREATING THIS ATTRIBUTES SO I CAN ACCESS THOSE METHODS
    private User user;
    private HeaderClass headers;
    private DatabaseWriter dataOutput;
    private DatabaseReader dataInput;
    private boolean inDev = true;

    /*
    This class is where every other class talks to each other
     */
    public UserController(Administrator admin, User user, HeaderClass headers, DatabaseWriter dataOutput, DatabaseReader dataInput) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.headers = headers;
        this.user = user;
        this.admin = admin;
        this.dataOutput = dataOutput;
        this.dataInput = dataInput;

        Scanner mySc = new Scanner(System.in);

        System.out.println("");

        dataOutput.databaseSetup();//RETURNS TRUE IF WORKS

        ArrayList<UserInterface> users = new ArrayList<>(); // CREATING A NEW ARRAY LIST TO RECEIVE REGISTERED USERS

        ImportingUtilities myUt = new ImportingUtilities();//Utilities to validate all the inputs

        boolean valid = false;
        String programHault = "";
        int accessMenuUserChoice, userMenuChoice = -1, adminMenuChoice = -1, userSignLogin, adminInput;

        do {
            headers.accessMenu();//USER OR ADMIN
            accessMenuUserChoice = myUt.GetUserInt("Type one of the options above: ", 1, 2);//GIVE THE USER ADMIN OR USER OPTIONS
            switch (accessMenuUserChoice) {
                case 1://CASE USER SELECTS USER OPTION
                    headers.userLogin();
                    userSignLogin = myUt.GetUserInt("Please type one of the options above: ", 1, 2);
                    System.out.println("");
                    switch (userSignLogin) {
                        case 1://USER SELECTS REGISTER 
                            int id = 1;

                            String name = myUt.getUserInput("Please, type your first name: ");
                            System.out.println("");
                            String surname = myUt.getUserInput("Please, type your surname: ");
                            System.out.println("");
                            int phoneNumber = myUt.GetUserInt("Please, type your phone number: ", 00000000, 99999999);
                            System.out.println("");
                            System.out.print("Please type a password: ");
                            String userPassword = mySc.next();

                            user = new User(name, surname, phoneNumber, userPassword);

                            users.add(user);

                            dataOutput.insertUserIntoDB(user);//METHOD TO PUSH INFO TO DB

                            break;

                        case 2://USER SELECTS LOGIN 
                            String userNameLogin = myUt.getUserInput("Please type your first name: ");

                            System.out.print("Please, type your password: ");
                            String userPasswordLogin = mySc.nextLine();

                            boolean userExists = (dataInput.userFound(userNameLogin, userPasswordLogin)); //method returns true if user exists and pass is correct

                            if (userExists) {
                                //if user exists maybe is a good idea to return an array list with all the info from this specific user 
                                headers.userMenuOptions();
                                int loggedUserChoice = myUt.GetUserInt("Please type one of the options above: ", 1, 4);
                                switch (loggedUserChoice) {
                                    case 1://user wants to chenge info
                                        String dataToBeChanged = myUt.getUserInput("Please type the info you would like to change -- ps. name");
                                        String dataToBeChangedLowerCase = dataToBeChanged.toLowerCase();
                                        switch (dataToBeChangedLowerCase) {
                                            case "name":
                                                String newName = myUt.getUserInput("Type the new name: ");
                                                int idUser = myUt.GetUserInt("Please type id", 1, 100);
                                                dataOutput.updateUserInfo(dataToBeChangedLowerCase, newName, idUser);
                                                break;
                                        }

                                        String loggedUserUpdatedInfo = myUt.getUserInput("Please, type the new value: ");

                                }
                            } else {
                                System.out.println("Your details seems to be incorrect, please try again!");
                            }
                            break;
                    }
                    break;

                case 2: //CASE USER IS ADMIN
                    headers.adminMenuOptions();

                    adminInput = myUt.GetUserInt("Please type one of the options above: ", 1, 4);

                    switch (adminInput) {
                        case 1:
                            break;
                        case 2://ADMIN CHOSE VIEW LIST OF USERS
                            System.out.println(users = dataInput.inputData());
                            break;
                    }

            }
            programHault = myUt.getUserValidInput("Would you like to return to the main menu? [y] or [n]");
        } while (programHault.equals("y"));//LOOP FOR THE USER TO RETURN TO MAIN HEADER
    }
}