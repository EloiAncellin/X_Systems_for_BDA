package combination_2;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.MilanMultiKeyBinarySearch;
import utils.Projection;

public class SingleThread extends Combination {

	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination() {
		System.out.println("Debut :"+System.nanoTime());
		getLoadData().read();
		System.out.println("Read :"+System.nanoTime());
		// ***** SELECTION ***** //
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(getLoadData().getCustomerPrice(), 0,
				getLoadData().getCustomerPrice().length - 1, getKeys(), 0, getKeys().length - 1);
		setSelection(mmkbs.getResults());
		System.out.println("Selection :"+System.nanoTime());
		// ***** PROJECTION ***** //
		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);
		
		prj.Project(selection,super.getColnames(),super.getDistinct());
		System.out.println("Projection :"+System.nanoTime());
		// ***** AGGREGATION ***** //
	}

	public void setSelection(ArrayList<Integer> results) {
		this.selection = results;
	}

}
