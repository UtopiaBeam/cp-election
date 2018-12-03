package model.map;

import java.util.ArrayList;
import java.util.List;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import model.Entity;
import model.MoveableEntity;
import model.item.Item;
import model.npc.NPC;
import model.Frame;
import model.IUpdatable;
import model.player.Player;
import sharedObject.SharedEntity;
import ui.GameScene;

public class Map extends Frame implements IUpdatable {
	
	public static final int X_PADDING = 100;
	public static final int Y_PADDING = 50;
	
	private Image img;
	private double moveSpeed = 5;
	
	private MediaPlayer bgm;
	
	public Map(Image img) {
		super(0, 0, img.getWidth(), img.getHeight());
		this.img = img;
	}
	
	public void motion(MoveableEntity e) {
		moveMoveableEntity(e);
		moveMap();
	}
	
	public void motionAll() {
		for (Entity e: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (e instanceof MoveableEntity) {				
				motion((MoveableEntity) e);
			}
		}
	}
	
	public List<NPC> collideCharacter(Frame f) {
		List<NPC> npcs = new ArrayList<NPC>();
		for (Entity e: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (e instanceof NPC && f.isCollideWith(e)) {
				npcs.add((NPC) e);
			}
		}
		return npcs;
	}
	
	public Item collideItem(Frame f) {
		for (Entity e: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (e instanceof Item && f.isCollideWith(e)) {
				return (Item) e;
			}
		}
		return null;
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
		} else if (player.getPosY() < posY + Y_PADDING) {			
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
	
	private void moveMoveableEntity(MoveableEntity e) {
		e.move();
		if (e.getPosX() < 0)
			e.setPosX(0);
		else if (e.getPosX() + e.getWidth() > width)
			e.setPosX(width - e.getWidth());
		if (e.getPosY() < 0)
			e.setPosY(9);
		else if (e.getPosY() + e.getHeight() > height)
			e.setPosY(height - e.getHeight());
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(img, -posX, -posY);
		GameManager.getInstance().getPlayer().render(gc);
		for (Entity e: SharedEntity.getInstance().getEntitiesOfMap(this)) {			
			e.render(gc);
		}
	}

	public void update() {
		for (Entity e: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (e instanceof IUpdatable) {				
				((IUpdatable) e).update();
			}
		}
	}

}
