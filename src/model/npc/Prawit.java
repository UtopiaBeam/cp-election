package model.npc;

import constants.Images;
import skill.WatchSkill;

public class Prawit extends Boss {

	public Prawit(double posX, double posY) {
		super(posX, posY, "Prawit", Images.prawitL, Images.prawitR, 5000, 50, 200, 80, new WatchSkill());
	}
	
}
