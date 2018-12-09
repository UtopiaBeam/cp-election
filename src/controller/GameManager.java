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
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private MonsterGen monsterGen;
	private MonsterAi monsterAi;
	private Map currentMap;
	private int timeCount = 0;
	private boolean isPlayingDeadBgm = false;
	
	public GameManager() {
		generateMap();
		monsterGen = new MonsterGen();
		monsterAi = new MonsterAi();
		player = new Player(100, 423);
	}
	
	public void render(GraphicsContext gc) {
		currentMap.render(gc);
		if (player.isDead()) {
			gc.drawImage(Images.deathScreen, 0, 0);
		}
	}
	
	public void update() {
		player.update();
		currentMap.update();
		if (!player.isDead()) {
			timeCount++;
		} 
		if (player.isDead() && !isPlayingDeadBgm){
			isPlayingDeadBgm = true;
			currentMap.stopBgm();
			Sounds.deadscenebgm.play();
		}
	}
	
	private void generateMap() {
		maps.add(new Map(Images.stage, Sounds.bgm));
		currentMap = maps.get(0);
	}
	
	public void startGame() {
		isGameRunning = true;
		currentMap.playBgm();
	}
	
	public void stopGame() {
		terminate();
		System.exit(0);
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
	
	public double getTimeCount() {
		return timeCount / 60.;
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

}
