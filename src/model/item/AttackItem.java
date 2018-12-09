package model.item;

import java.util.List;

import constants.Images;
import controller.GameManager;
import model.npc.NPC;

public class AttackItem extends Item {
	
	private final int minDamage = 75;
	private final int maxDamage = 150;
	
	public AttackItem() {
		super("Attack Item", 5, Images.attack);
	}
	
	public AttackItem(double posX, double posY) {
		super("Attack Item", 5, Images.attack, posX, posY);
	}
	
	private int getDamage() {
		return minDamage + (int) (Math.random() * (maxDamage - minDamage + 1));
	}

	@Override
	public boolean activate() {
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		int damage = getDamage();
		
		for (NPC npc: npcs) {
			npc.takeDamage(damage);
			if (GameManager.getInstance().getPlayer().getPosX()<npc.getPosX()) {
				npc.setPosX(npc.getPosX() + 60);
			} else {
				npc.setPosX(npc.getPosX() - 60);
			}
		}
		return true;
	}
	
}
