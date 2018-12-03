package model.npc;

import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.image.Image;
import model.Character;
import model.item.Item;
import model.player.Player;

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
	public void attack() throws CannotAttackException {
		if (!canAttack()) {
			throw new CannotAttackException();
		}
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}
	
	
	@Override
	public void update() {
		Player player = GameManager.getInstance().getPlayer();
		if (posX < player.getPosX()) {
			setSpeedX(2);
		} else if (posX > player.getPosX()) {
			setSpeedX(-2);
		} else {
			setSpeedX(0);
		}
		if (posY < player.getPosY()) {
			setSpeedY(2);
		} else if (posY > player.getPosY()) {
			setSpeedY(-2);
		} else {
			setSpeedY(0);
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
