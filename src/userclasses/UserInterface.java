/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.SQLException;

/**
 *
 * @author welli
 */
public interface UserInterface {
    
    /**
     * Method allows the user to log into the system if name and password matches
     * @param name
     * @param user_password
     * @return true if correct, false if not
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public boolean user_login(String name, String user_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    /**
     * method to allow the user to register himself into the system
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public boolean register(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    
    /**
     * method to allow the user to make changes on its own info
     * changes are made specifically for the info chosen
     * @param columnToBeChanged
     * @param user_name
     * @param email_address
     * @param old_info
     * @param new_info
     * @return true if success, false if not
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public String update_userinfo(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    
    /**
     * User can review the operation done by all users 
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
}
