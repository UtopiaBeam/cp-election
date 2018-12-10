package ui;

import constants.Images;
import controller.GameManager;
import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameScene extends Scene {
	
	public static final double WINDOW_WIDTH = 900;
	public static final double WINDOW_HEIGHT = 600;
	
	private Canvas canvas;
	
	public GameScene() {
		super(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
		StackPane root = (StackPane) getRoot();
		
		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		root.getChildren().add(canvas);
		
		KeyInput.bindScene(this);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			
			if (GameManager.getInstance().isGameRunning()) {
				GameManager.getInstance().getCurrentMap().motionPlayer(GameManager.getInstance().getPlayer());
				GameManager.getInstance().getCurrentMap().motionAll();
				GameManager.getInstance().update();
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				GameManager.getInstance().render(gc);
				if (GameManager.getInstance().getPlayer().isDead()) {
					gc.drawImage(Images.deadscreen, 0, 0);
					gc.drawImage(Images.quit, 300, 450);
					addCanvasEventHandler();
				}
			}
			
		});
		
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
	
	private boolean isOnQuitButton(MouseEvent event) {
		return event.getX() >= 300 && event.getX() < 600 && event.getY() >= 450 && event.getY() < 550;
	}
	
	private void addCanvasEventHandler() {
		canvas.setOnMouseClicked(e -> {
			if (isOnQuitButton(e)) {
				GameManager.getInstance().stopGame();
			}
		});
	}

}
