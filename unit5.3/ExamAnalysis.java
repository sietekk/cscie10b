// ExamAnalysis.java

/** 
 *  Processes student answers to an exam, where the exam answer data is provided
 *  by an external data file (blank lines are skipped over) and the answer key
 *  is inputed by the user. Answer keys and the student answer data set are
 *  limited to 1-100 elements.
 *
 *  @author: Michael Conroy mconroy@g.harvard.edu
 *  @version: Last modified 03_22_15
 */

import java.util.*;
import java.io.*;

class QuestionNumberException extends Exception {}
class AnswerKeyException extends Exception {}
class DataInputLengthException extends Exception {}

public class ExamAnalysis
{
	public static void main (String [] args) throws FileNotFoundException
	{
        // Scanner object to read from keyboard
		Scanner keyboard = new Scanner (System.in);
        // Initialize answer key string
		String answerKey = null;
		System.out.println("I hope you are ready to begin ...\n");
        // Boolean flag to continue or end do...while loop asking for answer key
		boolean enterAgain = true;
        // Keep looping until valid answer key entered 
		do { 
			try {
				System.out.print("Please type the correct answers to the exam questions,\n\tone right after the other: ");
				answerKey = keyboard.nextLine();
                // Throw exception if answer key is greater than 100 elements
				if (answerKey.length() > 100) throw new QuestionNumberException();
                // Throw exception if characters other than A, B, C, D, or E are entered
                //      {1,100} is a redundant check on length of answer key and may not be necessary
				if (!answerKey.matches("[A-E]{1,100}")) throw new AnswerKeyException();
				enterAgain  = false;
			} catch(QuestionNumberException e) {
				System.out.println("\nCannot process more than 100 questions. Please re-enter...\n");
				enterAgain = true;
			} catch(AnswerKeyException e) {
				System.out.println("\nAnswer key can only contain the characters A, B, C, D and E. Please re-enter...\n");
				enterAgain = true;
			}
		} while (enterAgain == true);
        // Get file object
		File file = openFile(answerKey.length());
        // Print student answer list
		studentAnswerList(new Scanner(file), answerKey.length());
		System.out.print("\tHere's the analysis:\n\n\n");
        // Print student answer analysis
		studentAnswerAnalysis(new Scanner(file), answerKey, answerKey.length());
        // Print question analysis
		questionAnalysis(new Scanner(file), answerKey, answerKey.length());
        // Close keyboard scanner
		keyboard.close();
	}
	
	/**
	 * Opens the file a user specifies and returns as a File object.
	 * 
	 * @param  answerKeyLength       Answer key length (number of elements in answer key
	 * @return						 Returns File object
	 * @throws FileNotFoundException If the filename entered is not found
	 */
	public static File openFile(int answerKeyLength) throws FileNotFoundException
	{
        // Scanner object to read from keyboard
		Scanner keyboard = new Scanner(System.in);
        // Initialize file name string
		String fileName = null;
        // Initialize File object 
		File fileObj = null;
        // Holds file line length
		long fileLineLength = 0;
        // Boolean flag to continue or end do...while loop asking for file name
		boolean enterAgain = true;
		do {
			try {
				System.out.print("\nWhat is the name of the file containing each student's\n\tresponses to the " + answerKeyLength + " questions? ");
				fileName = keyboard.nextLine();
                // Instantiate File object
				fileObj = new File(fileName);
                // Instantiate Scanner from File object for counting line length
				Scanner fileLineCounter = new Scanner(fileObj);
				while (fileLineCounter.hasNextLine()) {
                    // Increment file line legnth counter as long as line is not empty
					if (fileLineCounter.nextLine().trim().length() != 0) {
						fileLineLength++;
					} else {
						continue;
					}
				}
                // Close file line counter Scanner
				fileLineCounter.close();
                // Throw exception is file line length is not between 1 and 100 lines (excluding empty lines)
				if (fileLineLength < 1 || fileLineLength > 100) throw new DataInputLengthException();
				enterAgain = false;
			} catch(FileNotFoundException e) {
				System.out.println("\nCould not find file \"" + fileName + "\". Please re-enter correct data file...\n");
				enterAgain = true;
			} catch(DataInputLengthException e) {
				System.out.println("\nCan only analyze 1 to 100 data entries. Please re-enter correct data file...\n");
                enterAgain = true;
			}
		} while (enterAgain == true);
        // Close keyboard Scanner
		keyboard.close();
		return fileObj;
	}

	/**
	 * Prints each students' answers found in data file.
	 * 
	 * @param file			   Scanner object created from File object passed in
	 * @param answerKeyLength Answer key length (number of elements in answer key
	 */
	public static void studentAnswerList(Scanner file, int answerKeyLength)
	{
        // Student counter
		int studentCounter = 1;
		System.out.println("\n");
		while (file.hasNextLine()) 
		{
			String line = file.nextLine();
            // Output student responses and iterate student counter for non-blank lines
			if (line.trim().length() != 0) {
				System.out.println("Student #" + studentCounter + "'s responses: " + line);
				studentCounter++;
			} else {
				continue;
			}
		}
		System.out.println("We have reached \"end of file!\"\n");
		System.out.print("Thank you for the data on " + (studentCounter-1) + " students.");
        // Close file Scanner
		file.close();
	}
	
