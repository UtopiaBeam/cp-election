package model.player;

import java.util.List;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import exception.CannotMoveException;
import exception.CannotUseItemException;
import exception.InventoryEmptyIndexException;
import exception.InventoryFullException;
import exception.ItemTypeNotFoundException;
import input.KeyInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import model.Character;
import model.Frame;
import model.effect.HpBar;
import model.item.*;
import model.map.Map;
import model.npc.NPC;

public class Player extends Character {

	public static final int INVENTORY_SIZE = 5;
	
	private Item[] inventory = { new AttackItem(), new ImmuneItem(), new CCItem(), new HealItem(), new ReviveItem() };
	private boolean isRevivable = false;
	private double attackRange;
	private HpBar hpBar;
	
	public Player(double posX, double posY) {
		super("Netikun", Images.playrerL, Images.playerR, posX, posY, 1000, 100, 50, 30);
		this.attackRange = 30;
		hpBar = new HpBar(this);
	}
	
	public boolean canUseItem() {
		return !isDead() && (status == CCType.NONE);
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
		setAttacking(true);
		List<NPC> collideNPCs = GameManager.getInstance().getCurrentMap().collideCharacter(getAttackArea());
		for (NPC n: collideNPCs) {
			n.takeDamge(getAtk());
			if (facing==1) {
				n.setPosX(n.getPosX() + 60);
			} else {
				n.setPosX(n.getPosX() - 60);
			}
			
		}
	}
	
	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}
	
	public Frame getPlayerArea() {
		return new Frame(posX, posY, width, height);
	}
	
	public Frame getAttackArea() {
		Frame attackArea = new Frame(posX, posY, width/2 + attackRange, height);
		if (getFacing() == LEFT) {
			attackArea.setPosX(posX - attackRange);
		} else {
			attackArea.setPosX(posX + attackRange);
		}
		return attackArea;
	}

	public void collectItem(Item item) throws InventoryFullException, ItemTypeNotFoundException {
		int index = 0;
		if (item instanceof AttackItem) {
			index = 0;
		} else if (item instanceof ImmuneItem) {
			index = 1;
		} else if (item instanceof CCItem) {
			index = 2;
		} else if (item instanceof HealItem) {
			index = 3;
		} else if (item instanceof ReviveItem) {
			index = 4;
		} else {
			throw new ItemTypeNotFoundException();
		}
		if (inventory[index].addCount(1)) {			
			throw new InventoryFullException();
		}
		
		GameManager.getInstance().getCurrentMap().removeItem(item);
		
	}
	
	public void collectItems() {
		List<Item> items = GameManager.getInstance().getCurrentMap().collideItems(getPlayerArea());
		for (Item i: items) {
			try {
				collectItem(i);
			} catch (InventoryFullException | ItemTypeNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void useItem(int index) throws InventoryEmptyIndexException, CannotUseItemException {
		if (!canUseItem()) {
			throw new CannotUseItemException();
		}
		if (inventory[index].getCount() == 0) {
			throw new InventoryEmptyIndexException("No " + inventory[index].getClass());
		}
		inventory[index].use();
	}
	
	public void updateByPressingKeys() throws CannotMoveException, InventoryEmptyIndexException, CannotUseItemException, CannotAttackException {
		if (!canMove()) {
			throw new CannotMoveException();
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
				
	}
	
	@Override
	public void update() {
		try {			
			updateByPressingKeys();
			collectItems();
		} catch (InventoryEmptyIndexException e) {
			e.printStackTrace();
		} catch (CannotMoveException e) {
			e.printStackTrace();
		} catch (CannotUseItemException e) {
			e.printStackTrace();
		} catch (CannotAttackException e) {
			e.printStackTrace();
		}
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
		hpBar.render(gc);
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
