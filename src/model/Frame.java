package model;

import ui.GameScene;

public class Frame {

	protected double posX;
	protected double posY;
	protected double width;
	protected double height;
	
	public Frame(double posX, double posY, double width, double height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public boolean isCollideWith(Frame f) {
		return (posX < f.posX + f.width) && (posX + width > f.posX) && 
			(posY < f.posY + f.height) && (height + posY > f.posY);
	}
	
	public boolean isInWindow() {
		return (posX >= 0) && (posX + width < GameScene.WINDOW_WIDTH) &&
				(posY >= 0) && (posY + height < GameScene.WINDOW_HEIGHT);
	}
	
	// Getters & Setters

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