	/**
	 * Calculates the correct, incorrect, and blank answers for each student
	 * 
	 * @param file			  Scanner object created from File object passed in
	 * @param answerKey		  Answer key string
	 * @param answerKeyLength Answer key length (number of elements in answer key
	 */
	public static void studentAnswerAnalysis(Scanner file, String answerKey, int answerKeyLength)
	{
        // Counter for student number for each student answer data analysis line
		int analysisCounter = 1;
        // Correct answer counter
		int correct = 0;
        // Incorrect answer counter
		int incorrect = 0;
        // Blank answer counter
		int blank = 0;
		System.out.println("Student #         Correct         Incorrect         Blank");
		System.out.println("~~~~~~~~~         ~~~~~~~         ~~~~~~~~~         ~~~~~");
		while (file.hasNextLine())
		{
			// Reset correct, incorrect, and blank answer counters
            correct = 0;
			incorrect = 0;
			blank = 0;
            // Assign next file line from Scanner file to student answer string
			String studentAnswers = file.nextLine();
            // Check student responses, output student responses, and increment analysis counter
            //      for non-blank file lines
			if (studentAnswers.trim().length() != 0) {
                // Iterate over each answer key element
				for (int i = 0; i < answerKeyLength; i++)
				{
                    // Get correct, blank, and incorrect answers for this student
					if (answerKey.charAt(i) == studentAnswers.charAt(i)) {
						correct += 1;
					} else if (studentAnswers.charAt(i) == ' ') {
						blank += 1;
					} else {
						incorrect +=1;
					}
				}
                // Print analysis data for this student
				System.out.printf(
						"    %d                %d                %d               %d\n",
						analysisCounter, correct, incorrect, blank
						);
                // Increment counter to next student
				analysisCounter++;
			} else {
				continue;
			}
		}
        // Close file Scanner
		file.close();
	}
	
	/**
	 * For each question: lists the correct answer, number of times each answer choice was selected
	 * and the percentage of students picking each answer choice.
	 * 
	 * @param file
	 * @param answerKey
	 * @param answerKeyLength
	 */
	public static void questionAnalysis(Scanner file, String answerKey, int answerKeyLength)
	{
        // Create ArrayList to hold each student's answers
		ArrayList<String> answersArray = new ArrayList<String>();
        // Initialize ArrayList size
		int answersArraySize = 0;
        // Counter that designates ArrayList location to enter a student's answers
		int answersArrayCounter = 0;
		System.out.println("\nQUESTION ANALYSIS (* marks the correct response)");
		System.out.println("~~~~~~~~~~~~~~~~~\n");
        // Populate ArrayList with student responses/answers
		while (file.hasNextLine()) {
			String studentAnswers = file.nextLine();
            // Ignore empty lines
			if (studentAnswers.trim().length() != 0) {
                // Assign a student's answers/responses to the next location in ArrayList
				answersArray.add(answersArrayCounter, studentAnswers);
				answersArrayCounter++;
			} else {
				continue;
			}
		}
        // Get ArrayList size
		answersArraySize = answersArray.size();
        // Iterate over each answer key element/question to compute analysis of that question
		for (int i = 0; i < answerKeyLength; i++) {
            // Initialize data array to hold number of times each answer choice was selected
            //      for each question
			int [] data = { 0, 0, 0, 0, 0, 0 };
            // Get correct answer for this question
			char correctAnswer = answerKey.charAt(i);
            // Print question number
			System.out.println("Question #" + (i + 1) + ":\n");
            // Print question header, which also designates the correct answer with an *
			System.out.println(returnHeader(correctAnswer));
            // Iterate over each ArrayList element (each element is String of a single
            //      students responses
			for (int j = 0; j < answersArraySize; j++) {
                // Get student's answer for this question
				char answer = answersArray.get(j).charAt(i);
                // Increment data array element corresponding to a students answer for
                //      this particular question
				if (answer != ' ') {
					switch (answer) {
						case 'A': data[0]++;
							break;
						case 'B': data[1]++;
							break;
						case 'C': data[2]++;
							break;
						case 'D': data[3]++;
							break;
						case 'E': data[4]++;
							break;
					}
				} else {
					data[5]++;
				}
			}
            // Print analysis for this question (count per answer choice and percentage of students
            //      who chose each answer choice)
			System.out.printf(
					"  %d     %d     %d     %d     %d       %d\n\n",
					data[0], data[1], data[2], data[3], data[4], data[5]
					);
			System.out.printf(
					" %4.1f%% %4.1f%% %4.1f%% %4.1f%% %4.1f%% %5.1f%%\n\n",
					((double)data[0]/(answersArraySize))*100,
					((double)data[1]/(answersArraySize))*100, 
					((double)data[2]/(answersArraySize))*100,
					((double)data[3]/(answersArraySize))*100, 
					((double)data[4]/(answersArraySize))*100,
					((double)data[5]/(answersArraySize))*100
					);
		}
        // Close file Scanner
		file.close();
	}

	/**
	 * For each correct answer, returns answer designation header to be printed to the screen.
	 * 
	 * @param correctAnswer The correct answer
	 * @return				Returns the corresponding answer designation header
	 */
	public static String returnHeader(char correctAnswer)
	{
		String header = "";
		switch (correctAnswer) {
			case 'A': header = "  A*    B     C     D     E     Blank\n";
				break;
			case 'B': header = "  A     B*    C     D     E     Blank\n";
				break;
			case 'C': header = "  A     B    C*     D     E     Blank\n";
				break;
			case 'D': header = "  A     B    C     D*     E     Blank\n";
				break;
			case 'E': header = "  A     B    C     D     E*     Blank\n";
				break;
		}
		return header;
	}
}
