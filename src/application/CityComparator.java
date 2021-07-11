package application;

import java.util.Comparator;



public class CityComparator implements Comparator<Table> {

	@Override
	public int compare(Table c1, Table c2) {
		return (int) (c1.distance - c2.distance);
	}
}
