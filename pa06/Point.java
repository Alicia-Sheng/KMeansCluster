package PA06;

import java.util.*;

public class Point {
	private double x;
	private double y;
	private int clusterNumber;
	
	
        // Construct two fields of Point: x and y
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getClusterNumber() {
		return this.clusterNumber;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setClusterNumber(int number) {
		this.clusterNumber = number;
	}


	// calculate the distance between point and centroid
	public double distance(Point centroid) {
		SquareY = Math.pow((centroid.y - y), 2);
	        SquareX = Math.pow((centroid.x - x), 2);
		return Math.sqrt(SquareY+SquareX);
	}

        // creates a random Point
	public Point createRandomPoint(int a, int b) {
		Random rand = new Random();
		double x = a + (b-a)*rand.nextDouble();
		double y = a + (b-a)*rand.nextDouble();
		return new Point(x,y);
	}

	// create a list that is consisted of random Points
	public ArrayList<Point> createRandomList(int p, int q, int num) {
		ArrayList<Point> sample = new ArrayList<Point>();
		for (int i=0; i<num; i++) {
			sample.add(createRandomPoint(p,q));
		}
		return sample;

	}

	public String toString() {
		return "("+x+","+y+")";

	}
}
