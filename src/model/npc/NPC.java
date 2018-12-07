package model.npc;

import java.util.List;

import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.canvas.GraphicsContext;
import model.Character;
import model.item.*;
import model.player.Player;
import model.effect.HpBar;;

public class NPC extends Character {

	private double speed;
	private HpBar hpBar;
	
	public NPC(double posX, double posY) {
		super("Soldier", Images.soldierL, Images.soldierR, posX, posY, 100, 100, 0, 120);
		speed = 1 + (Math.random());
		setSpeedX(speed);
		hpBar = new HpBar(this);
	}
	
	@Override
	public void attack() throws CannotAttackException {
		if (!canAttack()) {
			throw new CannotAttackException();
		}
		
		Player player = GameManager.getInstance().getPlayer();
		
		if (isCollideWith(player)) {
			setAttacking(true);
			player.takeDamge(atk);
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
		
		List<Item> mapItems = GameManager.getInstance().getCurrentMap().getListItem();
		Item item = null;
		
		if (random <= 0.02) {
			item = new ReviveItem(dropX, dropY);
		} else if (random <= 0.05) {
			item = new HealItem(dropX, dropY);
		} else if (random <= 0.15) {
			item = new CCItem(dropX, dropY);
		} else if (random <= 0.30) {
			item = new ImmuneItem(dropX, dropY);
		} else if (random <= 0.60) {
			item = new AttackItem(dropX, dropY);
		}
		
		if (item != null) {
			mapItems.add(item);
		}
	}
	
	@Override
	public void update() {
		Player player = GameManager.getInstance().getPlayer();
		if (posX < player.getPosX() - 20) {
			setFacing(RIGHT);
			setSpeedX(speed);
		} else if (posX > player.getPosX() + 20){
			setFacing(LEFT);
			setSpeedX(speed);
		} 
		if (posY + getHeight() < player.getPosY() + player.getHeight()-10) {
			setSpeedY(speed);
		} else if (posY + getHeight() > player.getPosY() + player.getHeight()+10) {
			setSpeedY(-speed);
		}
		try {
			attack();
		} catch (CannotAttackException e) {
			System.out.println(e.getMessage());
		}
		addAttackTick();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().getPosX(), posY-GameManager.getInstance().getCurrentMap().getPosY());
		hpBar.render(gc);
	}

}
