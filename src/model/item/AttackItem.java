package model.item;

import javafx.scene.image.Image;

public class AttackItem extends Item {

	private int atk;
	
	public AttackItem(double posX, double posY, Image image, int maxCount, int atk) {
		super(posX, posY, image.getWidth(), image.getHeight(), "Attack Item", maxCount, image);
		this.atk = atk;
	}
	
	public AttackItem(Image image, int maxCount, int atk) {
		super("Attack Item", maxCount, image);
		this.atk = atk;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	// Getters & Setters
	
	public int getAtk() {
		return atk;
	}
	
}
