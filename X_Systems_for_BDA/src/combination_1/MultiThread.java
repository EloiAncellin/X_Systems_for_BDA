package combination_1;

import java.util.ArrayList;
import java.util.Hashtable;


import combination.Combination;
import utils.BasicHashSet;
import utils.BinarySearchMultiThread;
import utils.MultiThreadProjection;
import utils.Projection;

public class MultiThread extends Combination {
	private int part = 0;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	Hashtable<String, ArrayList<?>> projection = new Hashtable<>();
	private Projection prj;

	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() throws InterruptedException {
		
		System.out.println("Debut :"+System.nanoTime());
		getLoadData().read();
		System.out.println("Read :"+System.nanoTime());
		
		// ***** SELECTION ***** //
		BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[super.getNbThreads()];
		Thread myThreads[] = new Thread[super.getNbThreads()];

		for (int j = 0; j < getKeys().length; j++) {
			for (int i = 0; i < super.getNbThreads(); i++) {
				myBSMT[i] = new BinarySearchMultiThread(getLoadData().getCustomerPrice(),
						getLoadData().getCustomerPrice().length, getKeys()[j], part);
				myThreads[i] = new Thread(myBSMT[i]);
				myThreads[i].start();
				part++;
			}
			for (int i = 0; i < super.getNbThreads(); i++) {
				myThreads[i].join();
				if (myBSMT[i].getResult() != -1) {
					addSelection(myBSMT[i].getResult());
				}
			}
			part = 0;
		}
		System.out.println("Selection :"+System.nanoTime());
		// ***** PROJECTION ***** //
		this.prj = new Projection(super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName());
		MultiThreadProjection myPMT[] = new MultiThreadProjection[super.getNbThreads()];
		part = 0;
		for (int i = 0; i < super.getNbThreads(); i++) {

			myPMT[i] = new MultiThreadProjection(selection, super.getColnames(), super.getDistinct(),
					super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName(), part, selection.size());
			myThreads[i] = new Thread(myPMT[i]);
			myThreads[i].start();
			part++;
		}

		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();
		
		}
		
		getProjection();
		System.out.println("Projection :"+System.nanoTime());

		// ***** AGGREGATION ***** //
		
		System.out.println("Aggregation :"+System.nanoTime());
	}

	public void addSelection(int result) {
		this.selection.add(result + 1);
	}

	public void getProjection() {
		if(super.getDistinct()) {
		Hashtable<String, BasicHashSet> result;
		result = prj.getMTProjectionDistinct();
		for (String s : super.getColnames()) {
			//System.out.println(result.get(s).toList());
			projection.put(s,result.get(s).toList());
			System.out.println(projection);
			}
		}
		else {
			projection = prj.getMTProjection();
			System.out.println(projection);
		  
		}
		

	}
	

}
