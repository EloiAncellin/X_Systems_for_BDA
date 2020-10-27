package utils;

import java.util.ArrayList;
import java.util.Arrays;


public class MilanMultiKeyBinarySearch {

	ArrayList<Integer> results;
	public MilanMultiKeyBinarySearch() {
		results = new ArrayList<>();
	}

	public static int middle(int left, int right) {
		return ((left) + (((right) - (left)) >> 1));
	}

	public void milanMultiKeyBinarySearch(int[] data, int arr_l, int arr_r, int[] keys, int keys_l, int keys_r){  
		if(keys_r - keys_l < 0) {
			return;
		}

		int keys_middle = middle(keys_l, keys_r);
		
		if (keys[keys_middle] < data[arr_l]) {
			milanMultiKeyBinarySearch(data, arr_l, arr_r, keys, keys_middle + 1, keys_r);
	        return;
	    }
	    if (keys[keys_middle] > data[arr_r]) {
	    	milanMultiKeyBinarySearch(data, arr_l, arr_r, keys, keys_l, keys_middle);
	        return;
	    }
	    

	    int[] pos = BinarySearch.binarySearchForMkbs(data, arr_l, arr_r, keys[keys_middle]);
	  
	    if(pos[0] == 1 ) {
	    	results.add(pos[1]);
	    }
	   
	   
	    milanMultiKeyBinarySearch(data, arr_l, pos[1] - 1, keys, keys_l, keys_middle - 1);
	    milanMultiKeyBinarySearch(data,pos[0] == 1 ? pos[1] + 1 : pos[1], arr_r, keys, keys_middle + 1, keys_r);
		
	}
	
	public ArrayList<Integer> getResults() {
		return results;
	}
}
