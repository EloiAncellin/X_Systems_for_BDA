package utils;

import java.util.ArrayList;
import java.util.Iterator;

public interface BasicSet {
	
	 // Adds the specified element to this set if it is not already present
    boolean add(Object element);
    // Returns true if this set contains the specified element
    boolean contains(Object element);
    // Returns an iterator over the elements in this set
    Iterator<?> iterator();
    
    ArrayList<?> toList(); 
    // Returns the number of elements in this set
    int get_size();

}
