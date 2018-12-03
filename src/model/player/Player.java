package model.player;

import java.util.List;

import constants.CCType;
import controller.GameManager;
import exception.CannotAttackException;
import exception.InventoryFullException;
import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Character;
import model.Frame;
import model.item.Item;
import model.npc.NPC;

public class Player extends Character {

	public static final int INVENTORY_SIZE = 5;
	
	private Item[] inventory = new Item[INVENTORY_SIZE];
	private boolean isRevivable = false;
	private double attackRange = 3;
	
	public Player(String name, Image image, double posX, double posY, int maxHp, int atk, int def) {
		super(name, image, posX, posY, maxHp, atk, def);
	}
	
	public Player(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
	}
	
	public void revive() {
		if (!isRevivable()) {
			return;
		}
		refresh();
		setRevivable(false);
		status = CCType.NONE;
	}

	@Override
	public void attack() throws CannotAttackException {
		if (!canAttack()) {
			throw new CannotAttackException();
		}
		List<NPC> collideNPCs = GameManager.getInstance().getCurrentMap().collideCharacter(getAttackArea());
		for (NPC n: collideNPCs) {
			n.takeDamge(getAtk());
		}
	}
	
	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}
	
	public Frame getAttackArea() {
		Frame attackArea = new Frame(posX, posY, width + attackRange, height + attackRange);
		if (getFacing() == LEFT) {
			attackArea.setPosX(posX - attackRange);
		} else {
			attackArea.setPosX(posX + attackRange);
		}
		return attackArea;
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
	
	public void updateByPressingKeys() {
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			setFacing(LEFT);
			setSpeedX(5);
		}
		if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			setFacing(RIGHT);
			setSpeedX(5);
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			setSpeedY(-5);
		}
		if (KeyInput.pressingKey(KeyCode.DOWN)) {
			setSpeedY(5);
		}
		if (!KeyInput.pressingKey(KeyCode.LEFT) && !KeyInput.pressingKey(KeyCode.RIGHT) && !KeyInput.pressingKey(KeyCode.UP) && !KeyInput.pressingKey(KeyCode.DOWN)) {
			setSpeedX(0);
			setSpeedY(0);
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

	public boolean isRevivable() {
		return isRevivable;
	}

	public void setRevivable(boolean isRevivable) {
		this.isRevivable = isRevivable;
	}

}
