public class Min{
    int[] data; /*Cette liste doit être triée*/
    int lenData;

    public int minOrdered(){
        return data[0];
    }

    public int minUnordered(){
        int minValue = data[0];
        for(int i =1 ; i < lenData ; i++){
            if (data[i] < minValue){
                minValue = data[i];
            }
        }
        return(minValue);
    }
}