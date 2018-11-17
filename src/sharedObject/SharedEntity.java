package sharedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.GameManager;
import model.Moveable;
import model.map.Map;

public class SharedEntity {
	
	private static final SharedEntity instance = new SharedEntity();
	
	private List<Moveable> entities = new CopyOnWriteArrayList<>();
	
	public List<Moveable> getEntitiesOfMap(Map map) {
		List<Moveable> list = new ArrayList<>();
		for (Moveable i: entities) {
			list.add(i);
		}
		return list;
	}
	
	public List<Moveable> getEntitiesOfCurrentMap() {
		return getEntitiesOfMap(GameManager.getInstance().getCurrentMap());
	}
	
	public void add(Moveable e) {
		entities.add(e);
	}
	
	public void remove(Moveable e) {
		entities.remove(e);
	}
	
	public void clear() {
		entities.clear();
	}
	
	public static SharedEntity getInstance() {
		return instance;
	}
	
}
