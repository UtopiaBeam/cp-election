package model.item;

import javafx.scene.image.Image;

public class AttackItem extends Item {

	private int atk;
	
	public AttackItem(Image image, int maxCount, int atk) {
		super("Attack Item", maxCount, image);
		this.atk = atk;
	}

	@Override
	public boolean activate() {
		return true;
	}

	// Getters & Setters
	
	public int getAtk() {
		return atk;
	}
	
}
