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
 */
public interface AdminInterface {
    public boolean admin_login(String name, String admin_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    public ArrayList<UserInterface> access_list() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    public void delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    public boolean admin_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
    
    public String update_admin_info(String columnToBeChanged, String admin_name, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
