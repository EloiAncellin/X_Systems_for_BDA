package combination_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import combination.Combination;
import utils.BinarySearch;
import utils.Projection;
import utils.Mean;
import utils.Min;
import utils.Max;
import utils.Mesures;


public class SingleThreadC1 extends Combination {
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>(); 

	public SingleThreadC1(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,int nbThreads) {
		super(filename, lenFile, distinct, keys,colnames,nbThreads);
	}

	// SELECTION : Multi Key Binary Search
	// PROJECTION : Hashing based projection
	// AGGREGATION : Simple Mean
	public void start_combination( Mesures mesure) {
		

		getLoadData().read();

		// ***** SELECTION ***** //
		long selectionStart = System.nanoTime();
		for (int i = 0; i < getKeys().length; i++) {
		//	System.out.println(getKeys()[i]);
			addSelection(BinarySearch.binarySearch(getLoadData().getCustomerPrice(), 0,
					getLoadData().getCustomerPrice().length - 1, getKeys()[i]));
		}
		
		entireSelection();
		long selectionTime = System.nanoTime() - selectionStart;
	//	System.out.println("SingleThreadC1, Time for selection : " + selectionTime);
		mesure.addSelection(selectionTime);
		// ***** PROJECTION ***** //
		
		long projectionStart = System.nanoTime();
		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);
		
		projection = prj.Project(selection,super.getColnames(),super.getDistinct());
		//System.out.println(projection);
		
		long projectionTime = System.nanoTime() - projectionStart;
	//	System.out.println("SingleThreadC1, Time for Projection : " + projectionTime);
		mesure.addProjection(projectionTime);

		// ***** AGGREGATION ***** //
		long aggregationStart = System.nanoTime();
		
		ArrayList<String> ageListString = (ArrayList<String>)projection.get("CustomerAge");
		int sizeArray = ageListString.size();
		ArrayList<Integer> ageList = new ArrayList<Integer>(sizeArray); /*Pour palier à probleme de type*/
		for(String s : ageListString) ageList.add(Integer.valueOf(s));
		System.out.println(ageList);
		System.out.println(ageList.get(0));
		Mean meanObject = new Mean(ageList, sizeArray);
		double mean = meanObject.mean();
		Min minObject = new Min(ageList, sizeArray);
		int min = minObject.minOrdered();
		Max maxObject = new Max(ageList, sizeArray);
		int max = maxObject.maxOrdered();
//		System.out.println("Moyenne = :" + mean);
//		System.out.println("Min = :" + min);
//		System.out.println("Max = :" + max);
		

		long aggregationTime = System.nanoTime() - aggregationStart;
//		System.out.println("SingleThreadC1, Time for Aggregation : " + aggregationTime);
		mesure.addAggregation(aggregationTime);

	}

	public void addSelection(int result) {
		if (result != -1) {
			this.selection.add(result + 1);
		}
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
