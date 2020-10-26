package utils;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Projection {
	
	private static volatile Hashtable<String,Hashtable<Integer,?>> Columns = new Hashtable<>(); 
	private static volatile Hashtable<String,BasicHashSet> HashSetColumns = new Hashtable<String,BasicHashSet>();
	
	
	public Projection(Hashtable<String,Hashtable<Integer,?>> cl) {
		
		this.Columns = cl;
		Enumeration<String> e = cl.keys();
		while(e.hasMoreElements()) {
			
			HashSetColumns.put(e.nextElement(), new BasicHashSet(cl.get(e.nextElement()).size()));
		}
		
		
	}
	
	public synchronized static void MultiThreadProject( ArrayList<Integer> id, String[] columns, Boolean distinct) {
		
		if(distinct ==true) {
			int size = id.size();
		ArrayList<BasicHashSet> OutputsColumnsElements = new ArrayList<BasicHashSet>();
		
		for(String j : columns) {
			for(int i : id) {
				System.out.println(i);
				HashSetColumns.get(j).add( ((Hashtable)Columns.get(j)).get(i)  );
				
				
				
				/*if(elements.contains(((Hashtable)this.Columns.get(j)).get(id.get(i)))) {
					id.remove(i);
				}*/ 
			}
			OutputsColumnsElements.add(HashSetColumns.get(j));
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
					System.out.print(((Hashtable)Columns.get(j)).get(i)+ " | ");
				}
				System.out.println();
				
			}
			
		}
		
		
	}
	
	
	public synchronized void  Project( ArrayList<Integer> id, String[] columns, Boolean distinct) {
		
		Hashtable<Integer,Integer> hid = new Hashtable<Integer,Integer>();
		
		if( distinct == true) {
			
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
		LoadData RData = new LoadData("src/dataset/dataset_100.csv");
		
		RData.read();
		
		ArrayList<Integer> tab = new ArrayList<Integer>();
		Hashtable<String,Hashtable<Integer,?>> cl = RData.GetColumns();
		tab = new ArrayList(cl.get("CustomerId").values());
		
		
		Projection prj = new Projection(cl);
		String[] col = {"CustomerAge"};
		prj.Project(tab, col,true);
	
		}
	
	

}
