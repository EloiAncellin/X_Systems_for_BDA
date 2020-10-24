package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

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
	    
	 	}catch(IOException ex) {
	 		ex.printStackTrace();
	 	}
	
	}
	
	public void  Project( ArrayList<Integer> id, String[] columns, Boolean Distinct) {
		
		if( Distinct == true) {
			int size = id.size();
			ArrayList<BasicHashSet> OutputsColumnsElements = new ArrayList<BasicHashSet>();
			
			for(String j : columns) {
				BasicHashSet elements = new BasicHashSet(size);  
				for(int i : id) {
					elements.add( ((Hashtable)this.Columns.get(j)).get(i)  );
				}
				OutputsColumnsElements.add(elements);
				System.out.println();
				
			}
			
			for (int k=0 ; k < OutputsColumnsElements.size();k++) {
				Iterator it =  OutputsColumnsElements.get(k).iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
			}
			
		}
		else {
			
			for(String i : columns) {
				System.out.print(i + " | ") ;
			}
			System.out.println();
			for(int i : id) {
				for(String j : columns) {
					System.out.print(((Hashtable)this.Columns.get(j)).get(i)+ " | ");
				}
				System.out.println();
				
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
		tab.add(5);
		tab.add(5);
		
		
		
		String[] col = {"Age","Departement"};
		RData.Project(tab, col,true);
	
		}
	
	
}
