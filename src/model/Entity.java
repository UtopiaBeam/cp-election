package model;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity extends Frame {

	protected String name;

	private Image image;

	public Entity(double posX, double posY, double width, double height, String name, Image image) {
		super(posX, posY, width, height);
		this.name = name;
		this.image = image;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().posX, posY-GameManager.getInstance().getCurrentMap().posY);
	}
	
	// Getters & Setters

	public String getName() {
		return name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
