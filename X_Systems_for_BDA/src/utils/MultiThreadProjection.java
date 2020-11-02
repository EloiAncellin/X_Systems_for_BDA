package utils;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

public class MultiThreadProjection implements Runnable {
		
		private String[] cl;
		private ArrayList<Integer> id;
		Boolean distinct;
		private int last;
		private int part; 
	
		
		public MultiThreadProjection(ArrayList<Integer> id, String[] columns, Boolean distinct,Hashtable<String,Hashtable<Integer,?>> hcl,
				String[] allcolnames, int part ,int last) {
			this.cl = columns;
			this.distinct = distinct;
			this.id = id;
			this.part = part;
			this.last = last;
		}
		
		
		
		
		public  void run() {
			int first = part * (last/4);
			last = (part+1) * (last/4)-1;
			List<Integer> sub = this.id.subList(first ,last+1);
			System.out.println(sub);
			Projection.MultiThreadProject(new ArrayList<Integer>(sub),this.cl,this.distinct);
			
		}
		
		
		/*public static void main(String[] args) {
			LoadData RData = new LoadData("src/dataset/dataset_100.csv",100);
			
			RData.read();
			
			ArrayList<Integer> tab = new ArrayList<Integer>();
			Hashtable<String,Hashtable<Integer,?>> cl = RData.GetColumns();
			tab = new ArrayList();
			for (int i = 1; i <= 100; i++)
			tab.add(i);
			
			String [] All_col_names = RData.GetColumnsName();
			
					
			
			String[] col = {"ProductName"};
			
			
			
			//Thread T1 = new Thread( new MultiThreadProjection(new ArrayList(tab.subList(0, 50)),col,true,cl,All_col_names));
			//T1.start();
			
			
			
			
		}*/
		
	
	

}
