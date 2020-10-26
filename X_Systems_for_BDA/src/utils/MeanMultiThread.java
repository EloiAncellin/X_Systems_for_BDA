import java.util.Random;
import Mean;

public class MeanMultiThread implements Runnable{
    int[] data;
    int lenData;
    int last;
    int part;
    int nbPart;
    double result;

    public MeanMultiThread(int[] data, int lenData, int last, int part, int nbPart) {
        this.data = data;
        this.lenData = lenData;
        this.last = last;
        this.part = part;
        this.nbPart = nbPart;
    }

    public int[] subarray(int[] donnee, int min, int max){
        int n = max - min;
        int[] sub = new int[n];
        for(int i = min; i<max; i++){
            sub[i - min] = donnee[i];
        }
        return(sub);
    }

    double mean(){
        int sum = 0;
        int first = part * (last/4);
        last = (part+1) * (last/4)-1;
        int n = last - first;
        int[] sub = subarray(data, first , last);
        for (int i = 0; i<n; i++){
            sum+=sub[i];
        }
        double mean = (double)sum/n;
        return(mean);
    }

    double meanStochastic(){
        int sum = 0;
        int nbObservedValue = lenData/10;
        int first = part * (last/4);
        last = (part+1) * (last/4)-1;
        int n = last - first;
        int[] sub = subarray(data, first , last);
        Random random = new Random();
        int rd;
        for (int i = 0; i < nbObservedValue; i++){
            rd = random.NextInt(lenData);
           sum+=data[rd];
        }
        double mean = (double) sum/nbObservedValue;
        return(mean);
    }

    public void setResult(int result) {
            this.result = result;
    }


    @Override
    public void run() {
        int first = part * (last/4);
        last = (part+1) * (last/4)-1;
        int[] subdata = subarray(data, first , last);
        int lenSubData = last - first;
        Mean meanObj = new Mean(subdata, lenSubData);
        double res = meanObj.mean();
        setResults(res);
    }
}