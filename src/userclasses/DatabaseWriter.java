/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author welli
 */
public class DatabaseWriter {

    //MAKING A CONNECTION WITH THE DATABASE
    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    public boolean databaseSetup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//THIS IS LOADING THE DRIVER INTO OUR PROGRAM
        //MAKING A CLASS FROM FILE AND MAKING A NEW INSTANCE FROM THIS CLASS

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //this part of the code is creating a connection between the db and this user and password
            Statement stmt = conn.createStatement();
            //THIS METHOD IS USED TO DO THE QUERYES
            stmt.execute("CREATE SCHEMA IF NOT EXISTS equationssystem");//USING METHOD ABOVE TO EXECUTE THE QUERIE ONE BY ONE
            
            return true;

        } catch (SQLException e) {
            return false;
        }

    }
}
