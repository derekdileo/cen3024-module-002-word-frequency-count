package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileInput {

	// Declare filepath
	private static final String filepath = "/Users/derekdileo/Documents/Software Development/Workspaces/Java-Programming-For-Beginners/cen3024-module-002-word-frequency-count/src/application/poem.txt";

	// Initialize local variables
	private static BufferedReader buffer;

	// Declare the HashMap
	public static HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();
	public static HashMap<String, Integer> wordFrequencyComplete = new HashMap<String, Integer>();
	

	// Method to split file input into array of Strings
	public static String[] readFile() throws IOException {

		// Prepare to read file at filepath
		FileReader reader = new FileReader(filepath);

		// BufferedReader to read file line-by-line
		buffer = new BufferedReader(reader);

		String line = buffer.readLine();

		// Split each line into String array by word boundary \b
		// This will avoid including extra characters/punctuation
		String[] words = line.split("\\b");

		return words;

	}

	// Method to process String array created by readFile()
	public static HashMap<String, Integer> wordFrequencyCounter(String[] words) {
		
		// loop through every word in array
		for (int i = 0; i < words.length; i++) {
			
			// If HashMap contains the key, increment the value
			if (wordFrequency.containsKey(words[i])) {
				// Get the current word frequency count
				int n = wordFrequency.get(words[i]);
				
				// Increment and replace count
				wordFrequency.put(words[i], ++n);
			} 
		
			// Otherwise, place word in Hashmap, set count to 1
			else {
				wordFrequency.put(words[i], 1);
			}
			
			System.out.println("\nwordFreqyencyCounter() entry: " + i + " -  " + words[i].toString());
		
			
		}
		
		return wordFrequency;
	} 
	
	
	public static void processWordFrequencyCounter(HashMap<String, Integer> hashMap) throws IOException {
		
		
		
		
	}
	
	

	public static void main(String[] args) throws IOException {
		
		String[] wordsArray = readFile();
		
		wordFrequencyComplete = wordFrequencyCounter(wordsArray);
		
		
		for(Map.Entry entry: wordFrequencyComplete.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
	}
}
