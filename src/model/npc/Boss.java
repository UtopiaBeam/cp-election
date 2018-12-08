package model.npc;

import constants.Images;
import exception.CannotAttackException;
import model.Character;
import skill.Skill;

public class Boss extends Character {

	public static final int SKILL_COUNT = 3;
	
	private Skill[] skills;
	private int skillTick = 0;
	private int skillCoolDown;
	private boolean isUsingSkill = false;

	public Boss(double posX, double posY) {
		super("Prayut", Images.prayutL, Images.prayutR, posX, posY, 10000, 1000, 50, 120);
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
		// TODO Auto-generated method stub

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
