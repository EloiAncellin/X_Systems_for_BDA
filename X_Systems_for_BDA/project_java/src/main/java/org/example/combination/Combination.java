package combination;

import utils.LoadData;
import utils.Mesures;

public abstract class Combination {

	private Boolean distinct;
	private double[] keys;
	private LoadData loadData;
	private String [] colnames;  
	private int nbThreads;
	private int lenFile;

	public Combination(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,int nbThreads) {
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename, lenFile);
		this.colnames = colnames;
		this.nbThreads = nbThreads;
		this.lenFile =lenFile;
	}
	
	public abstract void start_combination(Mesures mesure) throws InterruptedException;
	
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
	
	public int getLenFile () {
		return this.lenFile;
	}
	
	public int getNbThreads() {
		return nbThreads; 
	}


	
}
