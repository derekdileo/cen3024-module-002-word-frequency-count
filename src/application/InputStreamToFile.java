package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/* A Class that will scrape text from given URL (while ignoring html tags)
 * and write resultant text to a file.
 * */

public class InputStreamToFile {

	public static final int DEFAULT_BUFFER_SIZE = 4096;
	public static final String filepath = "/Users/derekdileo/Documents/Software Development/Workspaces/Java-Programming-For-Beginners/cen3024-module-002-word-frequency-count/src/application/scrape.txt";
	public static final String site = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
	
    public static void createFile() throws IOException, URISyntaxException {

    	// Declare URL & filepath
    	URI u = new URI(site);
        try (InputStream inputStream = u.toURL().openStream()) {

            File file = new File(filepath);

            copyInputStreamToFile(inputStream, file);

        }

    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }
	
}
