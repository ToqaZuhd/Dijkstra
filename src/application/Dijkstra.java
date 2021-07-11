package application;


	import java.util.HashMap;
	import java.util.PriorityQueue;



	public class Dijkstra {

		HashMap<String, Table> T = new HashMap<String, Table>();
		HashMap<String, City> C = new HashMap<String, City>();

		Table[] Table;
		City[] city;
		PriorityQueue<Table> pq;

		public Dijkstra(City[] city, City source) {

			this.city = new City[city.length];
			this.city = city;
			this.Table = new Table[city.length];
			this.pq = new PriorityQueue<Table>(city.length, new CityComparator() {
			});
			initializeTable(source);

		}

		public void initializeTable(City source) {
			
			for (int i = 0; i < Table.length; i++) {
				Table[i] = new Table();
			}

			for (int i = 0; i < city.length; i++) {
				Table[i].vertName = city[i].cityName;
				Table[i].distance = Double.MAX_VALUE;
				Table[i].known = false;
				Table[i].previous = null;
			}

			for (int j = 0; j < city.length; j++) {
				T.put(city[j].cityName, Table[j]);
			}

			for (int j = 0; j < city.length; j++) {
				C.put(city[j].cityName, city[j]);
			}

			Table temp = T.get(source.cityName);
			temp.distance = 0;
			pq.add(temp);
			
			

		}

		public void findShortestPath(City source,City dest) {
			Table d=convert(dest);
          	for(;;) {
          		
				Table v = pq.poll();
				if(v==null)
					break;
				
                if(!v.known) {
					
				v.known = true;
				if(d.known)
					break;
				City Adj = C.get(v.vertName);

				Node node = Adj.adjacent.first;
				while (node != null) {

					Table w = T.get(node.data.adjName);

					double weight = node.data.weight;

					if (!w.known) {

						if ((v.distance + weight) < w.distance) {

							w.distance = v.distance + weight;

							w.previous = v;
							pq.add(w);
						}
					}

					node = node.next;
				}

			}
				
				 }
         
		}

		public void print() {
			T.entrySet().forEach(entry -> {
				if (entry.getValue().previous != null)
					System.out.println(entry.getKey() + " => " + ", "
							+ entry.getValue().distance + ", " + entry.getValue().previous.vertName);

				else
					System.out.println(
							entry.getKey() + " => " + entry.getValue().vertName + ", " + entry.getValue().distance);

			});

			
		}

		String S="";
		double dist;
		public void printPath(Table dest) {
			if(T.get(dest.vertName).previous!=null) {
				printPath(T.get(dest.vertName).previous);
				S+=("\n");
			}
			dist=T.get(dest.vertName).distance;
			S+=dest.vertName;	
			
			}
		
		public Table convert(City city) {
			Table t=T.get(city.cityName);
			return t;
		}

}
