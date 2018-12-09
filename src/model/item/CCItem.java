package model.item;

import java.util.List;

import constants.CCType;
import constants.Images;
import constants.Sounds;
import controller.GameManager;
import model.npc.NPC;

public class CCItem extends Item {

	public CCItem() {
		super("Random CC Item", 5, Images.cc);
	}
	
	public CCItem(double posX, double posY) {
		super("Random CC Item", 5, Images.cc, posX, posY);
	}

	@Override
	public boolean activate() {
		
		Sounds.ccsound.play();
		
		double random = Math.random();
		
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		if (random <= 0.2) {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.STUN);
				npc.setCCedDuration(90);
			}
		} else if (random <= 0.5) {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SLOW);
				npc.setCCedDuration(120);
			}
		} else {
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SILENCE);
				npc.setCCedDuration(180);
			}
		}
		return true;
	}

}
