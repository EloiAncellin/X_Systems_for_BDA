package combination_1;

import java.util.ArrayList;

import combination.Combination;
import utils.BinarySearch;

public class SingleThread extends Combination {
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, double[] keys) {
		super(filename, lenFile, distinct, keys);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION : Hashing based projection
	// AGGREGATION :
	public void start_combination() {
		
		getLoadData().read();
		// ***** SELECTION ***** //
		for (int i = 0; i < getKeys().length; i++) {
			addSelection(BinarySearch.binarySearch(getLoadData().getCustomerPrice(), 0,
					getLoadData().getCustomerPrice().length - 1, getKeys()[i]));
		}

		// ***** PROJECTION ***** //

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		if (result != -1) {
			this.selection.add(result + 1);
		}
	}

}
