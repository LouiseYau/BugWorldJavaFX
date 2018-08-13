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
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BugWorld extends Application{
	int width = 500, height = 400;// width and height of stage
	double x, y;
	double rad;

	//Group is the root node in the scene graph, a circle object/many circles could be added 
	//to the group either in the brackets or by root.getChildren().add() (however am using an ArrayList instead)
	Group root = new Group();//top node in scene
	List<Bug> bugList = new ArrayList <>();//list of bug objects 
	List<Plant> plantList = new ArrayList <>();//list of bug objects 
	Scene scene = new Scene(root,width,height,Color.LIGHTGREEN);//scene contains root, width, height, color
//	 private static final String beeURL = "bee.png";
//	 Image bee = new Image(beeURL);

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
			x=50+Math.random()*width-50;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-50;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			rad = 10;
			Bug c = new Bee(primaryStage, x,y,rad,Color.YELLOW,"Bee");
//			Image image = new Image(getClass().getResourceAsStream("ladybug.png"));
			Image image = new Image("Bumblebee.png");
			c.setFill(new ImagePattern(image));
			bugList.add(c);//add bug to bugList 
			root.getChildren().add(c);//adding each bug object to the Group root
			//System.out.println(x+" "+y+" "+rad);// printing x,y,radius to test each new object has random values
		}
	}

	public void addPlants(Stage primaryStage) {
		for(int i =0;i< (int) (10+Math.random()*15);i++) {
			x=5+Math.random()*width - 50;//assigning random number upto the width of the scene to the x variable 
			y=5+Math.random()*height - 50;//assigning random number upto the height of the scene to the y variable
			rad = 10+Math.random()*20;//random radius/size of circle
			Plant c = new Plant(primaryStage, x,y,rad,Color.GREEN);
			Image image = new Image("flowers.png");
			c.setFill(new ImagePattern(image));
			plantList.add(c);//add bug to bugList 
			root.getChildren().add(c);
		}
		for(int i =0;i< (int) (10+Math.random()*15);i++) {
			x=5+Math.random()*width - 30;//assigning random number upto the width of the scene to the x variable 
			y=5+Math.random()*height - 30;//assigning random number upto the height of the scene to the y variable
			rad = 5+Math.random()*15;//random radius/size of circle
			Plant c = new Plant(primaryStage, x,y,rad,Color.GREEN);
			Image image = new Image("kale.png");
			c.setFill(new ImagePattern(image));
			plantList.add(c);//add bug to bugList 
			root.getChildren().add(c);
		}
	}

	public void addSnails(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<3;i++) {
			x=50+Math.random()*height-20;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-20;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			rad = 5;
			Bug s = new Snail(primaryStage, x,y,rad,Color.DODGERBLUE,"Snail");
			Image image = new Image("Bug.png");
			s.setFill(new ImagePattern(image));
			bugList.add(s);//add bug to bugList 
			root.getChildren().add(s);//adding each bug object to the Group root
			//System.out.println(x+" "+y+" "+rad);// printing x,y,radius to test each new object has random values
		}
	}

	public void updateWorld() {
		KeyFrame frame = new KeyFrame(Duration.millis(15), new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent t) {

				//each bug calls animate method, passing in scene to method 
				for(Bug b:bugList) {
					b.animate(scene, plantList, bugList, root);
					System.out.println(b.getName()+" "+b.getID()+"eaten=" +b.isEaten());
					if(b.isEaten() == true) {
						root.getChildren().remove(b);
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