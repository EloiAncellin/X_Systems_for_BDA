package main_runner;

import combination_1.*;
import combination_2.*;
import combination_3.*;

public class main {

	public static void main(String[] args) throws InterruptedException {

		int lenFile = 100;
		double[] keys;
		if (lenFile == 100) {
			keys = new double[] { 111.46, 3001.37 };
		}
		else if (lenFile == 1000) {
			keys = new double[] { 2601.2, 4604.46 };
		}
		else if (lenFile == 10000) {
			keys = new double[] { 10.54, 4000.34 };
		}
		else {
			keys = new double[] { 1810.1, 4200.09 };
		}
		String[] colnames = { "CustomerAge", "CustomerName" };
		int nbThreads = 4;
		String filename = "src/dataset_sorted/dataset_sorted_" + lenFile + ".csv";

		SingleThreadC1 stc1 = new SingleThreadC1(filename, lenFile, true, keys, colnames, nbThreads);
		stc1.start_combination();
		MultiThreadC1 mtc1 = new MultiThreadC1(filename, lenFile, true, keys, colnames, nbThreads);
		mtc1.start_combination();
		
		SingleThreadC2 stc2 = new SingleThreadC2(filename, lenFile, true, keys, colnames, nbThreads);
		stc2.start_combination();
		MultiThreadC2 mtc2 = new MultiThreadC2(filename, lenFile, true, keys, colnames, nbThreads);
		mtc2.start_combination();
		
		SingleThreadC3 stc3 = new SingleThreadC3(filename, lenFile, true, keys, colnames, nbThreads);
		stc3.start_combination();
		MultiThreadC3 mtc3 = new MultiThreadC3(filename, lenFile, true, keys, colnames, nbThreads);
		mtc3.start_combination();


	}
}
