package sharedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.GameManager;
import model.Entity;
import model.map.Map;

public class SharedEntity {
	
	private static final SharedEntity instance = new SharedEntity();
	
	private List<Entity> entities = new CopyOnWriteArrayList<>();
	
	public List<Entity> getEntitiesOfMap(Map map) {
		List<Entity> list = new ArrayList<>();
		for (Entity i: entities) {
			list.add(i);
		}
		return list;
	}
	
	public List<Entity> getEntitiesOfCurrentMap() {
		return getEntitiesOfMap(GameManager.getInstance().getCurrentMap());
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public void clear() {
		entities.clear();
	}
	
	public static SharedEntity getInstance() {
		return instance;
	}
	
}
