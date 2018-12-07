package model.item;

import javafx.scene.image.Image;
import model.Entity;

public abstract class Item extends Entity {
	
	protected int count = 0;
	protected int maxCount;
	
	public Item(String name, int maxCount, Image image) {
		super(0, 0, image.getWidth(), image.getHeight(), name, image);
		this.maxCount = maxCount;
	}
	
	public Item(String name, int maxCount, Image image, double posX, double posY) {
		super(posX, posY, image.getWidth(), image.getHeight(), name, image);
		this.maxCount = maxCount;
	}
	
	public boolean addCount(int count) {
		if (this.count + count > this.maxCount) {
			return false;
		}
		this.count += count;
		return true;
	}
	
	public void use() {
		if (count > 0) {
			if (activate()) {				
				count--;
			};
		}
	}
	
	public abstract boolean activate();
	
	// Getters & Setters

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
}
