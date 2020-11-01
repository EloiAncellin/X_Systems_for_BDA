package utils;

import java.util.ArrayList;
import java.util.Random;

public class Max{
    ArrayList<Integer> data; /*Cette liste doit être triée*/
    int lenData; /*On doit recevoir la taille de la liste*/

    public Max(ArrayList<Integer> data, int lenData) {
        this.data = data;
        this.lenData = lenData;
    }

    public int maxOrdered(){
        return data.get(lenData -1);
    }

    public int maxUnordered(){
        int maxValue = data.get(0);
        for(int i =1 ; i < lenData ; i++){
            if (data.get(i) > maxValue){
                maxValue = data.get(i);
            }
        }
        return(maxValue);
    }

    public int stochasticMax(){
        int nbObservedValue = lenData/10;
        Random random = new Random();
        int rd;
        int maxValue = data.get(0);

        if (nbObservedValue != 0) {
            for(int i =1 ; i < nbObservedValue ; i++){
                if (data.get(i) > maxValue){
                    maxValue = data.get(i);
                }
            }
        }
        else {
            rd = random.nextInt(lenData);
            maxValue = data.get(rd);
        }
        return(maxValue);
    }
}