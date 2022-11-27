/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    + "`phone_number` VARCHAR(10),"
                    + "`user_password` VARCHAR(20),"
                    + "`email_address` VARCHAR (50),"
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
                    String.format("INSERT INTO equationssystem (name, surname, phone_number, user_password, email_address) "
                            + "VALUES (\"%s\", \"%s\", \"%d\", \"%s\", \"%s\");",
                            user.name, user.surname, user.phone_number, user.user_password, user.email_address)
            );
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateUserInfo(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ResultSet rs;
        boolean userExists;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            //IF USER NAME AND EMAIL ADDRESS MATCHES THAN WE ALLOW CHANGES 
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT name, email_address FROM equationssystem");

            rs.next();//code below is for the first line in db
            //user needs to be in the databases matching email address and name so they can make changes
            if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {

            } else {
                System.out.println("Please, try again with a different name or email address");
                return false;//stops the program
            }
            while (rs.next()) {//code for the rest of the lines in db
                if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {

                } else {

                    System.out.println("Please, try again with a different name or email address");
                    return false;
                }
            }
            String email = "email_address";
            
            stmt.execute("USE equationssystem;");
            stmt.executeUpdate("UPDATE equationssystem SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String sql = "DELETE FROM equationssystem WHERE id = ?";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute("USE equationssystem;");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
