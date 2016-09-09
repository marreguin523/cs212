import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Data structure to store strings and their positions.
 */
public class WordIndex {

	/**
	 * Stores a mapping of words to the positions the words were found.
	 */
	private Map<String, Set<Integer>> index;

	/**
	 * Initializes the index.
	 */
	public WordIndex() {
		/*
		 * TODO: Choose the best data structures. Keep in mind you do not want
		 * duplicates, and do not need to store anything in sorted order.
		 */
		setIndex(new HashMap<String, Set<Integer>>());
	}

	/**
	 * Adds the word and the position it was found to the index.
	 *
	 * @param word
	 *            word to clean and add to index
	 * @param position
	 *            position word was found
	 */
	public void add(String word, int position) {
		/*
		 * TODO: Make sure you initialize any inner data structures.
		 */
		if(this.getIndex() != null) {
			if(this.contains(word) == true) {
				this.getIndex().get(word).add(position);
			} else {
				Set<Integer> values = new HashSet<Integer>();
				values.add(position);
				this.getIndex().put(word, values);
			}
		} else {
			Set<Integer> values = new HashSet<Integer>();
			values.add(position);
			this.getIndex().put(word, values);
		}

		//check if key already exists
		//if not then create set and add the pair in
		//if so then add the position into the set
	}

	/**
	 * Returns the number of times a word was found (i.e. the number of
	 * positions associated with a word in the index).
	 *
	 * @param word
	 *            word to look for
	 * @return number of times the word was found
	 */
	public int count(String word) {
		/*
		 * TODO: Return the count.
		 */
		if(this.getIndex().containsKey(word) == true) {
			return this.getIndex().get(word).size();
		}
		return 0;
		
	}

	/**
	 * Returns the number of words stored in the index.
	 * 
	 * @return number of words
	 */
	public int words() {
		/*
		 * TODO: Return number of words. No counting is necessary!
		 */
		
		return this.getIndex().size();
	}

	/**
	 * Tests whether the index contains the specified word.
	 * 
	 * @param word
	 *            word to look for
	 * @return true if the word is stored in the index
	 */
	public boolean contains(String word) {
		/*
		 * TODO: Return whether the word is in the index.
		 */
		if(this.getIndex() != null) {
			if(this.getIndex().containsKey(word)==true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a copy of the words in this index as a sorted list.
	 * 
	 * @return sorted list of words
	 */
	public List<String> getWords() {
		/*
		 * TODO: Create a copy of the words in the index as a list, and sort
		 * before returning.
		 */
		List<String> sorted = new ArrayList<String>(this.getIndex().keySet());
		java.util.Collections.sort(sorted);
		return sorted;
	}

	/**
	 * Returns a copy of the positions for a specific word.
	 * 
	 * @param word
	 *            to find in index
	 * @return sorted list of positions for that word
	 */
	public List<Integer> getPositions(String word) {
		/*
		 * TODO: Create a copy of the positions for the word, and sort before
		 * returning.
		 */
		List<Integer> sorted = new ArrayList<Integer>(this.getIndex().get(word));
		java.util.Collections.sort(sorted);
		return sorted;
	}

	/**
	 * Returns a string representation of this index.
	 */
	@Override
	public String toString() {
		return getIndex().toString();
	}

	public Map<String, Set<Integer>> getIndex() {
		return index;
	}

	public void setIndex(Map<String, Set<Integer>> index) {
		this.index = index;
	}
}
