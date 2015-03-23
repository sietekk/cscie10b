// CaeserCipher.java

/**
 * Encrypts or decrypts a Caeser cipher string contained in an external file
 * and prints the resulting encryption or descryption to a second external
 * file (both files' names and shift bit specified by the user).
 * 
 * @author Michael Conroy, mconroy@g.harvard.edu
 * @version Last modified 03_22_15
 */

import java.util.*;
import java.io.*;

class DataInputLengthException extends Exception{}
class FileDoesNotExistException extends Exception{}

public class CaesarCipher
{
	public static void main(String [] args) throws FileNotFoundException
	{
		// Scanner object to read from keyboard
		Scanner keyboard = new Scanner (System.in);
		// Holds user selected run option (-1 to quit, 1 to descrypt, and 2 to encrypt)
		int runChoice;
		// Holds shift value
		int shift;
		System.out.println("Welcome to CaeserCipher");
		// Boolean flag to continue or end while loop, which contains main program
		boolean run = true;
		while (run == true) {
			System.out.print("\nEnter 1 to encipher, or 2 to decipher (-1 to exit): ");
			// Loop until user inputs a correct run option value
			while (!keyboard.hasNext("-1|1|2")) {
				System.out.println("\nOnly enter 1, 2, or -1! Please try again...\n");
				System.out.print("\nEnter 1 to encipher, or 2 to decipher (-1 to exit): ");
				keyboard.nextLine();
			}
			runChoice = Integer.parseInt(keyboard.nextLine());
			// Quit program (exit while loop and assign false value to run)
			if (runChoice == -1) {
				run = false;
				continue;
			}
			System.out.print("What shift should I use? ");
			// Loop until user inputs a correct shift value (1-26)
			while (!keyboard.hasNext("[1-9]|1[0-9]|2[0-6]")) {
				System.out.println("\nOnly enter an integer 1 though 26! Please try again...\n");
				System.out.print("What shift should I use? ");
				keyboard.nextLine();
			}
			shift = Integer.parseInt(keyboard.nextLine());
			// Create input file object
			File inputFileObj = openFile();
			// Create output file PrintWriter object (to output encrypted/decrypted data)
			PrintWriter outputFileObj = createFile();
			// Encrypt data
			if (runChoice == 1) {
				Scanner inputFile = new Scanner(inputFileObj);
				while (inputFile.hasNextLine()) {
					outputFileObj.println(caesarEncipher(inputFile.nextLine(), shift));
				}
			// Decrypt data
			} else {
				Scanner inputFile = new Scanner(inputFileObj);
				while (inputFile.hasNextLine()) {
					outputFileObj.println(caesarDecipher(inputFile.nextLine(), shift));
				}
			}
			// Close PrintWriter object to output encrypted/descrypted data to new file
			outputFileObj.close();
			System.out.println("DONE!");
		}
	}
	
	/**
	 * Encrypts a string using a Caeser cipher by the shift value.
	 * 
	 * @param input Input string to be encrypted
	 * @param shift Shift value to encrypt the string
	 * @return      Encrypted string
	 */
	public static String caesarEncipher(String input, int shift)
	{
		// Container for encrypted/decrypted and non-altered data
		StringBuilder encodedString = new StringBuilder();
		// Holds length of input string
		int inputLength = input.length();
		// Loop over length of string, and encrypt valid characters (lowercase a-z)
		for (int i = 0; i < inputLength; i++)
		{
			// Get character at current location
			char character = input.charAt(i);
			// Encrypt character at current location if valid characters (lowercase a-z)
			if (Character.toString(character).matches("[a-z]")) {
				int charASCII = character;
				charASCII = charASCII + (shift % 26);
	            if(charASCII > 'z') charASCII = charASCII - 26;
	            encodedString.append((char) charASCII);
	        // Append characters not eligible for encryption/descryption
			} else {
				encodedString.append(character);
			}
		}
		// Return encoded string
		return encodedString.toString();
	}
	
	/**
	 * Decrypts a string using a Caeser cipher by the shift value.
	 * 
	 * @param input Input string to be encrypted
	 * @param shift Shift value to encrypt the string
	 * @return      Decrypted string
	 */
	public static String caesarDecipher(String input, int shift)
	{
		// Container for encrypted/decrypted and non-altered data
		StringBuilder decodedString = new StringBuilder();
		// Holds length of input string
		int inputLength = input.length();
		// Loop over length of string, and decrypt valid characters (lowercase a-z)
		for (int i = 0; i < inputLength; i++)
		{
			// Get character at current location
			char character = input.charAt(i);
			// Decrypt character at current location if valid characters (lowercase a-z)
			if (Character.toString(character).matches("[a-z]")) {
				int charASCII = character;
				charASCII = charASCII - (shift % 26);
	            if(charASCII < 'a') charASCII = charASCII + 26;
	            decodedString.append((char) charASCII);
		        // Append characters not eligible for decryption/encryption
			} else {
				decodedString.append(character);
			}
		}
		// Return decoded string
		return decodedString.toString();
	}
	
	/**
	 * Opens input file as a File object.
	 * 
	 * @return File object returned
	 */
	public static File openFile()
	{
		// Scanner object to read from keyboard
		Scanner keyboard = new Scanner(System.in);
		// Filename of file to open
		String fileName = null;
		// Initialize File object to hold and return openend file
		File fileObj = null;
		// Boolean flag to continue or end while loop, which reads in file 
		boolean enterAgain = true;
		while (enterAgain == true) {
			try {
				System.out.print("What is the input file name? ");
				// Get file name
				fileName = keyboard.nextLine();
				// Instantiate File object with filename specified
				fileObj = new File(fileName);
				// Throw exception if file doesn't exist
				if (!fileObj.exists()) throw new FileDoesNotExistException();
				// Throw exception if empty file
				if (fileObj.length() == 0) throw new DataInputLengthException();
				enterAgain = false;
			} catch (FileDoesNotExistException e) {
				System.out.println("\nCould not find file \"" + fileName + "\". Please enter valid file name...\n");
				enterAgain = true;
			} catch (DataInputLengthException e) {
				System.out.println("\nFile \"" + fileName + "\" has no data. Please enter name of valid file...\n");
				enterAgain = true;
			}
		}
		// Return File object
		return fileObj;
	}
	
	/**
	 * Creates new PrintWriter object to output encrypted/descrypted data.
	 * 
	 * @return PrintWriter object returned
	 */
	public static PrintWriter createFile()
	{
		// Scanner object to read from keyboard
		Scanner keyboard = new Scanner(System.in);
		// Filename of file to open
		String fileName = null;
		// Initialize PrintWriter object
		PrintWriter newFile = null;
		// Boolean flag to continue or end while loop, which instantiates PrintWriter object 
		boolean enterAgain = true;
		while (enterAgain == true) {
			try {
				System.out.print("What is the output file name? ");
				// Get file name
				fileName = keyboard.nextLine();
				// Instantiate PrintWriter object
				newFile = new PrintWriter(fileName);
				enterAgain = false;
			// Exception if PrintWriter cannot create file
			} catch (FileNotFoundException e) {
				System.out.println("\nCould not create file \"" + fileName + "\". Please enter valid file name...\n");
				enterAgain = true;
			}
		}
		// Return PrintWriter object
		return newFile;
	}
}
