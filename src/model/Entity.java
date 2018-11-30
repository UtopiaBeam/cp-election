package model;

import javafx.scene.image.Image;

public class Entity extends Frame {

	protected String name;

	private Image image;

	public Entity(double posX, double posY, double width, double height, String name, Image image) {
		super(posX, posY, width, height);
		this.name = name;
		this.image = image;
	}

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
