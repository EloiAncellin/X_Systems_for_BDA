package combination_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;


public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		var fileName = "src/Data/dataset.csv";
		
		ArrayList records = new ArrayList<>();
		Hashtable EmployeNB = new Hashtable();
		Hashtable Age = new Hashtable();
		Hashtable Departement  = new Hashtable();
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    int i =0 ;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        EmployeNB.put(i, values[4]);
		        Age.put(i, values[0]);
		        Departement.put(i, values[2]);
		        i++;
		        
		        records.add(Arrays.asList(values));
		    }
		    Enumeration e = Departement.elements();
		    
		    while(e.hasMoreElements())
		        System.out.println(e.nextElement());
		    
		    //Exemple de list retourné par la team SELECTION 
		    int [] tab = {3,5,6,8};
		    for (int j : tab) {
		    	System.out.println(Age.get(j)+","+Departement.get(j));
		    }
		    //Projection sur Age et dépatement 
		    
		}

      
   }
	
}
