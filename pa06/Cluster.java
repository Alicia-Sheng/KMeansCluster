package pa06;

import java.util.ArrayList;
import java.util.Random;

/**
 * A cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 *
 */
public class Cluster {
	Sample clusterPt;
	ArrayList<Sample> rows;
	
	public Cluster(ArrayList<Sample> rows) {
		this.rows=rows;
	}
	
	//This method picks a random sample of a cluster
	public Sample randomPt() {
		Random rand=new Random();
		int randNum=rand.nextInt(this.rows.size());
		return this.rows.get(randNum);
	}
	
	//This method returns the average Sample in a cluster
	public Sample average() {
		ArrayList<Double> averageCoordinates=new ArrayList<Double>();
		int sampleNum=this.samples.size();
		int coordinateNum=this.samples.get(0).getSample().size();
		
		for (int i=0;i<coordinateNum;i++) {
			double sumCoordinate=0;
			for (int j=0;j<sampleNum;j++) {
				sumCoordinate+=this.samples.get(j).getSample().get(i);
			}
			averageCoordinates.add(sumCoordinate/sampleNum);
		}
		Sample average=new Sample(averageCoordinates);
		return average;
	}
	
	//This method prints a cluster
	public String printCluster() {
		String output="";
		for (Sample samp:this.rows) {
			output+=String.format("%s%n",samp.toString());
		}
		return output;
	}
	
	//this main method tests the Cluster class (can be deleted later)
	public static void main(String[] args) {
		System.out.println("testing for the Cluster class.");
		ArrayList<Double> p1=new ArrayList<Double>();//double[] p1 = {1,2};
		p1.add(1.0);p1.add(2.0);
		ArrayList<Double> p2=new ArrayList<Double>();////double[] p2 = {1,5};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		ArrayList<Sample> rows=new ArrayList<Sample>();
		rows.add(s1);rows.add(s2);
		Cluster c1=new Cluster(rows);
		System.out.println("random pt="+c1.randomPt());
		System.out.printf("%s%n%s","cluster:",c1.printCluster());
	}
}
