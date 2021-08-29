package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AppRunner {
	
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
		
		wordsArrayListStrings = new ArrayList<String>(TextAnalyzer.formatFile());
		
		wordFrequencyHashMap = new HashMap<String, Integer>(WordFrequencyAnalyzer.wordFrequencyCounter(wordsArrayListStrings));
		
		wordsArrayListWords = new ArrayList<Word>(WordFrequencyAnalyzer.processHashMap(wordFrequencyHashMap));
		
		// Sort wordsArrayList by frequency
		Collections.sort(wordsArrayListWords);

		// Reverse for highest frequency first
		Collections.reverse(wordsArrayListWords);

		// Print after sort
		System.out.println("\nSorted:");
		
		int size = wordsArrayListWords.size();
		
		for(int j = 1; j <= size; j++) {
			
			System.out.println("#" + j + ": " + wordsArrayListWords);
			
		}
		
		//System.out.println(wordsArrayListWords.toString());
		
		
	}

}
