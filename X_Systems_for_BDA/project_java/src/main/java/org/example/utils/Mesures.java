package utils;

import java.util.ArrayList;

public class Mesures{

	ArrayList<Long> projection;
	ArrayList<Long> selection;
	ArrayList<Long> aggregation;
	int lengthArr;

	public Mesures(int lengthArr){
		projection = new ArrayList<Long>();
		selection = new ArrayList<Long>();
		aggregation = new ArrayList<Long>();
		this.lengthArr = lengthArr;
	}

	public void addProjection(long newVal){
		this.projection.add(newVal);
	}

	public void addSelection(long newVal){
		this.selection.add(newVal);
	}

	public void addAggregation(long newVal){
		this.aggregation.add(newVal);
	}


	public Long getAverageProjection(){
			Long sum = 0L;
			    for (Long projectionMesure : this.projection) {
        	sum += projectionMesure;
    		}
    		return sum/this.projection.size();
	}

	public Long getAverageSelection(){
			Long sum = 0L;
			    for (Long selectionMesure : this.selection) {
        	sum += selectionMesure;
    		}
    		return sum/this.selection.size();
	}

	public Long getAverageAggregation(){
			Long sum = 0L;
			    for (Long aggregationMesure : this.aggregation) {
        	sum += aggregationMesure;
    		}
    		return sum/this.aggregation.size();
	}
}