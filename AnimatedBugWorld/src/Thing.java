import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Thing extends Circle{
	
	private double dx;
	private double dy;

	public Thing(Stage primaryStage, double x, double y, double radius, Color color) {
		super(x, y, radius, color);

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
