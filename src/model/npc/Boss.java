package model.npc;

import constants.Images;
import exception.CannotAttackException;
import model.Character;
import skill.*;

public class Boss extends Character {
	
	private Skill[] skills = { new PodiumSkill() };

	public Boss(double posX, double posY) {
		super(posX, posY, "Prayut", Images.prayutL, Images.prayutR, 10000, 100, 1000, 100, 120);
	}
	
	@Override
	public void attack() throws CannotAttackException {
		if (isAttacking()) {
			throw new CannotAttackException();
		}
		
	}

	@Override
	public void dead() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		addAttackTick();
	}
	
	// Getters & Setters

	public Skill[] getSkills() {
		return skills;
	}
	
}
