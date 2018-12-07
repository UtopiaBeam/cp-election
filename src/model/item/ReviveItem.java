package model.item;

import constants.Images;
import controller.GameManager;
import model.player.Player;

public class ReviveItem extends Item {
	
	public ReviveItem() {
		super("Revive Item", 1, Images.revive);
	}
	
	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.isRevivable()) {
			return false;
		}
		player.setRevivable(true);
		return true;
	}
	
}
