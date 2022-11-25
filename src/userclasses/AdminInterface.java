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
public interface AdminInterface {
    /*
    The modify method allows the admin to modify any information they want
    not sure what it returs yet
    */
    public void modify();
    
    
    /*
    Access List returns a list of all users within the databases
    */
    //public ArrayList<User> accessList();
    /*
    Method is called when admin wants to remove user from the list of user
    returns the list without user removed
    
    PS. It could be a nice idea to create a list of users removed from this list
    */
    public ArrayList<User> removeUser();
    
    /*
    Not sure yet how to review operations
    The return value could be changed in the future
    */
    public void reviewOperations();
    
}
