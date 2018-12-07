package model.item;

import java.util.List;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import model.IUpdatable;
import model.npc.NPC;
import model.player.Player;

public class CCItem extends Item implements IUpdatable {

	private int duration;

	public CCItem() {
		super("Random CC Item", 10, Images.cc);
		this.duration = 120;
		this.setImage(Images.cc);
	}
	
	public CCItem(double posX, double posY) {
		super("Random CC Item", 10, Images.cc, posX, posY);
		this.duration = 120;
		this.setImage(Images.cc);
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
