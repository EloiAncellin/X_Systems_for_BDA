package utils;

import java.util.Random;

public class Mean{
    int[] data;
    int lenData;

    public Mean(int[] data, int lenData) {
        this.data = data;
        this.lenData = lenData;
    }

    public double mean(){
        int sum = 0;
        for (int i = 0; i<lenData; i++){
            sum+=data[i];
        }
        double mean = (double) sum / lenData;
        return(mean);
    }

    public double stochasticMean(){
        int nbObservedValue = lenData/10;
        Random random = new Random();
        int rd;
        int sum = 0;
        for (int i = 0; i<nbObservedValue; i++){
            rd = random.NextInt(lenData);
            sum+=data[rd];
        }
        double mean = (double) sum/nbObservedValue;
        return(mean);
    }

}