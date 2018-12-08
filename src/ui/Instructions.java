package ui;

import application.Main;
import constants.Images;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

public class Instructions extends Scene{
	private Pane root;
	private Canvas canvas;
	private Image[] instructions = { Images.instruction1, Images.instruction2 };
	private int count = 0;
	
	public Instructions() {
		super(new Pane(), 900, 600);
		root = (Pane) getRoot();
		
		canvas = new Canvas(900, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(instructions[count], 0, 0);
		gc.drawImage(Images.button, 740, 490);
		
		addCanvasEventHandler();
		
		root.getChildren().addAll(canvas);
		
	}
	
	private boolean isOnNextButton(MouseEvent event) {
		return event.getX() >= 740 && event.getX() < 840 && event.getY() >= 490 && event.getY() < 556;
	}
	
	private void addCanvasEventHandler() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseMoved(e -> {
			if (isOnNextButton(e)) {
				gc.drawImage(instructions[count], 0, 0);
				gc.drawImage(Images.button_highlight, 740, 490);
			}
			else {
				gc.drawImage(instructions[count], 0, 0);
				gc.drawImage(Images.button, 740, 490);
			}
		});
		canvas.setOnMouseClicked(e -> {
			if (isOnNextButton(e)) {
				if (count < instructions.length-1) {
					count++;
				} else {
					count = 0;
					gc.drawImage(instructions[count], 0, 0);
					gc.drawImage(Images.button, 740, 490);
					Main.getStage().setScene(Main.getStartScene());
					Main.setCenter();
				}
			}
		});
	}
}
