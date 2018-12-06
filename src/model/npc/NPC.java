package model.npc;

import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Character;
import model.item.Item;
import model.player.Player;
import model.effect.HpBar;;

public class NPC extends Character {

	private Item dropItem;
	private double dropChance;
	private double speed;
	private HpBar hpBar;
	
	public NPC(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY, maxHp, atk, def);
		speed = 1 + (Math.random());
		setSpeedX(speed);
		hpBar = new HpBar(this);
	}
	
	public NPC(String name, Image image, double posX, double posY, int maxHp, int atk, int def, Item dropItem,
			double dropChance) {
		super(name, image, posX, posY, maxHp, atk, def);
		this.dropItem = dropItem;
		this.dropChance = dropChance;
		setSpeedX(speed);
		hpBar = new HpBar(this);
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
		resetAttackTick();
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
		
		addAttackTick();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().getPosX(), posY-GameManager.getInstance().getCurrentMap().getPosY());
		hpBar.render(gc);
	}

	// Getters & Setters
	
	public Item getDropItem() {
		return dropItem;
	}

	public double getDropChance() {
		return dropChance;
	}
	

}
