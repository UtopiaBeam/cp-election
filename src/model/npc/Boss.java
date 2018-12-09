package model.npc;

import exception.CannotAttackException;
import javafx.scene.image.Image;
import skill.*;

public class Boss extends NPC {
	
	private Skill skill;

	public Boss(double posX, double posY, String name, Image imageL, Image imageR, int maxHp, int minAtk, int maxAtk,
			int def, Skill skill) {
		super(posX, posY, name, imageL, imageR, maxHp, minAtk, maxAtk, def, 60);
		this.skill = skill;
	}
	
	@Override
	public void attack() throws CannotAttackException {
		super.attack();
//		skill.use();
	}
	
	@Override
	public void update() {
		super.update();
		skill.update();
	}
	
	// Getters & Setters
	
	public Skill getSkill() {
		return skill;
	}
	
}
