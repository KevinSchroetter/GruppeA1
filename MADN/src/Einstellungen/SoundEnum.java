package Einstellungen;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEnum {
	   FIRSTBLOOD("sounds/firstblood.wav"),   
	   KILL("sounds/girldie.wav"),         
	   START("sounds/connected.wav"),  
	   ERROR("sounds/error.wav");
	   
	   // Innere Klasse für Lautstärke
	   public static enum Volume {
	      MUTE, LOW, MEDIUM, HIGH
	   }
	   
	   public static Volume volume = Volume.LOW;
	   
	   
	   private Clip clip;
	   
	   // Konstruktor, weist jedem Enum-Element seinen eigenen Dateipfad zu
	   SoundEnum(String soundFileName) {
	      try {
	    	  File f = new File(soundFileName);
	         // Set up an audio input stream piped from the sound file.
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
	         // Get a clip resource.
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   // Play or Re-play the sound effect from the beginning, by rewinding.
	   public void play() {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning())
	            clip.stop();   // Stop the player if it is still running
	         clip.setFramePosition(0); // rewind to the beginning
	         clip.start();     // Start playing
	      }
	   }
	   
	   // Optional static method to pre-load all the sound files.
	   public static void init() {
	      values(); // calls the constructor for all the elements
	   }
	}