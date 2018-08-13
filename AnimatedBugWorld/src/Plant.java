import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Plant extends Circle {//may change this to just extend circle but for now plant
//	circles still use many of the same fields and methods as bug circles

	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	Stage primaryStage;
	
	public Plant(Stage primaryStage, double x, double y, double radius, Color color) {
		super(x, y, radius, color);
		
	}
	

	
	public void shrink() {
		//Creating scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(20)); 

		//Setting the node for the transition 
		scaleTransition.setNode(this); 

		//Setting the dimensions for scaling 
		scaleTransition.setByY(-0.33); 
		
		scaleTransition.setByX(-0.33); 

		//Setting the cycle count for the translation 
		scaleTransition.setCycleCount(1); 

		//Setting auto reverse value to true 
		//scaleTransition.setAutoReverse(true); 

		//Playing the animation 
		scaleTransition.play(); 
	}
	
	public void grow() {
		//Creating scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(6000)); 

		//Setting the node for the transition 
		scaleTransition.setNode(this); 

		//Setting the dimensions for scaling 
		scaleTransition.setByY(0.124); 
		
		scaleTransition.setByX(0.125); 

		//Setting the cycle count for the translation 
		scaleTransition.setCycleCount(1); 

		//Setting auto reverse value to true 
		//scaleTransition.setAutoReverse(true); 

		//Playing the animation 
		scaleTransition.play(); 
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
