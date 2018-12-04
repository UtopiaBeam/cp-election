package model.item;

import controller.GameManager;
import javafx.scene.image.Image;
import model.IUpdatable;
import model.player.Player;

public class DefenseItem extends Item implements IUpdatable {

	private int def;
	private int duration;
	
	public DefenseItem(double posX, double posY, Image image, int maxCount, int def, int duration) {
		super(posX, posY, image.getWidth(), image.getHeight(), "Defense Item", maxCount, image);
		this.def = def;
		this.duration = duration * 60;
	}

	public DefenseItem(int maxCount, Image image, int def, int duration) {
		super("Defense Item", maxCount, image);
		this.def = def;
		this.duration = duration * 60;
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
