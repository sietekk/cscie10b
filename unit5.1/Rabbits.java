/**
 *  EXERCISE 7 - Rabbits.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.1
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  --------------------------
 *  NOTES: 
 *  Recursively sums the number of rabbits generated after a number of months
 *  entered by the user. The Fibonacci sequence is implemented, and the months
 *  correspond to the Nth iteration (or, number of months).
 */

import java.util.*;

public class Rabbits
{
        public static int fibo(int n)
        {
                // First base case
                if (n <= 1) return 0;
                // Second base case
                if (n == 2) return 1;
                // Recusive case
                return (fibo(n-1) + fibo(n-2));
        }

        public static int monthInput()
        {
                // Get number of months/iterations from user
                int output = 0;
                Scanner userInput = new Scanner (System.in);
                System.out.print("Please enter number of months (integers only!): ");
                // Check if number of months is an integer, or throw error and ask again
                while (!userInput.hasNextInt()) {
                        System.out.print("Enter only integers! Please try again: ");
                        userInput.nextLine();
                }
                output = userInput.nextInt();
                // Return number of months/iterations
                return output;
        }

        public static void main (String [] args)
        {
                int pairs = 0; // Number of pairs (Fibonacci sum)
                int months = monthInput(); // Get number of months/iterations from user

                // Loop over each month/iteration, and recursively solve for the corresponding pair/Fibbonacci number
                for (int i = 1; i <= months; i++)
                {
                        pairs = fibo(i);
                        System.out.println("At the end of month #" + i + ", there are " + pairs + " mature pairs.");
                }
        }
}
