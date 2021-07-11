package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import javafx.fxml.Initializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class SampleController implements Initializable{
	Dijkstra Dij;
	
	 @FXML
	    private AnchorPane AnchP;

	    @FXML
	    private ImageView MapImg;

	    
	    @FXML
	    private Pane paneR;

	    @FXML
	    private Label titleLab;

	    @FXML
	    private Label sourceLab;

	    @FXML
	    private Label targetLab;

	    @FXML
	    private ComboBox<String> CBsource;

	    @FXML
	    private ComboBox<String> CBtarget;

	    @FXML
	    private Button runButton;

	    @FXML
	    private Label distanceLab;

	    @FXML
	    private TextField DistanceTF;

	    @FXML
	    private Label pathLab;

	    @FXML
	    private TextArea pathTF;

	    HashMap<String, City> Adj;
	    City[] cities;
	   Circle []circle;
	   Label [] label;
	public void readFile() throws FileNotFoundException {
		File Data = new File("Data.txt");
		Scanner Read =  new Scanner(Data);
		

		String[] data = (Read.nextLine()).split(",");
		int numberOfVer = Integer.parseInt(data[0].trim());
		int numberOfAdj = Integer.parseInt(data[1].trim());

		 cities = new City[numberOfVer];
		 circle=new Circle[numberOfVer];
		 label=new Label[numberOfVer];
		for (int i = 0; i < numberOfVer; i++) {
			String[] arr = (Read.nextLine()).split(",");
			cities[i] = new City((arr[2].trim()), Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
			 circle[i]=new Circle(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]), 3, javafx.scene.paint.Color.RED);
	         cities[i].circle=circle[i];
	         label[i]=new Label(arr[2].trim());
	         label[i].setLayoutX(Double.parseDouble(arr[0]));
	         label[i].setLayoutY(Double.parseDouble(arr[1]));
	         AnchP.getChildren().add(label[i]);
			AnchP.getChildren().add(circle[i]);
			

		}

		Adj = new HashMap<String, City>();
		for (int j = 0; j < numberOfVer; j++) {
			Adj.put(cities[j].cityName, cities[j]);
		}

		for (int i = 0; i < numberOfAdj; i++) {

			Adjacent adj_dest = new Adjacent();
			Adjacent adj_src = new Adjacent();
			String[] arr = (Read.nextLine()).split("#");
			double dist = Double.parseDouble(arr[2].trim());

			City src = Adj.get(arr[0].trim());
			
			City dest = Adj.get(arr[1].trim());
			
			adj_dest.adjName=dest.cityName;
			adj_dest.x=dest.x;
			adj_dest.y=dest.y;
			adj_dest.weight=dist;
			
			adj_src.adjName=src.cityName;
			adj_src.x=src.x;
			adj_src.y=src.y;
			adj_src.weight=dist;
			
			src.adjacent.AddLast(adj_dest);
         
			dest.adjacent.AddLast(adj_src);
			

		}

		Read.close();
		
		for (int i = 0; i < numberOfVer; i++) {	
			
			CBsource.getItems().add(cities[i].cityName);
			CBtarget.getItems().add(cities[i].cityName);
			
		}
		     
		
		
		//System.out.println(CBsource.getSelectionModel().getSelectedItem());
	}
	Line []line=new Line[0];
	
	public void find() {
		
		for(int i=0;i<cities.length;i++) {
			cities[i].getCircle().setFill(javafx.scene.paint.Color.RED);
		}
      
		for(int i=0;i<line.length;i++)
			 AnchP.getChildren().remove(line[i]);
		if(CBsource.getSelectionModel().getSelectedItem()!=null&&CBtarget.getSelectionModel().getSelectedItem()!=null) {
		  	
		City source=Adj.get(CBsource.getSelectionModel().getSelectedItem());
		 
		 source.getCircle().setFill(javafx.scene.paint.Color.BLACK);
			
		 City dest=Adj.get(CBtarget.getSelectionModel().getSelectedItem());
		 dest.getCircle().setFill(javafx.scene.paint.Color.BLACK);
		
		Dij=new Dijkstra(cities, source);
		Dij.findShortestPath(source,dest);
		
			
		Dij.printPath(Dij.convert(dest));
		if(Dij.dist==Double.MAX_VALUE) {
			pathTF.setText("There is No Path");
			pathTF.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
			DistanceTF.setText("There is No Path");
			DistanceTF.setFont(Font.font("Andalus", FontWeight.BOLD, 18));
		}
		else {
		pathTF.setText(Dij.S);
		pathTF.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
		DistanceTF.setText(Math.round(Dij.dist*100.0)/100.0+" كم");
		DistanceTF.setFont(Font.font("Andalus", FontWeight.BOLD, 18));
		String []s=Dij.S.split("\n");
		line=new Line[s.length-1];
		for(int i=0;i<s.length;i++) {
			if(i!=s.length-1) {
			City city1=Adj.get(s[i]);
			
			City city2=Adj.get(s[i+1]);
			 line[i]=new Line();
			
			line[i].setStartX(city1.x);
			
			line[i].setStartY(city1.y);
			
			line[i].setEndX(city2.x);
			
			line[i].setEndY(city2.y);
			
			AnchP.getChildren().add(line[i]);
			}
			
		}
		}}
		else {	
			if(CBsource.getSelectionModel().getSelectedItem()==null)
			   JOptionPane.showMessageDialog(null, "Insert the source city",null, JOptionPane.PLAIN_MESSAGE);
			else if
			(CBtarget.getSelectionModel().getSelectedItem()==null)
				   JOptionPane.showMessageDialog(null, "Insert the target city",null, JOptionPane.PLAIN_MESSAGE);
			
		
		}
		
	}
	
	int counter=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		try {
			readFile();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
			AnchP.setOnMouseClicked((MouseEvent e) -> {
		           double srcPointX=e.getSceneX();
		           double srcPointY=e.getSceneY();
		           City srcCity;
		           City destCity;
		           if(counter==0)
		           for(int i=0;i<cities.length;i++) {
                      if(cities[i].getX()-4<=srcPointX && cities[i].getX()+4>=srcPointX&&
                    		  cities[i].getY()-4<=srcPointY && cities[i].getY()+4>=srcPointY) {
                    	  
                    	 srcCity=cities[i];
                    	 srcCity.getCircle().setFill(javafx.scene.paint.Color.BLACK);
                    	 CBsource.setValue(srcCity.cityName);
                    	 counter=1;
                    	 break;

                      }}
		           else if(counter ==1)
		        	   for(int i=0;i<cities.length;i++) {
		                   if(cities[i].getX()-4<=srcPointX && cities[i].getX()+4>=srcPointX&&
		                 		  cities[i].getY()-4<=srcPointY && cities[i].getY()+4>=srcPointY) {
		                 	  
		                 	 destCity=cities[i];
		                 	 destCity.getCircle().setFill(javafx.scene.paint.Color.BLACK);
		                 	 counter=0;
		                 	CBtarget.setValue(destCity.cityName);
		                 	find();
		                 	 break;

		                   }}
		        	   
		        });
		
		
	
	}

	
	
	
	
}
