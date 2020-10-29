package utils;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Collections;

public class Projection {

	private static volatile Hashtable<String, Hashtable<Integer, ?>> columns = new Hashtable<>();
	private static volatile Hashtable<String, BasicHashSet> hashSetColumns = new Hashtable<String, BasicHashSet>();
	private static volatile Hashtable<String,ArrayList<Integer>> id_wo_duplicate = new Hashtable<String,ArrayList<Integer>>();
	private static volatile Hashtable<String, Hashtable<Integer, ?>> result = new Hashtable<String, Hashtable<Integer, ?>>();
	public Projection(Hashtable<String, Hashtable<Integer, ?>> cl, String[] allcolnames) {

		columns = cl;

		for (String s : allcolnames) {
			hashSetColumns.put(s, new BasicHashSet(cl.get(s).size()));
			
		}

	}
	
	public static Hashtable<String, Hashtable<Integer, ?>> Array_to_Hash(Hashtable<String, Hashtable<Integer, ?>> cl, ArrayList<Integer> tab,String allcolnames) {

	    Hashtable<String,Hashtable<Integer,?>> colonne = new Hashtable<String,Hashtable<Integer,?>>();
	    Hashtable<Integer,Object> temp = new Hashtable();
	    //this.Columns = cl;
		for (Object i :tab)
		{ 
		 	 
		 		
		 		for (Map.Entry<Integer, ?> entry : cl.get(allcolnames).entrySet()) {
		 			if(entry.getKey() == i) {
		 			
		 				temp.put(entry.getKey(), entry.getValue());
		 				colonne.put(allcolnames, temp);
		 				
		 			}
		 			
		 		}
			
		}
		
		return colonne;
	}

	public synchronized static ArrayList<BasicHashSet> MultiThreadProject(ArrayList<Integer> index, String[] colnames, Boolean distinct) {
		ArrayList<BasicHashSet> outputsColumnsElements = new ArrayList<BasicHashSet>();
		if (distinct == true) {
			

			for (String j : colnames) {
				
				for (int i : index) {
		    
					hashSetColumns.get(j).add(((Hashtable<Integer, ?>) columns.get(j)).get(i));
						

					/*
					 * if(elements.contains(((Hashtable)this.Columns.get(j)).get(id.get(i)))) {
					 * id.remove(i); }
					 */
					
				}
				
				outputsColumnsElements.add(hashSetColumns.get(j));

			}
			
			

			

			  /*for (int k = 0; k < outputsColumnsElements.size(); k++) { 
				  Iterator<?> it =outputsColumnsElements.get(k).iterator(); 
				  while (it.hasNext()) {
			       System.out.println(it.next()); } 
				  }*/

			 

		} /*
			 * else {
			 * 
			 * for (String i : colnames) { System.out.print(i + " | "); }
			 * System.out.println(); for (int i : index) { for (String j : colnames) {
			 * System.out.print(((Hashtable) columns.get(j)).get(i) + " | "); }
			 * System.out.println();
			 * 
			 * }
			 * 
			 * }
			 */
		return outputsColumnsElements; 
	}
	
	
	
	
	

