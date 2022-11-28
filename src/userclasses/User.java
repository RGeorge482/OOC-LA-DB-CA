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

    public User(int id, String name, String surname, int phone_number, String user_password, String email_address) {//constructor to import data from db with id
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.email_address = email_address;
    }

    public boolean register(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
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

    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }
    protected ArrayList<UserInterface> users;

    @Override
    public boolean logIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean change_info(String columnToBeChanged, String user_name, String email_address, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
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
                String email = "email_address";

                stmt.execute("USE equationssystem;");
                stmt.executeUpdate("UPDATE equationssystem SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
                return true;
            }
            while (rs.next()) {//code for the rest of the lines in db
                if (rs.getString("name").equalsIgnoreCase(user_name) && (rs.getString("email_address").equalsIgnoreCase(email_address))) {
                    String email = "email_address";

                    stmt.execute("USE equationssystem;");
                    stmt.executeUpdate("UPDATE equationssystem SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "' AND " + email + "='" + email_address + "'");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    @Override
    public String seeEquations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUsers(ArrayList<UserInterface> users) {
        this.users = users;
    }

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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone_number=" + phone_number + ", email_address=" + email_address + " " + '}' + "\n";
    }

}
