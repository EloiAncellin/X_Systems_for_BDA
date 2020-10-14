package utils;

import java.util.LinkedHashMap;

import utils.BinarySearch;

public class BinarySearchMultiThread implements Runnable {
	
	LinkedHashMap<Integer, Integer> data;
	int last;
	int key;
	int part;
	int index = -1;
	
	public BinarySearchMultiThread(LinkedHashMap<Integer, Integer> data, int last, int key, int part) {
		this.data = data;
		this.last = last;
		this.key = key;
		this.part = part;
	}
	
	 public int getIndex() {
         return index;
     }

	@Override
	public void run() {
		int first = part * (last/4);
		last = (part + 1) * (last/4);
		index = BinarySearch.binarySearch(data, first, last, key);
	} 
}
