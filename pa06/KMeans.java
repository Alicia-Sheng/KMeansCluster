import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class KMeans {
	private static int K;
	private static int N = 5000;
	private static final int MIN_COORD = 0;
	private static final int MAX_COORD = 1200000;
	Cluster originalData;
	private ArrayList<Sample> samples;
	private ArrayList<Cluster> clusters;

	public KMeans() {
		this.samples = new ArrayList<Sample>();
		this.clusters = new ArrayList<Cluster>();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		KMeans kmeans = new KMeans();
		kmeans.setK();
		String SAMPLE_PATH = "C:/Users/lione/Desktop/Brandeis/ComputerScience/cs12b/PA06/s-originals/s1.txt";
		// String CLUSTER_PATH = ".../cluster.txt";
		// //"C:/Users/lione/Desktop/Brandeis/ComputerScience/cs12b/PA06/s-originals/s1-cb.txt";
		kmeans.readSamples(SAMPLE_PATH);
		kmeans.run();
		//Cluster orignalData = new Cluster(allSamples);
		//System.out.println(orignalData.printCluster());
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

	public void setClusters(int K) {
		for (int i = 0; i < K; i++) {
			Cluster cluster = new Cluster();
			Sample clusterPt = Sample.randomPoint(MIN_COORD, MAX_COORD);
			cluster.setClusterPt(clusterPt);
			this.clusters.add(cluster);
		}
	}

//    public void readClusters(String PATH) throws FileNotFoundException {
//		Scanner file = new Scanner(new File(PATH));
//		while (file.hasNextLine()) {
//			Scanner line = new Scanner(file.nextLine());
//			ArrayList<Double> coordinates = new ArrayList<Double>();
//			while (line.hasNext()) {
//				coordinates.add(line.nextDouble());
//			}
//			Cluster oneCluster = new Cluster(coordinates);
//			this.clusters.add(oneCluster);
//		}
//    }

	private void printClusters() {
		for (int i = 0; i < K; i++) {
			Cluster cluster = clusters.get(i);
			cluster.printCluster();
		}
	}
	
    private void clear() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }

	private void clearClusters() {
		for (Cluster cluster : clusters) {
			cluster.clear();
		}
	}

	private ArrayList getClusterPts() {
		ArrayList clusterPts = new ArrayList(K);
		for (Cluster cluster : clusters) {
			Sample clusterPt = cluster.getClusterPt();
			Sample sample = new Sample(clusterPt.sample);
			clusterPts.add(sample);
		}
		return clusterPts;
	}

	private void setCluster() {
		double min = Double.MAX_VALUE;
		int a = 0;
		double distance = 0.0;

		for (Sample sample : samples) {
			for (int i = 0; i < K; i++) {
				Cluster cluster = clusters.get(i);
				distance = sample.distance(cluster.getClusterPt());
				if (distance < min) {
					min = distance;
					a = i;
				}
			}
			//sample.setCluster(a);
			//clusters.get(a).addSample(sample);
		}
	}

	private void calculateClusterPts() {
        for(Cluster cluster : clusters) {
        	ArrayList<Double> list1 = new ArrayList<Double>();
        	ArrayList<Double> list2 = new ArrayList<Double>();
            ArrayList<Sample> samples = cluster.getSamples();
            int size = samples.size();
            int dimension = samples.get(1).sample.size();
            for(Sample sample : samples) {
            	for(int i = 0; i < dimension; i++) {
            		double a = sample.sample.get(i) + list1.get(i);
            		list1.set(i, a);
            	}
            }
            Sample clusterPt = cluster.getClusterPt();
            if(size > 0) {
            	for(int i = 0; i < dimension; i++) {
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
			clearClusters();

			ArrayList<Sample> ClusterPts1 = getClusterPts();
			setCluster();
			calculateClusterPts();
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