	public void Project(ArrayList<Integer> index, String[] colnames, Boolean distinct) {

		if (distinct == true) {

			int size = index.size();
			ArrayList<BasicHashSet> OutputsColumnsElements = new ArrayList<BasicHashSet>();

			for (String j : colnames) {
				BasicHashSet elements = new BasicHashSet(size);
				for (int i : index) {
					elements.add(((Hashtable<Integer, ?>) columns.get(j)).get(i));

				}
				OutputsColumnsElements.add(elements);

			}

			for (int k = 0; k < OutputsColumnsElements.size(); k++) {
				Iterator<?> it = OutputsColumnsElements.get(k).iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
			}

		} else {

			for (String i : colnames) {
				System.out.print(i + " | ");
			}
			System.out.println();
			
			for (int i : index) {
				for (String j : colnames) {
					System.out.print(((Hashtable<Integer, ?>) columns.get(j)).get(i) + " | ");
				}
				System.out.println();

			}

		}

	}
public Hashtable<String, BasicHashSet> getMTProjectionDistinct() {
		
		return hashSetColumns;
	}
	public Hashtable<String, Hashtable<Integer, ?>> getMTProjection() {
		
		return result; 
	}
	
    
	public synchronized static void MultiThreadProjectsort(ArrayList<Integer> index, String[] col, Boolean distinct) {
		
Hashtable<String, Hashtable<Integer, ?>> hashmap =columns;
        
    	if (distinct == true){
    		for (String column :col) {
    		Hashtable<String, Hashtable<Integer, ?>> tab1 = Array_to_Hash(hashmap,index,column);
    		ArrayList<Integer> tab_wo_dup = removeDuplicateHashmap(tab1,column);
    		
    		System.out.println();
        	
    		for( int i : tab_wo_dup) {
    			//System.out.println(i);
    			System.out.println(tab1.get(column).get(i)+ " | " );
    		
                
    		}
    		id_wo_duplicate.put(column, tab_wo_dup);
    		System.out.println(id_wo_duplicate);
    		
    		}
    		
    	}
    	else {
    		for (String i : col) {
    			System.out.print(i + " | ");
    		}
			
			System.out.println();
			int record;
			for (int i : index) {
				for (String j : col) {
					System.out.print(((Hashtable) columns.get(j)).get(i) + " | ");
				}
				System.out.println();

			}

		}
		
	}
    public synchronized void Project_sort(ArrayList<Integer> index, String[] col, Boolean distinct) {
    	Hashtable<String, Hashtable<Integer, ?>> hashmap =this.columns;
        
    	if (distinct == true){
    		for (String column :col) {
    		Hashtable<String, Hashtable<Integer, ?>> tab1 = Array_to_Hash(hashmap,index,column);
    		ArrayList<Integer> tab_wo_dup = removeDuplicateHashmap(tab1,column);
    		
    		System.out.println();
        	
    		for( int i : tab_wo_dup) {
    			//System.out.println(i);
    			System.out.println(tab1.get(column).get(i)+ " | " );
    		
                
    		}
    		id_wo_duplicate.put(column, tab_wo_dup);
    	//	System.out.println(id_wo_duplicate);
    		
    		}
    		
    	}
    	else {
    		for (String i : col) {
    			System.out.print(i + " | ");
    		}
			
			System.out.println();
			int record;
			for (int i : index) {
				for (String j : col) {
					System.out.print(((Hashtable) columns.get(j)).get(i) + " | ");
				}
				System.out.println();

			}

		}
    }

