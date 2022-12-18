/*
INSERT INTO equationssystem.admin_info(id, admin_name, admin_password, email_address)
VALUES('1', 'CCT', 'Dublin', 'dublin@adinistrator.cct.ie');
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

    // Administrator atributes 
    private String name;
    private int id;
    private String email_address;
    private String admin_password;
    
    String dbName = "equationssystem";
    String DB_URL = "jdbc:mysql://localhost/";
    String USER = "CCT";
    String PASSWORD = "Dublin";

    //THESE METHODS ARE ALLOWED ONCE THE ADMIN IS LOGGED IN 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Administrator(String name, String email_address, String admin_password) {
        this.name = name;
        this.email_address = email_address;
        this.admin_password = admin_password;
    }

    
    // Administrator log in method
    @Override
    public boolean admin_login(String name, String admin_password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        ResultSet rs;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();//Creating the queries `statements`
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT admin_name, admin_password from admin_info"); //SQL Query for the first line of the DB

            rs.next();
            if (rs.getString("admin_name").equalsIgnoreCase(name) && (rs.getString("admin_password").equals(admin_password))) {
                return true;
            }
            //Code to go through the rest of the DB
            while (rs.next()) {
                if (rs.getString("admin_name").equalsIgnoreCase(name) && (rs.getString("admin_password").equals(admin_password))) {
                    return true;
                }
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    // Allow administartior to access the user list
    @Override
    public ArrayList<UserInterface> access_list() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<UserInterface> users = new ArrayList<>();

        ResultSet rs;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ) {
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT * from user_info"); 

            while (rs.next()) {//loop to get all the user's info
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
        return users;//RETURNES USERS
    }

    // Delete users form the database
    @Override
    public void delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "DELETE FROM user_info WHERE id = ?";//sql query for deletion

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute("USE equationssystem;");
            pstmt.setInt(1, id);//method receives id of user to be deleted
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation executed successfully.");
    }
    
    // Review all the eqautions and their results
    @Override
    public void review_operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<String> two_var_equations = new ArrayList<>(); // Create an ArrayList for equations with two variables
        ArrayList<String> three_var_equations = new ArrayList<>(); // Create an ArrayList for equations with three variables
        
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
                System.out.println(" ");
            }
            
            rs = stmt.executeQuery("SELECT * from three_var_equations"); //rs receiving value from querie
            
            while (rs.next()) {//loop to get info from the whole databases
                three_var_equations.add(rs.getString("first_equation")); // add to list first equation
                three_var_equations.add(rs.getString("second_equation")); // add to list second equation
                three_var_equations.add(rs.getString("third_equation")); // add to list third equation
                three_var_equations.add(rs.getString("equation_final_result")); // add to list final result with x, y and z values
            }
            System.out.println("Three variable equations:");
            for(String three_var : three_var_equations){
                System.out.println(three_var + " "); // print equations and result
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Create Administrator database
    @Override
    public boolean admin_datadb_setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String ADMIN_DB_NAME = "admin_info";

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
                    + "`admin_password` VARCHAR(20),"
                    + "`email_address` VARCHAR (50),"
                    + "PRIMARY KEY(`id`)"
                    + ")");

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    // Method to update administrator information
    @Override
    public String update_admin_info(String columnToBeChanged, String admin_name, String old_info, String new_info) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            ResultSet rs;
        boolean userExists;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();//Creating the queries `statements`
            stmt.execute("USE equationssystem;");

            rs = stmt.executeQuery("SELECT admin_name FROM admin_info");

            rs.next();//code below is for the first line in db
            if (rs.getString("admin_name").equalsIgnoreCase(admin_name)) {

                stmt.execute("USE equationssystem;");
                stmt.executeUpdate("UPDATE admin_info SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "'");
                return "Updated successfully";
            }
            while (rs.next()) {//code for the rest of the lines in db
                if (rs.getString("admin_name").equalsIgnoreCase(admin_name)) {
                    stmt.execute("USE admin_info;");
                    stmt.executeUpdate("UPDATE user_info SET " + columnToBeChanged + "='" + new_info + "' WHERE " + columnToBeChanged + "='" + old_info + "'");
                    return "Updated successfully";
                }
            }//The code above updates only the field desired to be changed
            return "Your administrator details are incorrect. Please try again";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Your administrator details are incorrect. Please try again";
        }
    }

}
