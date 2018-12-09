package constants;

import javafx.scene.media.AudioClip;

public class Sounds {
	public static final AudioClip startbgm = new AudioClip(ClassLoader.getSystemResource("audio/startscenebgm.m4a").toString());
	public static final AudioClip bgm = new AudioClip(ClassLoader.getSystemResource("audio/gamescenebgm.m4a").toString());
	public static final AudioClip deadscenebgm = new AudioClip(ClassLoader.getSystemResource("audio/deadscenebgm.mp3").toString());

	public static final AudioClip attacksound = new AudioClip(ClassLoader.getSystemResource("audio/punch.mp3").toString());
	public static final AudioClip deadsound = new AudioClip(ClassLoader.getSystemResource("audio/splat.wav").toString());
	public static final AudioClip healsound = new AudioClip(ClassLoader.getSystemResource("audio/healsound.m4a").toString());
	public static final AudioClip revivesound = new AudioClip(ClassLoader.getSystemResource("audio/revivesound.m4a").toString());
	public static final AudioClip ccsound = new AudioClip(ClassLoader.getSystemResource("audio/ccsound.wav").toString());
	public static final AudioClip immunesound = new AudioClip(ClassLoader.getSystemResource("audio/immunesound.wav").toString());
	
	public static final AudioClip spawnbosssound = new AudioClip(ClassLoader.getSystemResource("audio/spawnbosssound.m4a").toString());

	
	static {
		bgm.setVolume(0.01);
		attacksound.setVolume(0.1);
		ccsound.setVolume(1.5);
		immunesound.setVolume(1.5);
		
	}
}