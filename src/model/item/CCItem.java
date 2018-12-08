package model.item;

import java.util.List;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import model.npc.NPC;

public class CCItem extends Item {

	public CCItem() {
		super("Random CC Item", 5, Images.cc);
		this.setImage(Images.cc);
	}
	
	public CCItem(double posX, double posY) {
		super("Random CC Item", 5, Images.cc, posX, posY);
		this.setImage(Images.cc);
	}

	@Override
	public boolean activate() {
		
		double random = Math.random();
		
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		if (random <= 1) {
//			System.out.println("NPCs are stunned");
			for (NPC npc: npcs) {
				npc.setStatus(CCType.STUN);
				npc.setCCedDuration(90);
			}
		} else {
//			System.out.println("NPCs are slowed");
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SLOW);
				npc.setCCedDuration(120);
			}
		}
		return true;
	}

}
