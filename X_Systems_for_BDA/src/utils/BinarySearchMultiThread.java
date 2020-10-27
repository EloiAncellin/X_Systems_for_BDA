package utils;


public class BinarySearchMultiThread implements Runnable {
	
	float[] data;
	int last;
	double key;
	int part;
	int index = -1;
	int result;
	
	public BinarySearchMultiThread(float[] data, int last, double key, int part) {
		this.data = data;
		this.last = last;
		this.key = key;
		this.part = part;
	}
	
	@Override
	public void run() {
		int first = part * (last/4);
		last = (part+1) * (last/4) -1;
		index  = BinarySearch.binarySearch(data, first, last, key);
	} 
	
	public int getResult() {
		if(index != -1 ) {
			return index  + part*4;
		}
		return index;
	}

}
