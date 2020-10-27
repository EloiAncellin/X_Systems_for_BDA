package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class LoadData {
	
	private String FileName; 
	
	private ArrayList records = new ArrayList<>();
	
	private Hashtable<Integer,String> CustomerName = new Hashtable<>();
	private Hashtable<Integer,Integer> CustomerAge = new Hashtable<>();
	private Hashtable<Integer,Integer> ProductId  = new Hashtable<>();
	private Hashtable<Integer,Integer> CustomerId = new Hashtable<>(); 
	private Hashtable<Integer,String> ProductName = new Hashtable<>();
	private Hashtable<Integer,Float> ProductPrice = new Hashtable<>();
	private Hashtable<Integer,Integer> PurchaseId = new Hashtable<>(); 
	
	
	private Hashtable<String,Hashtable<Integer,?>> Columns = new Hashtable<>(); 
	
	private String[] columnsname = {"CustomerName","CustomerAge","ProductId","CustomerId",
			"ProductName","ProductPrice","PurchaseId"};
	
	
	
	public LoadData(String fileName) {
		this.FileName = fileName; 
		
		Columns.put("CustomerName", CustomerName); 
		Columns.put("CustomerAge", CustomerAge ); 
		Columns.put("ProductId", ProductId);
		Columns.put("CustomerId", CustomerId);
		Columns.put("ProductName", ProductName);
		Columns.put("ProductPrice", ProductPrice);
		Columns.put("PurchaseId", PurchaseId);
	
		
		
		
		
	}
	
	
	public void read() {
	
	try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
	    String line;
	    int i =0 ;
	    while ((line = br.readLine()) != null) {
	    	
	    	if(i>=1) {
	    	String[] values = line.split(",");
	        int id = Integer.parseInt(values[0]);
	        int age = Integer.parseInt(values[2]);
	        int pdid = Integer.parseInt(values[3]);
	        float pdprice = Float.parseFloat(values[5]);
	        int purch = Integer.parseInt(values[6]);
	        
	        CustomerId.put(i,id);
	        CustomerName.put(i, values[1]);
	        CustomerAge.put(i, age);
	        ProductId.put(i, pdid);
	        ProductName.put(i, values[4]);
	        ProductPrice.put(i, pdprice);
	        PurchaseId.put(i, purch); 
	      
	        
	        records.add(Arrays.asList(values));
	    	}
	          i++;
	    }
	    
	    
	    /*Enumeration e = Columns.keys();
	    while(e.hasMoreElements())
	        System.out.println(Columns.get(e.nextElement()).size());*/
	    
	    
	 	}catch(IOException ex) {
	 		ex.printStackTrace();
	 	}
	
	}
	

	
	public Hashtable<String,Hashtable<Integer,?>> GetColumns(){
		return this.Columns; 
	}
	
	public String[] GetColumnsName() {
		return this.columnsname; 
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LoadData RData = new LoadData("src/dataset/dataset_100.csv");
		
		RData.read();
		ArrayList<Integer> tab = new ArrayList<Integer>();
		Hashtable<String,Hashtable<Integer,?>> cl = RData.GetColumns();
		
		tab = new ArrayList(cl.get("CustomerId").values());
		
		
	
		}
	
	
}
