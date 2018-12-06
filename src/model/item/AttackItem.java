package model.item;

import java.util.List;

import controller.GameManager;
import javafx.scene.image.Image;
import model.npc.NPC;

public class AttackItem extends Item {
	
	public static final int INFINITE = 1<<30;
	
	public AttackItem(Image image, int maxCount) {
		super("Attack Item", maxCount, image);
	}

	@Override
	public boolean activate() {
		List<NPC> npcs = GameManager.getInstance().getCurrentMap().getListNPC();
		
		for (NPC npc: npcs) {
			npc.takeDamge(INFINITE);
		}
		return true;
	}
	
}
