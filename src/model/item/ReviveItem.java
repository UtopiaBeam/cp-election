package model.item;

import controller.GameManager;
import javafx.scene.image.Image;
import model.player.Player;

public class ReviveItem extends Item {
	
	public ReviveItem(Image image) {
		super("Revive Item", 1, image);
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
