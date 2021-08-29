package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
				
				// Counter to find lines where poem starts and finishes on site
				int lineCount = 0;
				
				// Create String from current line and iterate through end
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
								wordsList.add(word.toString());
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
	
	
}
