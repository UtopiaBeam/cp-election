package model.effect;

import constants.Images;
import controller.GameManager;
import model.MoveableEntity;
import model.player.Player;
import ui.GameScene;

public class Podium extends MoveableEntity {
	
	public Podium(double posX, double posY) {
		super(posX, posY, "Podium", Images.podiumL, Images.podiumR);
		setSpeedX(10);
		setSpeedY(0);
		
		Player player = GameManager.getInstance().getPlayer();
		if (posX <= player.getPosX()) {
			setFacing(RIGHT);
		} else {
			setFacing(LEFT);
		}
	}

	public boolean isOutOfWindow() {
		return (posX + width < 0) || (posX >= GameScene.WINDOW_HEIGHT); 
	}
	
}
