package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class Projection {

	private static volatile Hashtable<String, Hashtable<Integer, ?>> columns = new Hashtable<>();
	private static volatile Hashtable<String, BasicHashSet> hashSetColumns = new Hashtable<String, BasicHashSet>();
	private static volatile Hashtable<String, ArrayList<?>> resultMT = new Hashtable<String, ArrayList<?>>();
	private Hashtable<String, ArrayList<?>> resultST = new Hashtable<>();
	//private static volatile Hashtable<String, ArrayList<?>> id_wo_duplicate = new Hashtable<String, ArrayList<?>>();
	
	public Projection(Hashtable<String, Hashtable<Integer, ?>> cl, String[] allcolnames) {

		columns = cl;

		for (String s : allcolnames) {
			hashSetColumns.put(s, new BasicHashSet(cl.get(s).size()));

		}

	}

	public static Hashtable<String, Hashtable<Integer, ?>> Array_to_Hash(Hashtable<String, Hashtable<Integer, ?>> cl,
			ArrayList<Integer> tab, String allcolnames) {
		Hashtable<String, Hashtable<Integer, ?>> hashmap = cl;
		Hashtable<String, Hashtable<Integer, ?>> colonne = new Hashtable<String, Hashtable<Integer, ?>>();
		Hashtable<Integer, Object> temp = new Hashtable();
	
		for (Integer i : tab) {

			for (Map.Entry<Integer, ?> entry : hashmap.get(allcolnames).entrySet()) {
		
				if (entry.getKey().compareTo(i) == 0) {
					//System.out.println("getkey : " +entry.getKey());
					temp.put(entry.getKey(), entry.getValue());
				//	System.out.println("temp : " +temp);
					colonne.put(allcolnames, temp);
	
				}

			}

		}
		return colonne;
	}

	public synchronized static Hashtable<String, ArrayList<?>> MultiThreadProject(ArrayList<Integer> index,
			String[] colnames, Boolean distinct) {

		if (distinct == true) {

			for (String j : colnames) {

				for (int i : index) {

					hashSetColumns.get(j).add(((Hashtable<Integer, ?>) columns.get(j)).get(i));
				}
			}

		}

		else {

			for (String j : colnames) {
				ArrayList tab = new ArrayList<>();
				for (int i : index) {
					tab.add(columns.get(j).get(i));
					if (!resultMT.containsKey(j)) {
						resultMT.put(j, new ArrayList<>());
					}
				}
				resultMT.get(j).addAll(tab);
			}
		}
		return resultMT;

	}

	public Hashtable<String, BasicHashSet> getMTProjectionDistinct() {

		return hashSetColumns;
	}

	public Hashtable<String, ArrayList<?>> getMTProjection() {

		return resultMT;
	}

	public Hashtable<String, ArrayList<?>> Project(ArrayList<Integer> index, String[] colnames, Boolean distinct) {

		if (distinct == true) {/*
								 * 
								 * int size = index.size(); ArrayList<BasicHashSet> OutputsColumnsElements = new
								 * ArrayList<BasicHashSet>();
								 * 
								 * for (String j : colnames) { BasicHashSet elements = new BasicHashSet(size);
								 * for (int i : index) { elements.add(((Hashtable<Integer, ?>)
								 * columns.get(j)).get(i));
								 * 
								 * 
								 * } OutputsColumnsElements.add(elements);
								 * 
								 * }
								 * 
								 * for (int k = 0; k < OutputsColumnsElements.size(); k++) {
								 * 
								 * Iterator<?> it = OutputsColumnsElements.get(k).iterator(); ArrayList tab =
								 * new ArrayList<>(); while (it.hasNext()) { tab.add(it.next()); }
								 * System.out.println(tab); resultST.put(colnames[k],tab);
								 */

			for (String j : colnames) {

				for (int i : index) {

					hashSetColumns.get(j).add(((Hashtable<Integer, ?>) columns.get(j)).get(i));
				}
				resultST.put(j, hashSetColumns.get(j).toList());
			}

		} else {

			for (String j : colnames) {
				ArrayList tab = new ArrayList<>();
				for (int i : index) {
					tab.add(columns.get(j).get(i));
				}
				resultST.put(j, tab);

			}

		}
		return resultST;

	}

	public synchronized static Hashtable<String, ArrayList<?>> MultiThreadProjectsort(ArrayList<Integer> index, String[] col, Boolean distinct) {

		Hashtable<String, Hashtable<Integer, ?>> hashmap = columns;

		if (distinct == true) {
			for (String column : col) {
				Hashtable<String, Hashtable<Integer, ?>> tab1 = Array_to_Hash(hashmap, index, column);
				ArrayList<Integer> tab_wo_dup = removeDuplicateHashmap(tab1, column);
				ArrayList result = new ArrayList();

				System.out.println();

				for (int i : tab_wo_dup) {

					result.add(tab1.get(column).get(i));
				}	
				if (!resultMT.containsKey(column)) {
					resultMT.put(column, new ArrayList<>());
				}
				resultMT.get(column).addAll(result);
			}
		} else {
			for (String i : col) {
				System.out.print(i + " | ");
			}

			System.out.println();
			for (String j : col) {
				ArrayList tab = new ArrayList<>();
				for (int i : index) {
					tab.add(columns.get(j).get(i));
					if (!resultMT.containsKey(j)) {
						resultMT.put(j, new ArrayList<>());
					}
				}
				resultMT.get(j).addAll(tab);
			}
		}
		return resultMT;
	}

	
	public synchronized Hashtable<String, ArrayList<?>> Project_sort(ArrayList<Integer> index, String[] col, Boolean distinct) {
		Hashtable<String, Hashtable<Integer, ?>> hashmap = this.columns;

		if (distinct == true) {
			for (String column : col) {
				Hashtable<String, Hashtable<Integer, ?>> tab1 = Array_to_Hash(hashmap, index, column);
		
				ArrayList<Integer> tab_wo_dup = removeDuplicateHashmap(tab1, column);
				ArrayList result = new ArrayList();
				System.out.println();

				for (int i : tab_wo_dup) {
			
					result.add(tab1.get(column).get(i));

				}
				resultST.put(column, result);
			}

		} else {
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
		
		return resultST;
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
			if (arr[i] != (arr[i + 1])) {

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
			if (arr[i] != (arr[i + 1])) {

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

	public static ArrayList<Integer> removeDuplicateHashmap(Hashtable<String, Hashtable<Integer, ?>> tab,
			String attribut) {

		ArrayList<Integer> tab_id_wo_duplicate = new ArrayList<Integer>();
	//	System.out.println("bon" + tab.get(attribut));
		ArrayList<Object> tab_values = new ArrayList(tab.get(attribut).values());
		if (tab_values.get(0) instanceof String) {
			Object[] arr = tab_values.toArray(new String[tab_values.size()]);
			String[] str = (String[]) arr;
			int longueur = str.length;

			mergeSort(str, 0, str.length - 1);

			int longueure = removeDuplicateElements(str, longueur);

			String[] tab_wo_duplicate = new String[longueur];
			for (int i = 0; i < longueure; i++) {
				tab_wo_duplicate[i] = str[i];
			}
			for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
				for (int i = 0; i < longueure; i++) {
					// System.out.println("tab " +tab_wo_duplicate[i]);
					if (entry.getValue() == tab_wo_duplicate[i]) {
						// System.out.println("value " +tab_wo_duplicate[i]);
						tab_id_wo_duplicate.add(entry.getKey());
						// System.out.println(" key "+ entry.getKey());
						tab_wo_duplicate[i] = null;

					}

				}
			}
		}
		if (tab_values.get(0) instanceof Integer) {
			Integer[] arr = tab_values.toArray(new Integer[tab_values.size()]);
			// int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
			int[] str = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
			// int[] str = convertListoint(arr);
			int longueur = str.length;

			sort(str, 0, str.length - 1);

			int longueure = removeDuplicateElements_int(str, longueur);

			int[] tab_wo_duplicate = new int[longueur];
			for (int i = 0; i < longueure; i++) {
				tab_wo_duplicate[i] = str[i];

			}
			for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
				for (int i = 0; i < longueure; i++) {

					if (entry.getValue().equals(tab_wo_duplicate[i])) {
						// System.out.println("value " +tab_wo_duplicate[i]);
						tab_id_wo_duplicate.add(entry.getKey());
						// System.out.println(" key "+ entry.getKey());
						tab_wo_duplicate[i] = 0;

					}

				}
			}
		}
		if (tab_values.get(0) instanceof Double) {
			Double[] arr = tab_values.toArray(new Double[tab_values.size()]);
			// int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
			// int[] str = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
			// int[] str = convertListoint(arr);
			// Object[] arr = tab_values.toArray(new Float[tab_values.size()]);
			// float[] str = (float[]) arr;

			// Float[] arr = tab_values.toArray(new Float[tab_values.size()]);
			// int[] intArray = arr.stream(arr).mapToInt(Integer::intValue).toArray();
			// int[] str = Arrays.stream(arr).mapToFloat(Float::floatValue).toArray();

			double[] str = new double[arr.length];
			int k = 0;
			for (double f : arr) {
				str[k++] = f;
			}

			int longueur = str.length;

			sort(str, 0, str.length - 1);

			int longueure = removeDuplicateElements_double(str, longueur);

			double[] tab_wo_duplicate = new double[longueur];
			for (int i = 0; i < longueure; i++) {
				tab_wo_duplicate[i] = str[i];

			}
			for (Map.Entry<Integer, ?> entry : tab.get(attribut).entrySet()) {
				for (int i = 0; i < longueure; i++) {

					if (entry.getValue().equals(tab_wo_duplicate[i])) {
						// System.out.println("value " +tab_wo_duplicate[i]);
						tab_id_wo_duplicate.add(entry.getKey());
						// System.out.println(" key "+ entry.getKey());
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
			} else {
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
			} else {
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
    
	public static Hashtable<String, ArrayList<?>> removemergeMulti(Hashtable<String, ArrayList<?>> result, String[] colnames){
		
		Hashtable<String, ArrayList<?>> results = new Hashtable<String, ArrayList<?>> ();
		for (String s : colnames ) {
			if(result.get(s).get(0) instanceof String) {
				
				Object[] arr = result.get(s).toArray(new String[result.get(s).size()]);
				String[] str = (String[]) arr;
				int longueur = str.length;

				mergeSort(str, 0, str.length - 1);

				int longueure = removeDuplicateElements(str, longueur);
				ArrayList<String> tab_wo_duplicate = new ArrayList<String>(longueure);
				for (int i = 0; i < longueure; i++) {
					tab_wo_duplicate.add(str[i]);
 
				}
				results.put(s,tab_wo_duplicate);
				
			}
			if(result.get(s).get(0) instanceof Integer) {
				Integer[] arr = result.get(s).toArray(new Integer[result.get(s).size()]);
				int[] str = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
				int longueur = str.length;


				sort(str, 0, str.length - 1);

				int longueure = removeDuplicateElements_int(str, longueur);

				ArrayList<Integer> tab_wo_duplicate = new ArrayList<Integer>(longueure);
				for (int i = 0; i < longueure; i++) {
					tab_wo_duplicate.add(str[i]);

				}
				
				results.put(s,tab_wo_duplicate);
				
			}
			
		}
		
		
		return  results;
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
		String[] col = { "CustomerAge", "CustomerName" };
        
		Hashtable<String, ArrayList<?>> projection = prj.Project_sort(tab, col, true);
		System.out.println(projection);
	}
}
