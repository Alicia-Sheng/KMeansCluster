package PA06;

import java.util.*;

public class Point {
	public double x;
	public double y;
	private int clusterNumber = 0;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public void setClusterNumber(int n) {
		this.clusterNumber = n;
	}

	public int getClusterNumber(int n) {
		return this.clusterNumber;
	}

	// calculate the distance between point and centroid
	public double distance(Point centroid) {
		return Math.sqrt(Math.pow((centroid.y - y), 2) + Math.pow((centroid.x - x), 2));
	}

  // creates a random Point
	public Point createRandomPoint(int min, int max) {
		Random rand = new Random();
		double x = min + (max-min)*rand.nextDouble();
		double y = min + (max-min)*rand.nextDouble();
		return new Point(x,y);
	}

	// create a list that is consisted of random Points
	public ArrayList<Point> createRandomList(int min, int max, int number) {
		ArrayList<Point> sample = new ArrayList<Point>();
		for (int i=0; i<number; i++) {
			sample.add(createRandomPoint(min,max));
		}
		return sample;

	}

	public String toString() {
		return "("+x+","+y+")";

	}
}
