/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author welli
 * The administrator interface contains all methods that an administrator is allowed to do on this system
 */
public interface AdminInterface {
    /**
     * This method is used as the user chose to log in as an administrator
     * @param name
     * @param admin_password
     * @return a list of things an administrator can do within the system
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public boolean admin_login(String name, String admin_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    /** This method presents the user with a list of all users inserted into the databases
     * @return an array list of all users in the databases
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public ArrayList<UserInterface> access_list() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    /**
     * This method deletes an user from the databases
     * @param id The administrator have access to all users id, for this reason the id is the parameter for this method
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public void delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    
    /**
     * This method contains a list with all the equations that had been previously recorded on the the databases
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    /**
     * this method is called to construct an administrator databases
     * @return true if db successfully created, false if not
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    public boolean admin_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    /**
     * method is called to update an information from the administrator
     * @param columnToBeChanged
     * @param admin_name
     * @param old_info
     * @param new_info
     * @return the same administrator with the information chosen by the administrator updated
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public String update_admin_info(String columnToBeChanged, String admin_name, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
