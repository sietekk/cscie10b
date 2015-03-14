/**
 *  EXERCISE 7 - RecursiveSum.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.1
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  --------------------------
 *  NOTES: 
 *  This program takes an array of length and elements specified by the user,
 *  and recusively sums those elements. The entered array and sum are displayed
 *  for the user before the program exits.
 */

import java.util.*;

public class RecursiveSum
{
        public static int recursiveSum(int [] array, int size)
        {
                // Base Case 1
                if (size <= 0) {
                        return 0;
                // Base Case 2
                } else if (size == 1) {
                        return array[0];
                // Recursive Case
                } else {
                        int sum = array[size - 1];
                        return sum + recursiveSum(array, (size-1));
                }
        }

        public static int [] arrayInput()
        {
                // GET ARRAY LENGTH
                int arrayLength = 0;
                Scanner arrayLengthScanner = new Scanner(System.in);
                // Loop input until arrayLength is a postive integer
                while (arrayLength <= 0) {
                        // Ask for size of array
                        System.out.print("Enter length of array (positive integers only!): ");
                        arrayLength = arrayLengthScanner.nextInt();
                        // Test if array size is a postive integer, and give error message if not
                        if (arrayLength <= 0) {
                                System.out.println("Enter only positive integers! Please try again...");
                                continue;
                        }
                }
                
                // GET ARRAY ELEMENTS
                // Declare array with legnth entered by user above
                int [] inputArray = new int [arrayLength];
                Scanner arrayIntScanner = new Scanner(System.in);
                // Scan over length of array and enter elements for each array index
                for (int i = 0; i < arrayLength; i++)
                {
                        System.out.print("Enter integer " + (i+1) + ": ");
                        // Check for valid input, return error if invalid, and ask for correct entry
                        while (!arrayIntScanner.hasNextInt()) {
                                System.out.print("Enter only integers! Please try again: ");
                                arrayIntScanner.nextLine();
                        }
                        inputArray[i] = arrayIntScanner.nextInt();
                }
                // Return array
                return inputArray;
        }


        public static void main(String [] args)
        {
                // DECLARE VARIABLES
                int [] arrayToSum = arrayInput(); // Get array to sum
                int finalSum = recursiveSum(arrayToSum, arrayToSum.length); // Sum elements in array
                
                // PRINT OUTPUT TO SCREEN
                System.out.println("Array entered: " + Arrays.toString(arrayToSum));
                System.out.println("Sum of elements: " + finalSum);
        }
}
