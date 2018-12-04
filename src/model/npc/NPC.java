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
	
	public NPC(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
		setSpeedX(1);
	}
	
	public NPC(String name, Image image, double posX, double posY, int maxHp, int atk, int def, Item dropItem,
			double dropChance) {
		super(name, image, posX, posY, maxHp, atk, def);
		this.dropItem = dropItem;
		this.dropChance = dropChance;
		setSpeedX(1);
	}

	public boolean isDropItem() {
		return dropChance <= Math.random();
	}
	
	@Override
	public void attack() throws CannotAttackException {
		if (!canAttack()) {
			throw new CannotAttackException();
		}
		Player player = GameManager.getInstance().getPlayer();
		if (isCollideWith(player)) {
			player.takeDamge(atk);
		}
		attackTick = 20;
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
		if (isDropItem()) {
			dropItem();
		}
	}
	
	public void dropItem() {
		
	}
	
	@Override
	public void update() {
		Player player = GameManager.getInstance().getPlayer();
		if (posX <= player.getPosX()) {
			setFacing(RIGHT);
		} else {
			setFacing(LEFT);
		}
		if (posY + getHeight() < player.getPosY() + player.getHeight()) {
			setSpeedY(2);
		} else if (posY + getHeight() > player.getPosY() + player.getHeight()) {
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
