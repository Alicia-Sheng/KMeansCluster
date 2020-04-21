import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A Sample represents a vector of doubles to be used in a clustering
 * algorithm...
 *
 */

public class Sample {
	static ArrayList<Double> sample;
    private int cluster_number = 0;

	public Sample(ArrayList<Double> values) { // I changed this from double[] to ArrayList, so that it's easier to
												// access data from the file
		this.sample = new ArrayList<Double>();
		for (int i = 0; i < values.size(); i++) {
			sample.add(values.get(i));
		}
	}
	
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }

	// This method calculates the distance between two samples
	public double distance(Sample other) {
		double sum = 0;
		for (int i = 0; i < this.sample.size(); i++) {
			sum += Math.pow((this.sample.get(i) - other.sample.get(i)), 2);
		}
		double distance = Math.sqrt(sum);
		return distance;
	}

	public String toString() {
		String output = "(" + this.sample.get(0);
		for (int i = 1; i < this.sample.size(); i++) {
			output += "," + this.sample.get(i);
		}
		return output + ")";
	}

	public ArrayList<Double> getSample() {
		return this.sample;
	}

	public void setSample(ArrayList<Double> sample) {
		this.sample = sample;
	}

	public static Sample randomPoint(int min, int max) {
		Random r = new Random();
		int dimension = sample.size();
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < dimension; i++) {
			double a = min + (max - min) * r.nextDouble();
			result.add(a);
		}
		return new Sample(result);
	}

	// this main method tests the Cluster class (can be deleted later)
	public static void main(String[] args) {
		System.out.println("testing for the Sample class.");
		ArrayList<Double> p1 = new ArrayList<Double>();// double[] p1 = {1,2};
		p1.add(1.0);
		p1.add(2.0);
		ArrayList<Double> p2 = new ArrayList<Double>();//// double[] p2 = {1,5};
		p2.add(1.0);
		p2.add(5.0);
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		System.out.println("s1=" + s1.toString());
		System.out.println(s1.distance(s2));
	}

}