package combination;

import utils.LoadData;
import utils.Mesures;
import org.apache.spark.api.java.JavaSparkContext;

public abstract class Combination {

	private Boolean distinct;
	private double[] keys;
	private LoadData loadData;
	private String [] colnames;  
	private int nbThreads;
	private int lenFile;
	private JavaSparkContext sc;

	public Combination(String filename, int lenFile, Boolean distinct, double[] keys, String[] colnames,int nbThreads, JavaSparkContext sc) {
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename, lenFile);
		this.colnames = colnames;
		this.nbThreads = nbThreads;
		this.lenFile =lenFile;
		this.sc = sc;
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

	public JavaSparkContext getSc() {
		return sc;
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
