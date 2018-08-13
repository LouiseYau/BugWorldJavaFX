import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BugWorld extends Application{
	int width = 450, height = 350;// width and height of stage
	double x, y;
	double rad;

	//Group is the root node in the scene graph, a circle object/many circles could be added 
	//to the group either in the brackets or by root.getChildren().add() (however am using an ArrayList instead)
	Group root = new Group();//top node in scene
	List<Bug> bugList = new ArrayList <>();//list of bug objects 
	List<Plant> plantList = new ArrayList <>();//list of bug objects 
	Scene scene = new Scene(root,width,height,Color.LIGHTGREEN);//scene contains root, width, height, color

	//primaryStage is passed into the start method
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Bug World");//title of stage is Bug World
		primaryStage.setScene(scene);//setting primaryStage scene to scene that was defined above
		addPlants(primaryStage);
		addBee(primaryStage);
		addSnails(primaryStage);
		updateWorld();
		primaryStage.show();
	}

	public void addBee(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<10;i++) {
			x=50+Math.random()*350;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*250;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			rad = 8;
			Bug c = new Bee(primaryStage, x,y,rad,Color.YELLOW,"Bee");
			bugList.add(c);//add bug to bugList 
			root.getChildren().add(c);//adding each bug object to the Group root
			//System.out.println(x+" "+y+" "+rad);// printing x,y,radius to test each new object has random values
		}
	}

	public void addPlants(Stage primaryStage) {
		for(int i =0;i< (int) (10+Math.random()*15);i++) {
			//	for(int i =0;i< 2;i++) {
			x=5+Math.random()*350;//assigning random number upto the width of the scene to the x variable 
			y=5+Math.random()*300;//assigning random number upto the height of the scene to the y variable
			rad = 5+Math.random()*15;//random radius/size of circle
			Plant c = new Plant(primaryStage, x,y,rad,Color.GREEN);
			plantList.add(c);//add bug to bugList 
			root.getChildren().add(c);
		}

	}

	public void addSnails(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<5;i++) {
			x=50+Math.random()*350;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*250;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			rad = 5;
			Bug s = new Snail(primaryStage, x,y,rad,Color.DODGERBLUE,"Snail");
			bugList.add(s);//add bug to bugList 
			root.getChildren().add(s);//adding each bug object to the Group root
			//System.out.println(x+" "+y+" "+rad);// printing x,y,radius to test each new object has random values
		}
	}

	public void updateWorld() {
		KeyFrame frame = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent t) {

				//each bug calls animate method, passing in scene to method 
				for(Bug b:bugList) {
					b.animate(scene, plantList, bugList);
					System.out.println(b.getName()+" "+b.getID()+"eaten=" +b.isEaten());
					if(b.isEaten() == true) {
						root.getChildren().remove(b);
						//bugList.remove(b);
						System.out.println(b.getName()+" "+b.getID()+" "+ " is dead");
					}

				}
			}
		});
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE)
		.keyFrames(frame).build().play();

	}


	private void bugDies(Bug b) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		launch();

	}
}