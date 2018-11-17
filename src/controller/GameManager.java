package controller;

import java.util.ArrayList;
import java.util.List;

import constants.Images;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import application.Main;
import model.map.Map;
import model.player.Player;
import sharedObject.SharedEntity;
import ui.StartScene;



public class GameManager {
	
private static GameManager instance = new GameManager();
	
	private boolean isGameRunning = false;
	private boolean isPausing = false;
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private Map currentMap;
	
	public GameManager() {
		generateMap();
		//TODO add player
		player = new Player("Netikun", Images.playerR, 0, 0, 100, 100, 100, 1);
	}
	
	public void render(GraphicsContext gc) {
		if (!isPausing) {
			currentMap.render(gc);
		}
	}
	
	public void update() {
		player.update();
		currentMap.update();
	}
	
	private void generateMap() {
		//TODO add player
		maps.add(new Map(Images.stage1));
		currentMap = maps.get(0);
	}
	
	public void startGame() {
		isGameRunning = true;
	}
	
	public void stopGame() {
		
	}
	
	
	
	// Getters & Setters
	
	public List<Map> getMaps() {
		return maps;
	}

	public Player getPlayer() {
		return player;
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public void setCurrentMap(Map m) {
		currentMap = m;
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	public boolean isGameRunning() {
		return isGameRunning;
	}
	
	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}
	
	public boolean isPausing() {
		return isPausing;
	}
	
	public void setPausing(boolean isPausing) {
		this.isPausing = isPausing;
	}

}
