package combination_1;

import java.util.ArrayList;

import utils.BinarySearch;
import utils.LoadData;

public class SingleThread {

	private String filename;
	private int lenFile;
	private Boolean distinct;
	private int[] keys;
	private LoadData loadData;
	private int[] customerPrice;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, int[] keys) {
		this.filename = filename;
		this.lenFile = lenFile;
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() {

		// ***** SELECTION ***** //

		// Read customer price

		// this.customerIdPrice =
		for (int i = 0; i < keys.length; i++) {
			addSelection(BinarySearch.binarySearch(customerPrice, 0, customerPrice.length - 1, keys[i]));
		}

		// ***** PROJECTION ***** //

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		this.selection.add(result);
	}

	public void setCustomerPrice(int[] customerPrice) {
		this.customerPrice = customerPrice;
	}
}
