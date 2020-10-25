package combination_2;

import java.util.ArrayList;

import utils.MilanMultiKeyBinarySearch;
import utils.LoadData;

public class SingleThread {

	private String filename;
	private int lenFile;
	private Boolean distinct;
	private int[] keys;
	private LoadData loadData;
	private int[][] customerIdPrice;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, int[] keys) {
		this.filename = filename;
		this.lenFile = lenFile;
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename);
	}
	

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() {

		// ***** SELECTION ***** //

		// Read customer price

		// this.customerIdPrice =
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(customerIdPrice, 0, customerIdPrice.length - 1, keys, 0, keys.length - 1);
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

	public void setCustomerIdPrice(int[][] customerIdPrice) {
		this.customerIdPrice = customerIdPrice;
	}

}
