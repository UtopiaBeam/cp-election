package model;

import constants.CCType;
import javafx.scene.image.Image;

public abstract class Character {

	private int hp;
	private int maxHp;
	private int atk;
	private int def;
	private CCType status;
	
	private Image image;
	private float posX;
	private float posY;
	private float speed;
	private float maxSpeed;
	
	public Character(int hp, int maxHp, int atk, int def, Image image, float posX, float posY,
			float speed, float maxSpeed) {
		this.hp = hp;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.status = CCType.NONE;
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.maxSpeed = maxSpeed;
	}
	
	public boolean isDead() {
		return (hp <= 0);
	}
	
	public boolean canAttack() {
		return (status == CCType.NONE);
	}
	
	public boolean canMove() {
		return (status != CCType.STUN);
	}
	
	public void heal(int hp) {
		this.hp += hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
	}
	
	public void takeDamge(int damage) {
		damage -= def;
		if (damage < 0) {
			damage = 0;
		}
		
		hp -= damage;
	}
	
	public abstract void attack();
	
	// Getters & Setters

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public CCType getStatus() {
		return status;
	}

	public void setStatus(CCType status) {
		this.status = status;
	}

	public Image getImage() {
		return image;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
}
