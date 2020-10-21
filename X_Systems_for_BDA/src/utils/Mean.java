import java.util.Random;

public class Mean{
    int[] data;
    int lenData;
    float results;

    public Mean(int[] data, int lenData) {
        this.data = data;
        this.lenData = lenData;
    }

    public double moyenne(){
        int mean = 0;
        for (int i = 0; i<lenData; i++){
            mean+=data[i];
        }
        double moy = (double) mean / lenData;
        return(mean);
    }

    public double moyenneStochastic(){
        int nbValeurObservee = lenData/10;
        Random random = new Random();
        int rd;
        float moyenne = 0;
        for (int i = 0; i<nbValeurObservee; i++){
            rd = NextInt(lenData);
            moyenne+=data[rd];
        }
        double moy = (double) moyenne/nbValeurObservee;
        return(moy);
    }

}