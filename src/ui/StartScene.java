package ui;

import constants.Images;
import constants.Sounds;
import controller.GameManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import application.Main;

public class StartScene extends Scene {
	
	public static final double WINDOW_WIDTH = 420;
	public static final double WINDOW_HEIGHT = 540;
	
	private Pane root;
	private Canvas canvas;
	private MediaPlayer bgm;
	
	public StartScene() {
		super(new Pane(), WINDOW_WIDTH, WINDOW_HEIGHT);
		root = (Pane) getRoot();
		
		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		gc.drawImage(Images.playbutton, 165, 400);
		gc.drawImage(Images.info, 370, 10);
		
		addCanvasEventHandler();
		
		bgm = new MediaPlayer(new Media(Sounds.startbgm.getSource()));
		bgm.setCycleCount(MediaPlayer.INDEFINITE);
		bgm.play();
		
		
		root.getChildren().addAll(canvas);
	}
	
	private boolean isOnPlayButton(MouseEvent event) {
		return event.getX() >= 165 && event.getX() < 265 && event.getY() >= 400 && event.getY() < 430;
	}
	
	private boolean isOnInstructionsButton(MouseEvent event) {
		return event.getX() >= 370 && event.getX() < 410 && event.getY() >= 10 && event.getY() < 50;
	}
	
	private void addCanvasEventHandler() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseMoved(e -> {
			if (isOnPlayButton(e)) {
				gc.drawImage(Images.startscreen, 0, 0);
				gc.drawImage(Images.playbutton_highlight, 165, 400);
				gc.drawImage(Images.info, 370, 10);
			}
			else {
				gc.drawImage(Images.startscreen, 0, 0);
				gc.drawImage(Images.playbutton, 165, 400);
				gc.drawImage(Images.info, 370, 10);
			}
			
		});
		canvas.setOnMouseClicked(e -> {
			if (isOnPlayButton(e)) {
				bgm.stop();
				GameManager.getInstance().startGame();
				Main.getStage().setScene(Main.getGameScene());
				Main.setCenter();
			}
			
			if (isOnInstructionsButton(e)) {
				Main.getStage().setScene(Main.getInstructionsScene());
				Main.setCenter();
			}
		});
		
	}
	
}
