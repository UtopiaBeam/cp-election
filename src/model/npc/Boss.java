package model.npc;

import exception.CannotAttackException;
import javafx.scene.image.Image;
import model.Character;
import skill.Skill;

public class Boss extends Character {

	public static final int SKILL_COUNT = 3;
	
	private Skill[] skills;

	public Boss(Image image, double posX, double posY) {
		super("Prayut", image, posX, posY, 10000, 1000, 50, 120);
	}
	
	public Boss(Image imageL, Image imageR, double posX, double posY) {
		super("Prayut", imageL, imageR, posX, posY, 10000, 1000, 50, 120);
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
		// TODO Auto-generated method stub

	}

	// Getters & Setters
	
	public Skill[] getSkills() {
		return skills;
	}
	
}
