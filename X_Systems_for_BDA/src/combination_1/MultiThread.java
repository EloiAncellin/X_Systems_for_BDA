package combination_1;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.BasicHashSet;
import utils.BinarySearchMultiThread;
import utils.MultiThreadProjection;

public class MultiThread extends Combination {
	private int part = 0;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private ArrayList<BasicHashSet> projection = new ArrayList<BasicHashSet>(); 

	public MultiThread(String filename, int lenFile, Boolean distinct, double[] keys,String [] colnames,int nbThreads) {
		super(filename, lenFile, distinct, keys,colnames,nbThreads);
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
				System.out.println(super.getNbThreads());
				if (myBSMT[i].getResult() != -1) {
					addSelection(myBSMT[i].getResult());
				}
			}
			part = 0;
		}

		// ***** PROJECTION ***** //
		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		MultiThreadProjection myPMT[] = new MultiThreadProjection[super.getNbThreads()];
		part = 0; 
		for(int i =0 ; i<super.getNbThreads();i++) {
			myPMT[i] = new MultiThreadProjection(selection,super.getColnames(),super.getDistinct(),super.getLoadData().GetColumns(),super.getLoadData().GetColumnsName(),part,selection.size()); 
		    myThreads[i] = new Thread(myPMT[i]);
		    myThreads[i].start();
			part ++;
		}
			
		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();	
		}
			
			
		
		//Thread T1 = new Thread( new MultiThreadProjection(new ArrayList(tab.subList(0, 50)),col,true,cl,All_col_names));
		
		
		
		

		// ***** AGGREGATION ***** //
	}

	public void addSelection(int result) {
		System.out.println(result+1);
		this.selection.add(result+1);
	}

}
