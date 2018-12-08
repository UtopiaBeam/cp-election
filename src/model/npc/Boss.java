package model.npc;

import constants.Images;
import exception.CannotAttackException;
import model.Character;
import skill.*;

public class Boss extends Character {
	
	private Skill[] skills = { new ShockSkill(), new PodiumSkill() };
	private int skillTick = 0;
	private int skillCoolDown = 90;
	private boolean isUsingSkill = false;

	public Boss(double posX, double posY) {
		super(posX, posY, "Prayut", Images.prayutL, Images.prayutR, 10000, 100, 1000, 100, 120);
	}

	public void resetSkillTick() {
		skillTick = 0;
	}
	
	public void addSkillTick() {
		if (skillTick < skillCoolDown) {
			skillTick++;
		}
		if (skillTick == skillCoolDown) {
			resetSkillTick();
			setUsingSkill(false);
		}
	}
	
	@Override
	public void attack() throws CannotAttackException {
		if (isUsingSkill()) {
			throw new CannotAttackException();
		}
		
	}

	@Override
	public void dead() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (isUsingSkill()) {
			addSkillTick();
		}
	}
	
	// Getters & Setters

	public Skill[] getSkills() {
		return skills;
	}

	public int getSkillCoolDown() {
		return skillCoolDown;
	}

	public boolean isUsingSkill() {
		return isUsingSkill;
	}

	public void setSkillCoolDown(int skillCoolDown) {
		this.skillCoolDown = skillCoolDown;
	}

	public void setUsingSkill(boolean isUsingSkill) {
		this.isUsingSkill = isUsingSkill;
	}
	
}
