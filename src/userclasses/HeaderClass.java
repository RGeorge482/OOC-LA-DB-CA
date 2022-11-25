/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

/**
 *
 * @author welli
 */
public class HeaderClass {
    public void userMenuOptions(){
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("   You are logged in as a user");
        System.out.println("------------------------------------");
        System.out.println("[1] Modify Your Profile");
        System.out.println("[2] Solve Equation of 2 Variables");
        System.out.println("[3] Solve Equation of 3 Variables");
        System.out.println("[4] Save Equation");
        System.out.println("[5] See Equation");
        System.out.println("------------------------------------");
        System.out.println("");
    }
    
    public void adminMenuOptions(){
        System.out.println("------------------------------------");
        System.out.println("You are logged in as an administrator");
        System.out.println("------------------------------------");
        System.out.println("[1] Modify Your Profile");
        System.out.println("[2] See the list of all users");
        System.out.println("[3] Remove User From the System");
        System.out.println("[4] Review Operation");
        System.out.println("-----------------------------------");
    }
    
    public void accessMenu(){
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("\tSelect an option:");
        System.out.println("------------------------------------");
        System.out.println("[1] I am an User");
        System.out.println("[2] I am an Administrator");
        System.out.println("");
    }
    
    public void userLogin(){
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("\t\tUser");
        System.out.println("------------------------------------");
        System.out.println("[1] Register");
        System.out.println("[2] Log In");
        System.out.println("");
    }
}
