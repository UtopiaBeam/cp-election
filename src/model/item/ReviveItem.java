package model.item;

import javafx.scene.image.Image;

public class ReviveItem extends Item {

	public ReviveItem(double posX, double posY, Image image) {
		super(posX, posY, image.getWidth(), image.getHeight(), "Revive Item", 1, image);
	}
	
	public ReviveItem(Image image) {
		super("Revive Item", 1, image);
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// Do nothing
	}
	
}
