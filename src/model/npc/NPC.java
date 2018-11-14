package model.npc;

import javafx.scene.image.Image;
import model.Character;
import model.item.Item;

public class NPC extends Character {

	private Item dropItem;
	private double dropChance;
	
	public NPC(String name, Image image, double posX, double posY, int maxHp, int atk, int def, Item dropItem,
			double dropChance) {
		super(name, image, posX, posY, maxHp, atk, def);
		this.dropItem = dropItem;
		this.dropChance = dropChance;
	}

	public boolean isDropItem() {
		return dropChance < Math.random();
	}
	
	@Override
	public void attack() {
		if (!canAttack()) {
			return;
		}
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}

	// Getters & Setters
	
	public Item getDropItem() {
		return dropItem;
	}

	public double getDropChance() {
		return dropChance;
	}
	

}
