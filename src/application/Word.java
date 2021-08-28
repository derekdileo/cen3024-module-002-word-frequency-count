package application;

public class Word implements Comparable<Word> {

	// Local variables
	private String key;
	private int value;

	// Constructor
	public Word(String key, int value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	// Getters and Setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Word word) {
		return this.value - word.getValue();
	}

}
