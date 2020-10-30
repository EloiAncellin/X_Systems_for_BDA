public class Max{
    int[] data; /*Cette liste doit être triée*/
    int lenData; /*On doit recevoir la taille de la liste*/

    public int min(){
        return data[lenData -1];
    }

    public int maxUnordered(){
        int maxValue = data[0];
        for(int i =1 ; i < lenData ; i++){
            if (data[i] > maxValue){
                maxValue = data[i];
            }
        }
        return(maxValue);
    }
}