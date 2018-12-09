package model.item;

import constants.Images;
import constants.Sounds;
import controller.GameManager;
import model.player.Player;

public class HealItem extends Item {
	
	private final int minHealHp = 50;
	private final int maxHealHp = 200;
	
	public HealItem() {
		super("Heal Potion", 5, Images.heal);
	}
	
	public HealItem(double posX, double posY) {
		super("Heal Potion", 5, Images.heal, posX, posY);
	}
	
	private int getHealHp() {
		return minHealHp + (int) (Math.random() * (maxHealHp - minHealHp + 1));
	}
	
	@Override
	public boolean activate() {
		Sounds.healsound.play();
		Player player = GameManager.getInstance().getPlayer();
		return player.heal(getHealHp());
	}

}
