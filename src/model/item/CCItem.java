package model.item;

import constants.CCType;
import controller.GameManager;
import javafx.scene.image.Image;
import model.IUpdatable;
import model.player.Player;

public class CCItem extends Item implements IUpdatable {

	private CCType effect;
	private int duration;

	public CCItem(String name, int maxCount, Image image, CCType effect, int duration) {
		super(name, maxCount, image);
		this.effect = effect;
		this.duration = duration * 60;
	}

	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.getStatus() == CCType.NONE) {			
			player.setStatus(effect);
			return true;
		}
		return false;
	}
	
	@Override
	public void update() {
		if (--duration == 0) {
			Player player = GameManager.getInstance().getPlayer();
			player.setStatus(CCType.NONE);
		}
	}

	// Getters & Setters
	
	public CCType getEffect() {
		return effect;
	}
	
	public int getDuration() {
		return duration;
	}

}
