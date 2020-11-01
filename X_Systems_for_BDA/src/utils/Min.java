package utils;

import java.util.ArrayList;
import java.util.Random;

public class Min{
    ArrayList<Integer> data; /*Cette liste doit être triée*/
    int lenData;

    public Min(ArrayList<Integer> data, int lenData) {
        this.data = data;
        this.lenData = lenData;
    }

    public int minOrdered(){return data.get(0);}

    public int minUnordered(){
        int minValue = data.get(0);
        for(int i =1 ; i < lenData ; i++){
            if (data.get(i) < minValue){
                minValue = data.get(i);
            }
        }
        return(minValue);
    }

    public int stochasticMin(){
        int nbObservedValue = lenData/10;
        Random random = new Random();
        int rd;
        int minValue = data.get(0);

        if (nbObservedValue != 0) {
            for(int i =1 ; i < nbObservedValue ; i++){
                if (data.get(i) < minValue){
                    minValue = data.get(i);
                }
            }
        }
        else {
            rd = random.nextInt(lenData);
            minValue = data.get(rd);
        }
        return(minValue);
    }
}