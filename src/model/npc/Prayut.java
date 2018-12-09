package model.npc;

import constants.Images;
import skill.PodiumSkill;

public class Prayut extends Boss {

	public Prayut(double posX, double posY) {
		super(posX, posY, "Prayut", Images.prayutL, Images.prayutR, 10000, 100, 500, 100, new PodiumSkill());
	}
	
}
