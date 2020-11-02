package utils;

import java.util.ArrayList;

public class MeanMultiThread implements Runnable{
	ArrayList<Integer> data;
    int lenData;
    int part;
    int nbPart;
    double result;

    public MeanMultiThread(ArrayList<Integer> data, int lenData, int part, int nbPart) {
        this.data = data;
        this.lenData = lenData;
        this.part = part;
        this.nbPart = nbPart;
        this.result = 0;
    }

    public ArrayList<Integer> subarray(ArrayList<Integer> donnee, int min, int max){
        int n = max - min +1;
        ArrayList<Integer> sub = new ArrayList<Integer>(n);
        for(int i = min; i<max; i++){
            sub.add(i, donnee.get(i));
        }
        return(sub);
    }

    public double mean(int part){
        int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = last - first;
        Mean meanObj = new Mean(subdata, lenSubData);
        double res = meanObj.mean();
        return(res);
    }
    
    public double stochasticMean(int part){
    	int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = last - first;
        Mean meanObj = new Mean(subdata, lenSubData);
        double res = meanObj.stochasticMean();
        return(res);
    }
    
    public void setResults(double res) {
    	this.result = res;
    }
    
    public double getResult() {
    	return(result);
    }
    
    public void run() {
		this.setResults(mean(part));
    }
}