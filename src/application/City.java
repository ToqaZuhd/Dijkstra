package application;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class City {
	String cityName;
	double distance=0;
	double x, y;
	LinkedList  adjacent=new LinkedList();
	Circle circle;
	Label label;
	
	
	public City(){
		
	}
	
	public City(String cityName, double x, double y){
		
		this.cityName = cityName;
		
		this.x = x;
		this.y = y;
		
	}

public City(String cityName, LinkedList adjacent){
		
		this.cityName = cityName;
		this.adjacent = adjacent;
		
	}

public String getCityName() {
	return cityName;
}

public void setCityName(String cityName) {
	this.cityName = cityName;
}

public LinkedList getAdjacent() {
	return adjacent;
}

public void setAdjacent(LinkedList adjacent) {
	this.adjacent = adjacent;
}

public double getX() {
	return x;
}

public void setX(double x) {
	this.x = x;
}

public void setDimension(int x, int y) {
	this.x = x;
	this.y = y;
}

public double getY() {
	return y;
}

public void setY(double y) {
	this.y = y;
}

public double getDistance() {
	return distance;
}

public void setDistance(double distance) {
	this.distance = distance;
}

public Circle getCircle() {
	return circle;
}

public void setCircle(Circle circle) {
	this.circle = circle;
}


}
