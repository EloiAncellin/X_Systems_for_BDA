package combination_2;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.BasicHashSet;
import utils.MilanMultiKeyBinarySearchMultiThread;
import utils.MultiThreadProjection;
import utils.Projection;

public class MultiThread extends Combination {

	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	Hashtable<String, ArrayList<?>> projection = new Hashtable<>();
	private Projection prj;

	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
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
		System.out.println("Projection  :"+System.nanoTime());
		

		// ***** AGGREGATION ***** //
		System.out.println("Aggregation :"+System.nanoTime());
	}

	public void addSelection(ArrayList<Integer> selection) {
		this.selection.addAll(selection);
	}
	public void getProjection() {
		if(super.getDistinct()) {
		Hashtable<String, BasicHashSet> result;
		result = prj.getMTProjectionDistinct();
		for (String s : super.getColnames()) {
			System.out.println(result.get(s).toList());
			projection.put(s,result.get(s).toList());
			}
		}
		else {
			for (String s : super.getColnames()) {
				projection.put(s,new ArrayList<>(super.getLoadData().GetColumns().get(s).values()));
				System.out.println(projection.get(s));
				}
		  
		}
		

	}

}
