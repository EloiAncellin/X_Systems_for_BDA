package utils;

import java.util.Random;
import java.util.ArrayList;

public class Mean{
    ArrayList<Integer> data;
    int lenData;

    public Mean(ArrayList<Integer> data, int lenData) {
        this.data = data;
        this.lenData = lenData;
    }

    public double mean(){
        int sum = 0;
        for (int i = 0; i<lenData; i++){
            sum+=data.get(i);
        }
        double mean = (double) sum / lenData;
        return(mean);
    }

    public double stochasticMean(){
        int nbObservedValue = lenData/10;
        Random random = new Random();
        int rd;
        int sum = 0;
        double mean;
        if (nbObservedValue != 0) {
	        for (int i = 0; i<nbObservedValue; i++){
	            rd = random.nextInt(lenData);
	            sum+=data.get(rd);
	            
	        }
	         mean = (double) sum/nbObservedValue;
        }
        else {
        	rd = random.nextInt(lenData);
            sum+=data.get(rd);
             mean = (double) sum;
        }
      // double mean = (double) sum/nbObservedValue;
        return(mean);
    }

}