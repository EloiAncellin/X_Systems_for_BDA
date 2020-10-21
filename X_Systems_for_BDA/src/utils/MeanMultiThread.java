public class MeanMultiThread{
    int[] data;
    int lenData;
    int last;
    int part;
    int nbPart;

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
        float mean = 0;
        int first = part * (last/4);
        last = (part+1) * (last/4)-1;
        int n = last - first;
        int[] sub = subarray(data, first , last);
        for (int i = 0; i<n; i++){
            mean+=sub[i];
        }
        double moy = (double)mean/n;
        return(moy);
    }

    double meanStochastic(){
        int moyenne = 0;
        int nbValeurObservee = lenData/10;
        int first = part * (last/4);
        last = (part+1) * (last/4)-1;
        int n = last - first;
        int[] sub = subarray(data, first , last);
        Random random = new Random();
        int rd;
        for (int i = 0; i<nbValeurObservee; i++){
            rd = random.NextInt(lenData);
            moyenne+=data[rd];
        }
        double moy = (double) moyenne/nbValeurObservee;
        return(moy);
    }
}