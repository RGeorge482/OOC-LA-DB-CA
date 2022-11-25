/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author welli
 */
public class DatabaseReader {

    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    //method returns an array of users
    public ArrayList<UserInterface> inputData() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ArrayList<UserInterface> users = new ArrayList<>();

        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT * from equationssystem"); //rs receiving value from querie

            while (rs.next()) {//loop to get info from the whole databases
                users.add(new User(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("phoneNumber"),
                        rs.getString("userPassword")
                ));
            }

        } catch (SQLException e) {
            return null;
        }
        return users;//RETURNED USERS
    }

    public boolean userFound(String name, String userPassword) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        HashMap<String, String> userNamePassword = new HashMap<>();//Creating the hashmap to receive password and user name

        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");   

            rs = stmt.executeQuery("SELECT name, userPassword from equationssystem"); //rs receiving value from querie

            rs.next();
            if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("userPassword").equals(userPassword))) {
                return true;
            }
            
            while(rs.next()){    
                if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("userPassword").equals(userPassword))) {
                    userNamePassword.put(rs.getString("name"), userPassword);
                    return true;
                }
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    
    
    
    
}
