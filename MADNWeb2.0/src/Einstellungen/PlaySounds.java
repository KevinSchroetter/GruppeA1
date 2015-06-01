package Einstellungen;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySounds{
	
	private static Object lock = new Object();
	
	private static String soundFile = null;
	
	private static Clip clip = null;

	public static void playStart() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{	
		 
		soundFile = "sounds/connected.wav";

		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundFile))) {

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.addLineListener((e) -> {
				if (e.getType() == LineEvent.Type.STOP) {
					synchronized (lock) {
						lock.notify();
					}
				}	
			});
			clip.start();
		}
		synchronized (lock) {
			lock.wait();
		}
		clip.close();
	}
	
	public static void kill() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{	
		 
		soundFile = "sounds/girldie.wav";

		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundFile))) {

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.addLineListener((e) -> {
				if (e.getType() == LineEvent.Type.STOP) {
					synchronized (lock) {
						lock.notify();
					}
				}	
			});
			clip.start();
		}
		synchronized (lock) {
			lock.wait();
		}
		clip.close();
	}
	
	public static void firstblood() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{	
		 
		soundFile = "sounds/firstblood.wav";

		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundFile))) {

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.addLineListener((e) -> {
				if (e.getType() == LineEvent.Type.STOP) {
					synchronized (lock) {
						lock.notify();
					}
				}	
			});
			clip.start();
		}
		synchronized (lock) {
			lock.wait();
		}
		clip.close();
	}
	public static void gameOver() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{	
		 
		soundFile = "sounds/fanfare.wav";

		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundFile))) {

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.addLineListener((e) -> {
				if (e.getType() == LineEvent.Type.STOP) {
					synchronized (lock) {
						lock.notify();
					}
				}	
			});
			clip.start();
		}
		synchronized (lock) {
			lock.wait();
		}
		clip.close();
	}
}
