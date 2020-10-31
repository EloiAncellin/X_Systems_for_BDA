package combination_1;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.BinarySearch;
import utils.Projection;

public class SingleThread extends Combination {
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	public SingleThread(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,int nbThreads) {
		super(filename, lenFile, distinct, keys,colnames,nbThreads);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION : Hashing based projection
	// AGGREGATION :
	public void start_combination() {
		
		System.out.println("Debut :"+System.nanoTime());
		getLoadData().read();
		System.out.println("Read :"+System.nanoTime());
		// ***** SELECTION ***** //
		for (int i = 0; i < getKeys().length; i++) {
			addSelection(BinarySearch.binarySearch(getLoadData().getCustomerPrice(), 0,
					getLoadData().getCustomerPrice().length - 1, getKeys()[i]));
		}
		System.out.println("Selection :"+System.nanoTime());

		// ***** PROJECTION ***** //
		
		
		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);
		
		prj.Project(selection,super.getColnames(),super.getDistinct());
		System.out.println("Projection:"+System.nanoTime());
		// ***** AGGREGATION ***** //
		ArrayList<Integer> resultProj = prj.getResults();
		
		System.out.println("Aggregation :"+System.nanoTime());
	}

	public void addSelection(int result) {
		if (result != -1) {
			this.selection.add(result + 1);
		}
	}

	
}
