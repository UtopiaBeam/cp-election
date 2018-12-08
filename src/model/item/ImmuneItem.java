package model.item;

import constants.Images;
import controller.GameManager;
import model.player.Player;

public class ImmuneItem extends Item {

	public static final int duration = 180;

	public ImmuneItem() {
		super("Immune Item", 5, Images.immune);
	}
	
	public ImmuneItem(double posX, double posY) {
		super("Immune Item", 5, Images.immune, posX, posY);
	}
	
	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.isImmune()) {
			return false;
		}
		player.setImmune(true);
		return true;
	}

}
