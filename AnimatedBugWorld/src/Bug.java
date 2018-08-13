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


/**to do: create method where bugs become hungry and fade over time if they do not eat
if bugs are hungry then they eat if they are not hungry they stop eating move away from plant


method for bugs reproduce if they bugs of diff colour if they get to a certain size and meet another bug
of the same size

if plant shrinks to certain size it dies (removed from array and new plant generated elsewhere

get plant grow method to work**/

public class Bug extends Circle {
	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	protected int id = 0;
	protected static int idCounter;
	protected String name;
	

	Stage primaryStage;
	boolean eaten = false;
	boolean eating = false;
	public Bug(Stage primaryStage,double x, double y, double radius, Color color, String name) {
		super(x,y,radius,color);
		this.id = idCounter++;
		this.name = name;
		}
	
	public Bug(Stage primaryStage) {
		super(2);
		this.primaryStage = primaryStage;
		double randDX = Math.random()*4;
		double randDY = Math.random()*4;
		this.setCenterX((float)(Math.random()*400));
		this.setCenterY((float)(Math.random()*300));
		if(randDX >1) {
			this.dx=-dx;}
		if (randDY>1) {
			this.dy=-dy;}
	}
	/**----------------------------------------------------------------------------------------**/
	public void animate(Scene scene,List<Plant> plantList, List<Bug> bugList) {

				eatPlants(plantList, bugList);
				eatOtherBugs(bugList);
				// bugs change direction randomly after eating plant
				if(dx==0) {
					dx = -1.5f+Math.random()*3;
					dy = -1.5f+Math.random()*3;	

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
	
	public void eatPlants(List<Plant> plantList, List<Bug> bugList) {
		boolean found = false;

		for (Plant p : plantList) {

			if (p.getBoundsInParent().intersects(getBoundsInParent())) {
				found = true;
			}

			if (found&& p.getScaleX()>0 &&p.getScaleY()>0){
				if(this instanceof Bee) {
					p.shrink();
					this.eat();
					dx=0;
					dy=0;
					if(p.getScaleX()<0.125) {
						p.setFill(Color.LIGHTGREEN);
					}
				}
				break;
			}
		}
	}
	/**---------------------------------------------------------------------------------------**/	
	public void eatOtherBugs( List<Bug> bugList) {	// carnivorous snails eat bees
		boolean meet = false;
		
		for (Bug b:bugList) {

			if (this.getBoundsInParent().intersects(b.getBoundsInParent())||b.getBoundsInParent().intersects(this.getBoundsInParent())) {
				meet = true;
			}
			
			if (meet){
				if(this instanceof Snail) {

					this.eat();
					if(b instanceof Bee) {
									
					b.setEaten(true);
						}
					System.out.println(name+" ID="+id+" "+b.name+" "+b.id+" eaten="+b.eaten);
					
				}
				break;
			}
			}
		}
	
		
	

	/**------------------------------------------------------------------------------**/	
	//bee eats plant and grows in size
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