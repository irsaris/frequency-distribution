package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import DataStructures.SortedList.*;

/**
 * This class implements the SortedList strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Irsaris Perez Rodriguez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SortedListFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	/**
	 * Our SortedList classes require the data type to be Comparable.
	 * However, Map.Entry and AbstractMap.SimpleEntry are not Comparable,
	 * so we extend AbstractMap.SimpleEntry and create a Comparable
	 * version that we can use with our SortedList.
	 * Note: The K (key) of this class will be the E of SortedListFD,
	 *       so it will be Comparable.
	 * @author Fernando J. Bermudez
	 *
	 * @param <K>  The type of the key of each entry
	 * @param <V>  The type of the value of each entry
	 */
	@SuppressWarnings("serial")
	private static class ComparableEntry<K extends Comparable<K>, V> extends AbstractMap.SimpleEntry<K, V>
																	 implements Comparable<Map.Entry<K, V>> {

		public ComparableEntry(K key, V value) {
			super(key, value);
		}

		@Override
		public int compareTo(Map.Entry<K, V> entry) {
			/* Entries will be compared based on their keys, which are Comparable */
			return getKey().compareTo(entry.getKey());
		}

	} // End of ComparableEntry class

	/* Constructor */
	public SortedListFD() {
		super("SortedList");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a SortedList. It uses
	 * Map.Entry to count the frequency of each elements inside dataSet.
	 * 
	 * @author Irsaris Perez Rodriguez
	 * @param dataSet : The Data Set to count and distribute the frequency of its elements
	 * @return results : The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		SortedArrayList<ComparableEntry<E, Integer>> sorted = new SortedArrayList<ComparableEntry<E, Integer>>(dataSet.size());
		
		for (E e : dataSet) {
			boolean added = false;
			
			// Stops if a value is added or the frequency is increased 
			for (int i = 0; i < sorted.size() && !added; i++) {
				ComparableEntry<E, Integer> entry = sorted.get(i);
				if (entry.getKey().compareTo(e) == 0) {
					// Checks if e is in sorted and increase frequency for that entry
					entry.setValue(entry.getValue() + 1);
					added = true;
				}
				else if(entry.getKey().compareTo(e) > 0) {
					// Check if current entry is bigger than e
					// If so a new entry for e must be created since it is not in sorted list
					ComparableEntry<E, Integer> ce = new ComparableEntry<E, Integer>(e, 1);
					sorted.add(ce);
					added = true;
				}
			}
			
			// If value was never either increased or added due to the sorted constraints 
			// then add a new entry for e
			if(!added) {
				ComparableEntry<E, Integer> ce = new ComparableEntry<E, Integer>(e, 1);
				sorted.add(ce);
			}
		}
		
		// Copy frequencies over from sorted to results
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		for(int i = 0; i < sorted.size(); i++) {
			results.add(sorted.get(i));
		}
		return results;	
	}

}