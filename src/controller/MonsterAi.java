package controller;


import model.npc.NPC;

public class MonsterAi extends Thread {
	
	public MonsterAi() {
		super(new Runnable() {
			@Override
			public void run() {
				while (true) {
					for (NPC m: GameManager.getInstance().getCurrentMap().getListNPC()) {
						m.update();
					}
					try {
						Thread.sleep(1000/60);
					} catch (InterruptedException e) {
						System.out.println("MonsterAi thread has been interrupted");
						break;
					}
				}
			}
		}, "Monster AI Thread");
		start();
	}
	
}
