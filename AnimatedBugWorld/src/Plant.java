import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Plant extends Thing {//may change this to just extend circle but for now plant
	//	circles still use many of the same fields and methods as bug circles

	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	private Stage primaryStage;

	public Plant(Stage primaryStage, double x, double y, double radius, Color color) {
		super(primaryStage, x, y, radius, color);

	}
	// orginally had scale transition animation to shrink and grow plants because 
	//my animation was acting on the update method not the update/animate world method in bugworld class 
	//so couldn't use radius becuase loop only happened once
	public void shrink() {
// orginally had scale transition animation to shrink and grow bugs because 		
//		//Creating scale Transition 
//		ScaleTransition scaleTransition = new ScaleTransition(); 
//
//		//Setting the duration for the transition 
//		scaleTransition.setDuration(Duration.millis(20)); 
//
//		//Setting the node for the transition 
//		scaleTransition.setNode(this); 
//
//		//Setting the dimensions for scaling 
//		scaleTransition.setByY(-0.125); 
//
//		scaleTransition.setByX(-0.125); 
//
//		//Setting the cycle count for the translation 
//		scaleTransition.setCycleCount(1); 
//
//		//Setting auto reverse value to true 
//		//scaleTransition.setAutoReverse(true); 
//
//		//Playing the animation 
//		scaleTransition.play(); 
		
		this.setRadius(this.getRadius()-1);//subtract one from plant radius

		if(this.getRadius() < 1) { // if plant is below size threshold, reduces radius to 0
			this.setRadius(0);
		}
	}

	//}
	

	public void grow(List<Plant> plantList, Group root) {
double newPlant = Math.random()*200; 
			if(this.getRadius() < 22) {
				this.setRadius(this.getRadius()+0.05);
			}

//			if(this.getRadius()>22&& newPlant<1) {
//				addPlant(plantList, root);
//			}
			if(this.getRadius() > 23) {
				this.setRadius(22);
			}
		}
	public void addPlant(List<Plant> plantList, Group root){
	double randomPlant= Math.random()*2;//random plant generator
	double x=5+Math.random()*500;//assigning random number upto the width of the scene to the x variable 
	double y=5+Math.random()*450;//assigning random number upto the height of the scene to the y variable
	double radius = 20;//random radius/size of circle
	Plant p = new Plant(primaryStage, x,y,radius,Color.GREEN);
	if(randomPlant<1) {
		Image image = new Image("flowers2.png");
		p.setFill(new ImagePattern(image));
	}
	else {
		Image image = new Image("kale.png");
		p.setFill(new ImagePattern(image));
	}
	plantList.add(p);//add bug to bugList 
	root.getChildren().add(p);
}
	

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}


}
