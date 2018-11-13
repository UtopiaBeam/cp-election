package ui;

import constants.Images;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StartScene extends Scene{
	private Pane root;
	private Button backBtn;
	private Button startBtn;
	private Canvas canvas;
	
	public StartScene() {
		super(new Pane(), 420, 540);
		root = (Pane) getRoot();
		
		canvas = new Canvas(420, 540);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		
		startBtn = new Button("Start!");
		startBtn.setPrefHeight(30);
		startBtn.setPrefWidth(100);
		startBtn.relocate(165, 400);
		
		backBtn = new Button("Back");
		backBtn.setPrefHeight(75);
		backBtn.setPrefWidth(200);
		backBtn.relocate(600, 425);
		
		root.getChildren().addAll(canvas, backBtn, startBtn);
	}
}
