package combination_1;

import java.util.ArrayList;

import utils.BinarySearchMultiThread;
import utils.LoadData;

public class MultiThread {
	private String filename;
	private int lenFile;
	private Boolean distinct;
	private int[] keys;
	private LoadData loadData;
	private float[] customerPrice;
	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public MultiThread(String filename, int lenFile, Boolean distinct, int[] keys) {
		this.filename = filename;
		this.lenFile = lenFile;
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() throws InterruptedException {

		// ***** SELECTION ***** //

		// Read customer price

		// this.customerIdPrice =
		BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[4];
		Thread myThreads[] = new Thread[4];

		for (int j = 0; j < keys.length; j++) {
			for (int i = 0; i < nbThreads; i++) {
				myBSMT[i] = new BinarySearchMultiThread(customerPrice, customerPrice.length, keys[i], part);
				myThreads[i] = new Thread(myBSMT[i]);
				myThreads[i].start();
				part++;
			}
			for (int i = 0; i < nbThreads; i++) {
				myThreads[i].join();
				if (myBSMT[i].getResult() != 0) {
					addSelection(myBSMT[i].getResult());
				}
			}
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
