package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextAnalyzerFromWeb {

	// ArrayList to store individual words pulled from text file
	public static ArrayList<String> wordsList = new ArrayList<>();

	
	// Method to parse text file into ArrayList<String> using File and Scanner
	public static ArrayList<String> getFileContents() {

		try {
			// Declare URL
			URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");

			// Read text returned by server
			BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

			// Initialize String array to hold strings from line.split()
			String[] words = null;
			
			// Counter to find lines where poem starts and finishes on site
			int lineCount = 0;
			
			// Create a string from current line and iterate over until end
			String line;
			while ((line = input.readLine()) != null) {
				
				lineCount++;

				// Poem occurs on site between these lines
				if (lineCount>= 68 && lineCount<= 242) {
					
					String nohtml = line.toString().toLowerCase().replaceAll("\\<.*?>", "");
					//System.out.println(nohtml + ": " + lineCount);
					
					// Split string, ignoring all but letters of alphabet and apostrophe (to allow contractions)
					words = nohtml.split("[^a-zA-Z’]+");

					// For each: set all characters to lower case and add to ArrayList<String> wordsList
					for (String word : words) {
						// Do not allow white blank white space (frequency = 30 and near top of list)
						// Works when pulling text from file. Not sure why it's not working here!!
						if (word.toString() != "" && word.toString() != " " && !word.toString().contains("mdash") && !word.toString().contains("	")) {
							wordsList.add(word.toString().toLowerCase());
						}
					}
				}
			}
			return wordsList;

		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: ");
			System.out.println(me.getMessage());
			me.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("IOException: ");
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
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

				// Increment and replace frequency count
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

	// HashMap to hold all word/frequency (key/value) pairs prior to sorting by
	// frequency (value)
	public static HashMap<String, Integer> wordFrequencyHashMap = new HashMap<String, Integer>();

	// ArrayList to hold all new Word objects created prior to sorting by frequency
	// (value)
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
