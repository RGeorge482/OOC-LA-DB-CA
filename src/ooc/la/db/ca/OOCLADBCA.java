
package ooc.la.db.ca;


import java.sql.SQLException;
import userclasses.Administrator;
import userclasses.DatabaseWriter;
import userclasses.HeaderClass;
import userclasses.User;
import userclasses.UserController;


/**
 *
 * @Mariano Wellington
 * student number
 * 
 * @Gheorghita Rata 
 * student number
 */
public class OOCLADBCA {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.sql.SQLException
     */


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        User myUser = new User(null, null, -1, null, null);
        HeaderClass myHeaders = new HeaderClass();
        Administrator myAdmin = new Administrator(null, -1, null, null);
        DatabaseWriter dataOutput = new DatabaseWriter();
        
        UserController myControler = new UserController(myAdmin, myUser, myHeaders, dataOutput, null);
        
        
        
        //Solve2Equations s1 = new Solve2Equations();
        //s1.twoVariableEquation("2x - 3y -2 = 0", "3x + 8y - 3 = 0");

        //Solve3Equations s2 = new Solve3Equations();
        //s2.threeVariableEquation(" x + y + z = 2", " 2x + 3y + 5z = 11", "x - 5y + 6z = 29");
        
    }
    
}
