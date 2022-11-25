/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.util.ArrayList;

/**
 *
 * @author welli
 */
public interface UserInterface {
    
    /*
    Method that allow user to log in
    returns true if password and user name matches
    */
    //public ArrayList<UserInterface> signIn(int id);
    
    public boolean logIn();
    
    /*
    Try to set a hashmap with user and password
    */
    //public HashMap<User, String> setPassword();
    
    /*
    Within this method the user is allowed to modify their profile
    */
    public void modify();
    
    /*
    So far the result of the equation is returned as a string
    */
    public String solveEquationTwoVariables();
    
    /*
    Methos called to solve equation of e variable
    so far it just returns string
    */
    public String solveEquationThreeVariables();
   
    /**
     * so far the equations are returned as string so i will keep it that way for now
     * @return equations as string
     */
    public String seeEquations();
    
    /**
     *
     * @return
     */
    public ArrayList<UserInterface> getUsers();
    
    
}
