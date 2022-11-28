
package ooc.la.db.ca;

import SloveEquations.Solve2Equations;
import SloveEquations.Solve3Equations;

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
     */
    public static void main(String[] args) {
      
        Solve2Equations s1 = new Solve2Equations();
        s1.twoVariableEquation("2x - 3y -2 = 0", "3x + 8y - 3 = 0");

        //Solve3Equations s2 = new Solve3Equations();
        //s2.threeVariableEquation(" x + y + z = 2", " 2x + 3y + 5z = 11", "x - 5y + 6z = 29");
    }
    
}
