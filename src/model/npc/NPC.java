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
	private double speed;
	
	public NPC(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
		speed = 3;
		setSpeedX(speed);
	}
	
	public NPC(String name, Image image, double posX, double posY, int maxHp, int atk, int def, Item dropItem,
			double dropChance) {
		super(name, image, posX, posY, maxHp, atk, def);
		this.dropItem = dropItem;
		this.dropChance = dropChance;
		setSpeedX(speed);
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
		speed = 2 + (Math.random()*2);
		Player player = GameManager.getInstance().getPlayer();
		if (posX < player.getPosX()-20) {
			setFacing(RIGHT);
			setSpeedX(speed);
		} else if (posX > player.getPosX()+20){
			setFacing(LEFT);
			setSpeedX(speed);
		} 
		if (posY + getHeight() < player.getPosY() + player.getHeight()-10) {
			setSpeedY(speed);
		} else if (posY + getHeight() > player.getPosY() + player.getHeight()+10) {
			setSpeedY(-speed);
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
