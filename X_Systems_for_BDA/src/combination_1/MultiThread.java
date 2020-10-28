package combination_1;

import java.util.ArrayList;

import utils.BinarySearchMultiThread;
import combination.Combination;

public class MultiThread extends Combination {
	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys) {
		super(filename, lenFile, distinct, keys);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() throws InterruptedException {

		// ***** SELECTION ***** //
		BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[4];
		Thread myThreads[] = new Thread[4];

		for (int j = 0; j < getKeys().length; j++) {
			for (int i = 0; i < nbThreads; i++) {
				myBSMT[i] = new BinarySearchMultiThread(getLoadData().getCustomerPrice(),
						getLoadData().getCustomerPrice().length, getKeys()[i], part);
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

}
