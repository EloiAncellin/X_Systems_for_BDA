package combination_2;

import java.util.ArrayList;

import utils.MilanMultiKeyBinarySearchMultiThread;
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
      	mmkbsmt[i] = new MilanMultiKeyBinarySearchMultiThread(customerPrice, keys, customerPrice.length, part);
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
	
	public void setCustomerPrice(int[] customerPrice) {
		this.customerPrice = customerPrice;
	}


}
