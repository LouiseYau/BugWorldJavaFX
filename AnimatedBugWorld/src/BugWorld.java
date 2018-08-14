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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class BugWorld extends Application{
	private static final int INDEFINITE = 0;
	private int width = 500, height = 400;// width and height of stage
	private double x, y; //x and y values of centre of circles (bugs and plants)
	private double radius;// radiuses of circles (bugs and plants)
	//Group is the root node in the scene graph, a circle object/many circles could be added 
	//to the group either in the brackets or by root.getChildren().add() (however am using an ArrayList instead)
	private Group root = new Group();//top node in scene
	private List<Bug> bugList = new ArrayList <>();//list of bug objects 
	private List<Plant> plantList = new ArrayList <>();//list of bug objects 
	private HBox pane = new HBox();
	private Scene scene = new Scene(root,width,height,Color.LIGHTGREEN);//scene contains root, width, height, color
	private Button playButton = new Button("play");
	private Button pauseButton = new Button("pause");
	private Button stopButton = new Button("stop");
	private KeyFrame frame;
	//primaryStage is passed into the start method
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Bug World");//title of stage is Bug World
		root.getChildren().add(pane);
		pane.setAlignment(Pos.BOTTOM_CENTER);
		pane.setPadding((new Insets(height-30,0,0,190)));
		pane.getChildren().add(playButton);
		pane.getChildren().add(pauseButton);
		pane.getChildren().add(stopButton);
		primaryStage.setScene(scene);//setting primaryStage scene to scene that was defined above
		addPlants(primaryStage);
		addBee(primaryStage);
		addLadybugs(primaryStage);
		animateWorld();
		primaryStage.show();
	}

	public void addBee(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<10;i++) {
			x=50+Math.random()*width-50;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-50;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			radius = 10;
			Bug c = new Bee(primaryStage, x,y,radius,Color.YELLOW,"Bee");
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
			radius = 10+Math.random()*20;//random radius/size of circle
			Plant c = new Plant(primaryStage, x,y,radius,Color.GREEN);
			Image image = new Image("flowers.png");
			c.setFill(new ImagePattern(image));
			plantList.add(c);//add bug to bugList 
			root.getChildren().add(c);
		}
		for(int i =0;i< (int) (10+Math.random()*15);i++) {
			x=5+Math.random()*width - 30;//assigning random number upto the width of the scene to the x variable 
			y=5+Math.random()*height - 30;//assigning random number upto the height of the scene to the y variable
			radius = 5+Math.random()*15;//random radius/size of circle
			Plant c = new Plant(primaryStage, x,y,radius,Color.GREEN);
			Image image = new Image("kale.png");
			c.setFill(new ImagePattern(image));
			plantList.add(c);//add bug to bugList 
			root.getChildren().add(c);
		}
	}

	public void addLadybugs(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<3;i++) {
			x=50+Math.random()*height-20;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-30;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			radius = 5;
			Bug s = new Ladybug(primaryStage, x,y,radius,Color.DODGERBLUE,"Ladybug");
			Image image = new Image("Bug.png");
			s.setFill(new ImagePattern(image));
			bugList.add(s);//add bug to bugList 
			root.getChildren().add(s);//adding each bug object to the Group root
		}
	}

	public void animateWorld() {
		KeyFrame frame = new KeyFrame(Duration.millis(15),new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent t) {

				//each bug calls animate method, passing in scene to method 
				for(Bug b:bugList) {
					b.update(scene, plantList, bugList, root);
					System.out.println(b.getName()+" "+b.getID()+"eaten=" +b.isEaten());
					if(b.isEaten() == true) {
						root.getChildren().remove(b);
						System.out.println(b.getName()+" "+b.getID()+" "+ " is dead");
					}
				}
			}
		});
		Timeline tl = TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build();
		//tl.play();
		playButton(tl);
		pauseButton(tl);
		stopButton(tl);
	
//			TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE)
//			.keyFrames(frame).build().play();



	}


	public void playButton(Timeline tl) {
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				tl.play();
			}
		});
	}

	public void pauseButton(Timeline tl) {
		
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				tl.pause();
			}
		});
	}
	public void stopButton(Timeline tl) {
	
		stopButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				tl.stop();
			}
		});
	}
	private void bugDies(Bug b) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		launch();

	}
}