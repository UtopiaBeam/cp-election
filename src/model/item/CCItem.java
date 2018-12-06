package model.item;

import java.util.List;

import constants.CCType;
import controller.GameManager;
import javafx.scene.image.Image;
import model.IUpdatable;
import model.npc.NPC;
import model.player.Player;

public class CCItem extends Item implements IUpdatable {

	private int duration;

	public CCItem(String name, int maxCount, Image image, int duration) {
		super(name, maxCount, image);
		this.duration = duration * 60;
	}

	@Override
	public boolean activate() {
		double random = Math.random();
		
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		if (random <= 0.2) {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.STUN);
			}
		} else if (random <= 0.3) {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SLOW);
			}
		} else {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SILENCE);
			}
		}
		return true;
	}
	
	@Override
	public void update() {
		if (--duration == 0) {
			Player player = GameManager.getInstance().getPlayer();
			player.setStatus(CCType.NONE);
		}
	}

	// Getters & Setters
	
	public int getDuration() {
		return duration;
	}

}
