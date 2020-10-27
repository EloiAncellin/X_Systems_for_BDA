import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import utils.BasicHashSet;
import utils.LoadData;
import utils.Projection;

public class MultiThreadProjection implements Runnable {
		
		private String[] cl;
		private ArrayList<Integer> id;
		Boolean d;
		Projection prj; 
		
		public MultiThreadProjection(ArrayList<Integer> id, String[] columns, Boolean distinct,Hashtable<String,Hashtable<Integer,?>> hcl) {
			this.cl = columns;
			this.d = distinct;
			this.id = id; 
			prj = new Projection(hcl);
		}
		
		
		
		
		public void run() {
			prj.MultiThreadProject(this.id,this.cl,this.d); 
		}
		
		
		
		
		public static void main(String[] args) {
			LoadData RData = new LoadData("src/dataset/dataset_100.csv");
			
			RData.read();
			
			ArrayList<Integer> tab = new ArrayList<Integer>();
			Hashtable<String,Hashtable<Integer,?>> cl = RData.GetColumns();
			tab = new ArrayList();
			for (int i = 1; i <= 100; i++)
			tab.add(i);
			
					
			
			String[] col = {"ProductName"};
			
			
			
			Thread T1 = new Thread( new MultiThreadProjection(new ArrayList(tab.subList(0, 50)),col,false,cl));
			T1.start();
			Thread T2 = new Thread( new MultiThreadProjection(new ArrayList(tab.subList(50, 100)),col,false,cl));
			T2.start();
			/*
			for(int i =1 ; i<tab.size();i += tab.size()/4) {
				if(tab.size()%4 == 0) {
					if(i+tab.size()/4>tab.size()) {
						new Thread( new MultiThreadProjection(new ArrayList(tab.subList(i, tab.size())),col,true,cl)).start();
					}
					else {
						new Thread( new MultiThreadProjection(new ArrayList(tab.subList(i, i+tab.size()/4)),col,true,cl)).start();
					}
				}
				}
			for(int i =1 ; i<tab.size();i += tab.size()/4) {
				if(tab.size()%4 == 0) {
					new Thread( new MultiThreadProjection(new ArrayList(tab.subList(i, i+tab.size()/4)),col,false,cl)).start();
				}
				else {
					
					if(i+tab.size()/4>tab.size()) {
						new Thread( new MultiThreadProjection(new ArrayList(tab.subList(i, tab.size())),col,false,cl)).start();
					}
					else {
						new Thread( new MultiThreadProjection(new ArrayList(tab.subList(i, i+tab.size()/4)),col,false,cl)).start();
					}
					
				}
				
				
			}*/
			
			
			
			
		}
		
	
	

}
