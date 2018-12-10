package model;

import constants.CCType;
import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.effect.HpBar;
import model.map.Map;

public abstract class Character extends MoveableEntity implements IUpdatable {

	protected int hp;
	protected int maxHp;
	protected int minAtk;
	protected int maxAtk;
	protected int def;
	protected CCType status = CCType.NONE;
	protected HpBar hpBar;
	
	private int animationTick = 0;
	private int attackTick = 0;
	private int attackCooldown;
	private int ccedTick = 0;
	private int ccedDuration;
	private boolean isAttacking;
	private boolean isReviveAnimating = false;
	private boolean isHealAnimating = false;

	public Character(double posX, double posY, String name, Image imageL, Image imageR, int maxHp, int minAtk, int maxAtk, int def, int attackCooldown) {
		super(posX, posY, name, imageL, imageR);
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.minAtk = minAtk;
		this.maxAtk = maxAtk;
		this.def = def;
		this.attackCooldown = attackCooldown;
		this.hpBar = new HpBar(this);
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
	
	public int getDamage() {
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
		if (!isAttacking()) {
			return;
		}
		if (attackTick < attackCooldown) {
			attackTick++;
		}
		if (attackTick == attackCooldown) {
			resetAttackTick();
			setAttacking(false);
		}
	}
	
	public void resetAnimationTick() {
		animationTick = 0;
	}
	
	public void addAnimationTick() {
		if (animationTick < 59) {
			animationTick++;
		} else {
			resetAnimationTick();
			setReviveAnimating(false);
			setHealAnimating(false);
		}
	}
	
	public void resetCCedTick() {
		ccedTick = 0;
	}
	
	public void addCCedTick() {
		if (!isCCed()) {
			return;
		}
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
			gc.drawImage(img, this.getPosX()-img.getWidth()-map.getPosX()+10, this.getPosY()-map.getPosY()+30);
		} else {
			gc.drawImage(img, this.getPosX()+this.getWidth()+img.getWidth()-map.getPosX()-10, this.getPosY()-map.getPosY()+30, -img.getWidth(), img.getHeight());
		}
		addAttackTick();
	}
	
	public void renderStatusEffect(GraphicsContext gc) {
		Map map = GameManager.getInstance().getCurrentMap();
		if (isSlowed()) {
			Image img = Images.slowEffect[ccedDuration/41];
			gc.drawImage(img, this.getPosX()-map.getPosX()-16.5, this.getPosY()-map.getPosY()-16.5);
		}
		if (isStunned()) {
			Image img = Images.stunEffect[ccedDuration/46];
			gc.drawImage(img, this.getPosX()-map.getPosX()-16.5, this.getPosY()-map.getPosY()-16.5);
		}
		if (isSilenced()) {
			Image img = Images.silenceEffect[ccedDuration/61];
			gc.drawImage(img, this.getPosX()-map.getPosX()-16.5, this.getPosY()-map.getPosY()-16.5);
		}
	}
	
	public void renderHealEffect(GraphicsContext gc) {
		if(isHealAnimating) {
			Map map = GameManager.getInstance().getCurrentMap();
			Image img = Images.healEffect[animationTick/30];
			gc.drawImage(img, this.getPosX()-map.getPosX()-18, this.getPosY()-map.getPosY()-16.5);
			addAnimationTick();
		}
	}
	
	public void renderReviveEffect(GraphicsContext gc) {
		if(isReviveAnimating) {
			Image img = Images.reviveEffect[animationTick/3];
			double imageX = this.getPosX()+66/2-img.getWidth()/2-GameManager.getInstance().getCurrentMap().getPosX();
			double imageY = this.getPosY()+100-img.getHeight()-GameManager.getInstance().getCurrentMap().getPosY();
			gc.drawImage(img, imageX, imageY);
			addAnimationTick();
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
	
	public boolean isAttacking() {
		return this.isAttacking;
	}
	
	public boolean isReviveAnimating() {
		return this.isReviveAnimating;
	}
	
	public boolean isHealAnimating() {
		return this.isHealAnimating;
	}

	public void setStatus(CCType status) {
		this.status = status;
		if (status == CCType.STUN) {
			setSpeedX(0);
			setSpeedY(0);
		}
	}
	
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
	public void setReviveAnimating(boolean isAnimating) {
		this.isReviveAnimating = isAnimating;
	}
	
	public void setHealAnimating(boolean isAnimating) {
		this.isHealAnimating = isAnimating;
	}

	public void setCCedDuration(int ccedDuration) {
		this.ccedDuration = ccedDuration;
	}
	
}
