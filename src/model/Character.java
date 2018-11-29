package model;

import constants.CCType;
import javafx.scene.image.Image;

public abstract class Character extends MoveableEntity {

	protected int hp;
	protected int maxHp;
	protected int atk;
	protected int def;
	protected CCType status;
	
	public Character(String name, Image image, double posX, double posY, int maxHp, int atk, int def) {
		super(name, image, posX, posY);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.status = CCType.NONE;
	}

	public Character(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.status = CCType.NONE;
	}

	public boolean isDead() {
		return (hp <= 0);
	}
	
	public boolean canAttack() {
		return !isDead() && (status == CCType.NONE);
	}
	
	public boolean canMove() {
		return !isDead() && (status != CCType.STUN);
	}
	
	public void heal(int hp) {
		this.hp += hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
	}
	
	public void refresh() {
		this.hp = this.maxHp;
	}
	
	public void revive() {
		refresh();
		status = CCType.NONE;
	}
	
	public void takeDamge(int damage) {
		damage -= def;
		if (damage < 0) {
			damage = 0;
		}
		
		hp -= damage;
		if (hp <= 0) {
			dead();
		}
	}
	
	public abstract boolean attack();
	
	public abstract void dead();
	
	// Getters & Setters

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public CCType getStatus() {
		return status;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setStatus(CCType status) {
		this.status = status;
	}
	
}
