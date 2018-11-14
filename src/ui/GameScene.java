package ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GameScene extends Scene{
	
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 600;
	
	public GameScene() {
		super(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
		StackPane root = (StackPane)getRoot();
		
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		root.getChildren().add(canvas);
	}
}
