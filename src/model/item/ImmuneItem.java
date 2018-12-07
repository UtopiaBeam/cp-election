package model.item;

import constants.Images;
import controller.GameManager;
import model.player.Player;

public class ImmuneItem extends Item {

	public static final int duration = 180;
	
	private int def;

	public ImmuneItem() {
		super("Immune Item", 15, Images.immune);
		this.def = 100;
		this.setImage(Images.immune);
	}
	
	public ImmuneItem(double posX, double posY) {
		super("Immune Item", 15, Images.immune, posX, posY);
		this.def = 100;
		this.setImage(Images.immune);
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
	
	// Getters & Setters
	
	public int getDef() {
		return def;
	}
	
	public int getDuration() {
		return duration;
	}

}
