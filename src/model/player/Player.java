package model.player;

import java.util.List;

import controller.GameManager;
import exception.InventoryFullException;
import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Character;
import model.item.Item;

public class Player extends Character {

	public static final int INVENTORY_SIZE = 5;
	
	private Item[] inventory = new Item[INVENTORY_SIZE];
	
	public Player(String name, Image image, double posX, double posY, int maxHp, int atk, int def) {
		super(name, image, posX, posY, maxHp, atk, def);
	}
	
	public Player(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
	}

	@Override
	public boolean attack() {
		if (!canAttack()) {
			return false;
		}
		return true;
	}
	
	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}

	public void collectItem(Item item) throws InventoryFullException {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = item;
				return;
			}
			if (inventory[i].isSame(item) && inventory[i].addCount(1)) {
				return;
			}
		}
		throw new InventoryFullException();
	}
	
//	public void pushAccX(double acc) {
//		this.speedX += acc;
//		if (this.speedX > this.maxSpeed) {
//			this.speedX = this.maxSpeed;
//		}
//	}
	
	public void updateByPressingKeys() {
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			setFacing(LEFT);
			move(-5, 0);
		}
		if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			setFacing(RIGHT);
			move(5, 0);
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			move(0, -5);
		}
		if (KeyInput.pressingKey(KeyCode.DOWN)) {
			move(0, 5);
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		updateByPressingKeys();
	}
	
	// Getters & Setters

	public Item[] getInventory() {
		return inventory;
	}

}
