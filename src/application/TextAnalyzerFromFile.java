package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextAnalyzerFromFile {

	// Declare filepath
	private static final String filepath = "/Users/derekdileo/Documents/Software Development/Workspaces/Java-Programming-For-Beginners/cen3024-module-002-word-frequency-count/src/application/poem.txt";

	// ArrayList to store individual words pulled from text file
	public static ArrayList<String> wordsList = new ArrayList<>();

	// Scanner for text file input
	static Scanner sc;

	// Method to parse text file into ArrayList<String> using File and Scanner
	public static ArrayList<String> getFileContents() {

		try {

			// Create file object from file at filepath location
			File file = new File(filepath);

			// Initialize scanner for text file input
			sc = new Scanner(file);

			// Initialize String array to hold strings from line.split()
			// line.split("[^a-zA-Z]+") ignores all characters except alphabetical letters
			String[] words = null;

			// Loop until end of poem
			while (sc.hasNextLine()) {

				// Create a string from current line
				String line = sc.nextLine();

				// Split string, ignoring all but letters of alphabet and apostrophe (to allow contractions)
				words = line.split("[^a-zA-Z’]+");

				for (String word : words) {
					// Set all characters to lower case and add to ArrayList<String> wordsList
					// Do not allow white blank white space (frequency = 30 and near top of list)
					if (word.toString() != "") {
					wordsList.add(word.toString().toLowerCase());
					}
				}
			}
			return wordsList;

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println(fnfe.getMessage());
		}
		return null;
	}

	// Declare HashMap with word=key, frequency=value
	public static HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();

	// Method to process String array created by readFile()
	public static HashMap<String, Integer> wordFrequencyCounter(ArrayList<String> words) {

		// loop through every word in array
		for (int i = 0; i < words.size(); i++) {

			// If HashMap contains the key, increment the value
			if (wordFrequency.containsKey(words.get(i))) {
				// Get the current word frequency count
				int n = wordFrequency.get(words.get(i));

				// Increment and replace count
				wordFrequency.put(words.get(i), ++n);
			}

			// Otherwise, place word in Hashmap, set count to 1
			else {
				wordFrequency.put(words.get(i), 1);
			}
		}
		return wordFrequency;
	}

	// Method to call getFileContents() and wordFrequencyCounter() methods
	private static HashMap<String, Integer> processTextInput() {
		ArrayList<String> wordsArrayList = getFileContents();

		return wordFrequencyCounter(wordsArrayList);
	}

	// Method to parse HashMap key/value pairs and push to an ArrayList
	private static ArrayList<Word> processHashMap(HashMap<String, Integer> hm) {

		// ArrayList to hold all new Word objects created prior to sorting by frequency
		// (value)
		ArrayList<Word> wordsUnsorted = new ArrayList<Word>();

		for (@SuppressWarnings("rawtypes")
		Map.Entry entry : hm.entrySet()) {

			// Pull key/value pairs for each word in wordFrequencyComplete HashMap
			String wordKey = entry.getKey().toString();
			String wordValue = entry.getValue().toString();
			int wordValueInt = Integer.parseInt(wordValue);

			// Use pairs to create new instances of Word class
			// (which implements Comparable<Word>)
			Word word = new Word(wordKey, wordValueInt);
			wordsUnsorted.add(word);
		}
		return wordsUnsorted;
	}
	
	// HashMap to hold all word/frequency (key/value) pairs prior to sorting by frequency (value)
	public static HashMap<String, Integer> wordFrequencyHashMap = new HashMap<String, Integer>();

	// ArrayList to hold all new Word objects created prior to sorting by frequency (value)
	private static ArrayList<Word> wordsArrayList;

	// Main method
	public static void main(String[] args) throws IOException {

		// Call getFileContents() and wordFrequencyCounter() methods
		wordFrequencyHashMap = processTextInput();

		wordsArrayList = new ArrayList<Word>();

		// Parse HashMap key/value pairs and push to an ArrayList
		wordsArrayList = processHashMap(wordFrequencyHashMap);

		// Sort wordsArrayList by frequency
		Collections.sort(wordsArrayList);

		// Reverse for highest frequency first
		Collections.reverse(wordsArrayList);

		// Print after sort
		System.out.println("\nSorted:");
		System.out.println(wordsArrayList.toString());
	}
}
