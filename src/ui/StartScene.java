package ui;

import constants.Images;
import controller.GameManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import application.Main;

public class StartScene extends Scene {
	private Pane root;
	private Canvas canvas;
	
	public StartScene() {
		super(new Pane(), 420, 540);
		root = (Pane) getRoot();
		
		canvas = new Canvas(420, 540);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		gc.drawImage(Images.playbutton, 165, 400);
		
		addCanvasEventHandler();
		
		
		root.getChildren().addAll(canvas);
	}
	
	private boolean isOnPlayButton(MouseEvent event) {
		return event.getX() >= 165 && event.getX() < 265 && event.getY() >= 400 && event.getY() < 430;
	}
	
	private void addCanvasEventHandler() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseMoved(e -> {
			if (isOnPlayButton(e)) {
				gc.drawImage(Images.startscreen, 0, 0);
				gc.drawImage(Images.playbutton_highlight, 165, 400);
			}
			else {
				gc.drawImage(Images.startscreen, 0, 0);
				gc.drawImage(Images.playbutton, 165, 400);
			}
		});
		canvas.setOnMouseClicked(e -> {
			if (isOnPlayButton(e)) {
				GameManager.getInstance().startGame();
				Main.getStage().setScene(Main.getGameScene());
				Main.setCenter();
			}
		});
	}
}
