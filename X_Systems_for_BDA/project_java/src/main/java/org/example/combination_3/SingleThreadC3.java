package combination_3;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.Max;
import utils.Mean;
import utils.MilanMultiKeyBinarySearch;
import utils.Min;
import utils.Projection;
import utils.Mesures;


public class SingleThreadC3 extends Combination{
	
	private ArrayList<Integer> selection = new ArrayList<Integer>();

	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>(); 

	public SingleThreadC3(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination(Mesures mesure) {

		getLoadData().read();

		// ***** SELECTION ***** //
		long selectionStart = System.nanoTime();
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(getLoadData().getCustomerPrice(), 0, getLoadData().getCustomerPrice().length - 1, getKeys(), 0, getKeys().length - 1);
		setSelection(mmkbs.getResults());

		entireSelection();
		long selectionTime = System.nanoTime() - selectionStart;
	//	System.out.println("SingleThreadC3, Time for selection : " + selectionTime);
		mesure.addSelection(selectionTime);


		// ***** PROJECTION ***** //
		long projectionStart = System.nanoTime();

		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);

		projection = prj.Project_sort(selection,super.getColnames(),super.getDistinct());
		//System.out.println(projection);

		long projectionTime = System.nanoTime() - projectionStart;
	//	System.out.println("SingleThreadC3, Time for Projection : " + projectionTime);
		mesure.addProjection(projectionTime);




		// ***** AGGREGATION ***** //
		long aggregationStart = System.nanoTime();
		if (projection.get("CustomerAge").get(0) instanceof Integer) {
		ArrayList<Integer> ageListString = (ArrayList<Integer>)projection.get("CustomerAge");
		int sizeArray = ageListString.size();
		ArrayList<Integer> ageList = new ArrayList<Integer>(sizeArray); /*Pour palier à probleme de type*/
		for(Integer s : ageListString) ageList.add(s);
		//System.out.println(ageList);
		//System.out.println(ageList.get(0));
		Mean meanObject = new Mean(ageList, sizeArray);
		double mean = meanObject.stochasticMean();
		Min minObject = new Min(ageList, sizeArray);
		int min = minObject.stochasticMin();
		Max maxObject = new Max(ageList, sizeArray);
		int max = maxObject.stochasticMax();
	//	System.out.println("Moyenne = :" + mean);
	//	System.out.println("Min = :" + min);
	//	System.out.println("Max = :" + max);

		long aggregationTime = System.nanoTime() - aggregationStart;
	//	System.out.println("SingleThreadC3, Time for Aggregation : " + aggregationTime);
		mesure.addAggregation(aggregationTime);
		}
		

		
	//	System.out.println("Aggregation :"+System.nanoTime());
	}

	public void addSelection(int result) {
		this.selection.add(result);
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
