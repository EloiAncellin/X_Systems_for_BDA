package combination_3;

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
import utils.MultiThreadProjectionSort;
import utils.Projection;

public class MultiThreadC3 extends Combination {

	private int part = 0;
	private int nbThreads = 4;
	private ArrayList<Integer> selection = new ArrayList<Integer>();
	private Projection prj;
	private Hashtable<String, ArrayList<?>> projection = new Hashtable<String, ArrayList<?>>();

	public MultiThreadC3(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,
			int nbThreads) {
		super(filename, lenFile, distinct, keys, colnames, nbThreads);
	}

	// SELECTION : Milan Multi Key Binary Search

	public void start_combination() throws InterruptedException {

		System.out.println("Debut :" + System.nanoTime());
		getLoadData().read();
		System.out.println("Read :" + System.nanoTime());

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
		entireSelection();
		System.out.println("Selection :" + System.nanoTime());

		// ***** PROJECTION ***** //

		// System.out.println("th " + super.getNbThreads());
		this.prj = new Projection(super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName());
		MultiThreadProjectionSort myPMT[] = new MultiThreadProjectionSort[super.getNbThreads()];
		part = 0;
		for (int i = 0; i < super.getNbThreads(); i++) {

			myPMT[i] = new MultiThreadProjectionSort(selection, super.getColnames(), super.getDistinct(),
					super.getLoadData().GetColumns(), super.getLoadData().GetColumnsName(), part, selection.size());
			myThreads[i] = new Thread(myPMT[i]);
			myThreads[i].start();
			myThreads[i].join();
			part++;

		}

		projection = myPMT[super.getNbThreads() - 1].getprojection();
		System.out.println("project = " + projection);

		System.out.println("Projection  :" + System.nanoTime());

		// ***** AGGREGATION ***** //
		part = 0;
		MeanMultiThread myMean[] = new MeanMultiThread[super.getNbThreads()];
		ArrayList<Integer> ageList = (ArrayList<Integer>) projection.get("CustomerAge");
		int sizeArray = ageList.size();
		double average = 0;
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMean[i] = new MeanMultiThread(ageList, sizeArray, part, super.getNbThreads(), false);
			myThreads[i] = new Thread(myMean[i]);
			myThreads[i].start();
			part++;
		}
		for (int j = 0; j < super.getNbThreads(); j++) {
			myThreads[j].join();
			average += myMean[j].getResult();
		}
		average /= super.getNbThreads();
		System.out.println("Average = " + average);

		part = 0;
		MinMultiThread myMin[] = new MinMultiThread[super.getNbThreads()];
		ArrayList<Integer> dataMin = new ArrayList<Integer>(super.getNbThreads());
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMin[i] = new MinMultiThread(ageList, sizeArray, part, super.getNbThreads(), false);
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
		System.out.println("Min = " + min);

		part = 0;
		MaxMultiThread myMax[] = new MaxMultiThread[super.getNbThreads()];
		ArrayList<Integer> dataMax = new ArrayList<Integer>(super.getNbThreads());
		for (int i = 0; i < super.getNbThreads(); i++) {

			myMax[i] = new MaxMultiThread(ageList, sizeArray, part, super.getNbThreads(), false);
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
		System.out.println("Max = " + max);
		System.out.println("Aggregation :" + System.nanoTime());
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

}
