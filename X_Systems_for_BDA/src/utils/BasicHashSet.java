package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicHashSet implements BasicSet {
	
	private Entry[] buckets;
    private int size;
    
    public BasicHashSet(int capacity) {

        buckets = new Entry[capacity];
        size = 0;
    }
    
    // hash function that computes the index into the bucket array giving the hash value of an object
    private int hashFunction(int hashCode) {

        int index = hashCode;
        if (index < 0) { index = -index; }
        return index % buckets.length;
    }
    
    @Override
    public boolean add(Object element) {

        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            // element is already in set
            if (current.key.equals(element)) { return false; }
            // otherwise visit next entry in the bucket
            current = current.next;
        }
        
        // no element found so add new entry
        Entry newentry = new Entry();
        newentry.key = element;
        // current Entry is null if bucket is empty
        // if it is not null it becomes next Entry
        newentry.next  = buckets[index];
        buckets[index] = newentry;
        size++;
        return true;
    }
    
    @Override
    public boolean contains(Object element) {

        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            // check if node contains element
            if (current.key.equals(element)) { return true; }
            // otherwise visit next node in the bucket
            current = current.next;
        }
        // no element found
        return false;
    }
    
    @Override
    public Iterator iterator() {
        return new BasicHashSetIterator();
    }
    public int get_size() {
        return size;
    }
    
    public ArrayList toList(){
    	ArrayList myList = new ArrayList();
    	Entry currentEntry = null;
    	   for (int index=0; index < buckets.length; index++) {
               // we have an entry
               if (buckets[index] != null) {
                   currentEntry = buckets[index];
                   myList.add(currentEntry.key.toString());
               }
           }
    	   return myList; 
    	
    }
    
    
    
    
    


	
    
    //inner to build linked list of collided objects
	private static class Entry {
        Object key;
        Entry next;
    }
	
	
	
	
	
	
	
	// Simple Iterator for this class 
	class BasicHashSetIterator implements Iterator {

        private int currentBucket;
        private int previousBucket;
        private Entry currentEntry;
        private Entry previousEntry;

        public BasicHashSetIterator() {
            currentEntry = null;
            previousEntry = null;
            currentBucket = -1;
            previousBucket = -1;
        }

        @Override
        public boolean hasNext() {

            // currentEntry node has next
            if (currentEntry != null && currentEntry.next != null) { return true; }

            // there are still nodes
            for (int index = currentBucket+1; index < buckets.length; index++) {
                if (buckets[index] != null) { return true; }
            }

            // nothing left
            return false;
        }

        @Override
        public Object next() {

            previousEntry = currentEntry;
            previousBucket = currentBucket;

            // if either the current or next node are null
            if (currentEntry == null || currentEntry.next == null) {

                // go to next bucket
                currentBucket++;

                // keep going until you find a bucket with a node
                while (currentBucket < buckets.length &&
                        buckets[currentBucket] == null) {
                    // go to next bucket
                    currentBucket++;
                }

                // if bucket array index still in bounds
                // make it the current node
                if (currentBucket < buckets.length) {
                    currentEntry = buckets[currentBucket];
                }
                // otherwise there are no more elements
                else {
                    throw new NoSuchElementException();
                }
            }
            // go to the next element in bucket
            else {

                currentEntry = currentEntry.next;
            }

            // return the element in the current node
            return currentEntry.key;

        }

    }
	
	

}
