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
import exception.ItemTypeNotExistException;
import input.KeyInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Character;
import model.Frame;
import model.item.*;
import model.map.Map;
import model.npc.NPC;

public class Player extends Character {
	
	private Item[] inventory = { new AttackItem(), new CCItem(), new HealItem(), new ImmuneItem() };
	private boolean isImmune = false;
	private boolean isRevivable = false;
	private int immuneTick = 0;
	private double attackRange;
	
	public Player(double posX, double posY) {
		super(posX, posY, "Netikun", Images.playrerL, Images.playerR, 1000, 50, 150, 50, 30);
		this.attackRange = 30;
	}
	
	@Override
	public boolean isDead() {
		return super.isDead() && !isRevivable();
	}
	
	public boolean canUseItem() {
		return !isDead() && (status == CCType.NONE);
	}
	
	public boolean heal(int hp) {
		if (this.hp == this.maxHp) {
			return false;
		}
		this.hp += hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
		setHealAnimating(true);
		return true;
	}
	
	public void refresh() {
		this.hp = this.maxHp;
	}
	
	public void revive() {
		if (!isRevivable()) {
			return;
		}
		setReviveAnimating(true);
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
		List<NPC> collideNPCs = GameManager.getInstance().getCurrentMap().collideNPCs(getAttackArea());
		for (NPC n: collideNPCs) {
			n.takeDamage(getDamage());
			if (getFacing() == RIGHT) {
				n.setPosX(n.getPosX() + 60);
			} else {
				n.setPosX(n.getPosX() - 60);
			}	
		}
	}
	
	public boolean takeDamage(int damage) {
		if (isImmune()) {
			return false;
		}
		return super.takeDamage(damage);
	}
	
	@Override
	public void dead() {
		if (isRevivable()) {
			revive();
		}
		if (!isDead()) {
			return;
		}
		setSpeedX(0);
		setSpeedY(0);
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
	
	public void resetImmuneTick() {
		immuneTick = 0;
	}
	
	public void addImmuneTick() {
		if (!isImmune()) {
			return;
		}
		if (immuneTick < ImmuneItem.duration) {
			immuneTick++;
		}
		if (immuneTick == ImmuneItem.duration) {
			resetImmuneTick();
			setImmune(false);
		}
	}

	public void collectItem(Item item) throws InventoryFullException, ItemTypeNotExistException {
		int index = 0;
		if (item instanceof AttackItem) {
			index = 0;
		} else if (item instanceof CCItem) {
			index = 1;
		} else if (item instanceof HealItem) {
			index = 2;
		} else if (item instanceof ImmuneItem) {
			index = 3;
		} else if (item instanceof ReviveItem) {
			((ReviveItem) item).activate();
		} else {
			throw new ItemTypeNotExistException();
		}
		if (item instanceof ReviveItem || inventory[index].addCount(1)) {			
			GameManager.getInstance().getCurrentMap().removeItem(item);
		} else {
			throw new InventoryFullException();			
		}		
	}
	
	public void collectItems() {
		List<Item> items = GameManager.getInstance().getCurrentMap().collideItems(getPlayerArea());
		for (Item i: items) {
			try {
				collectItem(i);
			} catch (InventoryFullException e) {
				System.out.println("Inventory is full");
			} catch (ItemTypeNotExistException e) {
				System.out.println("Item type not found");
			}
		}
	}
	
	public void useItem(int index) throws InventoryEmptyIndexException, CannotUseItemException {
		if (!canUseItem()) {
			throw new CannotUseItemException();
		}
		if (inventory[index].getCount() == 0) {
			throw new InventoryEmptyIndexException("No " + inventory[index].getName());
		}
		inventory[index].use();
	}
	
	public void updateByPressingKeys() throws CannotMoveException, CannotUseItemException, CannotAttackException {
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
		
		if (KeyInput.pressingKey(KeyCode.SPACE)) {
			if (isAttacking() == false) {
				attack();
			}
		}			
	}
	
	public void updateByPollKey(KeyCode key) {
		if (key.isDigitKey()) {
			int digit = Integer.parseInt(key.toString().substring(5));
			int index = (digit + 9) % 10;
			try {
				useItem(index);
			} catch (InventoryEmptyIndexException e) {
				System.out.println(e.getMessage());
			} catch (CannotUseItemException e) {
				System.out.println("Cannot use item now");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Index out of range");
			}
		}
	}
	
	@Override
	public void update() {
		try {			
			updateByPressingKeys();
			while (KeyInput.isPollAvailable()) {
				KeyCode key = KeyInput.pollKey();
				updateByPollKey(key);
			}
			collectItems();
		} catch (CannotMoveException e) {
//			System.out.println("Cannot move now");
		} catch (CannotUseItemException e) {
//			System.out.println("Cannot use item now");
		} catch (CannotAttackException e) {
//			System.out.println("Cannot attack now");
		}
		addAttackTick();
		addImmuneTick();
		addCCedTick();
	}
	
	public void renderImmuneEffect(GraphicsContext gc){
		Map map = GameManager.getInstance().getCurrentMap();
		if (isImmune()) {
			Image img = Images.immuneEffect;
			gc.drawImage(img, this.getPosX()-map.getPosX()-20, this.getPosY()-map.getPosY());
		}
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().getPosX(), posY-GameManager.getInstance().getCurrentMap().getPosY()+5);
		if (isAttacking()) {
			renderNormalAttack(gc);
		}
		renderStatusEffect(gc);
		renderReviveEffect(gc);
		renderImmuneEffect(gc);
		renderHealEffect(gc);
		hpBar.render(gc);
	}
	
	// Getters & Setters

	public Item[] getInventory() {
		return inventory;
	}

	public boolean isRevivable() {
		return isRevivable;
	}
	
	public boolean isImmune() {
		return isImmune;
	}

	public void setRevivable(boolean isRevivable) {
		this.isRevivable = isRevivable;
	}

	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}
	

}
