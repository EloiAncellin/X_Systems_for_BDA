package combination_3;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.BasicHashSet;
import utils.MilanMultiKeyBinarySearchMultiThread;
import utils.MultiThreadProjection;
import utils.MultiThreadProjectionSort;
import utils.Projection;

public class MultiThread extends Combination {

	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private Projection prj;
	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>(); 
	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys,colnames,nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search
	
	public void start_combination() throws InterruptedException {

		System.out.println("Debut :"+System.nanoTime());
		getLoadData().read();
		System.out.println("Read :"+System.nanoTime());
		
		// ***** SELECTION *****//
		Thread myThreads[] = new Thread[4];
		MilanMultiKeyBinarySearchMultiThread mmkbsmt[] = new MilanMultiKeyBinarySearchMultiThread[4];

		for (int i = 0; i < nbThreads; i++) {
			mmkbsmt[i] = new MilanMultiKeyBinarySearchMultiThread(getLoadData().getCustomerPrice(), getKeys(),
					getLoadData().getCustomerPrice().length, part, getLenFile());
			myThreads[i] = new Thread(mmkbsmt[i]);
			myThreads[i].start();
			part++;
		}
		for (int i = 0; i < nbThreads; i++) {
			myThreads[i].join();
			if (mmkbsmt[i].getResults() != null) {
				addSelection(mmkbsmt[i].getResults());
			}
		}
       System.out.println(selection.size());
       System.out.println("Selection :"+System.nanoTime());

		// ***** PROJECTION ***** //
		
	 //	System.out.println("th " + super.getNbThreads());
		this.prj = new Projection(super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName());
		MultiThreadProjectionSort myPMT[] = new MultiThreadProjectionSort[super.getNbThreads()];
		part = 0;
		for (int i = 0; i < super.getNbThreads(); i++) {

			myPMT[i] = new MultiThreadProjectionSort(selection, super.getColnames(), super.getDistinct(),
					super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName(), part, selection.size());
			myThreads[i] = new Thread(myPMT[i]);
			myThreads[i].start();
			myThreads[i].join();
			part++;
			
			
		}
     
        projection = myPMT[super.getNbThreads()-1].getprojection();
		System.out.println("project = " + projection);

		System.out.println("Projection  :"+System.nanoTime());


		// ***** AGGREGATION ***** //
	}

	public void addSelection(ArrayList<Integer> selection) {
		this.selection.addAll(selection);
	}
	
}
