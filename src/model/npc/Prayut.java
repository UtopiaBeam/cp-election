package model.npc;

import constants.Images;
import controller.GameManager;
import skill.PodiumSkill;

public class Prayut extends Boss {

	public Prayut(double posX, double posY) {
		super(posX, posY, "Prayut", Images.prayutL, Images.prayutR, 10000, 100, 500, 100, new PodiumSkill());
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
		GameManager.getInstance().getCurrentMap().spawnBossPrawitRandom();
	}
}
