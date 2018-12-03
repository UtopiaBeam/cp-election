package model.item;

import javafx.scene.image.Image;
import model.Entity;
import model.IUpdatable;

public abstract class Item extends Entity implements IUpdatable {
	
	protected int count = 1;
	protected int maxCount;

	public Item(double posX, double posY, double width, double height, String name, int maxCount, Image image) {
		super(posX, posY, width, height, name, image);
		this.maxCount = maxCount;
	}
	
	public Item(String name, int maxCount, Image image) {
		super(0, 0, 0, 0, name, image);
		this.maxCount = maxCount;
	}
	
	public boolean isSame(Item item) {
		return this.getName() == item.getName();
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
			activate();
			count--;
		}
	}
	
	public abstract void activate();
	
	// Getters & Setters

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
}
