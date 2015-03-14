/**
 *  EXERCISE 5 - Palindrome.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.1
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  --------------------------
 *  NOTES: Tests if a user inputted sentence/string is a palindrome.
 *         This program will not accept strings with numbers, but
 *         will accept punctuation and spaces, though, it will ignore
 *         puncturation and spaces.
 */

import java.util.*;

public class Palindrome
{
        public static boolean isPalindrome (String testString)
        {
                // Sentence length
                int len = testString.length();
                
                // Base case
                if (len <= 1) {
                        return true;
                }

                // Recursive case
                if (testString.charAt(0) == testString.charAt(len - 1)) {
                        String subString = testString.substring(1, (len - 1));
                        return isPalindrome(subString);
                } else {
                        return false;
                }
        }

        public static String stringFormatter (String toFormat)
        {
                // Convert to lowercase
                String lowerCase = toFormat.toLowerCase();

                // Create charOnlySentence to hold character-only string
                // and initialize it as a StringBuilder
                StringBuilder charOnly = new StringBuilder();

                // Build newSentence
                for (int i = 0; i < lowerCase.length(); i++) {
                        if (Character.isLetter(lowerCase.charAt(i))) {
                                charOnly.append(lowerCase.charAt(i));
                        }
                }

                // Convert back to string
                String formattedString = charOnly.toString();
                
                // RETURN FORMATTED STRING
                return formattedString;
        }

        public static String getUserInput ()
        {
                // DEFINE VARIABLES
                Scanner userInput = new Scanner (System.in); // Create Scanner object to input sentence
                String inputSentence; // Holds user input

                // Ask user for input
                System.out.print("Please type a sentence (no numbers!): ");
                
                while (userInput.hasNext(".*\\d.*")) {
                        System.out.print(
                                        "Sentence may not contain numbers!\n"
                                        + "Please try again: "
                                        );
                        userInput.nextLine();
                }

                // Assign input to a new string
                inputSentence = userInput.nextLine();

                // RETURN INPUT
                return inputSentence;
        }

        public static void main (String [] args)
        {
                // GET INPUT
                String rawUserInput = getUserInput();

                // FORMAT STRING
                String sentence = stringFormatter(rawUserInput);

                // TEST IF PALINDROME
                if (isPalindrome(sentence)) {
                        System.out.println("Your sentence is a palindrome!");
                } else {
                        System.out.println("Your sentence is NOT a palindrome!");
                }
        }

}
