package combination;

import utils.LoadData;

public abstract class Combination {

	private Boolean distinct;
	private double[] keys;
	private LoadData loadData;

	public Combination(String filename, int lenFile, Boolean distinct, double[] keys) {
		this.distinct = distinct;
		this.keys = keys;
		this.loadData = new LoadData(filename, lenFile);
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

	
}
