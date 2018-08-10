import javafx.animation.KeyFrame;
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

public class Bug extends Circle{
//	double x = 100;
//	double y = 100;
//	double rad = 30;
	//Circle circle = new Circle(x,y,rad,Color.CORNFLOWERBLUE);
private double dx = -1.5f+Math.random()*3;
private double dy = -1.5f+Math.random()*3;

Stage primaryStage;

public Bug(Stage primaryStage,double x, double y, double rad, Color color) {
	super(x,y,rad,color);
	
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
	
public void animate(Scene scene) {
	KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {
		public void handle(ActionEvent t) {
			if(getCenterX()+getTranslateX()< getRadius()||
					getCenterX() + getTranslateX()+getRadius()>scene.getWidth()) {
				dx = -dx;
				}
			if(getCenterY()+getTranslateY()< getRadius()||
					getCenterY() + getTranslateY()+getRadius()>scene.getHeight()) {
				dy = -dy;
				}
			setTranslateX(getTranslateX()+dx);//look up translate
			setTranslateY(getTranslateY()+dy);
		}
	});
	TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE)
	.keyFrames(frame).build().play();
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