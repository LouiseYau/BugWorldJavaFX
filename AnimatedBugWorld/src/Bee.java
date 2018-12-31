import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bee extends Bug{

	public Bee(Stage primaryStage, double x, double y, double radius, Color color, String name) {
		super(primaryStage, x, y, radius, color, name);
		// TODO Auto-generated constructor stub
	}
	/**-------------------------------------------------------------------------------------------**/	
	public Bee(Stage primaryStage, double x, double y, double radius, String name) {
		super(primaryStage);

	}
	/**-------------------------------------------------------------------------------------------**/	
	public void eat() {
		if(this.getRadius() < 30) {
			this.setRadius(this.getRadius()+0.125);
		}
		if(this.getRadius() > 30) {
			this.setRadius(30);
		}


	}
	/**-------------------------------------------------------------------------------------------**/
	//bees move in one direction longer than ladybugs/bugs to simulate a flying motion
	public void moveRandom() { 
		double changeChecker = Math.random()*40;//generate a random number between 0 and 30

		if (changeChecker<1) {//bug has a 1 in 30 chance of changing direction

			dx = -1.5+Math.random()*3;//speed is random between -1.5 and 3, this also randomly changes direction of bug
			dy = -1.5+Math.random()*3;	
		}
	}

	public void distanceBetweenPlantAndBee(List<Plant> plantList) {
		for(Plant p:plantList) {
			double xDis= p.getCenterX()-this.getCenterX();
			double yDis = p.getCenterY() - this.getCenterY();	
			System.out.println("Bee "+this.id+" x distance:"+xDis+" y distance:"+yDis);
		}
	}
	/**-------------------------------------------------------------------------------------------
	 * @param plantList **/	
	public void findFood(List<Plant> plantList) {
		int distance = 20;
		for(Plant p:plantList) {
			if((this.getCenterX()>=p.getCenterX()-distance&& this.getCenterX()<p.getCenterX())
			&&(this.getCenterY()>=p.getCenterY()-distance&& this.getCenterY()<p.getCenterY())) { 
					this.setCenterX(this.getCenterX()+10);
					this.setCenterY(this.getCenterY()+10);
					
					break;
			}
			else {
				moveRandom();
			}
		}
	}
	/**-------------------------------------------------------------------------------------------**/
	public void moveTowards() {

	}
	/**-------------------------------------------------------------------------------------------**/

}
