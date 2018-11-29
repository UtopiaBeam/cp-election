package sharedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.GameManager;
import model.Character;
import model.map.Map;

public class SharedEntity {
	
	private static final SharedEntity instance = new SharedEntity();
	
	private List<Character> characters = new CopyOnWriteArrayList<>();
	
	public List<Character> getEntitiesOfMap(Map map) {
		List<Character> list = new ArrayList<>();
		for (Character i: characters) {
			list.add(i);
		}
		return list;
	}
	
	public List<Character> getEntitiesOfCurrentMap() {
		return getEntitiesOfMap(GameManager.getInstance().getCurrentMap());
	}
	
	public void add(Character e) {
		characters.add(e);
	}
	
	public void remove(Character e) {
		characters.remove(e);
	}
	
	public void clear() {
		characters.clear();
	}
	
	public static SharedEntity getInstance() {
		return instance;
	}
	
}
