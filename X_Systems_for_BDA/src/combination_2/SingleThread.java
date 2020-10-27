package combination_2;

import java.util.ArrayList;

import combination.Combination;
import utils.MilanMultiKeyBinarySearch;


public class SingleThread extends Combination {

	private float[] customerPrice;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, double[] keys) {
		super(filename, lenFile, distinct, keys);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() {

		// ***** SELECTION ***** //

		// Read customer price

		// this.customerIdPrice =
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(customerPrice, 0, customerPrice.length - 1, getKeys(), 0, getKeys().length - 1);
		setSelection(mmkbs.getResults());

		// ***** PROJECTION ***** //

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		this.selection.add(result);
	}

	public void setSelection(ArrayList<Integer> results) {
		this.selection = results;
	}

	public void setCustomerPrice(float[] customerPrice) {
		this.customerPrice = customerPrice;
	}

}
