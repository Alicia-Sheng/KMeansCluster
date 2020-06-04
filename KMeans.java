import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class KMeans {
	private static int K;
	private static int N = 30;
	private static final int MIN_COORD = 0;
	private static final int MAX_COORD = 100;
	private ArrayList<Sample> samples;
	private ArrayList<Cluster> clusters;
	private static int D = 2;

	public KMeans() {
		this.samples = new ArrayList<Sample>();
		this.clusters = new ArrayList<Cluster>();
	}

	public static void main(String[] args) throws FileNotFoundException {
		KMeans kmeans = new KMeans();
		kmeans.setK();
		String SAMPLE_PATH = "C:/Users/lione/Desktop/Brandeis/ComputerScience/cs12b/PA06/s-originals/s1.txt";
		kmeans.readSamples(SAMPLE_PATH);
		kmeans.readClusterPts(K);
		kmeans.run();
	}

	public void setK() {
		Scanner in = new Scanner(System.in);
		System.out.println("number of cluster: ");
		int n = in.nextInt();
		this.K = n;
	}

	public void readSamples(String PATH) throws FileNotFoundException {
		Scanner file = new Scanner(new File(PATH));
		while (file.hasNextLine()) {
			Scanner line = new Scanner(file.nextLine());
			ArrayList<Double> coordinates = new ArrayList<Double>();
			while (line.hasNext()) {
				coordinates.add(line.nextDouble());
			}
			Sample oneSample = new Sample(coordinates);
			this.samples.add(oneSample);
		}
	}

	public void readClusterPts(int K) {
		for (int i = 0; i < K; i++) {
			Cluster cluster = new Cluster();
			Sample clusterPt = Sample.randomSample(MIN_COORD, MAX_COORD, D);
			cluster.setClusterPt(clusterPt);
			clusters.add(cluster);
		}
		printClusters();
	}

	private void printClusters() {
		for (int i = 0; i < K; i++) {
			Cluster cluster = clusters.get(i);
			cluster.printCluster();
		}
	}

	private void clear() {
		for (Cluster cluster : clusters) {
			cluster.clear();
		}
	}

	private ArrayList<Sample> getClusterPts() {
		ArrayList<Sample> clusterPts = new ArrayList<Sample>();
		for (Cluster cluster : clusters) {
			Sample clusterPt = cluster.getClusterPt();
			Sample sample = new Sample(clusterPt.sample);
			clusterPts.add(sample);
		}
		return clusterPts;
	}

	private void setCluster() {
		double minDistance = Double.MAX_VALUE;
		int index = 0;
		double distance = 0.0;

		for (Sample sample : samples) {
			minDistance = Double.MAX_VALUE;
			for (int i = 0; i < K; i++) {
				Cluster cluster = clusters.get(i);
				distance = sample.distance(cluster.getClusterPt());
				if (distance < minDistance) {
					minDistance = distance;
					index = i;
				}
			}
			sample.setClusterNum(index);
			clusters.get(index).addSample(sample);
		}
	}

	private void calculateClusterPts(int D) {
		for (Cluster cluster : clusters) {
			ArrayList<Double> list1 = new ArrayList<Double>();
			ArrayList<Double> list2 = new ArrayList<Double>();
			ArrayList<Sample> samples = cluster.getSamples();
			int size = samples.size();
			for (int i = 0; i < D; i++) {
				list1.add(0.0);
				list2.add(0.0);
			}
			for (Sample sample : samples) {
				for (int i = 0; i < D; i++) {
					double a = sample.sample.get(i) + list1.get(i);
					list1.set(i, a);
				}
			}
			Sample clusterPt = cluster.getClusterPt();
			if (size > 0) {
				for (int i = 0; i < D; i++) {
					double a = list1.get(i) / size;
					list2.set(i, a);
				}
				clusterPt.setSample(list2);
				cluster.setClusterPt(clusterPt);
			}
		}
	}

	public void run() {
		boolean run = true;
		int count = 0;

		while (run) {
			clear();
			ArrayList<Sample> ClusterPts1 = getClusterPts();

			setCluster();
			calculateClusterPts(D);
			count++;
			ArrayList<Sample> ClusterPts2 = getClusterPts();

			double distance = 0;
			for (int i = 0; i < ClusterPts1.size(); i++) {
				distance += ClusterPts1.get(i).distance(ClusterPts2.get(i));
			}
			System.out.println(count + ": ");
			System.out.println("Cluster point distance: " + distance);
			System.out.println();
			printClusters();
			if (distance == 0) {
				run = false;
			} 
		}
	}

}