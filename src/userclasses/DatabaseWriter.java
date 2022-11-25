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
            stmt.execute("USE equationssystem;");

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS " + dbName + "("
                    + "`id` INT(100) NOT NULL AUTO_INCREMENT,"        
                    + "`name` VARCHAR(25),"
                    + "`surname` VARCHAR(25),"
                    + "`phoneNumber` VARCHAR(10),"
                    + "`userPassword` VARCHAR(20),"
                    + "PRIMARY KEY(`id`)"        
                    + ")");
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public boolean insertUserIntoDB(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("USE equationssystem;");
            stmt.execute(
                String.format("INSERT INTO equationssystem (name, surname, phoneNumber, userPassword) "
                    + "VALUES (\"%s\", \"%s\", \"%d\", \"%s\");",
                       user.name, user.surname, user.phoneNumber, user.userPassword)
                );
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
    
      public boolean updateUserInfo(String columnToBeChanged, String newInfo, int userID) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("UPDATE equationssystem;");
            stmt.execute("SET " + columnToBeChanged + "=" + newInfo +  "WHERE name = " + userID + " ");
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
  
}
