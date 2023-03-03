package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a mapping of labels to program addresses
 *
 * @author Birkbeck
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if(labels.containsKey(label)){
			System.err.println("Error in program: Duplicate label found (" + label + ")");
			System.exit(-1);
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.

		// if the label is not found in the HashMap a null is returned
		// primitive types, in this case int, cannot have null values
		// and so a NullPointerException is thrown

		try
		{
			int address = labels.get(label);
			return address;
		}
		catch(NullPointerException e)
		{
			System.err.println("Error in program: No such label found (" + label + ")");
			System.exit(-1);
		}
		return -1;
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream().map(e -> e.getKey() + "->" + e.getValue()).collect(Collectors.toSet()).toString();
	}

	// TODO: Implement equals and hashCode (needed in class Machine).

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
