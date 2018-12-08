package model;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.map.Map;

public abstract class Character extends MoveableEntity {

	protected int hp;
	protected int maxHp;
	protected int minAtk;
	protected int maxAtk;
	protected int def;
	protected CCType status = CCType.NONE;
	
	private int attackTick = 0;
	private int attackCooldown;
	private int ccedTick = 0;
	private int ccedDuration;
	private boolean isAttacking;

	public Character(double posX, double posY, String name, Image imageL, Image imageR, int maxHp, int minAtk, int maxAtk, int def, int attackCooldown) {
		super(posX, posY, name, imageL, imageR);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.minAtk = minAtk;
		this.maxAtk = maxAtk;
		this.def = def;
		this.attackCooldown = attackCooldown;
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
	
	public int getAtk() {
		return minAtk + (int) (Math.random() * (maxAtk - minAtk + 1));
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
		if (attackTick < attackCooldown) {
			attackTick++;
		}
		if (attackTick == attackCooldown) {
			resetAttackTick();
			setAttacking(false);
		}
	}
	
	public void resetCCedTick() {
		ccedTick = 0;
	}
	
	public void addCCedTick() {
		if (ccedTick < ccedDuration) {
			ccedTick++;
		}
		if (ccedTick == ccedDuration) {
			resetCCedTick();
			setStatus(CCType.NONE);
		}
	}

	public void renderNormalAttack(GraphicsContext gc) {
		Map map = GameManager.getInstance().getCurrentMap();
		Image img = Images.normalAttackEffect[attackTick/3];
		if (this.getFacing() == LEFT) {
			gc.drawImage(img, this.getPosX()-img.getWidth()-map.getPosX()+10, this.getPosY()-map.getPosY()+20);
		} else {
			gc.drawImage(img, this.getPosX()+this.getWidth()+img.getWidth()-map.getPosX()-10, this.getPosY()-map.getPosY()+20, -img.getWidth(), img.getHeight());
		}
		addAttackTick();
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

	public int getMinAtk() {
		return minAtk;
	}
	
	public int getMaxAtk() {
		return maxAtk;
	}

	public int getDef() {
		return def;
	}

	public CCType getStatus() {
		return status;
	}
	
	public boolean isAttacking() {
		return this.isAttacking;
	}
	
	public int getCCedDuration() {
		return ccedDuration;
	}

	public void setMinAtk(int minAtk) {
		this.minAtk = minAtk;
	}
	
	public void setMaxAtk(int maxAtk) {
		this.maxAtk = maxAtk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setStatus(CCType status) {
		this.status = status;
	}
	
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public void setCCedDuration(int ccedDuration) {
		this.ccedDuration = ccedDuration;
	}
	
}
