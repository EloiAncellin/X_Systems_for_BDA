package utils;

import java.util.Arrays;
import java.util.ArrayList;

public class MilanMultiKeyBinarySearchMultiThread implements Runnable {
	
	double[] data;
	int last;
	double[] keys;
	int part;
	ArrayList<Integer> results = new ArrayList<>();
	private int lenFile;
	
	public MilanMultiKeyBinarySearchMultiThread(double[] data, double[] keys, int last,  int part, int lenFile) {
		this.data = data;
		this.last = last;
		this.keys = keys;
		this.part = part;
		this.lenFile = lenFile;
	}

	@Override
	public void run() {
		int first = part * (last/4);
		last = (part+1) * (last/4)-1;
		double[] sub = subArray(data, first , last);
		MilanMultiKeyBinarySearch mmkbs = new MilanMultiKeyBinarySearch();
		mmkbs.milanMultiKeyBinarySearch(sub, 0, sub.length -1 , keys, 0, keys.length-1);
		this.setResults(mmkbs.getResults());
	} 
	
	// Generic function to get sub-array of a non-primitive array
	// between specified indices
	public static<T> double[] subArray(double[] data, int beg, int end) {
		return Arrays.copyOfRange(data, beg, end + 1);
	}

	public ArrayList<Integer> getResults() {
		return results;
	}
	
	public void setResults(ArrayList<Integer> results) {
		for(int i =0; i<results.size() ; i ++) {
			this.results.add(results.get(i) + part*(lenFile/4));
		}
	}

	
}
