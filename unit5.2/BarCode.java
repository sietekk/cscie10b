/**
 *  EXERCISE 4 - BarCode.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.2
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  -------------------------
 *  NOTES: Converts five digit zip codes into their equivalent bar codes
 *  using "|" for a full bar and ":" for a half bar. Also converts
 *  encoded barcodes into their equivalent five digit zip codes. Will
 *  throw an illegal argument exception if given an incorrectly formatted
 *  bar code or zip code.
 */

import java.util.*;

public class BarCode
{
        // Data Fields
        private String myZipCode;
        private String myBarCode;

        // Constructor
        public BarCode (String zipOrBarCode)
        {
                // Check If Barcode Length
                if (zipOrBarCode.length() == 32) {
                        // Check If Valid Barcode
                        if (isValidBarCode(zipOrBarCode)) {
                                myZipCode = decode(zipOrBarCode);
                                myBarCode = zipOrBarCode;
                        // For Invalid Barcode, set Data Fields to Null and Throw Exception
                        } else {
                                myZipCode = null;
                                myBarCode = null;
                                throw new IllegalArgumentException("Illegal bar code value: " + zipOrBarCode);
                        }
                // Check if Zipcode Length
                } else if (zipOrBarCode.length() == 5) {
                        // Check If Valid Zip Code
                        if (isValidZipCode(zipOrBarCode)) {
                                myZipCode = zipOrBarCode;
                                myBarCode = encode(zipOrBarCode);
                        // For Invalid Zipcode, set Data Fields to Null and Throw Exception
                        } else {
                                myZipCode = null;
                                myBarCode = null;
                                throw new IllegalArgumentException("Illegal zip code value: " + zipOrBarCode);
                        }
                // Set Data Fields to Null if Not Zipcode or Barcode Length and Throw Exception
                } else {
                        myZipCode = null;
                        myBarCode = null;
                        throw new IllegalArgumentException("Illegal code length: " + zipOrBarCode);
                }
        }

        // Getter Methods
        public String getZipCode ()
        {
                return myZipCode;
        }

        public String getBarCode ()
        {
                return myBarCode;
        }

        // Private Helper Methods
        private String digitToCode (char digit)
        {
                String code = "";
                switch (digit) {
                        case '0': code = "||:::";
                                break;
                        case '1': code = ":::||";
                                break;
                        case '2': code = "::|:|";
                                break;
                        case '3': code = "::||:";
                                break;
                        case '4': code = ":|::|";
                                break;
                        case '5': code = ":|:|:";
                                break;
                        case '6': code = ":||::";
                                break;
                        case '7': code = "|:::|";
                                break;
                        case '8': code = "|::|:";
                                break;
                        case '9': code = "|:|::";
                                break;
                }
                return code;
        }

        private char codeToDigit (String code)
        {
                char digit = '\0';
                switch (code) {
                         case "||:::": digit = '0';
                                    break;
                         case ":::||": digit = '1';
                                    break;
                         case "::|:|": digit = '2';
                                    break;
                         case "::||:": digit = '3';
                                    break;
                         case ":|::|": digit = '4';
                                    break;
                         case ":|:|:": digit = '5';
                                    break;
                         case ":||::": digit = '6';
                                    break;
                         case "|:::|": digit = '7';
                                    break;
                         case "|::|:": digit = '8';
                                    break;
                         case "|:|::": digit = '9';
                                    break;
                }
                return digit;
        }

        private boolean isValidBarCode (String testBarCode)
        {
                // Check If Starts and Ends with Frame Bars
                if (testBarCode.startsWith("|") && testBarCode.endsWith("|")) {
                        // Define Variables
                        int sum = 0; // Sum of zip code digits
                        int chunk = 1; // Counter for code-digit chunks (groups of five characters)
                        String zipDigitString = new String(); // Holds zipcode digits as a string
                        StringBuilder zipDigits = new StringBuilder(); // Holds zipcode digits
                        // Break into five character chunks, convert to digits, and append to string
                        for (int i = 0; i < 6; i++)
                        {
                                zipDigits.append(codeToDigit(testBarCode.substring(chunk, chunk + 5)));
                                chunk += 5;
                        }
                        // Convert zipDigits to String
                        zipDigitString = zipDigits.toString();
                        // Check if all Digits (Including Check Digit)
                        if (zipDigitString.matches("\\d{6}")) {
                                // Check if Correct Check Digit
                                // Sum first five digits
                                for (int k = 0; k < (zipDigitString.length() - 1); k++)
                                {
                                        sum += (int) zipDigitString.charAt(k);
                                }
                                
                                // Check If Last Digit is Same as Calculated Check Digit
                                if (zipDigitString.charAt(5) == Character.forDigit(getCheckDigit(sum), 10)) {
                                        // Return true if match
                                        return true;
                                } else {
                                        // Return false, last digit doesn't match check digit
                                        return false;
                                }
                        } else {
                                // Not all digits
                                return false;
                        }
                } else {
                        // Return False, because No Frame Bars
                        return false;
                }
        }

        private boolean isValidZipCode (String testZipCode)
        {
                if (testZipCode.matches("\\d{5}")) {
                        return true;
                } else {
                        return false;
                }
        }

        private int getCheckDigit (int checkDigitSum)
        {
                if (checkDigitSum % 10 != 0) {
                        return 10 - (checkDigitSum % 10);
                } else {
                        return 0;
                }
        }

        private String encode (String zipCode)
        {
                // Define Variables
                StringBuilder barCode = new StringBuilder(); // Holds encoded barcode
                int sum = 0; // Holds zipcode digit sum
                // Append First Frame Bar
                barCode.append("|");
                // Calculate Sum (for Check Digit) and Build Barcode per Each Zipcode Digit
                for (int i = 0; i < zipCode.length(); i++)
                {
                        sum += (int) zipCode.charAt(i);
                        barCode.append(digitToCode(zipCode.charAt(i)));
                }
                // Get and Append Check Digit
                barCode.append(digitToCode(Character.forDigit(getCheckDigit(sum), 10)));
                // Append Last Frame Bar
                barCode.append("|");
                // Return Barcode as a String
                return barCode.toString();
        }

        private String decode (String barCode)
        {
                // Define Variables
                StringBuilder zipCode = new StringBuilder(); // Holds decoded zipcode
                int chunk = 1; // Counter for code-digit chunks (groups of five characters)
                // Break into five character chunks, convert to digits, and append to string
                for (int i = 0; i < 5; i++)
                        {
                                zipCode.append(codeToDigit(barCode.substring(chunk, chunk + 5)));
                                chunk += 5;
                        }
                // Return Zipcode as a String
                return zipCode.toString();
        }
}
