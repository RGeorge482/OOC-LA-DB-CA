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
public class Administrator implements AdminInterface{
    //Some of this attributes could go to the db
    private String name;
    private String surname;
    private int id;
    private int phone_number;
    private String email_address;
    
    
    //THESE METHODS ARE ALLOWED ONCE THE ADMIN IS LOGGED IN 
    
    /*@Override
    public ArrayList<User> accessList() {
        return 
    }*/

    @Override
    public ArrayList<User> removeUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reviewOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //ACCESSORS
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String emailAddress) {
        this.email_address = emailAddress;
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
