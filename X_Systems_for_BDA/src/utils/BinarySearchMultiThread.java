package utils;


public class BinarySearchMultiThread implements Runnable {
	
	int[] data;
	int last;
	int key;
	int part;
	int index = -1;
	int result;
	
	public BinarySearchMultiThread(int[] data, int last, int key, int part) {
		this.data = data;
		this.last = last;
		this.key = key;
		this.part = part;
	}
	
	@Override
	public void run() {
		int first = part * (last/4);
		last = (part+1) * (last/4)-1;
		index = BinarySearch.binarySearch(data, first, last, key);
		if(index != -1) {
			setResult(index);
		}
	} 
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}

}
