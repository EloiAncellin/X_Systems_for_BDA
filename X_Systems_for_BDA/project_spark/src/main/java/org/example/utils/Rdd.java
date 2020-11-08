package utils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import java.util.ArrayList;
import java.util.List;


public class Rdd {
    private ArrayList<Integer> data;
    private int lenData;
    private JavaSparkContext sc;

    public Rdd(ArrayList<Integer> data, int lenData, JavaSparkContext sc) {
        this.data = data;
        this.lenData = lenData;
        this.sc = sc;
    }

    public double avgMapReduce(){

        JavaRDD<Integer> ages = sc.parallelize(data);
        long totalAge = ages.reduce((x, y) -> x + y);
        long count = ages.map(item -> 1)
                .reduce((x, y) -> x + y);
        System.out.println("Total of ages: " + totalAge);
        System.out.println("count recu en param: " + lenData);
        System.out.println("Count trouve avec mapReduce: " + count);
        return (double) totalAge / count;
    }

}