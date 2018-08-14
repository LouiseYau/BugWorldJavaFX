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


/**to do list: 

 * Create method where bugs become hungry and seek food and fade over time if they do not eat

 * If they are not hungry they stop eating move away from plant

 *Method for bugs reproduce if they bugs of diff colour if they get to a certain size and meet another bug
 of the same size

 *New plant generated elsewhere

 *Drag objects and bugs around (if bugs get stuck)

 *Place bugs with clicks

 *add specific number bugs or objects

 *if no bees left carnivors eat eachother
 **/

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
		avoidRock(rockList);
		//if this is instance of a bee, bee eats plants and plants shrink
		if(this instanceof Bee) {
			eatPlants(plantList, bugList,root);
		}
		// if this is instance of ladybug then ladybug eats bees
		if(this instanceof Ladybug) {
			eatOtherBugs(bugList, root);
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

		for (Plant p : plantList) {//iterate through arrayList of plants
			//if bounds of plant intersects with bounds of the bug calling animate(); then found is true
			if (p.getBoundsInParent().intersects(getBoundsInParent())) {
				p.shrink();//plant shrinks
				this.eat();//bee eats plant
				moveRandom();
				if(p.getRadius()==0) {//if plant radius = 0 then plant is removed form group
					root.getChildren().remove(p);
					//remove plant from arraylist as well as root becuase bees seemed to be getting stuck on plants that existed in the array but not root
					//bees nolonger seem to get stuck on nothing 
					plantList.remove(p);
				}


			}

		}

	}
	/**---------------------------------------------------------------------------------------**/

	// carnivorous ladybugs eat bees
	public void eatOtherBugs( List<Bug> bugList, Group root) {	
		boolean meet = false;

		for (Bug b:bugList) {

			if (this.getBoundsInParent().intersects(b.getBoundsInParent())||b.getBoundsInParent().intersects(this.getBoundsInParent())) 
			{
				meet = true;
				if (meet){

					this.eat();
					if(b instanceof Bee) {
						root.getChildren().remove(b);
						//needed to remove from arraylist aswell as root because bees in arraylist were eating plants ?
						//plants not disapearing on their own
						bugList.remove(b);
					}
					System.out.println(name+" ID="+id+" "+b.name+" "+b.id+" eaten="+b.eaten);

					break;
				}

			}

		}

	}


	/**----------------------------------------------------------------------------------------**/
	public void moveRandom() {
		double changeChecker = Math.random()*40;//generate a random number between 0 and 30

		if (changeChecker<1) {//bug has a 1 in 30 chance of changing direction

			dx = -1.5+Math.random()*3;//speed is random between -1.5 and 3, this also randomly changes direction of bug
			dy = -1.5+Math.random()*3;	
		}
	}
	/**----------------------------------------------------------------------------------------------**/	
	//if bug eats something it grows in size
	public void eat() {
		//Creating new scale Transition 
		ScaleTransition scaleTransition = new ScaleTransition(); 

		//Setting the duration for the transition 
		scaleTransition.setDuration(Duration.millis(800)); 

		//Setting the node for the transition to the object that is calling the method
		scaleTransition.setNode(this); 

		//amount object should increase by 
		scaleTransition.setByY(0.33); 

		scaleTransition.setByX(0.33); 

		//number of times to repeat animation on each call
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