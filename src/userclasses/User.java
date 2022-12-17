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
    
    //constructor to import data from db with id
    public User(int id, String name, String surname, int phone_number, String user_password, String email_address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }

    public boolean create_user_table() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

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

    @Override
    public boolean register(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
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

    @Override
    public boolean user_login(String name, String user_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        ResultSet rs;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT name, user_password from user_info"); 

            rs.next();
            if (rs.getString("name").equalsIgnoreCase(name) && (rs.getString("user_password").equals(user_password))) {
                return true;
            }

            while (rs.next()) { 
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

    @Override
    public String update_userinfo(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ResultSet rs;
        boolean userExists;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            //IF USER NAME AND EMAIL ADDRESS MATCHES THAN WE ALLOW CHANGES 
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT name, email_address FROM user_info");

            rs.next();
            //user needs to be in the databases matching email address and name so they can make changes
            if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {
                String email = "email_address";

                stmt.execute("USE equationssystem;");
                stmt.executeUpdate("UPDATE user_info SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
                return "Updated successfully";
            }
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {
                    String email = "email_address";

                    stmt.execute("USE equationssystem;");
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
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ArrayList<String> two_var_equations = new ArrayList<>();
        ArrayList<String> three_var_equations = new ArrayList<>();

        ResultSet rs;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT * from two_var_equations");

            while (rs.next()) {
                two_var_equations.add(rs.getString("equation_final_result"));//array list adding all equation line by line
            }
            System.out.println("Two variable equations:");
<<<<<<< HEAD
            for(String two_var : two_var_equations){
                System.out.println(two_var + " "); // print eqautions and result
                System.out.println(" "); 
=======
            for (String two_var : two_var_equations) {
                System.out.println(two_var + " ");//printing all results for 2 var equations
>>>>>>> c215f522fb6dd917868b07a64865f1d6ae9f7951
            }

            rs = stmt.executeQuery("SELECT * from three_var_equations"); 

            while (rs.next()) {//loop to get info from the whole databases
                three_var_equations.add(rs.getString("equation_final_result"));
            }
            System.out.println("Three variable equations:"); //array list adding all equation line by line
            for (String three_var : three_var_equations) { //printing all results for 3 var equations
                System.out.println(three_var + " ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone_number=" + phone_number + ", email_address=" + email_address + " " + '}' + "\n";
    }

}
