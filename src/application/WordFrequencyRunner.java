package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordFrequencyRunner {

	public static String[] readFile(String input) {

		try {

			FileReader reader = new FileReader(input);

			BufferedReader buffer = new BufferedReader(reader);

			String line = buffer.readLine();

			String[] words = line.split("\\b");

			int noOfWords = words.length;

			for (int i = 0; i < words.length; i++) {

				// Make all words lower case
				String lowerWord = words[i].toLowerCase();

				String[] lowerWords = null;

				lowerWords[i] = lowerWord;

			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println(fnfe.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// call toLowerCase()

		return null;

	}

	public static HashMap<String, Integer> wordFrequency(String[] words) {

		// Initialize HashMap
		HashMap<String, Integer> wordFrequencyHashMap = new HashMap();

		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
