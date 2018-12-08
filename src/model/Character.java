package model;

import constants.CCType;
import exception.CannotAttackException;
import javafx.scene.image.Image;

public abstract class Character extends MoveableEntity implements IUpdatable {

	protected int hp;
	protected int maxHp;
	protected int atk;
	protected int def;
	protected CCType status = CCType.NONE;
	
	private int attackTick = 0;
	private int coolDown;
	private int ccedTick = 0;
	private int ccedDuration;
	private boolean isAttacking;

	public Character(String name, Image imageL, Image imageR, double posX, double posY, int maxHp, int atk, int def, int coolDown) {
		super(name, imageL, imageR, posX, posY);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.coolDown = coolDown;
	}

	public boolean isDead() {
		return hp <= 0;
	}
	
	public boolean canAttack() {
		return !isDead() && (status == CCType.NONE) && (isAttacking() == false);
	}
	
	public boolean canMove() {
		return !isDead() && (status != CCType.STUN);
	}
	
	public boolean isCCed() {
		return status != CCType.NONE;
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
	
	public boolean takeDamage(int damage) {
		damage = Math.max(damage - def, 0);
		if (damage == 0) {
			return false;
		}
		hp = Math.max(hp - damage, 0);
		if (hp == 0) {
			dead();
		}
		return true;
	}
	
	public void resetAttackTick() {
		attackTick = 0;
	}
	
	public void addAttackTick() {
		if (attackTick < coolDown) {
			attackTick++;
		}
		if (attackTick == coolDown) {
			resetAttackTick();
			setAttacking(false);
		}
	}
	
	public void resetCCTick() {
		ccedTick = 0;
	}
	
	public void addCCedTick() {
		if (ccedTick < ccedDuration) {
			ccedTick++;
		}
		if (ccedTick == ccedDuration) {
			resetCCTick();
			setStatus(CCType.NONE);
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
	
	public int getCCedDuration() {
		return ccedDuration;
	}
	
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public void setCCedDuration(int ccedDuration) {
		this.ccedDuration = ccedDuration;
	}
	
}
