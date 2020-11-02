package combination_2;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.Max;
import utils.Mean;
import utils.MilanMultiKeyBinarySearch;
import utils.Min;
import utils.Projection;

public class SingleThreadC2 extends Combination {

	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>(); 

	public SingleThreadC2(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
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
		
		entireSelection();
		System.out.println("Selection :"+System.nanoTime());
		// ***** PROJECTION ***** //
		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);
		
		projection = prj.Project(selection,super.getColnames(),super.getDistinct());
		System.out.println(projection);
		System.out.println("Projection :"+System.nanoTime());
		// ***** AGGREGATION ***** //
		
		ArrayList<String> ageListString = (ArrayList<String>)projection.get("CustomerAge");
		int sizeArray = ageListString.size();
		ArrayList<Integer> ageList = new ArrayList<Integer>(sizeArray); /*Pour palier � probleme de type*/
		for(String s : ageListString) ageList.add(Integer.valueOf(s));
		System.out.println(ageList);
		System.out.println(ageList.get(0));
		Mean meanObject = new Mean(ageList, sizeArray);
		double mean = meanObject.mean();
		Min minObject = new Min(ageList, sizeArray);
		int min = minObject.minOrdered();
		Max maxObject = new Max(ageList, sizeArray);
		int max = maxObject.maxOrdered();
		System.out.println("Moyenne = :" + mean);
		System.out.println("Min = :" + min);
		System.out.println("Max = :" + max);

		System.out.println("Aggregation :"+System.nanoTime());
	}

	public void setSelection(ArrayList<Integer> results) {
		this.selection = results;
	}
	
	public void entireSelection() {
		int lowerBound = this.selection.get(0);
		int higherBound = this.selection.get(1);

		this.selection.clear();
		for (int i = lowerBound; i <= higherBound; i++) {
			this.selection.add(i);
		}

	}

}