package main_runner;

import java.util.Arrays;
import java.util.List;

import utils.BinarySearch;
import utils.BinarySearchMultiThread;
import utils.LoadData;
import utils.MilanMultiKeyBinarySearch;
import utils.MilanMultiKeyBinarySearchMultiThread;

public class main {

	public static void main(String[] args) throws InterruptedException {
		double key = 2226.13;
		int part = 0;
		int nbOfThreads = 4;
		int lenFile = 100;
		float[] data = new float[lenFile];
		for (int i = 0; i < data.length; i++) {
			data[i] = i + 4;
		}
		
		LoadData RData = new LoadData("src/dataset_customer_price/dataset_customer_price_100.csv", lenFile);
		data = RData.readPrices();
		System.out.println(Arrays.toString(data));

//		 Example Binary Search 
		int index = BinarySearch.binarySearch(data, 0, data.length - 1, key);

		// Example Thread Binary Search
		BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[4];
		Thread myThreads[] = new Thread[4];

		for (int i = 0; i < nbOfThreads; i++) {
			myBSMT[i] = new BinarySearchMultiThread(data, data.length, key, part);
			myThreads[i] = new Thread(myBSMT[i]);
			myThreads[i].start();
			part++;
		}
		for (int i = 0; i < nbOfThreads; i++) {
			myThreads[i].join();
		}
		for (int i = 0; i < nbOfThreads; i++) {
				System.out.println(myBSMT[i].getResult());
		}

        double[] keys = new double[6];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = i;
		}
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(data, 0, data.length - 1, keys, 0, keys.length - 1);
		List<Integer> res = mmkbs.getResults();

        MilanMultiKeyBinarySearchMultiThread mmkbsmt[] = new MilanMultiKeyBinarySearchMultiThread[4];
        part = 0;
        for (int i = 0; i < nbOfThreads ; i++) {
        	mmkbsmt[i] = new MilanMultiKeyBinarySearchMultiThread(data, keys, data.length, part);
        	myThreads[i] = new Thread(mmkbsmt[i]);
            myThreads[i].start();
            part++;
		}     
        for (int i = 0; i < nbOfThreads ; i++) {
            myThreads[i].join();
		}
        for (int i = 0; i < nbOfThreads ; i++) {
        	if(mmkbsmt[i].getResults().size() > 0) {
            System.out.println(Arrays.toString(mmkbsmt[i].getResults().toArray()));
        	}
		}


	}
}