	public static int removeDuplicateElements(String arr[], int n) {

		String[] temp = new String[n];
		int j = 0;
		for (int i = 0; i < n - 1; i++) {
			if (!(arr[i].equals(arr[i + 1]))) {

				temp[j++] = arr[i];

			}

		}

		temp[j++] = arr[n - 1];

		// Changing original array

		for (int i = 0; i < j; i++) {

			arr[i] = temp[i];

		}
		return j;

	}
	public static int removeDuplicateElements_int(int arr[], int n) {

		int[] temp = new int[n];
		int j = 0;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i]!=(arr[i + 1])) {

				temp[j++] = arr[i];

			}

		}

		temp[j++] = arr[n - 1];

		// Changing original array

		for (int i = 0; i < j; i++) {

			arr[i] = temp[i];

		}
		return j;

	}
	public static int removeDuplicateElements_double(double arr[], int n) {

		double[] temp = new double[n];
		int j = 0;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i]!=(arr[i + 1])) {

				temp[j++] = arr[i];

			}

		}

		temp[j++] = arr[n - 1];

		// Changing original array

		for (int i = 0; i < j; i++) {

			arr[i] = temp[i];

		}
		return j;

	}
	
	public static ArrayList<Integer> removeDuplicateHashmap(Hashtable<String, Hashtable<Integer, ?>> tab,String attribut) {
        
		
		ArrayList<Integer> tab_id_wo_duplicate = new ArrayList<Integer>();
		ArrayList<Object> tab_values = new ArrayList(tab.get(attribut).values());
        if (tab_values.get(1) instanceof String) {
		Object[] arr = tab_values.toArray(new String[tab_values.size()]);
		String[] str = (String[]) arr;
		int longueur = str.length;
		
		mergeSort(str, 0, str.length - 1);
		
		int longueure = removeDuplicateElements(str,longueur);
		
		String[] tab_wo_duplicate = new String[longueur];
		for (int i = 0; i < longueure; i++) {
			tab_wo_duplicate[i] = str[i];
		}
		for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
			for (int i = 0; i < longueure; i++) {
				//System.out.println("tab " +tab_wo_duplicate[i]);
				if (entry.getValue() == tab_wo_duplicate[i]) {
				//	System.out.println("value " +tab_wo_duplicate[i]);
					tab_id_wo_duplicate.add(entry.getKey());
				//	System.out.println(" key "+ entry.getKey());
					tab_wo_duplicate[i] = null;

				}

			}
		}
        }
        if (tab_values.get(1) instanceof Integer) {
    		Integer[] arr = tab_values.toArray(new Integer[tab_values.size()]);
    	//	int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
    		int[] str = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
    	//	int[] str = convertListoint(arr);
    		int longueur = str.length;
    		
    		sort(str, 0, str.length - 1);
    		
    		int longueure = removeDuplicateElements_int(str,longueur);
    		
    		int[] tab_wo_duplicate = new int[longueur];
    		for (int i = 0; i < longueure; i++) {
    			tab_wo_duplicate[i] = str[i];
    		
    		}
    		for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
    			for (int i = 0; i < longueure; i++) {
    				
    				if (entry.getValue().equals(tab_wo_duplicate[i])) {
    				//	System.out.println("value " +tab_wo_duplicate[i]);
    					tab_id_wo_duplicate.add(entry.getKey());
    				//	System.out.println(" key "+ entry.getKey());
    					tab_wo_duplicate[i] = 0;

    				}

    			}
    		}
            }
        if (tab_values.get(1) instanceof Double) {
    		Double[] arr = tab_values.toArray(new Double[tab_values.size()]);
    	//	int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
    	//	int[] str = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
    	//	int[] str = convertListoint(arr);
    	//	Object[] arr = tab_values.toArray(new Float[tab_values.size()]);
    	//	float[] str = (float[]) arr;
        	
        //	Float[] arr = tab_values.toArray(new Float[tab_values.size()]);
        	//	int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
          //  int[] str = Arrays.stream(arr).mapToFloat(Float::floatValue).toArray();
    		
        	double[] str = new double[arr.length];
        	int k = 0;
        	for (double f : arr) {
        	  str[k++] = f;
        	}
        	
        	int longueur = str.length;
    		
    		sort(str, 0, str.length - 1);
    		
    		int longueure = removeDuplicateElements_double(str,longueur);
    		
    		double[] tab_wo_duplicate = new double[longueur];
    		for (int i = 0; i < longueure; i++) {
    			tab_wo_duplicate[i] = str[i];
    		
    		}
    		for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
    			for (int i = 0; i < longueure; i++) {
    				
    				if (entry.getValue().equals(tab_wo_duplicate[i])) {
    				//	System.out.println("value " +tab_wo_duplicate[i]);
    					tab_id_wo_duplicate.add(entry.getKey());
    				//	System.out.println(" key "+ entry.getKey());
    					tab_wo_duplicate[i] = 0;

    				}

    			}
    		}
            }
        
		return tab_id_wo_duplicate;

	}
	
	


	public static void merge(int arr[], int l, int m, int r)

	{

		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;

		}

	}

	public static int[] sort(int arr[], int l, int r)

	{

		if (l < r) {
			int m = (l + r) / 2;
			sort(arr, l, m);
			sort(arr, m + 1, r);
			merge(arr, l, m, r);
		}

		return arr;

	}
	public static void merge(double arr[], int l, int m, int r)

	{

		int n1 = m - l + 1;
		int n2 = r - m;
		double L[] = new double[n1];
		double R[] = new double[n2];
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;

		}

	}

	public static double[] sort(double arr[], int l, int r)

	{

		if (l < r) {
			int m = (l + r) / 2;
			sort(arr, l, m);
			sort(arr, m + 1, r);
			merge(arr, l, m, r);
		}

		return arr;

	}

	

	public static void mergeSort(String[] a, int from, int to) {

		if (from == to) {
			return;
		}
		int mid = (from + to) / 2;
		mergeSort(a, from, mid);

		mergeSort(a, mid + 1, to);

		merge(a, from, mid, to);

	}

	public static void merge(String[] a, int from, int mid, int to) {

		int n = to - from + 1;
		String[] b = new String[n];
		int i1 = from;
		int i2 = mid + 1;
		int j = 0;
		while (i1 <= mid && i2 <= to) {

			if (a[i1].compareTo(a[i2]) < 0) {

				b[j] = a[i1];

				i1++;

			} else {

				b[j] = a[i2];

				i2++;

			}

			j++;

		}

		while (i1 <= mid) {

			b[j] = a[i1];

			i1++;

			j++;

		}

		while (i2 <= to) {

			b[j] = a[i2];

			i2++;

			j++;

		}

		for (j = 0; j < n; j++) {

			a[from + j] = b[j];

		}

	}


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LoadData RData = new LoadData("src/dataset_sorted/dataset_sorted_100.csv", 100);

		RData.read();

		ArrayList<Integer> tab = new ArrayList<Integer>();
		Hashtable<String, Hashtable<Integer, ?>> cl = RData.GetColumns();
		System.out.println(cl.get("CustomerAge"));
		tab = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++)
			tab.add(i);
		String[] All_col_names = RData.GetColumnsName();
		Projection prj = new Projection(cl, All_col_names);
		String[] col = { "CustomerAge","ProductPrice" };	
		
		prj.Project_sort(tab, col, true);
        System.out.println();
	}
}
