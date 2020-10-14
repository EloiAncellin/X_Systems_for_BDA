package main_runner;
import java.util.LinkedHashMap;

import utils.BinarySearch;
import utils.BinarySearchMultiThread;

public class main {

	public static void main(String[] args) throws InterruptedException {
		int first = 0;
		int last = 12;
		int key = 26;
		int part = 0;
		int nbOfThreads = 4;
		LinkedHashMap<Integer, Integer> data = new LinkedHashMap< Integer, Integer>();
		 
        data.put(1, 5);
        data.put(2, 6);
        data.put(3, 9);
        data.put(4, 13);
        data.put(5, 21);
        data.put(6, 25);
        data.put(7, 26);
        data.put(8, 40);
        data.put(9, 100);
        data.put(10, 460);
        data.put(11, 453);
        data.put(12, 500);
        
		// Example Binary Search with LinkedHashMap        
//        int index = BinarySearch.binarySearch(data, 0, 6, 21);
//        System.out.println(index);
        
        
        // Example Thread Binary Search 
        BinarySearchMultiThread myBSMT[] = new BinarySearchMultiThread[4];
        Thread myThreads[] = new Thread[4];
        
        for (int i = 0; i < nbOfThreads ; i++) {
        	myBSMT[i] = new BinarySearchMultiThread(data, last, key, part);
        	myThreads[i] = new Thread(myBSMT[i]);
            myThreads[i].start();
            part++;
		}     
        for (int i = 0; i < nbOfThreads ; i++) {
            myThreads[i].join();
		}
        for (int i = 0; i < nbOfThreads ; i++) {
            System.out.println(myBSMT[i].getIndex());
		}
        
      
	}
}
