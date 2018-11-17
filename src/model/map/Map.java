package model.map;

import java.util.Iterator;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Moveable;
import model.Frame;
import model.player.Player;
import sharedObject.SharedEntity;

public class Map extends Frame{
	
	private Image img;
	private double movementSpeed = 3.9;
	
	private MediaPlayer bgm;
	
	public Map(Image img) {
		super(0, 0, img.getWidth(), img.getHeight());
		this.img = img;
	}
	
	public void motion(Moveable e) {
		moveMoveable(e);
		moveMap();
	}
	
	public void motionAll() {
		for (Moveable i : SharedEntity.getInstance().getEntitiesOfMap(this)) {
			motion(i);
		}
	}
	
	private void moveMap() {
		// Move map by object inside
		Player player = GameManager.getInstance().getPlayer();
		if (player.getPosX() > 900 + posX - player.getWidth() - 200)
			posX += movementSpeed;
		else if (player.getPosX() < posX + 200)
			posX -= movementSpeed;
		if (player.getPosY() > 600 + posY - player.getHeight() - 150)
			posY += movementSpeed;
		else if (player.getPosY() < posY + 150)
			posY -= movementSpeed;

		// Map boundary
		if (posX < 0)
			posX = 0;
		else if (posX > width - 900)
			posX = width - 900;
		if (posY < 0)
			posY = 0;
		else if (posY > height - 600)
			posY = height - 600;
	}
	
	private void moveMoveable(Moveable e) {
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
		for (Moveable i : SharedEntity.getInstance().getEntitiesOfMap(this))
			i.render(gc);
	}

	public void update() {
		for (Moveable i: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			i.update();
		}
	}
	
}
