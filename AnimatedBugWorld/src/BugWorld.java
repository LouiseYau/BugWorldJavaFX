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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
	private int width = 550, height = 450;// width and height of stage
	private double x, y; //x and y values of centre of circles (bugs and plants)
	private double radius;// radiuses of circles (bugs and plants)
	//Group is the root node in the scene graph, a circle object/many circles could be added 
	//to the group either in the brackets or by root.getChildren().add() (however am using an ArrayList instead)
	private Group root = new Group();//top node in scene
	private List<Bug> bugList = new ArrayList <>();//list of bug objects 
	private List<Plant> plantList = new ArrayList <>();//list of plant objects 
	private List<Rock> rockList = new ArrayList <>();//list of rock objects 
	private HBox pane = new HBox();//a horizontal box at the bottom of pane
	private Scene scene = new Scene(root,width,height,Color.LIGHTGREEN);//scene contains root, width, height, color
	private Button playButton = new Button("play");
	private Button pauseButton = new Button("pause");
	private Button stopButton = new Button("stop");
	private Button addBeeButton = new Button("add bee");
	private Button addPlantButton = new Button("add plant");


	//primaryStage is passed into the start method
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Bug World");//title of stage is Bug World
		root.getChildren().add(pane);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding((new Insets(height-30,width,0,130)));
		//add buttons to pane (Hbox)
		pane.getChildren().add(playButton);
		pane.getChildren().add(pauseButton);
		pane.getChildren().add(stopButton);
		pane.getChildren().add(addBeeButton);
		pane.getChildren().add(addPlantButton);
		primaryStage.setScene(scene);//setting primaryStage scene to scene that was defined above
		addRock(primaryStage);
		addPlants(primaryStage);
		AddBeeButton(primaryStage);
		AddPlantButton(primaryStage);
		addBee(primaryStage);
		addLadybugs(primaryStage);
		animateWorld(primaryStage);
		primaryStage.show();
	}

	/**-------------------------------------------------------------------------------------------**/
	public void addBee(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<25;i++) {//for loop to create 10 bees
			x=50+Math.random()*width-100;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-100;//assigning random number upto the height of the scene to the y variable
			radius = 10;
			Bug bee = new Bee(primaryStage, x,y,radius,Color.YELLOW,"Bee");
			Image image = new Image("Bumblebee.png");
			bee.setFill(new ImagePattern(image));
			bugList.add(bee);//add bug to bugList 
			root.getChildren().add(bee);//adding each bug object to the Group root
		}
	}

	/**-------------------------------------------------------------------------------------------**/
	public void addPlants(Stage primaryStage) {
		for(int i =0;i< (int) (7+Math.random()*12);i++) {//for loop to create between 10 and 15 plants
			x=50+Math.random()*450;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*300;//assigning random number upto the height of the scene to the y variable
			radius = 15+Math.random()*10;//random radius/size of circle
			Plant flower = new Plant(primaryStage, x,y,radius,Color.GREEN);
			Image image = new Image("flowers2.png");
			flower.setFill(new ImagePattern(image));
			plantList.add(flower);//add bug to bugList 
			root.getChildren().add(flower);//add plant to root (group)
		}
		for(int i =0;i< (int) (15+Math.random()*20);i++) {
			x=10+Math.random()*450;//assigning random number upto the width of the scene to the x variable 
			y=10+Math.random()*350;//assigning random number upto the height of the scene to the y variable
			radius = 10+Math.random()*10;//random radius/size of circle
			Plant plant = new Plant(primaryStage, x,y,radius,Color.GREEN);
			Image image = new Image("kale.png");//new image using png file 
			plant.setFill(new ImagePattern(image));//fill circle/plant object with image
			plantList.add(plant);//add plant to plantList 
			root.getChildren().add(plant);//add plant to root (group)
		}
	}

	/**-------------------------------------------------------------------------------------------**/
	public void addLadybugs(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<3;i++) {
			x=50+Math.random()*height-20;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-50;//assigning random number upto the height of the scene to the y variable
			//rad = 5+Math.random()*10;//random radius/size of circle
			radius = 5;
			Bug Ladybug = new Ladybug(primaryStage, x,y,radius,Color.DODGERBLUE,"Ladybug");
			Image image = new Image("Ladybug2.png");
			Ladybug.setFill(new ImagePattern(image));
			bugList.add(Ladybug);//add bug to bugList 
			root.getChildren().add(Ladybug);//adding each bug object to the Group root
		}
	}

	/**-------------------------------------------------------------------------------------------**/
	public void addRock(Stage primaryStage) {
		/**create new bug in for loop, pass in primaryStage starting positions x and y, radius, color can be a random colour
		by randomizing the rgb values Color.color(Math.random(), Math.random(), Math.random())**/
		for(int i = 0; i<5;i++) {//for loop to create 10 bees
			x=50+Math.random()*width-50;//assigning random number upto the width of the scene to the x variable 
			y=50+Math.random()*height-80;//assigning random number upto the height of the scene to the y variable
			radius = 20;
			Rock rock = new Rock(primaryStage, x,y,radius,Color.GRAY,"Rock");
			Image image = new Image("boulder.png");
			rock.setFill(new ImagePattern(image));
			rockList.add(rock);//add bug to bugList 
			root.getChildren().add(rock);//adding each bug object to the Group root
		}
	}

	/**-------------------------------------------------------------------------------------------**/
	//animateWorld method loops through bug list and calls update method 
	public void animateWorld(Stage primaryStage) {
		// new keyFrame where rate is 15milliseconds
		KeyFrame frame = new KeyFrame(Duration.millis(15),new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent t) {
				//each bug calls animate method, passing in scene to method 
				for(Bug bug:bugList) {
					bug.update(scene, plantList, bugList, root, rockList);
					//					System.out.println(bug.getName()+" "+bug.getID()+"eaten=" +bug.isEaten());
					//					if(bug.isEaten() == true) {// if bug has been eaten in update method bug is removed from root 
					//						root.getChildren().remove(bug);
					//						System.out.println(bug.getName()+" "+bug.getID()+" "+ " is dead");
					//					}
				}
				for(Plant p:plantList) {
					p.grow();
				}
			}
		});
		//make timelinebuilder a variable so can call methods play, pause, stop
		Timeline tl = TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build();
		playButton(tl);
		pauseButton(tl);
		stopButton(tl);

	}

	/**-------------------------------------------------------------------------------------------**/
	public void playButton(Timeline tl) {//create play button with tl variable
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				tl.play();
			}
		});
	}
	/**-------------------------------------------------------------------------------------------**/
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
	/**-------------------------------------------------------------------------------------------**/
	public void AddBeeButton(Stage primaryStage) {//add a new bee on click of add bee button

		addBeeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				x=50+Math.random()*width-50;//assigning random number upto the width of the scene to the x variable 
				y=50+Math.random()*height-50;//assigning random number upto the height of the scene to the y variable
				//rad = 5+Math.random()*10;//random radius/size of circle
				radius = 10;
				Bug c = new Bee(primaryStage, x,y,radius,Color.YELLOW,"Bee");
				Image image = new Image("Bumblebee.png");
				c.setFill(new ImagePattern(image));
				bugList.add(c);//add bug to bugList 
				root.getChildren().add(c);//adding each bug object to the Group root
			}
		});
	}
	/**-------------------------------------------------------------------------------------------**/
	public void AddPlantButton(Stage primaryStage) {//add a new bee on click of add plant button

		addPlantButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				double randomPlant= Math.random()*2;//random plant generator
				x=5+Math.random()*width - 50;//assigning random number upto the width of the scene to the x variable 
				y=5+Math.random()*height - 50;//assigning random number upto the height of the scene to the y variable
				radius = 20;//random radius/size of circle
				Plant p = new Plant(primaryStage, x,y,radius,Color.GREEN);
				if(randomPlant<1) {
					Image image = new Image("flowers2.png");
					p.setFill(new ImagePattern(image));
				}
				else {
					Image image = new Image("kale.png");
					p.setFill(new ImagePattern(image));
				}
				plantList.add(p);//add bug to bugList 
				root.getChildren().add(p);
			}
		});
	}


	public static void main(String[] args) {
		launch();

	}
}