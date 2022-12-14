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

/**
 *
 * @author welli
 */
public class User implements UserInterface {

    // User atributes
    protected int id;
    protected String name;
    protected String surname;
    protected int phone_number;
    protected String user_password;
    protected String email_address;

    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    /**
     * Constructor is called once method register is activated
     *
     * @param name
     * @param surname
     * @param phone_number
     * @param user_password
     * @param email_address
     */
    public User(String name, String surname, int phone_number, String user_password, String email_address) {//one constructor for export data to db
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }

    /**
     * Constructor to import data from db with id
     *
     * @param id
     * @param name
     * @param surname
     * @param phone_number
     * @param user_password
     * @param email_address
     */
    public User(int id, String name, String surname, int phone_number, String user_password, String email_address) {//constructor to import data from db with id
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }

    // Create database table for user information
    public boolean create_user_table() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//THIS IS LOADING THE DRIVER INTO OUR PROGRAM

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            //THIS METHOD IS USED TO DO THE QUERYES

            stmt.execute("USE equationssystem;");
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS user_info ("
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

    // Method used for user register
    public boolean register(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("USE equationssystem;");
            stmt.execute(
                    String.format("INSERT INTO user_info (name, surname, phone_number, user_password, email_address) "
                            + "VALUES (\"%s\", \"%s\", \"%d\", \"%s\", \"%s\");",
                            user.name, user.surname, user.phone_number, user.user_password, user.email_address)
            );
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Setters and Getters
    public String getUserPassword() {
        return user_password;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }
    protected ArrayList<UserInterface> users;

    // method used for user log in
    public boolean logIn(String name, String user_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT name, user_password from user_info"); //rs receiving value from querie

            rs.next();//code for the first line of db table
            if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("user_password").equals(user_password))) {
                return true;
            }

            while (rs.next()) {    //rest of the lines for the db table
                if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("user_password").equals(user_password))) {
                    return true;
                }
            }

        } catch (SQLException e) {
            return false;
        }
        System.out.println("");
        return false;

    }

    // Method used to change user information
    public String change_info(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ResultSet rs;
        boolean userExists;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            //IF USER NAME AND EMAIL ADDRESS MATCHES THAN WE ALLOW CHANGES 
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT name, email_address FROM user_info");

            rs.next();//code below is for the first line in db
            //user needs to be in the databases matching email address and name so they can make changes
            if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {
                String email = "email_address";

                stmt.execute("USE equationssystem;");
                stmt.executeUpdate("UPDATE user_info SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
                return "Updated successfully";
            }
            while (rs.next()) {//code for the rest of the lines in db
                if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {
                    String email = "email_address";

                    stmt.execute("USE user_info;");
                    stmt.executeUpdate("UPDATE user_info SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
                    return "Updated successfully";
                }
            }
            return "Try again";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Try again";
        }
    }

    @Override
    public String solveEquationTwoVariables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String solveEquationThreeVariables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // method used to review the equations and final results by the user
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ArrayList<String> two_var_equations = new ArrayList<>();
        ArrayList<String> three_var_equations = new ArrayList<>();
        
        ResultSet rs;//var of type result set as this is the type sql returns

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT * from two_var_equations"); //rs receiving value from querie

            while (rs.next()) {//loop to get info from the whole databases
                two_var_equations.add(rs.getString("first_equation")); // add to list first equation
                two_var_equations.add(rs.getString("second_equation")); // add to list second equation
                two_var_equations.add(rs.getString("equation_final_result")); // add to list final result with x and y values
            }
            System.out.println("Two variable equations:");
            for(String two_var : two_var_equations){
                System.out.println(two_var + " "); // print eqautions and result
            }
            
            rs = stmt.executeQuery("SELECT * from three_var_equations"); //rs receiving value from querie
            
            while (rs.next()) {//loop to get info from the whole databases
                three_var_equations.add(rs.getString("first_equation")); // add to list first equation
                three_var_equations.add(rs.getString("second_equation")); // add to list second equation
                three_var_equations.add(rs.getString("third_equation"));  // add to list third equation
                three_var_equations.add(rs.getString("equation_final_result")); // add to list final result with x, y and z values
            }
            System.out.println("Three variable equations:");
            for(String three_var : three_var_equations){
                System.out.println(three_var + " "); // print eqautions and result
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    
    }

    // Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String user_password) {
        this.user_password = user_password;
    }

    // Method to print user attributes
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone_number=" + phone_number + ", email_address=" + email_address + " " + '}' + "\n";
    }

}
