package model.player;

import java.util.ArrayList;

import exception.InventoryFullException;
import javafx.scene.image.Image;
import model.Character;
import model.Constant;
import model.item.Item;

public class Player extends Character {

	private ArrayList<Item> inventory;

	public Player(int hp, int maxHp, int atk, int def, Image image, float posX, float posY, float speed,
			float maxSpeed) {
		super(hp, maxHp, atk, def, image, posX, posY, speed, maxSpeed);
		this.inventory = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void collectItem(Item item) throws InventoryFullException {
		for (Item i : inventory) {
			if (i.isSameType(item)) {
				if (!i.addCount()) {
					throw new InventoryFullException();
				}
			}
		}
		if (inventory.size() == Constant.INVENTORY_SIZE) {
			throw new InventoryFullException();
		}
		inventory.add(item);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
}
