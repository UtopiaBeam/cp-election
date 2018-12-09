package model.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.Images;
import constants.Sounds;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.MoveableEntity;
import model.effect.Podium;
import model.item.Item;
import model.npc.Boss;
import model.npc.NPC;
import model.npc.Prayut;
import model.Frame;
import model.IUpdatable;
import model.player.Player;
import skill.PodiumSkill;
import ui.GameScene;
import ui.StatusBar;

public class Map extends Frame implements IUpdatable {
	
	public static final int X_PADDING = 150;
	public static final int Y_PADDING = 66;
	
	private Image img;
	private double moveSpeed = 4;
	
	private MediaPlayer bgm;
	
	private List<NPC> listNPC = new ArrayList<NPC>();
	private List<Item> listItem = new ArrayList<Item>();
	private List<Boss> listBoss = new ArrayList<Boss>();
	private List<Podium> listPodium = new ArrayList<Podium>();
	
	private StatusBar statusBar = new StatusBar();

	
	public Map(Image img, AudioClip bgm) {
		super(0, 0, img.getWidth(), img.getHeight());
		this.img = img;
		
		this.bgm = new MediaPlayer(new Media(bgm.getSource()));
		this.bgm.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	public List<NPC> collideNPCs(Frame f) {
		List<NPC> npcs = new ArrayList<NPC>();
		for (NPC n: listNPC) {
			if (f.isCollideWith(n)) {
				npcs.add((NPC) n);
			}
		}
		for (Boss b: listBoss) {
			if (f.isCollideWith(b)) {
				npcs.add((NPC) b);
			}
		}
		return npcs;
	}
	
	public List<Item> collideItems(Frame f) {
		List<Item> collideItems = new ArrayList<Item>();
		for (Item e: listItem) {
			if (e instanceof Item && f.isCollideWith(e)) {
				collideItems.add((Item) e);
			}
		}
		return collideItems;
	}
	
	public void addItem(Item item) {
		listItem.add(item);
	}
	
	public void removeItem(Item item) {
		listItem.remove(item);
	}
	
	public void addPodium(Podium podium) {
		listPodium.add(podium);
	}
	
	public void removePodium(Podium podium) {
		listPodium.remove(podium);
	}
	
	private void moveMap() {
		// Move map by object inside
		Player player = GameManager.getInstance().getPlayer();
		if (player.getPosX() > GameScene.WINDOW_WIDTH + posX - player.getWidth() - X_PADDING) {			
			posX += moveSpeed;
		} else if (player.getPosX() < posX + X_PADDING) {			
			posX -= moveSpeed;
		}
		if (player.getPosY() > GameScene.WINDOW_HEIGHT + posY - player.getHeight() - Y_PADDING) {			
			posY += moveSpeed;
		} else if (player.getPosY() < posY + 333 + Y_PADDING) {			
			posY -= moveSpeed;
		}

		// Map boundary
		if (posX < 0) {			
			posX = 0;
		} else if (posX > width - GameScene.WINDOW_WIDTH) {			
			posX = width - GameScene.WINDOW_WIDTH;
		}
		if (posY < 0) {			
			posY = 0;
		} else if (posY > height - GameScene.WINDOW_HEIGHT) {			
			posY = height - GameScene.WINDOW_HEIGHT;
		}
	}
	
	private void motion(MoveableEntity e) {
		e.move();
		if (e.getPosX() < 0)
			e.setPosX(0);
		else if (e.getPosX() + e.getWidth() > width)
			e.setPosX(width - e.getWidth());
		if (e.getPosY() < 333)
			e.setPosY(333);
		else if (e.getPosY() + e.getHeight() > height)
			e.setPosY(height - e.getHeight());
	}
	
	public void motionPlayer(Player p) {
		motion(p);
		moveMap();
	}
	
	public void motionAll() {
		for (NPC i: listNPC) {
			motion(i);
		}
		for (Podium p: listPodium) {
			p.move();
		}
	}
	
	public void spawnMonsterRandom() {
		double x = 333 + (int) (Math.random() * (height/2));
		double y = 333 + (int) (Math.random() * (width/2));
		NPC monster = new NPC(x, y);
		listNPC.add(monster);
	}
	
	public void spawnBossRandom() {
		double x = 333 + (int) (Math.random() * (height/2));
		double y = 333 + (int) (Math.random() * (width/2));
		Boss boss = new Prayut(x, y);
		listBoss.add(boss);
		System.out.println("Spawned at (" + x + ", " + y + ")");
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(img, -posX, -posY);
		GameManager.getInstance().getPlayer().render(gc);
		for (NPC e: listNPC) {			
			e.render(gc);
			e.renderStatusEffect(gc);
		}
		for (Item i: listItem) {
			i.render(gc);
		}
		for (Boss b: listBoss) {
			b.render(gc);
		}
		for (Podium p: listPodium) {
			p.render(gc);
		}
		statusBar.render(gc);
		
	}

	@Override
	public void update() {
		if (GameManager.getInstance().getTimeCount() == 5.0) {
			spawnBossRandom();
		}
		
		Iterator<NPC> npcIt = listNPC.listIterator();
		while (npcIt.hasNext()) {
			if (npcIt.next().isDead()) {
				Sounds.deadsound.play();
				npcIt.remove();
			}
		}
		for (NPC e: listNPC) {
			e.update();
		}
		
		for (int i = listItem.size()-1; i >= 0; i--) {
			listItem.get(i).addExpireTick();
			if (listItem.get(i).isExpired()) {
				listItem.remove(i);
			}
		}
		
		Iterator<Boss> bossIt = listBoss.listIterator();
		while (bossIt.hasNext()) {
			if (bossIt.next().isDead()) {
				bossIt.remove();
			}
		}
		
		for (Boss b: listBoss) {
			b.update();
		}
		
		Iterator<Podium> podiumIt = listPodium.listIterator();
		while (podiumIt.hasNext()) {
			if (podiumIt.next().isOutOfWindow()) {
				podiumIt.remove();
			}
		}
		for (Podium p: listPodium) {
			p.update();
		}
	}
	
	public void playBgm() {
		this.bgm.play();
	}
	
	// Getters & Setters

	public List<NPC> getListNPC() {
		return listNPC;
	}

	public List<Item> getListItem() {
		return listItem;
	}
	
	public List<Boss> getListBoss() {
		return listBoss;
	}
	
	public List<Podium> getListPodium() {
		return listPodium;
	}

}
