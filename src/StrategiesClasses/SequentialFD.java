package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class corresponds to the Sequential strategy to count frequencies in an
 * array list.
 * @author Fernando J. Bermudez && Irsaris Perez Rodriguez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SequentialFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public SequentialFD() {
		super("Sequential");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a regular ArrayList. It
	 * uses Map.Entry to count the frequency of each elements inside dataSet one by
	 * one without any sorting or re-arrangement of the elements.
	 * 
	 * @param dataSet : The Data Set to count and distribute the frequency of its elements
	 * @return results : The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {

		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		for (E e : dataSet) {
			// boolean flag for when an entry is modified or added 
			boolean entryFound = false;
			for (int i = 0; i < results.size() && !entryFound; i++) {
				Map.Entry<E, Integer> entry = results.get(i);
				if (entry.getKey().equals(e)) {
					// if the key from the current entry equals the current value for the data set,
					// then increase frequency for that entry by 1
					entry.setValue(entry.getValue() + 1);
					entryFound = true;
				}
			}
			if (!entryFound) {
				// need to create a new entry for first instance found of object e
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry(e, 1);
				results.add(entry);
			}
		}
		return results;
	}

}
