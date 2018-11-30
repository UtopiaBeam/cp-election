package model.item;

import javafx.scene.image.Image;

public class HealItem extends Item {
	
	private int healHp;

	public HealItem(double posX, double posY, Image image, int healHp) {
		super(posX, posY, image.getWidth(), image.getHeight(), "Heal Potion", 1, image);
		this.healHp = healHp;
	}
	
	public HealItem(Image image, int healHp) {
		super("Heal Potion", 1, image);
		this.healHp = healHp;
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void update() {
		// DO nothing
	}
	
	// Getters & Setters
	
	public int getHealHp() {
		return healHp;
	}

}
