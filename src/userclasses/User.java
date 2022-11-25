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
public class User implements UserInterface {
    
    protected int id;
    protected String name;
    protected String surname;
    protected int phoneNumber;
    protected String userPassword;
    protected ArrayList<UserInterface> users;
    
    //I HAVE THE ARRAY LIST OF USERS IN HERE BECAUSE EVERY TIME I ADD ONE USER IT SHOULD BE ADDED TO THIS LIST
    
    /**
     * Constructor is called once method register is activated
     * @param name
     * @param surname
     * @param phoneNumber
     * @param userPassword
     */
    public User(String name, String surname, int phoneNumber, String userPassword) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
    }

    @Override
    public boolean logIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String solveEquationTwoVariables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String solveEquationThreeVariables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String seeEquations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ArrayList<UserInterface> getUsers() {
        return users;
    }
    
    public void setUsers(ArrayList<UserInterface> users) {
        this.users = users;
    }

    /*@Override
    public ArrayList<UserInterface> signIn(int id) {
        //Creating an array list of users
        ArrayList<UserInterface> usersList = new ArrayList<>();
        //instantiating a new user object 
        User newUser = new User(id);
        //adding to array
        usersList.add(newUser);
        
        newUser.setUsers(usersList);
        
        return usersList;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    
    
    //MAYBE SET USERS HAS TO BE APPLIED AFTER USER IS ADDED TO THE LIST IN ORDER TO HAVE ACCESS TO IT
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + '}' + "\n";
    }
/*
    public HashMap<User, String> setPassword(String userName, String Password) {
        HashMap<String, String> setPassword = new HashMap<>();
       
        
    }
  */  
    
    
    
    
}
