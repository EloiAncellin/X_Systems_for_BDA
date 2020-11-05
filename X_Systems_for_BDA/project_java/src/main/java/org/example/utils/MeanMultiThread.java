package utils;

import java.util.ArrayList;

public class MeanMultiThread implements Runnable{
	ArrayList<Integer> data;
    int lenData;
    int part;
    int nbPart;
    double result;
    boolean ordered;
    boolean stochastic;

    public MeanMultiThread(ArrayList<Integer> data, int lenData, int part, int nbPart) {
        this.data = data;
        this.lenData = lenData;
        this.part = part;
        this.nbPart = nbPart;
        this.result = 0;
        this.ordered = true;
        this.stochastic = false;
    }
    
    public MeanMultiThread(ArrayList<Integer> data, int lenData, int part, int nbPart, boolean ordered, boolean stochastic) {
        this.data = data;
        this.lenData = lenData;
        this.part = part;
        this.nbPart = nbPart;
        this.result = 0;
        this.ordered = ordered;
        this.stochastic = stochastic;
    }

    public ArrayList<Integer> subarray(ArrayList<Integer> donnee, int min, int max){
        int n = max - min + 1;
        ArrayList<Integer> sub = new ArrayList<Integer>(n);
        for(int i = 0; i< n; i++){
            sub.add(i, donnee.get(i + min));
        }
        return(sub);
    }

    public double mean(int part){
        int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = subdata.size();
        Mean meanObj = new Mean(subdata, lenSubData);
        double res = meanObj.mean();
        //System.out.println();
        //System.out.println(subdata);
        return(res);
    }
    
    public double stochasticMean(int part){
    	int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = subdata.size();
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
    	if (ordered){
    		this.setResults(mean(part));
    	}
    	else
    	{	
    		if (stochastic) {
    			this.setResults(stochasticMean(part));
    		}
    		else {
    			this.setResults(mean(part));
    		}
		}
    }
}