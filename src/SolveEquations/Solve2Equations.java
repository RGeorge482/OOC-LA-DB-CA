/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolveEquations;

/**
 *
 * @author George
 */
public class Solve2Equations {

    public String twoVariableEquation(String eq1, String eq2) {
        // 2x - 3y - 2 = 0;  use this input type for the moment
        // 3x + 8y - 3 = 0;

        // 2x - 3y = 2;
        // 3x + 8y = 3;
        // replacing all the empty spaces from the string
        String equ1 = eq1.replaceAll("\\s", "");
        String equ2 = eq2.replaceAll("\\s", "");
        String final_result = "";
        double eq1Const1 = 0, eq2Const1 = 0;

        // Equation 1
        // Check if equations are equal zero, case when constant is before equal sign
        String lastDigitEq1 = equ1.substring(equ1.length() - 1); // get the last digit of equation
        int yIndex, equalIndex;
        String constValue, signConst, equation1 = "", equation2 = "", newEqu1;;

        if (lastDigitEq1.equals("0")) { // if last digit is zero get the constant
            yIndex = equ1.indexOf('y');
            equalIndex = equ1.indexOf('=');
            constValue = equ1.substring(yIndex + 2, equalIndex);
            signConst = equ1.substring(yIndex + 1, equalIndex);

            if (signConst.charAt(0) == 43) { // if is a positive number, after equal sign it will be negative
                newEqu1 = equ1.replace(signConst, "").replaceAll(lastDigitEq1, constValue); // replace the constant
                equalIndex = newEqu1.indexOf('='); // get the new equal index value
                equation1 = newEqu1.substring(0, equalIndex + 1) + "-" + newEqu1.substring(equalIndex + 1); // get the new string with the constant after the equal sign when negative
                String eq1ConstValue = equation1.substring(equalIndex + 1); // value of the constant
                eq1Const1 = Double.parseDouble(eq1ConstValue);
            } else { // this is the case when the constant is before equal and is negative
                newEqu1 = equ1.replace(signConst, "").replaceAll(lastDigitEq1, constValue);  // replace the constant
                equalIndex = newEqu1.indexOf('='); // get the new equal index value

                equation1 = equ1.replaceAll(signConst, "").replaceAll(lastDigitEq1, constValue);
                String eq1ConstValue = equation1.substring(equalIndex + 1); // // get the new string with the constant after the equal sign when positive
                eq1Const1 = Double.parseDouble(eq1ConstValue);
            }
        }

        // equation 2
        String lastDigitEq2 = equ2.substring(equ2.length() - 1); // get the last digit of equation
        String newEqu2;
        if (lastDigitEq2.equals("0")) {//if last digit is zero get the constant
            yIndex = equ2.indexOf('y');
            equalIndex = equ2.indexOf('=');
            constValue = equ2.substring(yIndex + 2, equalIndex);
            signConst = equ2.substring(yIndex + 1, equalIndex);
            if (signConst.charAt(0) == 43) { // if is a positive number, after equal sign it will be negative
                newEqu2 = equ2.replace(signConst, "").replaceAll(lastDigitEq2, constValue); // replace the constant
                equalIndex = newEqu2.indexOf('='); // get the new equal index value
                equation2 = newEqu2.substring(0, equalIndex + 1) + "-" + newEqu2.substring(equalIndex + 1); // get the new string with the constant after the equal sign when negative;

                String eq2ConstValue = equation2.substring(equalIndex + 1); // value of the constant
                eq2Const1 = Double.parseDouble(eq2ConstValue);
            } else { // this is the case when the constant is before equal and is negative
                newEqu2 = equ2.replace(signConst, "").replaceAll(lastDigitEq1, constValue);  // replace the constant
                equalIndex = newEqu2.indexOf('='); // get the new equal index value

                equation2 = equ2.replaceAll(signConst, "").replaceAll(lastDigitEq2, constValue);

                String eq2ConstValue = equation2.substring(equalIndex + 1); // // get the new string with the constant after the equal sign when positive
                eq2Const1 = Double.parseDouble(eq2ConstValue);
            }
        }

        double eq1Coefficent1 = 0, eq1Coefficent2 = 0, eq2Coefficent1 = 0, eq2Coefficent2 = 0;

        // Instantiating matrix
        double mat1[][] = new double[2][2];
        double mat2[][] = new double[2][1];
        double result[][] = new double[2][1];

        // FIRST EQUATION
        // Get the value of the first coefficient
        int eq1xIndex = equation1.indexOf("x"); // index of x variable
        if (eq1xIndex == 0) { // check if index of x is zero
            eq1Coefficent1 = 1; // coefficient is one if no number on front of x
        } else if (equation1.charAt(0) == 45) { // check if we have minus sign at first position
            if (eq1xIndex == 1) {
                eq1Coefficent1 = -1; // if x index is one the value of first coefficient is -1
            } else {
                String eq1coefXvalue = equation1.substring(1, eq1xIndex); // value of the coefficient which is before x if number is negative
                eq1Coefficent1 = Double.parseDouble(eq1coefXvalue); // parsing to double
                eq1Coefficent1 *= -1; // the number is negative
            }
        } else {
            String eq1coefXvalue = equation1.substring(0, eq1xIndex); // value of the coefficient which is before x
            eq1Coefficent1 = Double.parseDouble(eq1coefXvalue); // parsing to double
        }

        // Get the value of the second coefficient
        int eq1yIndex = equation1.indexOf("y"); // index of y variable
        String eq1coefYvalue = equation1.substring(eq1xIndex + 2, eq1yIndex); // value of the coefficient which is before y
        if (eq1coefYvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq1Coefficent2 = Double.parseDouble(eq1coefYvalue);
        }
        if (!eq1coefYvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq1Coefficent2 = 1;
        }
        if (equation1.charAt(eq1xIndex + 1) == 45) { // check if the number is negative
            eq1Coefficent2 *= -1;
        }

        // SECOND EQUATION
        // Get the value of the first coefficient
        int eq2xIndex = equation2.indexOf("x"); // index of x variable
        if (eq2xIndex == 0) { // check if index of x is zero
            eq2Coefficent1 = 1; // coefficient is one if no number on front of x
        } else if (equation2.charAt(0) == 45) { // check if we have minus sign at first position
            if (eq2xIndex == 1) {
                eq2Coefficent1 = -1; // if x index is one the value of first coefficient is -1
            } else {
                String eq2coefXvalue = equation2.substring(1, eq2xIndex); // value of the coefficient which is before x if number is negative
                eq2Coefficent1 = Double.parseDouble(eq2coefXvalue); // parsing to double
                eq2Coefficent1 *= -1; // the number is negative
            }
        } else {
            String eq2coefXvalue = equation2.substring(0, eq2xIndex); // value of the coefficient which is before x
            eq2Coefficent1 = Double.parseDouble(eq2coefXvalue); // parsing to double
        }

        // Get the value of the second coefficient
        int eq2yIndex = equation2.indexOf("y"); // index of y variable
        String eq2coefYvalue = equation2.substring(eq2xIndex + 2, eq2yIndex); // value of the coefficient which is before y
        if (eq2coefYvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq2Coefficent2 = Double.parseDouble(eq2coefYvalue);
        }
        if (!eq2coefYvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq2Coefficent2 = 1;
        }
        if (equation2.charAt(eq2xIndex + 1) == 45) { // check if the number is negative
            eq2Coefficent2 *= -1;
        }

        // Get the value of the constant
        int eq1EqualIndex = equation1.indexOf("="); // index of equal sign
        String eq1ConstValue = equation1.substring(eq1EqualIndex + 1); // value of the constant
        eq1Const1 = Double.parseDouble(eq1ConstValue);

        int eq2EqualIndex = equation2.indexOf("="); // index of equal sign
        String eq2ConstValue = equation2.substring(eq2EqualIndex + 1); // value of the constant
        eq2Const1 = Double.parseDouble(eq2ConstValue);

        // If determinant is zero ad - bc, we don't calculate inverse
        double determinat = eq1Coefficent1 * eq2Coefficent2 - eq1Coefficent2 * eq2Coefficent1;

        if (determinat == 0) {
            System.out.println("A singular matrix, don't have an inverse");
        } else {
            // detA is the value which we multiply with our matrix to find the inverse
            double detA = 1 / determinat;

            // we save in matrix 1 the element for each corresponding position
            mat1[0][0] = eq1Coefficent1;
            mat1[0][1] = eq1Coefficent2;
            mat1[1][0] = eq2Coefficent1;
            mat1[1][1] = eq2Coefficent2;

            // we save in matrix 2 element for each corresponding position
            mat2[0][0] = eq1Const1;
            mat2[1][0] = eq2Const1;

            double[][] reverseMat1 = new double[2][2];  // instantiating the reverse matrix with d -b -c a
            double[][] inverseMat1 = new double[2][2];  // instantiating the inverse matrix

            reverseMat1[0][0] = eq2Coefficent2;
            reverseMat1[0][1] = -eq1Coefficent2;
            reverseMat1[1][0] = -eq2Coefficent1;
            reverseMat1[1][1] = eq1Coefficent1;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    inverseMat1[i][j] = reverseMat1[i][j] * detA; // multiply each element of our reverse matrix with detA and save the new value in the inverse matrix
                }
            }

            // multiply the inverse of matrix with our constant matrix
            result[0][0] = inverseMat1[0][0] * mat2[0][0] + inverseMat1[0][1] * mat2[1][0];
            result[1][0] = inverseMat1[1][0] * mat2[0][0] + inverseMat1[1][1] * mat2[1][0];
            // parse the double as a string and concatenate them
            

            String first_result = String.valueOf(result[0][0] + " ");
            String second_result = String.valueOf(result[1][0]);

            final_result = first_result.concat(second_result);

        }
        return final_result;
    }
}
