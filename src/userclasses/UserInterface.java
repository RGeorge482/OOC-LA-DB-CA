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

    public boolean logIn(String name, String user_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

    public boolean register(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

    public String change_info(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
}
