import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ladybug extends Bug {
	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	
	public Ladybug(Stage primaryStage, double x, double y, double radius, Color color,String name) {
		super(primaryStage, x, y, radius, color, name);
		// TODO Auto-generated constructor stub
	}
	
	public void eat() {
		//Creating scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(800)); 

		//Setting the node for the transition 
		scaleTransition.setNode(this); 

		//Setting the dimensions for scaling 
		scaleTransition.setByY(0.5); 

		scaleTransition.setByX(0.5); 

		//Setting the cycle count for the translation 
		scaleTransition.setCycleCount(1); 

		//Setting auto reverse value to true 
		//scaleTransition.setAutoReverse(true); 

		//Playing the animation 
		scaleTransition.play(); 

	} 
	/**-------------------------------------------------------------------------------------------
	 * @return **/	
	public boolean smellFood() {
		return false;

	}
	/**-------------------------------------------------------------------------------------------**/
	public void moveTowards() {

	}
	/**-------------------------------------------------------------------------------------------**/


	
}


