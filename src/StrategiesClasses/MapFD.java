package StrategiesClasses;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class implements the Map/Hash table strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Irsaris Perez Rodriguez
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class MapFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public MapFD() {
		super("Map");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a Hash table. It uses a
	 * Hash table to count the frequency of each elements inside dataSet instead of
	 * Map.Entry like the previous strategies in this experiment.
	 * 
	 * @param dataSet : The Data Set to count and distribute the frequency of its elements
	 * @return results : The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		ArrayList<Entry<E, Integer>> results = new ArrayList<Entry<E, Integer>>();
		Map<E, Integer> m = new Hashtable<E, Integer>();
		for(E e : dataSet) {
			if(m.containsKey(e)) {
				// If key already in the map, increase its value/frequency
				m.put(e, m.get(e)+1);
			}
			else {
				// Otherwise create a new entry with value equal to 1
				m.put(e, 1);
			}
		}
		
		// Get all map entries and put them in an ArrayList
		for(Map.Entry<E, Integer> entry : m.entrySet()) {
			results.add(entry);
		}
		return results; 
	}

}
