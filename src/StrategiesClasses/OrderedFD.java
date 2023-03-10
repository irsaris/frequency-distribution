package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * This class implements the Ordered strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Irsaris Perez Rodriguez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class OrderedFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public OrderedFD() {
		super("Ordered");
	}

	/**
	 * Method that counts the frequency of a dataSet with a SORTED COPY of dataSet.
	 * It uses Map.Entry to count the frequency of each elements inside dataSet.
	 * 
	 * @param dataSet : The Data Set to count and distribute the frequency of its elements
	 * @return results : The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		// Sort dataSet first
		dataSet.sort(null);
		ArrayList<Entry<E, Integer>> results = new ArrayList<Entry<E, Integer>>();
		int resultIndex = -1; // Points to nothing at first, only when an entry is added it points to 0
		
		for(int i = 0; i < dataSet.size(); i++) {
			if(i > 0 && dataSet.get(i-1).equals(dataSet.get(i))) {
				// If value is not 0 and equal to the one before, since its sorted
				// then add 1 to the frequency of that key
				results.get(resultIndex).setValue(results.get(resultIndex).getValue() + 1);
			}
			else {
				// Otherwise then just create a new entry for that key with frequency 1
				results.add(new AbstractMap.SimpleEntry<E, Integer>(dataSet.get(i), 1));
				resultIndex++;
			}
		}
		return results; 
	}

}
