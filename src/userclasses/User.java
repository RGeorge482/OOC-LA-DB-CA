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
    protected int phone_number;
    protected String user_password;
    protected String email_address;
    
   /**
     * Constructor is called once method register is activated
     * @param name
     * @param surname
     * @param phone_number
     * @param user_password
     * @param email_address
     */
    public User(String name, String surname, int phone_number, String user_password, String email_address) {//one constructor for export data to db
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }
    
    public User(int id, String name, String surname, int phone_number, String user_password, String email_address){//constructor to import data from db with id
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }
    
    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }
    protected ArrayList<UserInterface> users;

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
        return phone_number;
    }

    public void setPhoneNumber(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone_number=" + phone_number + '}' + "\n";
    }
    
    
    
}
