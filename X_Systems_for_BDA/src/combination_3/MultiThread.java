package combination_3;

import java.util.ArrayList;

import combination.Combination;
import utils.MilanMultiKeyBinarySearchMultiThread;


public class MultiThread extends Combination{

	private float[] customerPrice;
	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys) {
		super(filename, lenFile, distinct, keys);
	}

	
	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() throws InterruptedException {
		
		//***** SELECTION *****//
		
		
		// Read customer price

		// this.customerIdPrice =
	  Thread myThreads[] = new Thread[4];
      MilanMultiKeyBinarySearchMultiThread mmkbsmt[] = new MilanMultiKeyBinarySearchMultiThread[4];

      for (int i = 0; i < nbThreads ; i++) {
      	mmkbsmt[i] = new MilanMultiKeyBinarySearchMultiThread(customerPrice, getKeys(), customerPrice.length, part);
      	myThreads[i] = new Thread(mmkbsmt[i]);
          myThreads[i].start();
          part++;
		}     
      for (int i = 0; i < nbThreads ; i++) {
          myThreads[i].join();
          if(mmkbsmt[i].getResults() != null) {
        	  addSelection(mmkbsmt[i].getResults());
          }
		}
      
      
      // ***** PROJECTION ***** //
      
      // ***** AGGREGATION ***** //
	}

	public void addSelection(ArrayList<Integer> selection) {
		this.selection.addAll(selection);
	}
	

	public void setCustomerPrice(float[] customerPrice) {
		this.customerPrice = customerPrice;
	}

}
