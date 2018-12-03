package model.item;

import controller.GameManager;
import javafx.scene.image.Image;
import model.player.Player;

public class HealItem extends Item {
	
	private int healHp;

	public HealItem(double posX, double posY, Image image, int healHp) {
		super(posX, posY, image.getWidth(), image.getHeight(), "Heal Potion", 1, image);
		this.healHp = healHp;
	}
	
	public HealItem(Image image, int healHp) {
		super("Heal Potion", 1, image);
		this.healHp = healHp;
	}
	
	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		return player.heal(healHp);
	}
	
	// Getters & Setters
	
	public int getHealHp() {
		return healHp;
	}

}
