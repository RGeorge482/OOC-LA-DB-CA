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
            e.printStackTrace();
            return false;
        }

    }

    public boolean two_var_equation_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String ADMIN_DB_NAME = "two_var_equations";//need a constructor and than pass a Admin type as par to insert things into db

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //this part of the code is creating a connection between the db and this user and password
            Statement stmt = conn.createStatement();
            //THIS METHOD IS USED TO DO THE QUERYES
            stmt.execute("USE equationssystem;");

            stmt.execute(//if admin table does not exists create it
                    "CREATE TABLE IF NOT EXISTS " + ADMIN_DB_NAME + "("
                    + "`id` INT(100) NOT NULL AUTO_INCREMENT,"
                   // + "`equation_final_result` TEXT(100)," 
                    + "`first_equation` TEXT(100),"
                    + "`second_equation` TEXT(100),"
                    + "`equation_final_result` TEXT(100),"
                    + "PRIMARY KEY(`id`)"
                    + ")");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public boolean three_var_equation_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String ADMIN_DB_NAME = "three_var_equations";//need a constructor and than pass a Admin type as par to insert things into db

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //this part of the code is creating a connection between the db and this user and password
            Statement stmt = conn.createStatement();
            //THIS METHOD IS USED TO DO THE QUERYES
            stmt.execute("USE equationssystem;");

            stmt.execute(//if admin table does not exists create it
                    "CREATE TABLE IF NOT EXISTS " + ADMIN_DB_NAME + "("
                    + "`id` INT(100) NOT NULL AUTO_INCREMENT,"
                    + "`first_equation` TEXT(100),"
                    + "`second_equation` TEXT(100),"
                    + "`third_equation` TEXT(100),"
                    + "`equation_final_result` TEXT(100),"
                    + "PRIMARY KEY(`id`)"
                    + ")");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
       public boolean save_two_var_equation(String first_equation, String second_equation, String equation_result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("USE equationssystem;");
            stmt.execute(
                    String.format("INSERT INTO two_var_equations (first_equation, second_equation, equation_final_result) "
                            + "VALUES (\"%s\", \"%s\", \"%s\");",
                            first_equation, second_equation, equation_result)
            );
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
       
    public boolean save_thee_var_equation(String first_equation, String second_equation, String third_equation, String equation_result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("USE equationssystem;");
            stmt.execute(
                    String.format("INSERT INTO three_var_equations (first_equation, second_equation, third_equation, equation_final_result) "
                            + "VALUES (\"%s\",\"%s\",\"%s\",\"%s\");",
                            first_equation, second_equation, third_equation, equation_result)
            );
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
