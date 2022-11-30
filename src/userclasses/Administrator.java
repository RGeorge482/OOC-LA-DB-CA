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
    private String surname;
    private int id;
    private int phone_number;
    private String email_address;
    ////////////////////////////////////////////////
    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    //THESE METHODS ARE ALLOWED ONCE THE ADMIN IS LOGGED IN 
    //method returns an array of users
    public ArrayList<UserInterface> access_list() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
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

    @Override
    public void reviewOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //ACCESSORS
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
