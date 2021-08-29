package application;

import java.io.IOException;
import java.net.URISyntaxException;

public class AppRunner {

	public static void main(String[] args) {
		
		// Scrape website to create text file
		try {
			InputStreamToFile.createFile();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}

}
