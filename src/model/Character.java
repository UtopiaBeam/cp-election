package model;

import constants.CCType;
import exception.CannotAttackException;
import javafx.scene.image.Image;

public abstract class Character extends MoveableEntity implements IUpdatable {

	protected int hp;
	protected int maxHp;
	protected int atk;
	protected int def;
	protected CCType status;
	protected int attackTick;
	
	private int maxAttackTick = 30;
	private boolean isAttacking = false;
	
	public Character(String name, Image image, double posX, double posY, int maxHp, int atk, int def) {
		super(name, image, posX, posY);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.status = CCType.NONE;
		this.attackTick = maxAttackTick;
	}

	public Character(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def) {
		super(name, imageL, imageR, posX, posY);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.status = CCType.NONE;
		this.attackTick = maxAttackTick;
	}

	public boolean isDead() {
		return (hp <= 0);
	}
	
	public boolean canAttack() {
		return !isDead() && (status == CCType.NONE) && (isAttacking() == false);
	}
	
	public boolean canMove() {
		return !isDead() && (status != CCType.STUN);
	}
	
	public boolean isStunned() {
		return status == CCType.STUN;
	}
	
	public boolean isSlowed() {
		return status == CCType.SLOW;
	}
	
	public boolean isSilenced() {
		return status == CCType.SILENCE;
	}
	
	public boolean heal(int hp) {
		if (this.hp == this.maxHp) {
			return false;
		}
		this.hp += hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
		return true;
	}
	
	public void refresh() {
		this.hp = this.maxHp;
	}
	
	public boolean takeDamge(int damage) {
		damage = Math.max(damage - def, 0);
		if (damage == 0) {
			return false;
		}
		hp -= damage;
		if (hp <= 0) {
			dead();
		}
		return true;
	}
	
	public void resetAttackTick() {
		attackTick = 0;
	}
	
	public void addAttackTick() {
		if (attackTick < maxAttackTick) {
			attackTick++;
		} else {
			resetAttackTick();
			setIsAttacking(false);
		}
	}
	
	public abstract void attack() throws CannotAttackException;
	
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
	
	public boolean isAttacking() {
		return this.isAttacking;
	}
	
	public void setIsAttacking(boolean check) {
		this.isAttacking = check;
	}
	
}
