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
import java.util.ArrayList;

/**
 *
 * @author welli
 */
public class Administrator implements AdminInterface {

    //Some of this attributes could go to the db
    private String name;
    private int id;
    private int phone_number;
    private String email_address;
    private String admin_password;
    ////////////////////////////////////////////////
    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    //THESE METHODS ARE ALLOWED ONCE THE ADMIN IS LOGGED IN 
    //method returns an array of users
    public Administrator(String name, int phone_number, String email_address, String admin_password) {
        this.name = name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.admin_password = admin_password;
    }

    public boolean admin_login(String name, String admin_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT admin_name, admin_password from admin_info"); //rs receiving value from querie

            rs.next();//code for the first line of db table
            if (rs.getString("admin_name").equalsIgnoreCase(name) && (rs.getString("admin_password").equals(admin_password))) {
                return true;
            }

            while (rs.next()) {    //rest of the lines for the db table
                if (rs.getString("admin_name").equalsIgnoreCase(name) && (rs.getString("admin_password").equals(admin_password))) {
                    return true;
                }
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public ArrayList<UserInterface> access_list() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ArrayList<UserInterface> users = new ArrayList<>();

        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT * from user_info"); //rs receiving value from querie

            while (rs.next()) {//loop to get info from the whole databases
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("phone_number"),
                        rs.getString("user_password"),
                        rs.getString("email_address")
                ));
            }

        } catch (SQLException e) {
            return null;
        }
        return users;//RETURNED USERS
    }

    public void delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String sql = "DELETE FROM user_info WHERE id = ?";

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

    @Override
    public void reviewOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean admin_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String ADMIN_DB_NAME = "admin_info";//need a constructor and than pass a Admin type as par to insert things into db

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //this part of the code is creating a connection between the db and this user and password
            Statement stmt = conn.createStatement();
            //THIS METHOD IS USED TO DO THE QUERYES
            stmt.execute("USE equationssystem;");

            stmt.execute(//if admin table does not exists create it
                    "CREATE TABLE IF NOT EXISTS " + ADMIN_DB_NAME + "("
                    + "`id` INT(100) NOT NULL AUTO_INCREMENT,"
                    + "`admin_name` VARCHAR(25),"
                    + "`phone_number` VARCHAR(10),"
                    + "`admin_password` VARCHAR(20),"
                    + "`email_address` VARCHAR (50),"
                    + "PRIMARY KEY(`id`)"
                    + ")");

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    //ACCESSORS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String emailAddress) {
        this.email_address = emailAddress;
    }

    public void change_admin_info() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
