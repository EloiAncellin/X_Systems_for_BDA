package combination_2;

import java.util.ArrayList;
import java.util.Hashtable;

import combination.Combination;
import utils.BasicHashSet;
import utils.Max;
import utils.MaxMultiThread;
import utils.MeanMultiThread;
import utils.MilanMultiKeyBinarySearchMultiThread;
import utils.Min;
import utils.MinMultiThread;
import utils.MultiThreadProjection;
import utils.Projection;
import utils.Mesures;

public class MultiThreadC2 extends Combination {

	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	Hashtable<String, ArrayList<?>> projection = new Hashtable<>();
	private Projection prj;

	public MultiThreadC2(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search
	// PROJECTION :
	// AGGREGATION :
	public void start_combination(Mesures mesure) throws InterruptedException {


		getLoadData().read();


		// ***** SELECTION *****//
		
		Thread myThreads[] = new Thread[nbThreads];
		MilanMultiKeyBinarySearchMultiThread mmkbsmt[] = new MilanMultiKeyBinarySearchMultiThread[4];
		long selectionStart = System.nanoTime();
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
		entireSelection();
		long selectionTime = System.nanoTime() - selectionStart;
	//	System.out.println("MultiThreadC2, Time for selection : " + selectionTime);
		mesure.addSelection(selectionTime);

		// ***** PROJECTION ***** //

		
		this.prj = new Projection(super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName());
		MultiThreadProjection myPMT[] = new MultiThreadProjection[super.getNbThreads()];
		part = 0;
		long projectionStart = System.nanoTime();
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

		long projectionTime = System.nanoTime() - projectionStart;
//		System.out.println("MultiThreadC2, Time for Projection : " + projectionTime);
		mesure.addProjection(projectionTime);

		// ***** AGGREGATION ***** //
		
		part = 0;
		MeanMultiThread myMean[] = new MeanMultiThread[super.getNbThreads()];
		ArrayList<String> ageListString = (ArrayList<String>) projection.get("CustomerAge");
		int sizeArray = ageListString.size();
		ArrayList<Integer> ageList = new ArrayList<Integer>(sizeArray); /* Pour palier � probleme de type */
		long aggregationStart = System.nanoTime();
		for (String s : ageListString)
			ageList.add(Integer.valueOf(s));
		double average = 0;
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMean[i] = new MeanMultiThread(ageList, sizeArray, part, super.getNbThreads());
			myThreads[i] = new Thread(myMean[i]);
			myThreads[i].start();
			part++;
		}
		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();
			average += myMean[j].getResult();
		}
		average /= super.getNbThreads();
//		System.out.println("Average = " + average);

		part = 0;
		MinMultiThread myMin[] = new MinMultiThread[super.getNbThreads()];
		ArrayList<Integer> dataMin = new ArrayList<Integer>(super.getNbThreads());
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMin[i] = new MinMultiThread(ageList, sizeArray, part, super.getNbThreads());
			myThreads[i] = new Thread(myMin[i]);
			myThreads[i].start();
			part++;
		}
		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();
			dataMin.add(j, myMin[j].getResult());
		}
		int min;
		Min minObj = new Min(dataMin, super.getNbThreads());
		min = minObj.minUnordered();
	//	System.out.println("Min = " + min);

		part = 0;
		MaxMultiThread myMax[] = new MaxMultiThread[super.getNbThreads()];
		ArrayList<Integer> dataMax = new ArrayList<Integer>(super.getNbThreads());
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMax[i] = new MaxMultiThread(ageList, sizeArray, part, super.getNbThreads());
			myThreads[i] = new Thread(myMax[i]);
			myThreads[i].start();
			part++;
		}
		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();
			dataMax.add(j, myMax[j].getResult());
		}
		int max;
		Max maxObj = new Max(dataMax, super.getNbThreads());
		max = maxObj.maxUnordered();
//		System.out.println("Max = " + max);


		long aggregationTime = System.nanoTime() - aggregationStart;
//		System.out.println("MultiThreadC2, Time for Aggregation : " + aggregationTime);
		mesure.addAggregation(aggregationTime);

	}




	public void addSelection(ArrayList<Integer> selection) {
		this.selection.addAll(selection);
	}

	public void entireSelection() {
		int lowerBound = this.selection.get(0);
		int higherBound = this.selection.get(1);
		this.selection.clear();
		for (int i = lowerBound; i <= higherBound; i++) {
			this.selection.add(i);
		}
	}

	public void getProjection() {
		if (super.getDistinct()) {
			Hashtable<String, BasicHashSet> result;
			result = prj.getMTProjectionDistinct();
			for (String s : super.getColnames()) {
				//System.out.println(result.get(s).toList());
				projection.put(s, result.get(s).toList());
			}
		} else {
			projection = prj.getMTProjection();
			//System.out.println(projection);

		}

	}

}
