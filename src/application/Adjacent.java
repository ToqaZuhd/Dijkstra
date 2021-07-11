package application;

public class Adjacent {
	String adjName;
	double weight;
	double x;
	double y;
	
	public Adjacent(){
		
	}
	
	public Adjacent(String adjName, double x, double y, double weight){
		
		this.adjName = adjName;
		this.weight = weight;
		this.x = x;
		this.y = y;
		
	}
	
public Adjacent(String adjName, double weight){
		
		this.adjName = adjName;
		this.weight = weight;
		
	}

public String getAdjName() {
	return adjName;
}

public void setAdjName(String adjName) {
	this.adjName = adjName;
}

public void setDimension(double x, double y) {
	this.x = x;
	this.y = y;
}

public double getX() {
	return x;
}

public void setX(double x) {
	this.x = x;
}

public double getY() {
	return y;
}

public void setY(double y) {
	this.y = y;
}

public double getWeight() {
	return weight;
}

public void setWeight(double weight) {
	this.weight = weight;
}



}
