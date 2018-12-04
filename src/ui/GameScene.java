package ui;

import controller.GameManager;
import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameScene extends Scene {
	
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 600;
	
	public GameScene() {
		super(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
		StackPane root = (StackPane)getRoot();
		
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		root.getChildren().add(canvas);
		
		KeyInput.bindScene(this);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			if (GameManager.getInstance().isGameRunning()) {
                
				if (!GameManager.getInstance().isPausing()) {
					GameManager.getInstance().getCurrentMap().motionPlayer(GameManager.getInstance().getPlayer());
					GameManager.getInstance().getCurrentMap().motionAll();
					GameManager.getInstance().update();
				}
				
				GameManager.getInstance().render(canvas.getGraphicsContext2D());
			}
		});
		
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
}
