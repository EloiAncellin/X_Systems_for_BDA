package main_runner;


import combination_1.SingleThread;
import combination_1.MultiThread;
public class main {

	public static void main(String[] args) throws InterruptedException {
		
		double[] keys = {17.38,4964.79};
//		SingleThread st = new SingleThread("src/dataset_sorted/dataset_sorted_100.csv", 100, true,keys);
//		st.start_combination();
		MultiThread mt = new MultiThread("src/dataset_sorted/dataset_sorted_100.csv", 100, true,keys);
		mt.start_combination();
		
	}
}
