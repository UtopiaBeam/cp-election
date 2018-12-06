package model.item;

import javafx.scene.image.Image;

public class AttackItem extends Item {
	
	public AttackItem(Image image, int maxCount) {
		super("Attack Item", maxCount, image);
	}

	@Override
	public boolean activate() {
		return true;
	}
	
}
