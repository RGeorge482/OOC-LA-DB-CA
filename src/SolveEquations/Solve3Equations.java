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
public class Solve3Equations {
    
    public String  threeVariableEquation(String eq1, String eq2, String eq3) {

        // x + y + z = 2
        // 2x + 3y + 5z = 11
        // x - 5y + 6z = 29

        // replacing all the empty spaces from the string
        String equation1 = eq1.replaceAll("\\s", "");
        String equation2 = eq2.replaceAll("\\s", "");
        String equation3 = eq3.replaceAll("\\s", "");


        double eq1Coefficent1 = 0,eq1Coefficent2 = 0, eq1Coefficent3 = 0, eq1Const1, eq2Coefficent1 = 0, eq2Coefficent2 = 0, eq2Coefficent3 = 0, eq2Const1;
        double eq3Coefficent1 = 0, eq3Coefficent2 = 0, eq3Coefficent3 = 0, eq3Const1;

        // FIRST EQUATION

        // Get the value of the first coefficient
        int eq1xIndex = equation1.indexOf("x"); // index of x variable
        if(eq1xIndex == 0) { // check if index of x is zero
            eq1Coefficent1 = 1; // coefficient is one if no number on front of x
        } else if(equation1.charAt(0) == 45){ // check if we have minus sign at first position
            if(eq1xIndex == 1) {
                eq1Coefficent1 = -1; // if x index is one the value of first coefficient is -1
            } else{
                String eq1coefXvalue = equation1.substring(1,eq1xIndex); // value of the coefficient which is before x if number is negative
                eq1Coefficent1 = Double.parseDouble(eq1coefXvalue); // parsing to double
                eq1Coefficent1 *= -1; // the number is negative
            }
        } else {
            String eq1coefXvalue = equation1.substring(0,eq1xIndex); // value of the coefficient which is before x
            eq1Coefficent1 = Double.parseDouble(eq1coefXvalue); // parsing to double
        }


        // Get the value of the second coefficient
        int eq1yIndex = equation1.indexOf("y"); // index of y variable
        String eq1coefYvalue = equation1.substring(eq1xIndex + 2,eq1yIndex); // value of the coefficient which is before y
        if(eq1coefYvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq1Coefficent2 = Double.parseDouble(eq1coefYvalue);
        }
        if(!eq1coefYvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq1Coefficent2 = 1;
        }
        if(equation1.charAt(eq1xIndex + 1) == 45) { // check if the number is negative
            eq1Coefficent2 *= -1;
        }

        // Get the value of the third coefficient
        int eq1zIndex = equation1.indexOf("z"); // index of z variable
        String eq1coefZvalue = equation1.substring(eq1yIndex + 2, eq1zIndex); // value of the coefficient which is before z

        if(eq1coefZvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq1Coefficent3 = Double.parseDouble(eq1coefZvalue);
        }
        if(!eq1coefZvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq1Coefficent3 = 1;
        }
        if(equation1.charAt(eq1yIndex + 1) == 45) { // check if the number is negative
            eq1Coefficent3 *= -1;
        }


        // SECOND EQUATION

        // Get the value of the first coefficient
        int eq2xIndex = equation2.indexOf("x"); // index of x variable
        if(eq2xIndex == 0) { // check if index of x is zero
            eq2Coefficent1 = 1; // coefficient is one if no number on front of x
        } else if(equation2.charAt(0) == 45){ // check if we have minus sign at first position
            if(eq2xIndex == 1) {
                eq2Coefficent1 = -1; // if x index is one the value of first coefficient is -1
            } else{
                String eq2coefXvalue = equation2.substring(1,eq2xIndex); // value of the coefficient which is before x if number is negative
                eq2Coefficent1 = Double.parseDouble(eq2coefXvalue); // parsing to double
                eq2Coefficent1 *= -1; // the number is negative
            }
        } else {
            String eq2coefXvalue = equation2.substring(0,eq2xIndex); // value of the coefficient which is before x
            eq2Coefficent1 = Double.parseDouble(eq2coefXvalue); // parsing to double
        }


        // Get the value of the second coefficient
        int eq2yIndex = equation2.indexOf("y"); // index of y variable
        String eq2coefYvalue = equation2.substring(eq2xIndex + 2,eq2yIndex); // value of the coefficient which is before y
        if(eq2coefYvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq2Coefficent2 = Double.parseDouble(eq2coefYvalue);
        }
        if(!eq2coefYvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq2Coefficent2 = 1;
        }
        if(equation2.charAt(eq2xIndex + 1) == 45) { // check if the number is negative
            eq2Coefficent2 *= -1;
        }


        // Get the value of the third coefficient
        int eq2zIndex = equation2.indexOf("z"); // index of z variable
        String eq2coefZvalue = equation2.substring(eq2yIndex + 2, eq2zIndex); // value of the coefficient which is before z
        if(eq2coefZvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq2Coefficent3 = Double.parseDouble(eq2coefZvalue);
        }
        if(!eq2coefZvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq2Coefficent3 = 1;
        }
        if(equation2.charAt(eq2yIndex + 1) == 45) { // check if the number is negative
            eq2Coefficent3 *= -1;
        }

        // THIRD EQUATION

        // Get the value of the first coefficient
        int eq3xIndex = equation3.indexOf("x"); // index of x variable
        if(eq3xIndex == 0) { // check if index of x is zero
            eq3Coefficent1 = 1; // coefficient is one if no number on front of x
        } else if(equation3.charAt(0) == 45){ // check if we have minus sign at first position
            if(eq3xIndex == 1) {
                eq3Coefficent1 = -1; // if x index is one the value of first coefficient is -1
            } else{
                String eq3coefXvalue = equation3.substring(1,eq3xIndex); // value of the coefficient which is before x if number is negative
                eq3Coefficent1 = Double.parseDouble(eq3coefXvalue); // parsing to double
                eq3Coefficent1 *= -1; // the number is negative
            }
        } else {
            String eq3coefXvalue = equation3.substring(0,eq3xIndex); // value of the coefficient which is before x
            eq3Coefficent1 = Double.parseDouble(eq3coefXvalue); // parsing to double
        }


        // Get the value of the second coefficient
        int eq3yIndex = equation3.indexOf("y"); // index of y variable
        String eq3coefYvalue = equation3.substring(eq3xIndex + 2,eq3yIndex); // value of the coefficient which is before y
        if(eq3coefYvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq3Coefficent2 = Double.parseDouble(eq3coefYvalue);
        }
        if(!eq3coefYvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq3Coefficent2 = 1;
        }
        if(equation3.charAt(eq3xIndex + 1) == 45) { // check if the number is negative
            eq3Coefficent2 *= -1;
        }


        // Get the value of the third coefficient
        int eq3zIndex = equation3.indexOf("z"); // index of z variable
        String eq3coefZvalue = equation3.substring(eq3yIndex + 2, eq3zIndex); // value of the coefficient which is before z
        if(eq3coefZvalue.matches("[0-9]+")) { // if the coefficient is a number we parse it as a double
            eq3Coefficent3 = Double.parseDouble(eq3coefZvalue);
        }
        if(!eq3coefZvalue.matches("[0-9]+")) {  // if we don't have a coefficient the number is one
            eq3Coefficent3 = 1;
        }
        if(equation3.charAt(eq3yIndex + 1) == 45) { // check if the number is negative
            eq3Coefficent3 *= -1;
        }

        // Instantiating matrix
        double mat1[][] = {{eq1Coefficent1, eq1Coefficent2, eq1Coefficent3}, {eq2Coefficent1, eq2Coefficent2, eq2Coefficent3}, {eq3Coefficent1, eq3Coefficent2,eq3Coefficent3}};

        // Matrix of minors
        double minors[][] = new double[3][3];
        minors[0][0] = eq2Coefficent2 * eq3Coefficent3 - eq2Coefficent3 * eq3Coefficent2;
        minors[0][1] = eq2Coefficent1 * eq3Coefficent3 - eq2Coefficent3 * eq3Coefficent1;
        minors[0][2] = eq2Coefficent1 * eq3Coefficent2 - eq2Coefficent2 * eq3Coefficent1;

        minors[1][0] = eq1Coefficent2 * eq3Coefficent3 - eq1Coefficent3 * eq3Coefficent2;
        minors[1][1] = eq1Coefficent1 * eq3Coefficent3 - eq1Coefficent3 * eq3Coefficent1;
        minors[1][2] = eq1Coefficent1 * eq3Coefficent2 - eq1Coefficent2 * eq3Coefficent1;

        minors[2][0] = eq1Coefficent2 * eq2Coefficent3 - eq1Coefficent3 * eq2Coefficent2;
        minors[2][1] = eq1Coefficent1 * eq2Coefficent3 - eq1Coefficent3 * eq2Coefficent1;
        minors[2][2] = eq1Coefficent1 * eq2Coefficent2 - eq1Coefficent2 * eq2Coefficent1;

        // Co-factors
        double coFactors[][] = new double[3][3];
        coFactors = minors;
        coFactors[0][1] *= -1;
        coFactors[1][0] *= -1;
        coFactors[1][2] *= -1;
        coFactors[2][1] *= -1;

        double determinant = mat1[0][0] * coFactors[0][0] + mat1[0][1] * coFactors[0][1] + mat1[0][2] * coFactors[0][2];

        // Adjoint of the matrix, the transpose of cofactors
        double adjoint[][] = new double[3][3]; // is coFactor transpose
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                adjoint[i][j] = coFactors[j][i];
            }
        }

        // Inverse of matrix is 1 / detA * adjoint
        double inverse[][] = new double[3][3];
        double detMat1 = 1 / determinant;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                inverse[i][j] = detMat1 * adjoint[i][j];
            }
        }

        // index of equal sign from each equation
        int equalIndexEq1 = equation1.indexOf("=");
        int equalIndexEq2 = equation2.indexOf("=");
        int equalIndexEq3 = equation3.indexOf("=");

        // save the value of constant number
        String lastEq1 = equation1.substring(equalIndexEq1 + 1);
        String lastEq2 = equation2.substring(equalIndexEq2 + 1);
        String lastEq3 = equation3.substring(equalIndexEq3 + 1);

        // get the second matrix which we will multiply with inverse
        double mat2[][] = new double[3][1];
        mat2[0][0] = Double.parseDouble(lastEq1);
        mat2[1][0] = Double.parseDouble(lastEq2);
        mat2[2][0] = Double.parseDouble(lastEq3);
        
        

        double result[][] = new double[3][1]; // inverse * mat2

        // multiply matrix
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 1; j++) {
                int sum = 0;
                for(int k = 0; k < 3; k++) {
                    sum += mat1[i][k] * mat2[k][j];
                }
                result[i][j] = sum;
            }
        }
        
        String first_result = String.valueOf(result[0][0] + " ");
        String second_result = String.valueOf(result[1][0] + " ");
        String third_result = String.valueOf(result[2][0] + " ");
        String final_result = first_result.concat(second_result);
        

//        for(int i = 0; i < 3; i++) {
//            for(int j = 0; j < 1; j++) {
//                 System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }
        return final_result;

    }
    
}
