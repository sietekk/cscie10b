/**
 *  EXERCISE 6 - LowestGrade.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.1
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  --------------------------
 *  NOTES: 
 *  Returns an integer array that contains all of the values entered by the user,
 *  except for the lowest integer. If one value is entered, that value is kept,
 *  if zero values are entered, an empty array is returned, and if there are
 *  multiples of the lowest value entered, only one is removed.
 */

import java.util.*;

public class LowestGrade
{
        public static int [] removeLowest (int... args)
        {
                // DEFINE VARIABLES
                int [] finalScores; // Holds finals scores for method to return
                int [] argHolder; // Holds arguments
                int argIndex = 0; // Indexes argHolder
                int lowestIndex = 0; // Holds index of lowest score
                int highScoreCounter = 0;
                
                // Generate finalScores based on number of args sent to method
                if (args.length == 0) { // If method is given no arguments
                        finalScores = new int [0];
                        return finalScores;
                } else if (args.length == 1) { // If method is given one argument
                        finalScores = new int [1];
                        finalScores[0] = args[0];
                        return finalScores;
                } else { // If method is given > 1 arguments
                        argHolder = new int [args.length];
                        for (int arg: args)
                        {
                                argHolder[argIndex] = arg;
                                argIndex++;
                        }
                        finalScores = new int[argHolder.length - 1];
                        for (int i = 0; i < argHolder.length; i++)
                        {
                                if (argHolder[i] < argHolder[lowestIndex]) {
                                        lowestIndex = i;
                                }
                        }
                        for (int j = 0; j < argHolder.length; j++)
                        {
                                if (j != lowestIndex){
                                        finalScores[highScoreCounter] = argHolder[j];
                                        highScoreCounter++;
                                }
                        }
                        return finalScores;
                }
        }

        private static String arrayPrint (int [] inputArr)
        {
                // GENERATE STRING OUTPUT TEXT
                int length = inputArr.length; //Length of input array
                StringBuilder outputString = new StringBuilder(); //Stringbuilder object to hold output string
                // Return empty array string if inputArr is empty
                if (length == 0) {
                        return "[]";
                } else {
                        outputString.append("[");
                        // Append the elements of the array except the last one (so no trailing comma)
                        for (int i = 0; i < (length - 1); i++)
                        {
                                outputString.append(inputArr[i] + ", ");
                        }
                        // Append last element of array with right bracket
                        outputString.append(inputArr[length-1] + "]");
                }

                // Return as string
                return outputString.toString();
        }

        public static void main (String [] args)
        {
                // REMOVE LOWEST GRADES
                // --Test cases given in homework assignment, and cover all use cases
                int [] a = removeLowest(23, 90, 47, 55, 88);
                int [] b = removeLowest(85);
                int [] c = removeLowest();
                int [] d = removeLowest(59, 92, 93, 47, 88, 47);

                // PRINT TEST CASE OUTPUT
                System.out.println("a = " + arrayPrint(a));
                System.out.println("b = " + arrayPrint(b));
                System.out.println("c = " + arrayPrint(c));
                System.out.println("d = " + arrayPrint(d));
        }
}
