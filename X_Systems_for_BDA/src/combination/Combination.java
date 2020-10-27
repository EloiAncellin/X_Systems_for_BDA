package combination;

import java.util.ArrayList;

import utils.LoadData;

public abstract class Combination {

	private String filename;
	private int lenFile;
	private Boolean distinct;
	private double[] keys;
	private LoadData loadData;
	private float[] customerPrice;
	
	public Combination(String filename, int lenFile, Boolean distinct, double[] keys) {
		this.filename = filename;
		this.lenFile = lenFile;
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename, lenFile);
	}
	
	public double[] getKeys() {
		return keys;
	}
	
	public LoadData getLoadData() {
		return loadData;
	}

	public abstract void start_combination() throws InterruptedException;
	
	
}
