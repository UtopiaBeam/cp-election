package model.player;

import java.util.List;

import constants.CCType;
import controller.GameManager;
import exception.CannotAttackException;
import exception.InventoryEmptyIndexException;
import exception.InventoryFullException;
import input.KeyInput;
import javafx.scene.canvas.GraphicsContext;
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
	private double attackRange;
	
	
	public Player(String name, Image image, double posX, double posY, int maxHp, int atk, int def, double attackRange) {
		super(name, image, posX, posY, maxHp, atk, def);
		this.attackRange = attackRange;
	}
	
	public Player(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def, double attackRange) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
		this.attackRange = attackRange;
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
		setIsAttacking(true);
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
		Frame attackArea = new Frame(posX, posY, width + attackRange, height);
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
	
	public void useItem(int index) throws InventoryEmptyIndexException {
		if (inventory[index] == null) {
			throw new InventoryEmptyIndexException("Inventory empty at index = " + index);
		}
		inventory[index].use();
		if (inventory[index].getCount() == 0) {
			inventory[index] = null;
		}
	}
	
	public void updateByPressingKeys() {
		if (isStunned()) {
			setSpeedX(0);
			setSpeedY(0);
			return;
		}
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			setFacing(LEFT);
			setSpeedX(isSlowed() ? 3 : 5);
		} else if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			setFacing(RIGHT);
			setSpeedX(isSlowed() ? 3 : 5);
		} else {
			setSpeedX(0);
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			setSpeedY(isSlowed() ? -3 : -5);
		} else if (KeyInput.pressingKey(KeyCode.DOWN)) {
			setSpeedY(isSlowed() ? 3 : 5);
		} else {
			setSpeedY(0);
		}
		try {
			if (KeyInput.pressingKey(KeyCode.DIGIT1)) {				
				useItem(0);
			}
			if (KeyInput.pressingKey(KeyCode.DIGIT2)) {
				useItem(1);
			}
			if (KeyInput.pressingKey(KeyCode.DIGIT3)) {
				useItem(2);
			}
			if (KeyInput.pressingKey(KeyCode.DIGIT4)) {
				useItem(3);
			}
			if (KeyInput.pressingKey(KeyCode.DIGIT5)) {
				useItem(4);
			}
			if (KeyInput.pressingKey(KeyCode.SPACE)) {
				if (isAttacking() == false) {
					attack();
				}
				
			}
		} catch (InventoryEmptyIndexException e) {
			System.out.println(e.getMessage());
		} catch (CannotAttackException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void update() {
		updateByPressingKeys();
		if(isAttacking()) {
			addAttackTick();
		}
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().getPosX(), posY-GameManager.getInstance().getCurrentMap().getPosY());
		if (isAttacking()) {
			renderNormalAtk(gc);
		}
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
