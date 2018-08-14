import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**to do: create method where bugs become hungry and seek food and fade over time if they do not eat
if they are not hungry they stop eating move away from plant

method for bugs reproduce if they bugs of diff colour if they get to a certain size and meet another bug
of the same size

new plant generated elsewhere

get plant grow method to work**/

public class Bug extends Circle {
	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	protected int id = 0;
	protected static int idCounter;
	protected String name;


	private Stage primaryStage;
	boolean eaten = false;
	boolean eating = false;
	public Bug(Stage primaryStage,double x, double y, double radius, Color color, String name) {
		super(x,y,radius,color);
		this.id = idCounter++;
		this.name = name;
	}

	public Bug(Stage primaryStage) {

	}

	/**----------------------------------------------------------------------------------------
	 * @param rockList **/
	public void update(Scene scene,List<Plant> plantList, List<Bug> bugList, Group root, List<Rock> rockList) {
		//all bugs move randomly
		moveRandom();
		//if(this instanceof Ladybug) {
		avoidRock(rockList);
		//}
		//if this is instance of a bee, bee eats plants and plants shrink
		if(this instanceof Bee) {
			eatPlants(plantList, bugList,root);
		}
		// if this is instance of ladybug then ladybug eats bees
		if(this instanceof Ladybug) {
			eatOtherBugs(bugList, root);
		}
		// bugs change direction randomly after eating plant
		if(dx==0) {
//			dx = -1.5f+Math.random()*3;
//			dy = -1.5f+Math.random()*3;	
			moveRandom();
		}
		//bugs bounce off left or right wall 
		if(getCenterX()+getTranslateX()< getRadius()||
				getCenterX() + getTranslateX()+getRadius()>scene.getWidth()) {
			dx = -dx;
		}
		// bugs bounce off top/bottom wall
		if(getCenterY()+getTranslateY()< getRadius()||
				getCenterY() + getTranslateY()+getRadius()>scene.getHeight()) {
			dy = -dy;

		}
		//if none of the above bugs move forward
		setTranslateX(getTranslateX()+dx);//look up translate
		setTranslateY(getTranslateY()+dy);

	}
	/**---------------------------------------------------------------------------------------------**/

	//method to decrease size of plant if bee lands on it
	public void eatPlants(List<Plant> plantList, List<Bug> bugList, Group root) {
		boolean found = false;//boolean found variable, if bee has not found a plant then found is false

		for (Plant p : plantList) {//iterate through arrayList of plants
			//if bounds of plant intersects with bounds of the bug calling animate(); then found is true
			if (p.getBoundsInParent().intersects(getBoundsInParent())) {
				found = true;
			}
			// if found is true and plant x and y scales are greater than 0 then:
			if (found&& p.getScaleX()>0 &&p.getScaleY()>0){
				p.shrink();//plant shrinks
				this.eat();//bee eats plant
				dx=0;//bee dx set to 0
				dy=0;//bee dy set to 0
				if(p.getScaleX()<1&&p.getScaleY()<0.125) {//if plant radius is <1 then plant is removed form group
					//p.setFill(Color.LIGHTGREEN);
					root.getChildren().remove(p);
					
				}

				break;
			}
		}
	}
	/**---------------------------------------------------------------------------------------
	 * @param root **/	
	public void eatOtherBugs( List<Bug> bugList, Group root) {	// carnivorous ladybug eat bees
		boolean meet = false;

		for (Bug b:bugList) {

			if (this.getBoundsInParent().intersects(b.getBoundsInParent())||b.getBoundsInParent().intersects(this.getBoundsInParent())) 
			{
				meet = true;
				if (meet){

					this.eat();
					if(b instanceof Bee) {
						root.getChildren().remove(b);
					}
					System.out.println(name+" ID="+id+" "+b.name+" "+b.id+" eaten="+b.eaten);

					break;
				}

			}

		}

	}


	/**------------------------------------------------------------------------------**/
	public void moveRandom() {
		double changeChecker = Math.random()*30;//generate a random number between 0 and 30

		if (changeChecker<1) {//bug has a 1 in 30 chance of changing direction

			dx = -1.5f+Math.random()*3;//speed is random between -1.5 and 3, this also randomly changes direction of bug
			dy = -1.5f+Math.random()*3;	
		}
	}
	/**------------------------------------------------------------------------------**/	
	//if bug eats something it grows in size
	public void eat() {
		//Creating scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(800)); 

		//Setting the node for the transition 
		scaleTransition.setNode(this); 

		//Setting the dimensions for scaling 
		scaleTransition.setByY(0.33); 

		scaleTransition.setByX(0.33); 

		//Setting the cycle count for the translation 
		scaleTransition.setCycleCount(1); 

		//Setting auto reverse value to true 
		//scaleTransition.setAutoReverse(true); 

		//Playing the animation 
		scaleTransition.play(); 


	}

	/**---------------------------------------------------**/	
	public void eaten() {
		//Creating scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(800)); 

		//Setting the node for the transition 
		scaleTransition.setNode(this); 

		//Setting the dimensions for scaling 
		scaleTransition.setByX(-1); 

		scaleTransition.setByY(-1); 

		//Setting the cycle count for the translation 
		scaleTransition.setCycleCount(1); 

		//Playing the animation 
		scaleTransition.play(); 

	}
	/**-----------------------------------------------------------------------**/

	public void avoidRock(List<Rock> rockList) {

		for(Rock r:rockList) {
		if (r.getBoundsInParent().intersects(getBoundsInParent())) {
			dx=-dx;
			dy=-dy;
			moveRandom();
		}
		}
	}
	public void findPlants() {

	}

	public void findBees() {

	}

	public boolean isEaten() {
		return eaten;
	}
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return id;

	}

	public void setId(int id) {
		this.id = id;
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