package main_runner;

import java.util.Arrays;
import java.util.List;

import utils.BinarySearch;
import utils.BinarySearchMultiThread;
import utils.MilanMultiKeyBinarySearch;
import utils.MilanMultiKeyBinarySearchMultiThread;

public class main {

	public static void main(String[] args) throws InterruptedException {
		int key = 10;
		int part = 0;
		int nbOfThreads = 4;
		int[] data = new int[12];
		for (int i = 0; i < data.length; i++) {
			data[i]= i + 4;
		}

//		 Example Binary Search 
		int index = BinarySearch.binarySearch(data, 0, data.length - 1 , 4);
		System.out.println(index);

		// Example Thread Binary Search
//        BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[4];
//        Thread myThreads[] = new Thread[4];
//      
//        for (int i = 0; i < nbOfThreads ; i++) {
//        	myBSMT[i] = new BinarySearchMultiThread(data, data.length, key, part);
//        	myThreads[i] = new Thread(myBSMT[i]);
//            myThreads[i].start();
//            part++;
//		}     
//        for (int i = 0; i < nbOfThreads ; i++) {
//            myThreads[i].join();
//		}
//        for (int i = 0; i < nbOfThreads ; i++) {
//        	if(myBSMT[i].getResult() != 0) {
//            System.out.println(myBSMT[i].getResult());
//        	}
//		}
        
//        int[] keys = new int[12];
//		for (int i = 0; i < 12; i++) {
//			keys[i] = i;
//		}
        
//        MilanMultiKeyBinarySearchMultiThread mmkbsmt[] = new MilanMultiKeyBinarySearchMultiThread[4];
// 
//        for (int i = 0; i < nbOfThreads ; i++) {
//        	mmkbsmt[i] = new MilanMultiKeyBinarySearchMultiThread(data, keys, keys.length, part);
//        	myThreads[i] = new Thread(mmkbsmt[i]);
//            myThreads[i].start();
//            part++;
//		}     
//        for (int i = 0; i < nbOfThreads ; i++) {
//            myThreads[i].join();
//		}
//        for (int i = 0; i < nbOfThreads ; i++) {
//        	if(mmkbsmt[i].getResults() != null) {
//            System.out.println(Arrays.toString(mmkbsmt[i].getResults().toArray()));
//        	}
//		}
        
 

//		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
//		mmkbs.milanMultiKeyBinarySearch(data, 0, data.length - 1, keys, 0, keys.length - 1);
//		List<Integer> res = mmkbs.getResults();
//		System.out.println(Arrays.toString(res.toArray()));
	}
}
