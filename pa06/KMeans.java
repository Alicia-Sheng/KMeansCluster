package pa06;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * 
 * @author presenting
 *
 */
public class KMeans {
	Cluster originalData;
	ArrayList<Cluster> clusters;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner file=new Scanner(new File("src/pa06/Test.txt"));
		ArrayList<Sample> allSamples=new ArrayList<Sample>();
		
		while (file.hasNextLine()){
			Scanner line=new Scanner(file.nextLine());
			ArrayList<Double> coordinates=new ArrayList<Double>();
			while (line.hasNext()) {
				coordinates.add(line.nextDouble());
			}
			Sample oneSample=new Sample(coordinates);
			allSamples.add(oneSample);
		}

		Cluster orignalData=new Cluster(allSamples);
		System.out.println(orignalData.printCluster());
	}	
}
