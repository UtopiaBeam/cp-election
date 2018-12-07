package model.item;

import java.util.List;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import model.npc.NPC;
import model.player.Player;

public class CCItem extends Item {

	public static final int duration = 90;

	public CCItem() {
		super("Random CC Item", 10, Images.cc);
		this.setImage(Images.cc);
	}
	
	public CCItem(double posX, double posY) {
		super("Random CC Item", 10, Images.cc, posX, posY);
		this.setImage(Images.cc);
	}

	@Override
	public boolean activate() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.isCCUsed()) {
			return false;
		}
		
		player.setCCUsed(true);
		
		double random = Math.random();
		
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		if (random <= 0.2) {
//			System.out.println("NPCs are stunned");
			for (NPC npc: npcs) {
				npc.setStatus(CCType.STUN);
			}
		} else if (random <= 0.5) {
//			System.out.println("NPCs are slowed");
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SLOW);
			}
		} else {
//			System.out.println("NPCs are silenced");
			for (NPC npc: npcs) {
				npc.setStatus(CCType.SILENCE);
			}
		}
		return true;
	}

}
