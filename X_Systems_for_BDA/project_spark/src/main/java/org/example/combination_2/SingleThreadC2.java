package combination_2;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.Max;
import utils.Mean;
import utils.MilanMultiKeyBinarySearch;
import utils.Min;
import utils.Projection;
import utils.Mesures;
import org.apache.spark.api.java.JavaSparkContext;


public class SingleThreadC2 extends Combination {

	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>(); 

	public SingleThreadC2(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads, JavaSparkContext sc) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads, sc);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination(Mesures mesure) {

		getLoadData().read();

		// ***** SELECTION ***** //
		long selectionStart = System.nanoTime();

		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(getLoadData().getCustomerPrice(), 0,
				getLoadData().getCustomerPrice().length - 1, getKeys(), 0, getKeys().length - 1);
		setSelection(mmkbs.getResults());
		
		entireSelection();

		long selectionTime = System.nanoTime() - selectionStart;
	//	System.out.println("SingleThreadC2, Time for selection : " + selectionTime);
		mesure.addSelection(selectionTime);


		// ***** PROJECTION ***** //
		long projectionStart = System.nanoTime();

		Hashtable<String, Hashtable<Integer, ?>> cl = super.getLoadData().GetColumns();
		String[] All_col_names = super.getLoadData().GetColumnsName();
		
		Projection prj = new Projection(cl, All_col_names);
		
		projection = prj.Project(selection,super.getColnames(),super.getDistinct());
		//System.out.println(projection);

		long projectionTime = System.nanoTime() - projectionStart;
	//	System.out.println("SingleThreadC2, Time for Projection : " + projectionTime);
		mesure.addProjection(projectionTime);


		// ***** AGGREGATION ***** //
		long aggregationStart = System.nanoTime();
		ArrayList<String> ageListString = (ArrayList<String>)projection.get("CustomerAge");
		int sizeArray = ageListString.size();
		ArrayList<Integer> ageList = new ArrayList<Integer>(sizeArray); /*Pour palier � probleme de type*/
		for(String s : ageListString) ageList.add(Integer.valueOf(s));
		//System.out.println(ageList);
		//System.out.println(ageList.get(0));
		Mean meanObject = new Mean(ageList, sizeArray);
		double mean = meanObject.mean();
		Min minObject = new Min(ageList, sizeArray);
		int min = minObject.minOrdered();
		Max maxObject = new Max(ageList, sizeArray);
		int max = maxObject.maxOrdered();
	//	System.out.println("Moyenne = :" + mean);
	//	System.out.println("Min = :" + min);
	//	System.out.println("Max = :" + max);

		long aggregationTime = System.nanoTime() - aggregationStart;
	//	System.out.println("SingleThreadC2, Time for Aggregation : " + aggregationTime);
		mesure.addAggregation(aggregationTime);

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
