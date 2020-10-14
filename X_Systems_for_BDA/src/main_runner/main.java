package main_runner;
import java.util.LinkedHashMap;

import utils.BinarySearch;


public class main {

	public static void main(String[] args) {
		// Example Binary Search with LinkedHashMap
		LinkedHashMap<Integer, Integer> data = new LinkedHashMap< Integer, Integer>();
		 
        data.put(1, 5);
        data.put(2, 6);
        data.put(3, 9);
        data.put(4, 13);
        data.put(5, 20);
        data.put(6, 25);
        
        int index = BinarySearch.binarySearch(data, 0, 6, 21);
        System.out.println(index);
      
	}
}
