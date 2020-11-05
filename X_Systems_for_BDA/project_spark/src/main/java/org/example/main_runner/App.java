
package main_runner;

import combination_1.*;
import combination_2.*;
import combination_3.*;
import java.io.*;
import utils.Mesures;




import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.io.*;


public class App {

	public static void main(String[] args) throws InterruptedException {
			SparkSession session = SparkSession
                .builder()
                .appName("SparkJavaExample")
                .master("local[4]")
                .getOrCreate();



            try(JavaSparkContext context = new JavaSparkContext(session.sparkContext())) {

				int lenFile = 1000;
				int numberIteration = 1000;
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
				String filename = "./data/dataset_sorted/dataset_sorted_" + lenFile + ".csv";

				

				Mesures mesureST1 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					
					//long TimeSingleC1Start = System.nanoTime();
					SingleThreadC1 stc1 = new SingleThreadC1(filename, lenFile, true, keys, colnames, nbThreads);
					stc1.start_combination(mesureST1);	
				}
				System.out.println("ST1 selection : " + mesureST1.getAverageSelection());
				System.out.println("ST1 projection : " + mesureST1.getAverageProjection());
				System.out.println("ST1 aggregation : " + mesureST1.getAverageAggregation());
				

				Mesures mesureMT1 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					MultiThreadC1 mtc1 = new MultiThreadC1(filename, lenFile, true, keys, colnames, nbThreads);
		    		mtc1.start_combination(mesureMT1);
		    	}
		    	System.out.println("MT1 selection : " + mesureMT1.getAverageSelection());
				System.out.println("MT1 projection : " + mesureMT1.getAverageProjection());
				System.out.println("MT1 aggregation : " + mesureMT1.getAverageAggregation());

				
				Mesures mesureST2 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					SingleThreadC2 stc2 = new SingleThreadC2(filename, lenFile, true, keys, colnames, nbThreads);
					stc2.start_combination(mesureST2);
				}
				System.out.println("ST2 selection : " + mesureST2.getAverageSelection());
				System.out.println("ST2 projection : " + mesureST2.getAverageProjection());
				System.out.println("ST2 aggregation : " + mesureST2.getAverageAggregation());


				Mesures mesureMT2 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					MultiThreadC2 mtc2 = new MultiThreadC2(filename, lenFile, true, keys, colnames, nbThreads);
					mtc2.start_combination(mesureMT2);
				}
				System.out.println("MT2 selection : " + mesureMT2.getAverageSelection());
				System.out.println("MT2 projection : " + mesureMT2.getAverageProjection());
				System.out.println("MT2 aggregation : " + mesureMT2.getAverageAggregation());


				Mesures mesureST3 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					SingleThreadC3 stc3 = new SingleThreadC3(filename, lenFile, true, keys, colnames, nbThreads);
					stc3.start_combination(mesureST3);
				}
				System.out.println("ST3 selection : " + mesureST3.getAverageSelection());
				System.out.println("ST3 projection : " + mesureST3.getAverageProjection());
				System.out.println("ST3 aggregation : " + mesureST3.getAverageAggregation());


				Mesures mesureMT3 = new Mesures(numberIteration);
				for (int i=0;i<numberIteration;i++){
					MultiThreadC3 mtc3 = new MultiThreadC3(filename, lenFile, true, keys, colnames, nbThreads);
					mtc3.start_combination(mesureMT3);
				}	
				System.out.println("MT3 selection : " + mesureMT3.getAverageSelection());
				System.out.println("MT3 projection : " + mesureMT3.getAverageProjection());
				System.out.println("MT3 aggregation : " + mesureMT3.getAverageAggregation());
				
				context.stop();
			}

	}
}








