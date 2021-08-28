package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileInput {

	// Declare filepath
	private static final String filepath = "/Users/derekdileo/Documents/Software Development/Workspaces/Java-Programming-For-Beginners/cen3024-module-002-word-frequency-count/src/application/poem.txt";

	// Initialize local variables
	private static BufferedReader buffer;

	// Declare the HashMap
	public static HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();
	public static HashMap<String, Integer> wordFrequencyComplete = new HashMap<String, Integer>();
	

	public static ArrayList<String> wordsList = new ArrayList<>();
	
	static Scanner sc;
	
	
	// Method to split file input into array of Strings using File and Scanner
	
	public static ArrayList<String> getFileContents() {
		
		try {
		
		File file = new File(filepath);
		
		sc = new Scanner(file);
		
		String[] words = null;
		
		ArrayList<String> wordsList = new ArrayList<String>();
			
		/*
		 * while (sc.hasNextLine()) {
		 * 
		 * String line = sc.nextLine();
		 * 
		 * // Split each line into String array by word boundary \b // This will avoid
		 * including extra characters/punctuation
		 * 
		 * words = line.split("\\b"); wordsList.addAll(words);
		 * 
		 * //System.out.println(words);
		 * 
		 * }
		 */
		
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			words = line.split("[^a-zA-Z]+");
			
			for(String word : words) {
				word.toString().toLowerCase();
				wordsList.add(word);
			}
			
			//words.toString().toLowerCase();
		
		}
		System.out.println("wordsList.toString(): ");
		System.out.println(wordsList.toString());
		
			return wordsList;
			
		} catch(FileNotFoundException fnfe) {
			
		}
	
		return null;
	}
	
	
	
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
			
			//System.out.println("\nwordFreqyencyCounter() entry: " + i + " -  " + words[i].toString());
		
			
		}
		
		return wordFrequency;
	} 
	
	
	/*
	 * public static HashMap<String, Integer> sortByValue(HashMap<String, Integer>
	 * hashMap) {
	 * 
	 * // Create a list from elements of Hashmap List<Map.Entry<String, Integer> >
	 * list = new LinkedList<Map.Entry<String, Integer> > (hashMap.entrySet());
	 * 
	 * // Sort the list Collections.sort(list, new Comparator<Map.Entry<String,
	 * Integer> > () {
	 * 
	 * public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer,
	 * V> o2) {
	 * 
	 * return (o1.getValue()).compareTo(o2.getValue()); }
	 * 
	 * @Override public int compare(Entry<String, Integer> o1, Entry<String,
	 * Integer> o2) { // TODO Auto-generated method stub return 0; }
	 * 
	 * });
	 */
		
		
	/*
	 * HashMap<String, Integer> tempHashMap = new LinkedHashMap<String, Integer>();
	 * 
	 * return tempHashMap; }
	 */
	
	

	public static void main(String[] args) throws IOException {
		
		
		ArrayList<String> wordsArrayList = getFileContents();
		
		wordFrequencyComplete = wordFrequencyCounter(wordsArrayList);
		
		//Collections.sort(wordFrequencyComplete);
		
		
		
		  for(Map.Entry entry: wordFrequencyComplete.entrySet()) {
		  System.out.println(entry.getKey() + " " + entry.getValue()); }
		 
	}
}
