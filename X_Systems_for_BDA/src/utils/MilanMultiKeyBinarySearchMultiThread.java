package utils;

import java.util.Arrays;
import java.util.List;

public class MilanMultiKeyBinarySearchMultiThread implements Runnable {
	
	int[][] data;
	int last;
	int[] keys;
	int part;
	List<Integer> results;
	
	public MilanMultiKeyBinarySearchMultiThread(int[][] data, int[] keys, int last,  int part) {
		this.data = data;
		this.last = last;
		this.keys = keys;
		this.part = part;
	}

	@Override
	public void run() {
		int first = part * (last/4);
		last = (part+1) * (last/4)-1;
		int[] sub = subArray(keys, first , last);

		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(data, 0, data.length - 1, sub, 0, sub.length - 1);
		this.setResults(mmkbs.getResults());
	} 
	
	// Generic function to get sub-array of a non-primitive array
	// between specified indices
	public static<T> int[] subArray(int[] keys, int beg, int end) {
		return Arrays.copyOfRange(keys, beg, end + 1);
	}

	public List<Integer> getResults() {
		return results;
	}
	
	public void setResults(List<Integer> results) {
		this.results = results;
	}

	
}