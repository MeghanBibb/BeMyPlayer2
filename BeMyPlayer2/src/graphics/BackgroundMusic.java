package graphics;

import java.io.File;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The Class BackgroundMusic.
 */
public class BackgroundMusic {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(BackgroundMusic.class.getName());
	/** The clip. */
	static Clip clip;
	
	/** The status. */
	static String status = "stop";
	
	/** The audio input stream. */
	static AudioInputStream audioInputStream;
	
	/** The instance. */
	private static BackgroundMusic instance;
	
	/**
	 * Gets the single instance of BackgroundMusic.
	 *
	 * @return single instance of BackgroundMusic
	 */
	public static BackgroundMusic getInstance(){
		if(instance == null) {
			instance = new BackgroundMusic();
		}
		return instance;
	}
	
	/**
	 * Instantiates a new background music.
	 */
	private BackgroundMusic() {}
	
	/**
	 * Music.
	 */
	public void music() {
		if(status.contentEquals("play")) {
			logger.info("Background Music is stopped");
			stopSong();
			status = "stop";
		} else {
			logger.info("Background Music started to play");
			playSong();
			status = "play";
		}
	}

	/**
	 * Play song.
	 */
	private void playSong() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("music\\MiiSong.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
			logger.warning("Background Music FAILED to play");
		} 
	}
	
	/**
	 * Stop song.
	 */
	private static void stopSong() {
		clip.stop();
		clip.close();
		status = "stop";
	}
	
	/**
	 * Checks if is playing.
	 *
	 * @return true, if is playing
	 */
	public static boolean isPlaying() {
		if(status.equals("play")) {
			return true;
		}else {
			return false;
		}
	}
	
}
