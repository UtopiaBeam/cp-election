package model;

import javafx.scene.image.Image;
import model.type.CCType;

public class Character {

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
	
	public Character(int hp, int maxHp, int atk, int def, CCType status, Image image, float posX, float posY,
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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
