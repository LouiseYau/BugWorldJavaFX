import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Rock extends Thing {
	private double dx = -1.5f+Math.random()*3;
	private double dy = -1.5f+Math.random()*3;
	private Stage primaryStage;

	public Rock(Stage primaryStage, double x, double y, double radius, Color color, String name) {
		super(primaryStage, x, y, radius, color);

	}
	
}
