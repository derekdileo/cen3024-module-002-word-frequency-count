package application;

public class Word implements Comparable<Word> {

	// Local variables
	private String word;
	private int frequency;

	// Constructor
	public Word(String key, int value) {
		super();
		this.word = key;
		this.frequency = value;
	}
	
	// Getters and Setters
	public String getKey() {
		return word;
	}

	public void setKey(String key) {
		this.word = key;
	}

	public int getValue() {
		return frequency;
	}

	public void setValue(int value) {
		this.frequency = value;
	}

	@Override
	public int compareTo(Word word) {
		return this.frequency - word.getValue();
	}

	@Override
	public String toString() {
		return "\nWord: " + word + "\t\tFrequency: " + frequency;
	}
	
	

}
