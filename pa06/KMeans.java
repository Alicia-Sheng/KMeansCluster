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
			Cluster cluster = new Cluster(i);
			Sample clusterPt = Sample.createRandomPoint(MIN_COORD, MAX_COORD);
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

	private void clearClusters() {
		for (Cluster cluster : clusters) {
			cluster.clear();
		}
	}
	
    private ArrayList getClusterPts() {
    	ArrayList clusterPts = new ArrayList(K);
    	for(Cluster cluster : clusters) {
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
        
        for(Sample sample : samples) {
            for(int i = 0; i < K; i++) {
            	Cluster cluster = clusters.get(i);
                distance = sample.distance(cluster.getClusterPt());
                if(distance < min){
                    min = distance;
                    a = i;
                }
            }
            sample.setCluster(a);
            clusters.get(a).addPoint(sample);
        }
    }
    


	public static void main(String[] args) throws FileNotFoundException {
		KMeans kmeans = new KMeans();
		kmeans.setK();
		String SAMPLE_PATH = ".../sample.txt";
		// String CLUSTER_PATH = ".../cluster.txt";
		// //"C:/Users/lione/Desktop/Brandeis/ComputerScience/cs12b/PA06/s-originals/s1-cb.txt";
		kmeans.readSamples(SAMPLE_PATH);

		Cluster orignalData = new Cluster(allSamples);
		System.out.println(orignalData.printCluster());
	}
}