package combination;

import utils.LoadData;

public abstract class Combination {

	private Boolean distinct;
	private double[] keys;
	private LoadData loadData;
	private String [] colnames;  
	private int nbThreads;

	public Combination(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,int nbThreads) {
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename, lenFile);
		this.colnames = colnames;
		this.nbThreads = nbThreads;
	}
	
	public abstract void start_combination() throws InterruptedException;
	
	public double[] getKeys() {
		return keys;
	}
	
	public LoadData getLoadData() {
		return loadData;
	}

	public Boolean getDistinct() {
		return distinct;
	}
	
	public String[] getColnames() {
		return this.colnames;
	}
	
	public int getNbThreads() {
		return nbThreads; 
	}


	
}
