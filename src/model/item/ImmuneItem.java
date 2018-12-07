package model.item;

import constants.Images;
import controller.GameManager;
import model.IUpdatable;
import model.player.Player;

public class ImmuneItem extends Item implements IUpdatable {

	private int def;
	private int duration;

	public ImmuneItem() {
		super("Immune Item", 15, Images.immune);
		this.def = 100;
		this.duration = 300;
	}
	
	public ImmuneItem(double posX, double posY) {
		super("Immune Item", 15, Images.immune, posX, posY);
		this.def = 100;
		this.duration = 300;
	}
	
	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		player.setDef(player.getDef() + def);
		return true;
	}

	@Override
	public void update() {
		if (--duration == 0) {
			Player player = GameManager.getInstance().getPlayer();
			player.setDef(player.getDef() - def);
		}
	}
	
	// Getters & Setters
	
	public int getDef() {
		return def;
	}
	
	public int getDuration() {
		return duration;
	}

}
