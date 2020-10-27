package combination_1;

import java.util.ArrayList;

import combination.Combination;
import utils.BinarySearch;


public class SingleThread extends Combination {
	private float[] customerPrice;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, double[] keys) {
		super(filename, lenFile, distinct, keys);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() {

		// ***** SELECTION ***** //

		// Read customer price

		// this.customerIdPrice =
		for (int i = 0; i < getKeys().length; i++) {
			addSelection(BinarySearch.binarySearch(customerPrice, 0, customerPrice.length - 1, getKeys()[i]));
		}

		// ***** PROJECTION ***** //

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		this.selection.add(result);
	}

	public void setCustomerPrice(float[] customerPrice) {
		this.customerPrice = customerPrice;
	}
}
