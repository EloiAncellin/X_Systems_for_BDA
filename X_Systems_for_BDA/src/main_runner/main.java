package main_runner;


import combination_1.SingleThread;
import combination_1.MultiThread;
public class main {

	public static void main(String[] args) throws InterruptedException {
		
		double[] keys = {17.38,4964.79,381.89};
		String [] colnames = {"CustomerAge","CustomerName"}; 
		int nbThreads = 4; 
		//SingleThread st = new SingleThread("src/dataset_sorted/dataset_sorted_100.csv", 100, true,keys,colnames);		
		//st.start_combination();
		MultiThread mt = new MultiThread("src/dataset_sorted/dataset_sorted_100.csv", 100, true,keys,colnames,nbThreads);
		mt.start_combination();
		
	}
}
