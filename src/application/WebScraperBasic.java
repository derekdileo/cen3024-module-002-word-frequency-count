package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* A Class that will scrape text from given URL (while ignoring html tags)
 * and write resultant text to a file.
 * */

public class WebScraperBasic {

	
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

			// String variable to hold current line contents
			String line;

			while ((line = input.readLine()) != null) {

				// Strip html tags from line String
				String nohtml = line.toString().toLowerCase().replaceAll("\\<.*?>", "");

				// Split nohtml string, ignoring all but letters of alphabet and apostrophe (to
				// allow contractions)
				words = nohtml.split("[^a-zA-Z’]+");

				// For each: set all characters to lower case and add to ArrayList<String>
				// wordsList
				for (String word : words) {
					// Do not allow white blank white space (frequency = 30 and near top of list)
					// Works when pulling text from file. Not sure why it's not working here!!
					if (word.toString() != "" && word.toString() != " " && !word.toString().contains("mdash")
							&& !word.toString().contains("	")) {
						wordsList.add(word.toString());
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
	public static HashMap<String, Integer> wordFrequencyHM = new HashMap<String, Integer>();

	// Method to process String array created by readFile()
	public static HashMap<String, Integer> wordFrequencyCounter(ArrayList<String> words) {

		// loop through every word in array
		for (int i = 0; i < words.size(); i++) {

			// If HashMap contains the key, increment the value
			if (wordFrequencyHM.containsKey(words.get(i))) {
				// Get the current word frequency count
				int n = wordFrequencyHM.get(words.get(i));

				// Increment and replace frequency count
				wordFrequencyHM.put(words.get(i), ++n);
			}

			// Otherwise, place word in Hashmap, set count to 1
			else {
				wordFrequencyHM.put(words.get(i), 1);
			}
		}
		return wordFrequencyHM;
	}

	// Don't currently n
	// Method to parse HashMap key/value pairs and push to an ArrayList
	@SuppressWarnings("unused")
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

	// ArrayList to store individual words pulled from text file
	public static ArrayList<String> webScraperContentsAL;

	public static HashMap<String, Integer> webScraperContentsHM;

	public static void main(String[] args) {

		webScraperContentsAL = new ArrayList<String>(WebScraperBasic.getFileContents());

		webScraperContentsHM = new HashMap<String, Integer>(WebScraperBasic.wordFrequencyCounter(webScraperContentsAL));

	}
}
