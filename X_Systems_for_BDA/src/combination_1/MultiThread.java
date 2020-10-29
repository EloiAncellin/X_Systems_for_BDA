package combination_1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import combination.Combination;
import utils.BasicHashSet;
import utils.BinarySearchMultiThread;
import utils.MultiThreadProjection;
import utils.Projection;

public class MultiThread extends Combination {
	private int part = 0;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	// private ArrayList<BasicHashSet> projection = new ArrayList<BasicHashSet>();
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

		getLoadData().read();
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
		System.out.println("flor" + selection.size());
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

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		this.selection.add(result + 1);
	}

	public void getProjection() {
		Hashtable<String, BasicHashSet> result;
		result = prj.getMTProjection();
		for (String s : super.getColnames()) {
			System.out.println(result.get(s).toList());
		}

	}

}
