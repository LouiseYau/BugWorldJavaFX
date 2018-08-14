import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
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

	public void shrink() {
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
		
		this.setRadius(this.getRadius()-1);

		if(this.getRadius() < 1) { // if plant is below size threshold, reduces radius to 0
			this.setRadius(0);
		}
	}

	//}
	

	public void grow() {
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
//		scaleTransition.setByY(0.006); 
//
//		scaleTransition.setByX(0.006); 
//
//		//Setting the cycle count for the translation 
//		scaleTransition.setCycleCount(1); 
//
//		//Setting auto reverse value to true 
//		//scaleTransition.setAutoReverse(true); 
//
//		//Playing the animation 
//		scaleTransition.play(); 
	

			if(this.getRadius() < 20) {
				this.setRadius(this.getRadius()+0.025);
			}

			if(this.getRadius() > 25) {
				this.setRadius(25);
			}
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
