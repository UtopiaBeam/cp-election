package controller;

import java.util.ArrayList;
import java.util.List;

import constants.Images;
import constants.Sounds;
import javafx.scene.canvas.GraphicsContext;
import model.map.Map;
import model.player.Player;

public class GameManager {
	
private static GameManager instance = new GameManager();
	
	private boolean isGameRunning = false;
	private boolean isPausing = false;
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private MonsterGen monsterGen;
	private MonsterAi monsterAi;
	private Map currentMap;
	
	public GameManager() {
		generateMap();
		monsterGen = new MonsterGen();
		monsterAi = new MonsterAi();
		player = new Player(100, 423);
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
		maps.add(new Map(Images.stage1, Sounds.bgm));
		currentMap = maps.get(0);
	}
	
	public void startGame() {
		isGameRunning = true;
		currentMap.playBgm();
	}
	
	public void stopGame() {
		terminate();
	}
	
	public void terminate() {
		monsterGen.interrupt();
		monsterAi.interrupt();
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
