package model.npc;

import java.util.List;

import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Character;
import model.item.*;
import model.player.Player;

public class NPC extends Character {

	private double speed;
	
	public NPC(double posX, double posY) {
		super(posX, posY, "Soldier", Images.soldierL, Images.soldierR, 100, 50, 150, 50, 120);
		this.speed = 1 + (Math.random());
	}
	
	public NPC(double posX, double posY, String name, Image imageL, Image imageR, int maxHp, int minAtk, int maxAtk,
			int def, int attackCooldown) {
		super(posX, posY, name, imageL, imageR, maxHp, minAtk, maxAtk, def, attackCooldown);
		this.speed = 8;
	}

	@Override
	public void attack() throws CannotAttackException {
		if (!canAttack()) {
			throw new CannotAttackException();
		}
		
		Player player = GameManager.getInstance().getPlayer();
		
		if (isCollideWith(player)) {
			setAttacking(true);
			player.takeDamage(getDamage());
		}
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
		dropItem();
	}
	
	public void dropItem() {
		double random = Math.random();
		double dropX = getPosX();
		double dropY = getPosY() + getHeight() - 40;
		
		Item item = null;
		
		if (random <= 0.02) {
			item = new ReviveItem(dropX, dropY);
		} else if (random <= 0.10) {
			item = new HealItem(dropX, dropY);
		} else if (random <= 0.20) {
			item = new CCItem(dropX, dropY);
		} else if (random <= 0.35) {
			item = new ImmuneItem(dropX, dropY);
		} else if (random <= 0.60) {
			item = new AttackItem(dropX, dropY);
		}
		
		if (item != null) {
			GameManager.getInstance().getCurrentMap().addItem(item);
		}
	}
	
	@Override
	public void update() {
		Player player = GameManager.getInstance().getPlayer();
		try {
			addCCedTick();
			addAttackTick();
			
			if (!canMove()) {
				setSpeedX(0);
				setSpeedY(0);
				return;
			}
			
			if (posX < player.getPosX() - 20) {
				setFacing(RIGHT);
				setSpeedX(isSlowed() ? speed/2 : speed);
			} else if (posX > player.getPosX() + 20){
				setFacing(LEFT);
				setSpeedX(isSlowed() ? speed/2 : speed);
			} 
			if (posY + getHeight() < player.getPosY() + player.getHeight() - 10) {
				setSpeedY(isSlowed() ? speed/2 : speed);
			} else if (posY + getHeight() > player.getPosY() + player.getHeight() + 10) {
				setSpeedY(isSlowed() ? -speed/2 : -speed);
			}
			
			attack();
		} catch (CannotAttackException e) {
			// Do Nothing
		}
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().getPosX(), posY-GameManager.getInstance().getCurrentMap().getPosY());
		hpBar.render(gc);
	}

}
