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
	
	private Hashtable<Integer,String> EmployeNB = new Hashtable<>();
	private Hashtable<Integer,String> Age = new Hashtable<>();
	private Hashtable<Integer,String> Departement  = new Hashtable<>();
	
	private Hashtable<String,Hashtable<Integer,String>> Columns = new Hashtable<>(); 
	
	
	
	public LoadData(String fileName) {
		this.FileName = fileName; 
		
		Columns.put("EmployeId", EmployeNB); 
		Columns.put("Age", Age); 
		Columns.put("Departement", Departement); 
		
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
	    //Enumeration e = Departement.elements();
	    //while(e.hasMoreElements())
	        //System.out.println(e.nextElement());
	    
	    
	    //Exemple de list retourné par la team SELECTION 
	   
	    //Projection sur Age et dépatement 
	 	}catch(IOException ex) {
	 		ex.printStackTrace();
	 	}
	
	}
	
	public void Project( ArrayList<Integer> id, String[] columns) {
		
		for(int i : id) {
			for(String j : columns) {
				System.out.println(((Hashtable)this.Columns.get(j)).get(i));
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LoadData RData = new LoadData("src/Data/dataset.csv");
		
		RData.read();
		ArrayList<Integer> tab = new ArrayList<Integer>();
		tab.add(1);
		tab.add(3);
		tab.add(4);
		tab.add(5);
		String[] col = {"Age","Departement"};
		RData.Project(tab, col);
		
		}
	
	
}
