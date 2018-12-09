package model.effect;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import model.IUpdatable;
import model.MoveableEntity;
import model.player.Player;
import ui.GameScene;

public class Podium extends MoveableEntity implements IUpdatable {
	
	public Podium(double posX, double posY) {
		super(posX, posY, "Podium", Images.podiumL, Images.podiumR);
		
		double speed = 2 + (int) (Math.random() + 5);
		setSpeedX(speed);
		setSpeedY(0);
		
		Player player = GameManager.getInstance().getPlayer();
		if (posX <= player.getPosX()) {
			setFacing(RIGHT);
		} else {
			setFacing(LEFT);
		}
	}

	public boolean isOutOfWindow() {
		return (posX + width < 0) || (posX > GameScene.WINDOW_WIDTH); 
	}
	
	@Override
	public void update() {
		Player player = GameManager.getInstance().getPlayer();
		if (isCollideWith(player.getPlayerArea())) {
			player.takeDamage(200);
			player.setStatus(CCType.STUN);
			player.setCCedDuration(60);
			GameManager.getInstance().getCurrentMap().removePodium(this);
		}
	}
	
}
