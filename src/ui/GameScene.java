package ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GameScene extends Scene{
	public GameScene() {
		super(new StackPane(), 1000, 600);
		StackPane root = (StackPane)getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
	}
}
