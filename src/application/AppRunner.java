package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AppRunner {
	
	// Local Lists and Maps to hold return values from Class methods
	public static ArrayList<String> wordsArrayListStrings;
	public static HashMap<String, Integer> wordFrequencyHashMap;
	public static ArrayList<Word> wordsArrayListWords;

	public static void main(String[] args) {
		
		// Scrape website to create text file
		try {
			WebScraperInputStreamToFile.createFile();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ArrayList of Strings returned from local file
		wordsArrayListStrings = new ArrayList<String>(TextAnalyzer.formatFile());
		
		// Process ArrayList and move into HashMap with key = Word and value = frequency of Word 
		wordFrequencyHashMap = new HashMap<String, Integer>(WordFrequencyAnalyzer.wordFrequencyCounter(wordsArrayListStrings));
		
		// Process HashMap<Word> and return to ArrayList<Word> to be sorted
		wordsArrayListWords = new ArrayList<Word>(WordFrequencyAnalyzer.processHashMap(wordFrequencyHashMap));
		
		// Sort wordsArrayList by frequency
		Collections.sort(wordsArrayListWords);

		// Reverse for highest frequency first
		Collections.reverse(wordsArrayListWords);

		// Print after sort
		System.out.println("\nSorted:");
		
		for (Word word : wordsArrayListWords) {
			
			// Get value of index location to pass into Word.toString(int index)
			int index = wordsArrayListWords.indexOf(word);
			
			// Print each Word in wordsArrayListWords
			System.out.println(wordsArrayListWords.get(index).toString(index));

		}
		
	}

}
