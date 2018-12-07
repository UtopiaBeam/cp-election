package model.item;

import java.util.List;

import constants.Constants;
import constants.Images;
import controller.GameManager;
import model.npc.NPC;

public class AttackItem extends Item {
	
	public AttackItem() {
		super("Attack Item", 15, Images.attack);
		this.setImage(Images.attack);
	}
	
	public AttackItem(double posX, double posY) {
		super("Attack Item", 15, Images.attack, posX, posY);
		this.setImage(Images.attack);
	}

	@Override
	public boolean activate() {
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		
		for (NPC npc: npcs) {
			npc.takeDamage(Constants.INFINITE);
		}
		return true;
	}
	
}
