package utils;
import java.util.LinkedHashMap;

public class BinarySearch {
	public static int binarySearch(LinkedHashMap<Integer, Integer> data, int first, int last, int key){  
		   int mid = (first + last)/2;  
		   while( first <= last ){  
		      if ( data.get(mid) < key ){  
		        first = mid + 1;     
		      }else if ( data.get(mid) == key ){  
		        return mid;
		      }else{  
		         last = mid - 1;  
		      }  
		      mid = (first + last)/2;  
		   }  
		   if ( first > last ){  
		      return -1; 
		   }
		return -1;  
	}  
}
