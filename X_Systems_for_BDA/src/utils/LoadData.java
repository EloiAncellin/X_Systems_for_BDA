package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class LoadData {
	
	private String FileName; 
	private ArrayList records = new ArrayList<>();
	private Hashtable EmployeNB = new Hashtable();
	private Hashtable Age = new Hashtable();
	private Hashtable Departement  = new Hashtable();
	
	
	
	public LoadData(String fileName) {
		this.FileName = fileName; 
	}
	
	
	public void read() {
	
	try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
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
	 	}catch(IOException ex) {
	 		ex.printStackTrace();
	 	}
	
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LoadData RData = new LoadData("src/Data/dataset.csv");
		RData.read();
		    
		}
	
	
}
