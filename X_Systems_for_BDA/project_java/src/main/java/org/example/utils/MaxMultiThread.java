package utils;

import java.util.ArrayList;

public class MaxMultiThread implements Runnable{
	ArrayList<Integer> data;
    int lenData;
    int part;
    int nbPart;
    int result;
    boolean ordered;
    boolean stochastic;
    
    public MaxMultiThread(ArrayList<Integer> data, int lenData, int part, int nbPart) {
        this.data = data;
        this.lenData = lenData;
        this.part = part;
        this.nbPart = nbPart;
        this.result = 0;
        this.ordered = true;
        this.stochastic = false;
    }
    
    public MaxMultiThread(ArrayList<Integer> data, int lenData, int part, int nbPart, boolean ordered, boolean stochastic) {
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

    public int maxOrdered(int part){
        int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = subdata.size();
        Max maxObj = new Max(subdata, lenSubData);
        int res = maxObj.maxOrdered();
        //System.out.println();
        //System.out.println(subdata);
        return(res);
    }
    
    public int maxUnordered(int part){
        int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = subdata.size();
        Max maxObj = new Max(subdata, lenSubData);
        int res = maxObj.maxUnordered();
        //System.out.println();
        //System.out.println(subdata);
        return(res);
    }
    
    public int stochasticMax(int part){
    	int first = part * (lenData/nbPart);
        int last = (part+1) * (lenData/nbPart)-1;
        ArrayList<Integer> subdata = subarray(data, first , last);
        int lenSubData = subdata.size();
        Max maxObj = new Max(subdata, lenSubData);
        int res = maxObj.stochasticMax();
        return(res);
    }
    
    public void setResults(int res) {
    	this.result = res;
    }
    
    public int getResult() {
    	return(result);
    }
    
    public void run() {
    	if (ordered){
    		this.setResults(maxOrdered(part));
    	}
    	else
    	{	
    		if(stochastic){
    		this.setResults(stochasticMax(part));
    		}
    		else {
    			this.setResults(maxUnordered(part));
    		}
    		
    	}
    }
}