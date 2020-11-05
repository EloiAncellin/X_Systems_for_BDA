package utils;
import java.util.ArrayList;
import java.util.List;


import java.util.Hashtable;
public class MultiThreadProjectionSort implements Runnable{
	
	
	private String[] cl;
	private ArrayList<Integer> id;
	Boolean distinct;
	private int last;
	private int part;
	private Hashtable<String, ArrayList<?>> projection;
	public MultiThreadProjectionSort(ArrayList<Integer> id,String[] columns, Boolean distinct, Hashtable<String,Hashtable<Integer,?>>data,String[] allcolnames, int part,int last)
	{   Projection obj = new Projection(data,allcolnames);
		this.cl = columns;
		this.id = id; 
		this.distinct = distinct;
		this.part = part;
		this.last = last;
	}
	public void run() {
		int first = part * (last/4);
		last = (part+1) * (last/4)-1;
		List<Integer> sub = this.id.subList(first ,last+1);
		this.projection = Projection.MultiThreadProjectsort(new ArrayList<Integer>(sub),this.cl,this.distinct);
		this.projection = Projection.removemergeMulti(projection,this.cl);
	}
    
	public synchronized Hashtable<String, ArrayList<?>> getprojection() {
		return this.projection;
	}
	public static void main(String[] args) {
		/*
		LoadData Rdata = new LoadData("src/dataset_sorted/dataset_sorted_100.csv",100);
		Rdata.read();
		

		ArrayList<Integer> tab = new ArrayList<Integer>();
		Hashtable<String,Hashtable<Integer,?>> cl = Rdata.GetColumns();
		tab = new ArrayList<Integer>();
		for (int i = 0; i <= 100; i++) {
		tab.add(i);}
		
		String [] All_col_names = Rdata.GetColumnsName();
		String[] col = {"CustomerAge"};
		ArrayList <Integer> tab2 = new ArrayList<Integer> (tab.subList(0, 50));
		ArrayList <Integer> tab3 = new ArrayList<Integer> (tab.subList(0, 99));
	   
		Thread T1 = new Thread(new MultiThreadProjectionSort(tab2,col,false,cl,All_col_names,1,50));
		T1.start();
		System.out.println("thread1");
		
	    Thread T2 = new Thread(new MultiThreadProjectionSort(tab3,col,false,cl,All_col_names,1,60));
	    T2.start();
	    System.out.println("thread2");*/
	
	   
	}
}

