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

/**
 *
 * @author welli
 */
public class DatabaseReader {

    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    public boolean user_found(String name, String user_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");   

            rs = stmt.executeQuery("SELECT name, user_password from equationssystem"); //rs receiving value from querie

            rs.next();//code for the first line of db table
            if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("user_password").equals(user_password))) {
                return true;
            }
            
            while(rs.next()){    //rest of the lines for the db table
                if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("user_password").equals(user_password))) {
                    return true;
                }
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
